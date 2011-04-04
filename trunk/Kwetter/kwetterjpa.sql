-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 04 Apr 2011 om 11:21
-- Serverversie: 5.0.83
-- PHP-Versie: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `kwetterjpa`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `sequence`
--

CREATE TABLE IF NOT EXISTS `sequence` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) default NULL,
  PRIMARY KEY  (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `sequence`
--

INSERT INTO `sequence` (`SEQ_NAME`, `SEQ_COUNT`) VALUES
('SEQ_GEN', '400');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tweet`
--

CREATE TABLE IF NOT EXISTS `tweet` (
  `ID` int(11) NOT NULL,
  `POSTEDFROM` varchar(255) default NULL,
  `POSTDATE` date default NULL,
  `tweets` varchar(140) default NULL,
  `USER_ID` int(11) default NULL,
  PRIMARY KEY  (`ID`),
  KEY `FK_TWEET_USER_ID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `tweet`
--

INSERT INTO `tweet` (`ID`, `POSTEDFROM`, `POSTDATE`, `tweets`, `USER_ID`) VALUES
(251, 'PC', '2011-04-04', '#hallo @hans', 1),
(301, 'PC', '2011-04-04', '#hallo #again #you', 1),
(351, 'PC', '2011-04-04', '#hallo #where are #you', 1);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `tweeter`
--

CREATE TABLE IF NOT EXISTS `tweeter` (
  `ID` int(11) NOT NULL,
  `BIO` varchar(255) default NULL,
  `NAME` varchar(255) default NULL,
  `WEB` varchar(255) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `tweeter`
--

INSERT INTO `tweeter` (`ID`, `BIO`, `NAME`, `WEB`) VALUES
(1, 'geboren 1', 'hans', 'http://www.google.nl'),
(51, 'geboren 2', 'frank', 'httpF'),
(101, 'geboren 3', 'tom', 'httpT'),
(151, 'geboren 4', 'sjaak', 'httpS'),
(201, 'geboren 5', 'default', 'httpD');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `users_follower`
--

CREATE TABLE IF NOT EXISTS `users_follower` (
  `Tweeter_ID` int(11) NOT NULL,
  `followers_ID` int(11) NOT NULL,
  PRIMARY KEY  (`Tweeter_ID`,`followers_ID`),
  KEY `FK_users_follower_followers_ID` (`followers_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `users_follower`
--

INSERT INTO `users_follower` (`Tweeter_ID`, `followers_ID`) VALUES
(51, 1),
(101, 1),
(151, 1),
(1, 51),
(1, 201);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `users_following`
--

CREATE TABLE IF NOT EXISTS `users_following` (
  `Tweeter_ID` int(11) NOT NULL,
  `following_ID` int(11) NOT NULL,
  PRIMARY KEY  (`Tweeter_ID`,`following_ID`),
  KEY `FK_users_following_following_ID` (`following_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `users_following`
--

INSERT INTO `users_following` (`Tweeter_ID`, `following_ID`) VALUES
(51, 1),
(201, 1),
(1, 51),
(1, 101),
(1, 151);

--
-- Beperkingen voor gedumpte tabellen
--

--
-- Beperkingen voor tabel `tweet`
--
ALTER TABLE `tweet`
  ADD CONSTRAINT `FK_TWEET_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `tweeter` (`ID`);

--
-- Beperkingen voor tabel `users_follower`
--
ALTER TABLE `users_follower`
  ADD CONSTRAINT `FK_users_follower_followers_ID` FOREIGN KEY (`followers_ID`) REFERENCES `tweeter` (`ID`),
  ADD CONSTRAINT `FK_users_follower_Tweeter_ID` FOREIGN KEY (`Tweeter_ID`) REFERENCES `tweeter` (`ID`);

--
-- Beperkingen voor tabel `users_following`
--
ALTER TABLE `users_following`
  ADD CONSTRAINT `FK_users_following_following_ID` FOREIGN KEY (`following_ID`) REFERENCES `tweeter` (`ID`),
  ADD CONSTRAINT `FK_users_following_Tweeter_ID` FOREIGN KEY (`Tweeter_ID`) REFERENCES `tweeter` (`ID`);
