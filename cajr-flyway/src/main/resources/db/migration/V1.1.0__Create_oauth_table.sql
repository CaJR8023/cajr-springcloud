CREATE TABLE `client_details` (
    `appId` varchar(128) NOT NULL,
    `resourceIds` varchar(256) DEFAULT NULL,
    `appSecret` varchar(256) DEFAULT NULL,
    `scope` varchar(256) DEFAULT NULL,
    `grantTypes` varchar(256) DEFAULT NULL,
    `redirectUrl` varchar(256) DEFAULT NULL,
    `authorities` varchar(256) DEFAULT NULL,
    `access_token_validity` int(11) DEFAULT NULL,
    `refresh_token_validity` int(11) DEFAULT NULL,
    `additionalInformation` varchar(4096) DEFAULT NULL,
    `autoApproveScopes` varchar(256) DEFAULT NULL,
    PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_access_token` (
    `token_id` varchar(256) DEFAULT NULL,
    `token` blob,
    `authentication_id` varchar(128) NOT NULL,
    `user_name` varchar(256) DEFAULT NULL,
    `client_id` varchar(256) DEFAULT NULL,
    `authentication` blob,
    `refresh_token` varchar(256) DEFAULT NULL,
    PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_approvals` (
    `userId` varchar(256) DEFAULT NULL,
    `clientId` varchar(256) DEFAULT NULL,
    `scope` varchar(256) DEFAULT NULL,
    `status` varchar(10) DEFAULT NULL,
    `expiresAt` timestamp NULL DEFAULT NULL,
    `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_client_details` (
    id int (11) NOT NULL AUTO_INCREMENT ,
    client_id VARCHAR(255) ,
    resource_ids VARCHAR(255),
    client_secret VARCHAR(255),
    scope VARCHAR(255),
    authorized_grant_types VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities VARCHAR(255),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(255),
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_client_token` (
    `token_id` varchar(256) DEFAULT NULL,
    `token` blob,
    `authentication_id` varchar(128) NOT NULL,
    `user_name` varchar(256) DEFAULT NULL,
    `client_id` varchar(256) DEFAULT NULL,
    PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_code` (
    `code` varchar(256) DEFAULT NULL,
    `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_refresh_token` (
    `token_id` varchar(256) DEFAULT NULL,
    `token` blob,
    `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;