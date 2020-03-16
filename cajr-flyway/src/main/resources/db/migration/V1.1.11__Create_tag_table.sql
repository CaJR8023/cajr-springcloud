CREATE TABLE tag (
    id int (11) NOT NULL AUTO_INCREMENT ,
    name varchar(32) not null,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE news_tag (
    id int (11) NOT NULL AUTO_INCREMENT ,
    news_id int (11) NOT NULL,
    tag_id int (11) NOT NULL,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE module_tag (
    id int (11) NOT NULL AUTO_INCREMENT ,
    module_id int (11) NOT NULL,
    tag_id int (11) NOT NULL,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;