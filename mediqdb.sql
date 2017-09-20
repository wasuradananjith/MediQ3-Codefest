-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 20, 2017 at 03:15 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mediqdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

DROP TABLE IF EXISTS `appointment`;
CREATE TABLE IF NOT EXISTS `appointment` (
  `ref_No` varchar(10) NOT NULL,
  `patient_Phone` int(10) NOT NULL,
  `app_No` int(2) NOT NULL,
  `session_ID` varchar(10) NOT NULL,
  `requested_No` int(2) DEFAULT NULL,
  PRIMARY KEY (`ref_No`),
  KEY `fk_patient_Phone` (`patient_Phone`),
  KEY `fk_session_ID` (`session_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`ref_No`, `patient_Phone`, `app_No`, `session_ID`, `requested_No`) VALUES
('A0001', 771234567, 1, 'S0001', NULL),
('A0002', 712345678, 1, 'S0002', NULL),
('A0003', 723456789, 2, 'S0001', NULL),
('A0004', 723456789, 1, 'S0003', NULL),
('A0005', 771234567, 2, 'S0002', NULL),
('A0006', 771234567, 2, 'S0003', NULL),
('A0007', 712123456, 3, 'S0001', NULL),
('A0008', 772345678, 1, 'S0004', NULL),
('A0009', 777123456, 4, 'S0001', NULL),
('A0010', 712123456, 1, 'S0005', NULL),
('A0011', 777894561, 5, 'S0001', NULL),
('A0012', 712564556, 6, 'S0001', NULL),
('A0013', 785987654, 7, 'S0001', NULL),
('A0014', 714562321, 8, 'S0001', NULL),
('A0015', 754231678, 9, 'S0001', NULL),
('A0016', 754231678, 3, 'S0002', NULL),
('A0017', 714562321, 4, 'S0002', NULL),
('A0018', 725896719, 5, 'S0002', NULL),
('A0019', 776541236, 6, 'S0002', NULL),
('A0020', 774789135, 7, 'S0002', NULL),
('A0021', 718475869, 8, 'S0002', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
CREATE TABLE IF NOT EXISTS `doctor` (
  `doc_NIC` char(10) NOT NULL,
  `doc_Name` varchar(50) NOT NULL,
  `doc_Spec` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`doc_NIC`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doc_NIC`, `doc_Name`, `doc_Spec`, `password`) VALUES
('637411111V', 'QR Adikari', 'General Physician', 'abc'),
('642356789V', 'ST Peter', 'Dental Surgeon', ''),
('652121212V', 'UV Dias', 'Chest Specialist', '');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
CREATE TABLE IF NOT EXISTS `patient` (
  `patient_Phone` int(10) NOT NULL,
  `patient_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`patient_Phone`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_Phone`, `patient_Name`) VALUES
(771234567, 'ABC Perera'),
(712345678, 'DEF Silva'),
(723456789, 'GHI Fernando'),
(772345678, 'JK Rowling'),
(777123456, 'LM Stine'),
(712123456, 'OP Dennis'),
(777894561, 'KK Perera'),
(712564556, 'DM Ronald'),
(785987654, 'DM Anne'),
(714562321, 'SD Fernando'),
(754231678, 'LK Aruni'),
(725896719, 'RU De Silva'),
(776541236, 'KWH Brian'),
(774789135, 'PT Tony'),
(718475869, 'RK Silvia');

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE IF NOT EXISTS `session` (
  `session_ID` varchar(10) NOT NULL,
  `session_Date` date NOT NULL,
  `session_Time` int(4) NOT NULL,
  `doc_NIC` char(10) NOT NULL,
  `counter` int(2) DEFAULT '0',
  `avg_Time` int(10) NOT NULL,
  PRIMARY KEY (`session_ID`),
  KEY `fk_doc_NIC` (`doc_NIC`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`session_ID`, `session_Date`, `session_Time`, `doc_NIC`, `counter`, `avg_Time`) VALUES
('S0001', '2017-01-20', 1600, '637411111V', 3, 0),
('S0002', '2017-01-21', 1700, '637411111V', 0, 0),
('S0003', '2017-01-25', 1700, '652121212V', 0, 0),
('S0004', '2017-02-01', 1300, '652121212V', 0, 0),
('S0005', '2017-01-23', 1400, '642356789V', 0, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
