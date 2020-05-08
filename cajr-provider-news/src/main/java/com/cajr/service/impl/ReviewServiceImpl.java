package com.cajr.service.impl;

import com.cajr.mapper.ReviewMapper;
import com.cajr.service.*;
import com.cajr.util.TimeUtil;
import com.cajr.vo.news.News;
import com.cajr.vo.news.Review;
import com.cajr.vo.user.UserLikeReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 1:34 下午
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private IUserClientService iUserClientService;

    @Autowired
    private IUserLikeReviewClientService iUserLikeReviewClientService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private NewsService newsService;

    @Override
    public Integer getReviewCountByNewsId(Integer newsId) {
        return this.reviewMapper.findNumByNewsId(newsId);
    }

    @Override
    public Integer addOneReview(Review review) {
        return this.reviewMapper.insertSelective(review);
    }

    @Override
    public List<Review> getReviewsByNewsId(Integer newsId) {
        List<Review> reviews = this.reviewMapper.selectAllByNewsId(newsId);
        if (!reviews.isEmpty()){
            reviews.forEach(review -> {
                List<UserLikeReview> userLikeReviews = this.iUserLikeReviewClientService.getByReviewId(review.getId());
                if (!userLikeReviews.isEmpty()){
                    int likeNum = 0, unLikeNum = 0;
                    List<Integer> likeUserIds = new ArrayList<>();
                    List<Integer> unlikeUserIds = new ArrayList<>();
                    for (UserLikeReview userLikeReview : userLikeReviews) {
                        if (userLikeReview.getIsLike() == 1){
                            likeNum++;
                            likeUserIds.add(userLikeReview.getUserId());
                        }else {
                            unLikeNum ++;
                            unlikeUserIds.add(userLikeReview.getUserId());
                        }
                    }
                    review.setLikeNum(likeNum);
                    review.setUnlikeNum(unLikeNum);
                    review.setLikeUserIds(likeUserIds);
                    review.setUnlikeUserIds(unlikeUserIds);
                }else {
                    review.setLikeNum(0);
                    review.setUnlikeNum(0);
                }
                review.setUserOther(this.iUserClientService.getOneUserOther(review.getUserId()));
                review.setTime(TimeUtil.format(review.getCreatedAt()));
                review.setReplyList(this.replyService.getAllByReviewId(review.getId()));
            });
        }
        return reviews;
    }

    @Override
    public List<Review> getReviewsByUserId(Integer userId) {
        List<Review> reviews =  this.reviewMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(reviews)){
            return reviews;
        }
        reviews.forEach(review -> {
            review.setNews(this.newsService.getOne(review.getNewsId()));
        });
        return reviews;
    }
}
