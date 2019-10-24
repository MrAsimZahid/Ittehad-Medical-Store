-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2019 at 07:40 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pharma`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `showAllEmp` ()  NO SQL
select `empID` as ID, `UserName`, `empName` as Name, `empRole` as Role, `empContactNo.` as Contact_Number, `empCNIC` as CNIC,
`empSex` as Gender, `empBirthDate` as Birth_Date, `empSalary` as Salary
FROM `emploee`$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `attendence`
--

CREATE TABLE `attendence` (
  `emploeeID` int(11) NOT NULL,
  `status` enum('Absent','Present','Leave') NOT NULL,
  `attendDate` date NOT NULL,
  `timeIN` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `timeOut` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `emploee`
--

CREATE TABLE `emploee` (
  `empID` int(11) NOT NULL,
  `UserName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `empName` varchar(20) NOT NULL,
  `empCNIC` bigint(15) NOT NULL,
  `empContactNo.` bigint(15) UNSIGNED NOT NULL,
  `empSex` varchar(7) NOT NULL,
  `empSalary` int(11) NOT NULL,
  `empBirthDate` date NOT NULL,
  `empRole` varchar(9) NOT NULL,
  `pharmacyID` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emploee`
--

INSERT INTO `emploee` (`empID`, `UserName`, `password`, `empName`, `empCNIC`, `empContactNo.`, `empSex`, `empSalary`, `empBirthDate`, `empRole`, `pharmacyID`) VALUES
(1, 'abc', '123456', 'Asim', 25823, 2453256436, '1', 34353, '2019-05-01', 'Admin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `medcompany`
--

CREATE TABLE `medcompany` (
  `compID` int(11) NOT NULL,
  `compName` varchar(20) NOT NULL,
  `compContact` bigint(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medcompany`
--

INSERT INTO `medcompany` (`compID`, `compName`, `compContact`) VALUES
(1, 'hello', 3320443303);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_id` int(10) NOT NULL,
  `InVoiceDate` date NOT NULL,
  `order_type` varchar(7) NOT NULL,
  `creditReturnDate` date DEFAULT NULL,
  `order_total_amount` float NOT NULL,
  `batch` varchar(10) DEFAULT NULL,
  `supID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE `pharmacy` (
  `pharmacyID` int(11) NOT NULL,
  `pharmacyName` varchar(20) NOT NULL,
  `pharmaAddress` varchar(30) NOT NULL,
  `pharmaContactNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pharmacy`
--

INSERT INTO `pharmacy` (`pharmacyID`, `pharmacyName`, `pharmaAddress`, `pharmaContactNo`) VALUES
(1, 'Itthad Medical Store', 'cwec3rf3', 243234);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `productID` int(11) NOT NULL,
  `productName` varchar(50) NOT NULL,
  `productType` int(11) DEFAULT NULL,
  `batch_id` varchar(10) DEFAULT NULL,
  `productExpiryDate` varchar(12) DEFAULT NULL,
  `quaintityPerPack` int(11) NOT NULL,
  `Min_Level` int(6) UNSIGNED DEFAULT NULL,
  `p_compID` int(11) NOT NULL,
  `supplierID` int(11) NOT NULL,
  `pBuyingPrice` float NOT NULL,
  `pSellingPrice` float NOT NULL,
  `pOldPrice` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `productName`, `productType`, `batch_id`, `productExpiryDate`, `quaintityPerPack`, `Min_Level`, `p_compID`, `supplierID`, `pBuyingPrice`, `pSellingPrice`, `pOldPrice`) VALUES
(1, 'panadol', NULL, 'e34', NULL, 12, 3, 1, 1, 10, 11.5, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `purchaseinvoice`
--

CREATE TABLE `purchaseinvoice` (
  `id` int(11) NOT NULL,
  `productNam` varchar(30) NOT NULL,
  `BatchN` varchar(20) DEFAULT NULL,
  `expiryD` date DEFAULT NULL,
  `buying` float NOT NULL,
  `retailP` float NOT NULL,
  `totalQty` int(11) NOT NULL,
  `minLev` int(11) DEFAULT NULL,
  `nAmount` float NOT NULL,
  `Margine` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchaseinvoice`
--

INSERT INTO `purchaseinvoice` (`id`, `productNam`, `BatchN`, `expiryD`, `buying`, `retailP`, `totalQty`, `minLev`, `nAmount`, `Margine`) VALUES
(1, 'panadol', '123e', '2019-05-16', 34, 38.08, 7, 6, 102, 12),
(2, 'panerrtr', '3456', '2019-05-31', 3, 36.8, 7, 2, 96, 12.3),
(3, 'wuefh', '42', '2019-06-05', 34.345, 52.5479, 38, 20, 1202.08, 53);

-- --------------------------------------------------------

--
-- Table structure for table `saleinvoice`
--

CREATE TABLE `saleinvoice` (
  `ID` bigint(20) NOT NULL,
  `TotProducts` int(11) NOT NULL,
  `Reference` varchar(20) NOT NULL,
  `TotalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supplierID` int(11) NOT NULL,
  `supplierName` varchar(20) NOT NULL,
  `supplierContactNo` bigint(15) NOT NULL,
  `ExpiryDayPolicy` int(10) UNSIGNED NOT NULL,
  `supplierComp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supplierID`, `supplierName`, `supplierContactNo`, `ExpiryDayPolicy`, `supplierComp`) VALUES
(1, 'ere', 24, 2, 1),
(2, 'ffew', 535, 60, 1);

-- --------------------------------------------------------

--
-- Table structure for table `suppliercompany`
--

CREATE TABLE `suppliercompany` (
  `compID` int(11) NOT NULL,
  `compName` varchar(20) NOT NULL,
  `compContact` bigint(15) DEFAULT NULL,
  `comAddress` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suppliercompany`
--

INSERT INTO `suppliercompany` (`compID`, `compName`, `compContact`, `comAddress`) VALUES
(1, 'gsk', 1434243, 'fshdtew34'),
(2, 'echo', 253, 'fdffl;6');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendence`
--
ALTER TABLE `attendence`
  ADD KEY `emploeeID` (`emploeeID`);

--
-- Indexes for table `emploee`
--
ALTER TABLE `emploee`
  ADD PRIMARY KEY (`empID`),
  ADD UNIQUE KEY `empContactNo.` (`empContactNo.`),
  ADD KEY `pharmacyID` (`pharmacyID`);

--
-- Indexes for table `medcompany`
--
ALTER TABLE `medcompany`
  ADD PRIMARY KEY (`compID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `supID` (`supID`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD PRIMARY KEY (`pharmacyID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productID`),
  ADD KEY `p_compID` (`p_compID`,`supplierID`),
  ADD KEY `supplierID` (`supplierID`);

--
-- Indexes for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `saleinvoice`
--
ALTER TABLE `saleinvoice`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplierID`),
  ADD KEY `supplierComp` (`supplierComp`);

--
-- Indexes for table `suppliercompany`
--
ALTER TABLE `suppliercompany`
  ADD PRIMARY KEY (`compID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `emploee`
--
ALTER TABLE `emploee`
  MODIFY `empID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `medcompany`
--
ALTER TABLE `medcompany`
  MODIFY `compID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `productID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `purchaseinvoice`
--
ALTER TABLE `purchaseinvoice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `saleinvoice`
--
ALTER TABLE `saleinvoice`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supplierID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `suppliercompany`
--
ALTER TABLE `suppliercompany`
  MODIFY `compID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `emploee`
--
ALTER TABLE `emploee`
  ADD CONSTRAINT `emploee_ibfk_1` FOREIGN KEY (`pharmacyID`) REFERENCES `pharmacy` (`pharmacyID`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`supID`) REFERENCES `supplier` (`supplierID`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`p_compID`) REFERENCES `medcompany` (`compID`),
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`supplierID`) REFERENCES `supplier` (`supplierID`);

--
-- Constraints for table `supplier`
--
ALTER TABLE `supplier`
  ADD CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`supplierComp`) REFERENCES `suppliercompany` (`compID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
