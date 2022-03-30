CREATE TABLE IF NOT EXISTS `person`
(
    `id`                    varchar(36) NOT NULL PRIMARY KEY,
    `name`                  varchar(50)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `parent`
(
    `id`                    varchar(36) NOT NULL PRIMARY KEY,
    `name`                  varchar(50)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;

CREATE TABLE IF NOT EXISTS `child`
(
    `id`                    varchar(36) NOT NULL PRIMARY KEY,
    `name`                  varchar(50)
) ENGINE = InnoDB
  DEFAULT CHARSET = UTF8;