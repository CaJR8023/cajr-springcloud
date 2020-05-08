CREATE TABLE user_news_star (
                       id int (11) NOT NULL AUTO_INCREMENT ,
                       news_id int (11) NOT NULL,
                       user_id int (11) NOT NULL,
                       is_like tinyint not null default 1,
                       status tinyint not null default 1,
                       created_at timestamp not null default CURRENT_TIMESTAMP,
                       updated_at timestamp not null default CURRENT_TIMESTAMP,
                       PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;