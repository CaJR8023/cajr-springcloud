CREATE TABLE admin (
    id int (11) NOT NULL AUTO_INCREMENT ,
    account varchar(50) not null ,
    password varchar(150) not null ,
    status tinyint not null default 1,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null default '1980-01-01 00:00:01',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;