CREATE TABLE `news_user` (
                                       id int (11) NOT NULL AUTO_INCREMENT ,
                                       user_id int (11) NOT NULL,
                                       news_id int (11) NOT NULL,
                                       status tinyint not null default 1,
                                       created_at timestamp not null default CURRENT_TIMESTAMP,
                                       updated_at timestamp not null default CURRENT_TIMESTAMP,
                                       PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;