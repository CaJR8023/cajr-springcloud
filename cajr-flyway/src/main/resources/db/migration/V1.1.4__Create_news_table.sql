CREATE TABLE module (
    id int (11) NOT NULL AUTO_INCREMENT ,
    name varchar(15) not null ,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE news (
    id int (11) NOT NULL AUTO_INCREMENT ,
    module_id int(11) not null ,
    title varchar(50) not null ,
    source varchar(50) not null ,
    `desc` varchar(50) not null ,
    content text not null ,
    all_content text not null ,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE news_image (
    id int (11) NOT NULL AUTO_INCREMENT ,
    news_id int(11) not null ,
    url varchar(15) not null ,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;