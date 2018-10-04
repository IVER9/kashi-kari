CREATE TABLE `member` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `team_id` bigint(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `money` bigint(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_flag` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
