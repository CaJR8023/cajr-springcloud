# 创建新闻模块索引
CREATE INDEX IND_NEWS ON news(module_id);
CREATE INDEX IND_NEWS_IMAGE ON news_image(news_id);

# 创建推荐模块索引
CREATE INDEX IND_USER_LOGS ON news_logs(user_id);
CREATE INDEX IND_USER_PREF ON user_pref(user_id);
CREATE INDEX IND_USER_RECOMMEND ON news_recommend(user_id);

# 创建标签模块索引
CREATE INDEX IND_TAG ON tag(name);
CREATE INDEX IND_NEWS_TAG ON news_tag(news_id, tag_id) ;
CREATE INDEX IND_MODULE_TAG ON module_tag(module_id, tag_id);

# 创建评论模块标签
CREATE INDEX IND_REVIEW ON review(news_id);
CREATE INDEX IND_LIKE_REVIEW ON user_like_review(review_id);
CREATE INDEX IND_USER_REVIEW ON user_review(review_id);