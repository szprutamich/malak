DROP DATABASE IF EXISTS malak;
CREATE DATABASE malak DEFAULT CHARSET utf8;
GRANT ALL PRIVILEGES ON malak.* TO 'malak'@'localhost' IDENTIFIED BY 'malak';
GRANT ALL PRIVILEGES ON malak.* TO 'malak'@'%' IDENTIFIED BY 'malak';
FLUSH PRIVILEGES;

USE malak;

DROP TABLE IF EXISTS `pracodawcy`;
CREATE TABLE `pracodawcy` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nazwa` VARCHAR(255) NOT NULL,
  `teczka` BOOLEAN DEFAULT FALSE,
  `teczka_uwagi` VARCHAR(255) DEFAULT NULL,
  `ocena` BOOLEAN DEFAULT FALSE,
  `ocena_uwagi` VARCHAR(255) DEFAULT NULL,
  `szkolenia_okresowe` BOOLEAN DEFAULT FALSE,
  `szkolenia_okresowe_uwagi` VARCHAR(255) DEFAULT NULL,
  `szkolenia_pracodawcy` BOOLEAN DEFAULT FALSE,
  `szkolenia_pracodawcy_data` DATETIME DEFAULT NULL,
  `szkolenia_pracodawcy_uwagi` VARCHAR(255) DEFAULT NULL,
  `odziezowka` BOOLEAN DEFAULT FALSE,
  `odziezowka_uwagi` VARCHAR(255) DEFAULT NULL,
  `data_usuniecia` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zlecenia`;
CREATE TABLE `zlecenia` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nazwa` VARCHAR(255) NOT NULL,
  `kwestionariusz` BOOLEAN DEFAULT FALSE,
  `kwestionariusz_uwagi` VARCHAR(255) DEFAULT NULL,
  `karta_szkolenia` BOOLEAN DEFAULT FALSE,
  `karta_szkolenia_uwagi` VARCHAR(255) DEFAULT NULL,
  `karta_szkolenia_data` DATETIME DEFAULT NULL,
  `szkolenie` BOOLEAN DEFAULT FALSE,
  `szkolenie_uwagi` VARCHAR(255) DEFAULT NULL,
  `instruktaz` BOOLEAN DEFAULT FALSE,
  `instruktaz_uwagi` VARCHAR(255) DEFAULT NULL,
  `ryzyko` BOOLEAN DEFAULT FALSE,
  `ryzyko_uwagi` VARCHAR(255) DEFAULT NULL,
  `instrukcje_bhp` BOOLEAN DEFAULT FALSE,
  `instrukcje_bhp_uwagi` VARCHAR(255) DEFAULT NULL,
  `szkolenie_bhp` BOOLEAN DEFAULT FALSE,
  `szkolenie_bhp_uwagi` VARCHAR(255) DEFAULT NULL,
  `szkolenie_bhp_data` DATETIME DEFAULT NULL,
  `rachunki` BOOLEAN DEFAULT FALSE,
  `rachunki_uwagi` VARCHAR(255) DEFAULT NULL,
  `umowa` BOOLEAN DEFAULT FALSE,
  `umowa_uwagi` VARCHAR(255) DEFAULT NULL,
  `umowa_data` DATETIME DEFAULT NULL,
  `odbior_odziezy` BOOLEAN DEFAULT FALSE,
  `odbior_odziezy_uwagi` VARCHAR(255) DEFAULT NULL,
  `odbior_odziezy_data` DATETIME DEFAULT NULL,
  `zua` BOOLEAN DEFAULT FALSE,
  `zua_uwagi` VARCHAR(255) DEFAULT NULL,
  `zwua` BOOLEAN DEFAULT FALSE,
  `zwua_uwagi` VARCHAR(255) DEFAULT NULL,
  `zwua_data` DATETIME DEFAULT NULL,
  `badania` BOOLEAN DEFAULT FALSE,
  `badania_uwagi` VARCHAR(255) DEFAULT NULL,
  `badania_data` DATETIME DEFAULT NULL,
  `legitymacja` BOOLEAN DEFAULT FALSE,
  `legitymacja_uwagi` VARCHAR(255) DEFAULT NULL,
  `zyciorys` BOOLEAN DEFAULT FALSE,
  `zyciorys_uwagi` VARCHAR(255) DEFAULT NULL,
  `dowod` BOOLEAN DEFAULT FALSE,
  `dowod_uwagi` VARCHAR(255) DEFAULT NULL,
  `zaswiadczenie_sanitarne` BOOLEAN DEFAULT FALSE,
  `zaswiadczenie_sanitarne_uwagi` VARCHAR(255) DEFAULT NULL,
  `zaswiadczenie_student` BOOLEAN DEFAULT FALSE,
  `zaswiadczenie_student_uwagi` VARCHAR(255) DEFAULT NULL,
  `wyciag_kodeks` BOOLEAN DEFAULT FALSE,
  `wyciag_kodeks_uwagi` VARCHAR(255) DEFAULT NULL,
  `pracodawca_id` BIGINT NOT NULL,
  `data_usuniecia` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pracodawca_id_fk` (`pracodawca_id`),
  CONSTRAINT `pracodawca_id_fk` FOREIGN KEY (`pracodawca_id`) REFERENCES `pracodawcy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
