CREATE TABLE `loan` (
  `id` varchar(255) NOT NULL,
  `from_member_id` varchar(255) NOT NULL,
  `to_member_id` varchar(255) NOT NULL ,
  `time` bigint(11) NOT NULL DEFAULT 1,
  `task` varchar(255) NOT NULL DEFAULT "何か",
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `deleted_flag` char(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8