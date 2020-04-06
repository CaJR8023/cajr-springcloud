CREATE TABLE `special_column` (
                          id int (11) NOT NULL AUTO_INCREMENT ,
                          name varchar(25) NOT NULL ,
                          summary varchar(500) NOT NULL,
                          banner_img varchar(200) NOT NULL,
                          status tinyint not null default 1,
                          created_at timestamp not null default CURRENT_TIMESTAMP,
                          updated_at timestamp not null default CURRENT_TIMESTAMP,
                          PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `special_column_user` (
                                  id int (11) NOT NULL AUTO_INCREMENT ,
                                  special_column_id int (11) NOT NULL,
                                  user_id int (11) NOT NULL,
                                  type tinyint not null default 1,
                                  status tinyint not null default 1,
                                  created_at timestamp not null default CURRENT_TIMESTAMP,
                                  updated_at timestamp not null default CURRENT_TIMESTAMP,
                                  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `special_column_news` (
                                       id int (11) NOT NULL AUTO_INCREMENT ,
                                       special_column_id int (11) NOT NULL,
                                       news_id int (11) NOT NULL,
                                       status tinyint not null default 1,
                                       created_at timestamp not null default CURRENT_TIMESTAMP,
                                       updated_at timestamp not null default CURRENT_TIMESTAMP,
                                       PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;