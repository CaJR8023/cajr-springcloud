package com.cajr.vo.news;

import com.cajr.vo.tag.Tag;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/17 3:51 下午
 */
public class News {
    private int id;

    private int moduleId;

    private int userId;

    private String title;

    private String source;

    private String desc;

    @JsonIgnore
    private String content;

    @JsonIgnore
    private String allContent;

    private List<String> contentList;

    private String newsDataSign;

    private int status;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Timestamp createdAt;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Timestamp updatedAt;

    @ApiModelProperty(hidden = true)
    private String time;

    @ApiModelProperty(hidden = true)
    private String banner;

    @ApiModelProperty(hidden = true)
    private boolean existBanner;

    @ApiModelProperty(hidden = true)
    private  int visitorCount;

    @ApiModelProperty(hidden = true)
    private  int reviewCount;

    @ApiModelProperty(hidden = true)
    private  int browseStatus;

    @ApiModelProperty(hidden = true)
    private UserOther userOther;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private List<NewsImage> newsImages;

    @ApiModelProperty(hidden = true)
    private List<String> tags;

    @ApiModelProperty(hidden = true)
    private int isStar;

    @ApiModelProperty(hidden = true)
    private int unLike;

    @ApiModelProperty(hidden = true)
    private int isCollect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAllContent() {
        return allContent;
    }

    public void setAllContent(String allContent) {
        this.allContent = allContent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getNewsDataSign() {
        return newsDataSign;
    }

    public void setNewsDataSign(String newsDataSign) {
        this.newsDataSign = newsDataSign;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public UserOther getUserOther() {
        return userOther;
    }

    public void setUserOther(UserOther userOther) {
        this.userOther = userOther;
    }

    public List<NewsImage> getNewsImages() {
        return newsImages;
    }

    public void setNewsImages(List<NewsImage> newsImages) {
        this.newsImages = newsImages;
    }

    public void setExistBanner(boolean existBanner) {
        this.existBanner = existBanner;
    }

    public boolean getExistBanner() {
        return existBanner;
    }

    public int getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(int visitorCount) {
        this.visitorCount = visitorCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public int getBrowseStatus() {
        return browseStatus;
    }

    public void setBrowseStatus(int browseStatus) {
        this.browseStatus = browseStatus;
    }

    public int getIsStar() {
        return isStar;
    }

    public void setIsStar(int isStar) {
        this.isStar = isStar;
    }

    public int getUnLike() {
        return unLike;
    }

    public void setUnLike(int unLike) {
        this.unLike = unLike;
    }

    public int getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(int isCollect) {
        this.isCollect = isCollect;
    }
}