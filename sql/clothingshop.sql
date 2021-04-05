-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2021 at 12:46 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clothingshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `clothes`
--

CREATE TABLE `clothes` (
  `PK_Clothes` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Description` text DEFAULT NULL,
  `Price` decimal(10,2) NOT NULL,
  `ImagePath` varchar(255) DEFAULT NULL,
  `Sizes` varchar(150) NOT NULL,
  `PK_ClothesCategory` int(11) DEFAULT NULL,
  `PK_Color` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothes`
--

INSERT INTO `clothes` (`PK_Clothes`, `Name`, `Description`, `Price`, `ImagePath`, `Sizes`, `PK_ClothesCategory`, `PK_Color`) VALUES
(1, 'Футболка Itachi', 'Футболка прямого кроя, выполненная из синтетической ткани, приятной на ощупь. В следующий раз', '1050.00', 'itachi.jpg', 'M, L', 1, 1),
(2, 'Футболка Naruto VS Pain', 'Футболка прямого кроя, выполненная из синтетической ткани, приятной на ощупь.', '1200.00', 'naruto_vs_pain.jpg', 'S, M', 1, 1),
(4, 'Свитшот Naruto', 'Свитшот из 100% хлопка с мягким утепленным слоем.', '2100.00', 'naruto_longslive.jpg', 'M, L, XL', 3, 1),
(5, 'Худи Akatsuki', 'Худи из хлопка с мягким и теплым начесом внутри. Капюшон регулируется шнурком, спереди большой карман.', '2790.00', 'akatsuki_hudi.jpg', 'S, M, L, XL', 3, 5),
(96, 'Свитшот Naruto', 'Свитшот из 100% хлопка с мягким утепленным слоем. Красный', '2490.00', 'naruto_red.jpg', 'XL', 3, 1),
(97, 'Худи kaguya', 'Худи с самой красивой девочкой', '3200.00', 'kaguya.jpg', 'XL, L, M, S', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `clothescategory`
--

CREATE TABLE `clothescategory` (
  `PK_ClothesCategory` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clothescategory`
--

INSERT INTO `clothescategory` (`PK_ClothesCategory`, `Name`) VALUES
(1, 'Футболки'),
(2, 'Носки'),
(3, 'Худи и свитшоты');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `PK_Color` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`PK_Color`, `Name`) VALUES
(1, 'Черный'),
(3, 'Красный'),
(4, 'Фиолетовый'),
(5, 'Белый'),
(6, 'Синий');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `PK_Order` int(11) NOT NULL,
  `Size` varchar(15) NOT NULL,
  `Count` int(11) NOT NULL,
  `Date` date NOT NULL,
  `PK_User` int(11) NOT NULL,
  `PK_Clothes` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`PK_Order`, `Size`, `Count`, `Date`, `PK_User`, `PK_Clothes`) VALUES
(1, 'L', 2, '2021-04-02', 2, 97),
(2, 'XL', 1, '2021-04-02', 2, 96),
(5, 'L', 2, '2021-04-05', 3, 4);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `PK_User` int(11) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Role` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`PK_User`, `UserName`, `Password`, `FirstName`, `LastName`, `Role`) VALUES
(1, 'admin', '$2y$12$FV43D/oR02WUSIlOS.Y6bupMvurRIQADf.Rs15k4jvZYZ6h8.F1s.', 'Max', 'Borzenko', 'ADMIN'),
(2, 'user', '$2y$12$EkZJa63vw1pG.k6qrHN8dOHWglEdXlHida2EVMPW4V2kIYlZmmLAi', 'Ivan', 'Petrov', 'USER'),
(3, 'zzz', '$2y$12$.E4OFCpZPxHVSYHiZWkssOrHxS9W5Q5EMVmmFHBR047LyDPfcpqoG', 'Maxim', 'Borzenko', 'USER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clothes`
--
ALTER TABLE `clothes`
  ADD PRIMARY KEY (`PK_Clothes`,`PK_Color`),
  ADD KEY `IX_Relationship1` (`PK_ClothesCategory`),
  ADD KEY `Relationship3` (`PK_Color`);

--
-- Indexes for table `clothescategory`
--
ALTER TABLE `clothescategory`
  ADD PRIMARY KEY (`PK_ClothesCategory`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`PK_Color`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`PK_Order`),
  ADD KEY `Rel4` (`PK_User`),
  ADD KEY `Rel5` (`PK_Clothes`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`PK_User`),
  ADD UNIQUE KEY `Email` (`UserName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clothes`
--
ALTER TABLE `clothes`
  MODIFY `PK_Clothes` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT for table `clothescategory`
--
ALTER TABLE `clothescategory`
  MODIFY `PK_ClothesCategory` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `color`
--
ALTER TABLE `color`
  MODIFY `PK_Color` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `PK_Order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `PK_User` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clothes`
--
ALTER TABLE `clothes`
  ADD CONSTRAINT `Relationship1` FOREIGN KEY (`PK_ClothesCategory`) REFERENCES `clothescategory` (`PK_ClothesCategory`),
  ADD CONSTRAINT `Relationship3` FOREIGN KEY (`PK_Color`) REFERENCES `color` (`PK_Color`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `Rel4` FOREIGN KEY (`PK_User`) REFERENCES `users` (`PK_User`) ON DELETE CASCADE,
  ADD CONSTRAINT `Rel5` FOREIGN KEY (`PK_Clothes`) REFERENCES `clothes` (`PK_Clothes`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
