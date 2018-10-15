CREATE TABLE `member` (
  `id` varchar(255) NOT NULL,
  `team_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `money` bigint(11) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `updated_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `deleted_flag` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
