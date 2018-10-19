CREATE TABLE `loan` (
  `id` varchar(255) NOT NULL,
  `from_member_id` varchar(255) NOT NULL,
  `to_member_id` varchar(255) NOT NULL ,
  `time` bigint(11) NOT NULL DEFAULT 1,
  `task` varchar(255) NOT NULL DEFAULT "何か",
  `created_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `updated_at` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  `deleted_flag` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
