CREATE TABLE user_pref (
    id int (11) NOT NULL AUTO_INCREMENT ,
    user_id varchar(15) not null ,
    pref_list text not null default '',
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE news_logs (
    id int (11) NOT NULL AUTO_INCREMENT ,
    user_id int(11) not null ,
    news_id int(11) not null ,
    view_time int(20) not null ,
    prefer_degree tinyint not null default 1,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE news_recommend (
    id int (11) NOT NULL AUTO_INCREMENT ,
    user_id int(11) not null ,
    news_id int(11) not null ,
    feedback tinyint not null default 0,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;