-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 25, 2024 at 08:11 PM
-- Server version: 8.0.36
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vet-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `animals`
--

CREATE TABLE `animals` (
  `animal_id` bigint NOT NULL,
  `animal_breed` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `animal_color` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `animal_birth_date` date NOT NULL,
  `animal_gender` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `animal_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `animal_specie` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `animal_customer_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `animals`
--

INSERT INTO `animals` (`animal_id`, `animal_breed`, `animal_color`, `animal_birth_date`, `animal_gender`, `animal_name`, `animal_specie`, `animal_customer_id`) VALUES
(1, 'Golden Retriever', 'White', '2020-12-30', 'Male', 'Buddy', 'Dog', 1),
(2, 'Golden Retriever', 'Black', '2022-12-02', 'Female', 'True', 'Dog', 2),
(3, 'Poodle', 'Brown', '2023-07-21', 'Female', 'Sabra', 'Dog', 3),
(4, 'Husky', 'Mixed', '2021-03-27', 'Male', 'Dougie', 'Dog', 4),
(5, 'Bulldog', 'Black', '2022-08-13', 'Male', 'Sky', 'Dog', 5),
(6, 'Canary', 'Yellow', '2024-05-04', 'Female', 'Antonella', 'Bird', 2),
(7, 'Persian', 'Orange', '2020-02-14', 'Female', 'Kiki', 'Cat', 1);

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointment_id` bigint NOT NULL,
  `appointment_date` datetime(6) NOT NULL,
  `appointment_animal_id` bigint NOT NULL,
  `appointment_available_date_id` bigint NOT NULL,
  `appointment_doctor_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`appointment_id`, `appointment_date`, `appointment_animal_id`, `appointment_available_date_id`, `appointment_doctor_id`) VALUES
(1, '2024-05-13 10:00:00.051000', 3, 1, 1),
(2, '2024-05-14 11:00:00.825000', 5, 2, 2),
(3, '2024-05-15 08:00:00.660000', 1, 3, 3),
(4, '2024-05-16 14:00:00.047000', 2, 4, 4),
(5, '2024-05-17 15:00:33.548000', 4, 5, 5),
(6, '2024-05-18 17:00:00.687000', 6, 6, 1),
(7, '2024-05-19 12:00:00.203000', 7, 7, 2);

-- --------------------------------------------------------

--
-- Table structure for table `available_dates`
--

CREATE TABLE `available_dates` (
  `available_date_id` bigint NOT NULL,
  `available_date` date NOT NULL,
  `available_date_doctor_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `available_dates`
--

INSERT INTO `available_dates` (`available_date_id`, `available_date`, `available_date_doctor_id`) VALUES
(1, '2024-05-13', 1),
(2, '2024-05-14', 2),
(3, '2024-05-15', 3),
(4, '2024-05-16', 4),
(5, '2024-05-17', 5),
(6, '2024-05-18', 1),
(7, '2024-05-19', 2);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `customer_id` bigint NOT NULL,
  `customer_address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `customer_surname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `customer_address`, `customer_city`, `customer_mail`, `customer_name`, `customer_phone`, `customer_surname`) VALUES
(1, '34433 Cihangir', 'Istanbul', 'leylataylan@gmail.com', 'Leyla', '+905055055505', 'Taylan'),
(2, '04863 Schlimgen Park', 'Pingpo', 'lcurrierb@webmd.com', 'Leela', '+869278788735', 'Currier'),
(3, '27132 Stang Pass', 'Nebug', 'eshakesbyec@columbia.edu', 'Ethelin', '+79775786371', 'Shakesbye'),
(4, '66253 Morning Hill', 'Krychaw', 'lkeverend@fotki.com', 'Lurlene', '+3755367425181', 'Keveren'),
(5, '90 Sullivan Way', 'Yonglong', 'bdeversone@cpanel.net', 'Beverlee', '+868184620649', 'de Verson');

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `doctor_id` bigint NOT NULL,
  `doctor_address` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_mail` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_phone` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `doctor_surname` varchar(255) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`doctor_id`, `doctor_address`, `doctor_city`, `doctor_mail`, `doctor_name`, `doctor_phone`, `doctor_surname`) VALUES
(1, '9 Crescent Oaks Hill', 'Miura', 'kmusprat0@mail.ru', 'Killian', '+815286322262', 'Musprat'),
(2, '1 Beilfuss Park', 'Sremska Mitrovica', 'mbaxster1@nsw.gov.au', 'Morganica', '+3815247092462', 'Baxster'),
(3, '79 Mesta Terrace', 'Mambago', 'alewcock2@cdc.gov', 'Alana', '+635705938573', 'Lewcock'),
(4, '09 Maple Wood Street', 'Hayil', 'alongbone3@istockphoto.com', 'Antoine', '+9665521954035', 'Longbone'),
(5, '9 Sage Pass', 'Aimin', 'grender4@nymag.com', 'Geoff', '+861906427023', 'Render');

-- --------------------------------------------------------

--
-- Table structure for table `vaccines`
--

CREATE TABLE `vaccines` (
  `vaccine_id` bigint NOT NULL,
  `vaccine_code` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `vaccine_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `vaccine_protection_end_date` date NOT NULL,
  `vaccine_protection_start_date` date NOT NULL,
  `vaccine_animal_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vaccines`
--

INSERT INTO `vaccines` (`vaccine_id`, `vaccine_code`, `vaccine_name`, `vaccine_protection_end_date`, `vaccine_protection_start_date`, `vaccine_animal_id`) VALUES
(1, 'H101', 'Hepatitis', '2024-05-15', '2024-08-15', 1),
(2, 'D101', 'Distemper', '2024-05-16', '2024-08-16', 2),
(3, 'I101', 'Influenza', '2024-05-17', '2024-08-17', 4),
(4, 'L101', 'Lyme', '2024-05-14', '2024-08-14', 5),
(5, 'LP101', 'Leptospirosis', '2024-05-13', '2024-08-13', 3),
(6, 'PV101', 'PolyomaVirus', '2024-05-18', '2024-08-18', 6),
(7, 'FL101', 'Feline Leukemia', '2024-05-19', '2024-08-19', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `animals`
--
ALTER TABLE `animals`
  ADD PRIMARY KEY (`animal_id`),
  ADD KEY `FKnjsvd8kplxqmf48ybxayrx6ru` (`animal_customer_id`);

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `FKo4t945rb708af9ry6syof7bwt` (`appointment_animal_id`),
  ADD KEY `FKicgogibqnh5gnyh6ersmkya7s` (`appointment_available_date_id`),
  ADD KEY `FK27d0xcbajwaeeun2doojsae4` (`appointment_doctor_id`);

--
-- Indexes for table `available_dates`
--
ALTER TABLE `available_dates`
  ADD PRIMARY KEY (`available_date_id`),
  ADD KEY `FKf7jtwolyhaj07c4oh0j4pncb` (`available_date_doctor_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `UK_5vhox5jsqitujs1k7bcsb2rj8` (`customer_mail`),
  ADD UNIQUE KEY `UK_bn3wq4vhuqco545y52xp96gqd` (`customer_phone`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`doctor_id`),
  ADD UNIQUE KEY `UK_amsyrdrr2f0d48ciy29o9hvjm` (`doctor_mail`),
  ADD UNIQUE KEY `UK_7s4l7559eox2hor73b3qp3vq2` (`doctor_phone`);

--
-- Indexes for table `vaccines`
--
ALTER TABLE `vaccines`
  ADD PRIMARY KEY (`vaccine_id`),
  ADD KEY `FKekhfjmpsduds8nnilqe9b467v` (`vaccine_animal_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `animals`
--
ALTER TABLE `animals`
  MODIFY `animal_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `appointment_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `available_dates`
--
ALTER TABLE `available_dates`
  MODIFY `available_date_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `doctors`
--
ALTER TABLE `doctors`
  MODIFY `doctor_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `vaccines`
--
ALTER TABLE `vaccines`
  MODIFY `vaccine_id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `animals`
--
ALTER TABLE `animals`
  ADD CONSTRAINT `FKnjsvd8kplxqmf48ybxayrx6ru` FOREIGN KEY (`animal_customer_id`) REFERENCES `customers` (`customer_id`);

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `FK27d0xcbajwaeeun2doojsae4` FOREIGN KEY (`appointment_doctor_id`) REFERENCES `doctors` (`doctor_id`),
  ADD CONSTRAINT `FKicgogibqnh5gnyh6ersmkya7s` FOREIGN KEY (`appointment_available_date_id`) REFERENCES `available_dates` (`available_date_id`),
  ADD CONSTRAINT `FKo4t945rb708af9ry6syof7bwt` FOREIGN KEY (`appointment_animal_id`) REFERENCES `animals` (`animal_id`);

--
-- Constraints for table `available_dates`
--
ALTER TABLE `available_dates`
  ADD CONSTRAINT `FKf7jtwolyhaj07c4oh0j4pncb` FOREIGN KEY (`available_date_doctor_id`) REFERENCES `doctors` (`doctor_id`);

--
-- Constraints for table `vaccines`
--
ALTER TABLE `vaccines`
  ADD CONSTRAINT `FKekhfjmpsduds8nnilqe9b467v` FOREIGN KEY (`vaccine_animal_id`) REFERENCES `animals` (`animal_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
