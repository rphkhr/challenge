-- Create syntax for TABLE 'accounts'
CREATE TABLE IF NOT EXISTS `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `person_id` varchar(100) NOT NULL DEFAULT '',
  `balance` decimal(13,4) NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT '',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'persons'
CREATE TABLE IF NOT EXISTS `persons` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `full_name` varchar(100) NOT NULL DEFAULT '',
  `date_of_birth` date DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'transactions'
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(10) NOT NULL DEFAULT '',
  `amount` decimal(13,4) NOT NULL,
  `currency` varchar(3) NOT NULL DEFAULT '',
  `source_account` int(11) NOT NULL,
  `target_account` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;