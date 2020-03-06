package com.cajr.service.impl;

import com.cajr.service.*;
import com.cajr.util.CommonParam;
import com.cajr.util.RecommendKit;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.NewsLogs;
import com.cajr.vo.news.NewsRecommend;
import org.apache.log4j.Logger;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基于用户的协同过滤(Based-User-Collaborative Filter 基于用户的协同过滤)
 * @author CAJR
 * @date 2020/3/6 2:11 下午
 */
@Service("userCFRecommend")
public class UserCFRecommendImpl implements RecommendService {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserCFRecommendImpl.class);

    @Autowired
    private NewsLogsService newsLogsService;

    @Autowired
    private IUserClientService iUserClientService;

    @Autowired
    private MySQLBooleanPrefJDBCDataModel mySQLBooleanPrefJDBCDataModel;

    /**
     * 对应计算相似度时的时效天数
     */
    @Value("${new.recommend.CFValidDay}")
    private  int inRecDays;

    /**
     * 给每个用户推荐的新闻的条数
     */
    @Value("${new.recommend.CFRecNum}")
    public  int nNews;

    //用户邻居数量
    private static final int NEIGHBORHOOD_NUM = 10;

    @Autowired
    private RecommendCommonService recommendCommonService;

    @Autowired
    private NewsRecommendService newsRecommendService;

    @Override
    public void recommend(List<Integer> userIds) {
        int count = 0;
        logger.info("协同过滤推荐算法开始于" + new Date());

        try {
            MySQLBooleanPrefJDBCDataModel mySQLBooleanPrefJDBCDataModel = this.mySQLBooleanPrefJDBCDataModel;

            List<NewsLogs> newsLogsList = this.newsLogsService.findAll();
            if (newsLogsList.isEmpty()){
                return;
            }
            //移除过期的用户浏览新闻行为，这些行为对计算不再具有较大的价值
            for (NewsLogs newsLogs : newsLogsList) {
                if (newsLogs.getViewTime().before(TimeUtil.getInRecTimestamp(inRecDays))){
                    mySQLBooleanPrefJDBCDataModel.removePreference(newsLogs.getUserId(),newsLogs.getNewsId());
                }
            }
            //用户相似度
            UserSimilarity userSimilarity = new LogLikelihoodSimilarity(mySQLBooleanPrefJDBCDataModel);

            //NearestNeighborhood（最近的邻居）的数量有待考察
            UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, userSimilarity, mySQLBooleanPrefJDBCDataModel);
            Recommender recommender = new GenericUserBasedRecommender(mySQLBooleanPrefJDBCDataModel, userNeighborhood, userSimilarity);

            //遍历用户id
            for (Integer userId : userIds) {
                List<RecommendedItem> recommendedItems = recommender.recommend(userId, nNews);

                //生成推荐新闻id列表
                List<Integer> toRecNewsList = new ArrayList<>();
                for (RecommendedItem recommendedItem :
                        recommendedItems) {
                    toRecNewsList.add((int) recommendedItem.getItemID());
                }
                if (toRecNewsList.isEmpty()){
                    continue;
                }

                //过滤已推荐的新闻和已过期的新闻
                this.recommendCommonService.filterRecCedNews(toRecNewsList, userId);
                this.recommendCommonService.filterOutDateNews(toRecNewsList, userId);


                //如果推荐的新闻id大于规定的大小，自动去掉多余的
                if (toRecNewsList.size() > nNews){
                    RecommendKit.removeOverNews(toRecNewsList, nNews);
                }
                //加入数据库
                this.newsRecommendService.insertRecommend(toRecNewsList, userId, CommonParam.CF_ALGORITHM);
                //统计推荐数量
                count += toRecNewsList.size();
            }

        } catch (TasteException e) {
            logger.error("CB算法构造偏好对象失败！");
            e.printStackTrace();
        }

        logger.info("CF has contributed " + (count/userIds.size()) + " recommending news on average");
        logger.info("CF finish at "+new Date());

    }
}
