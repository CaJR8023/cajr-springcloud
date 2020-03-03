CREATE TABLE user_log (
    id int (11) NOT NULL AUTO_INCREMENT ,
    user_id int(11) not null ,
    last_login_ip varchar(50) not null,
    latest_login_time timestamp not null default CURRENT_TIMESTAMP,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;