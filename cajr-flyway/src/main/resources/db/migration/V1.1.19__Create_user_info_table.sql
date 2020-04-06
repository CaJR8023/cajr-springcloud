CREATE TABLE `user_info` (
                             id int (11) NOT NULL AUTO_INCREMENT ,
                             user_id int (11) NOT NULL,
                             avatar varchar(100) not null ,
                             profession varchar(30) not null ,
                             location varchar(30) not null,
                             signature varchar(300) not null ,
                             status tinyint not null default 1,
                             created_at timestamp not null default CURRENT_TIMESTAMP,
                             updated_at timestamp not null default CURRENT_TIMESTAMP,
                             PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;