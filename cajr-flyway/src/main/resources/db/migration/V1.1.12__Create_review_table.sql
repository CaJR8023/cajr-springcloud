CREATE TABLE review (
    id int (11) NOT NULL AUTO_INCREMENT ,
    news_id int (11) NOT NULL,
    user_id int (11) NOT NULL,
    content varchar(500) not null ,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_like_review (
    id int (11) NOT NULL AUTO_INCREMENT ,
    news_id int (11) NOT NULL,
    review_id int (11) NOT NULL,
    is_like tinyint not null default 1,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE user_review (
    id int (11) NOT NULL AUTO_INCREMENT ,
    review_id int (11) NOT NULL,
    user_id int (11) NOT NULL,
    content varchar(500) not null ,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

