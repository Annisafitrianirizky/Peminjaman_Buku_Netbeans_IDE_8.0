-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2024 at 06:24 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `peminjaman`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblabsensi`
--

CREATE TABLE `tblabsensi` (
  `id_absensi` int(12) NOT NULL,
  `id_petugas` varchar(12) NOT NULL,
  `tgl_absensi` datetime NOT NULL,
  `kehadiran` enum('Hadir','Tidak Hadir','Izin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblabsensi`
--

INSERT INTO `tblabsensi` (`id_absensi`, `id_petugas`, `tgl_absensi`, `kehadiran`) VALUES
(1, 'admin', '2024-07-07 00:00:00', 'Hadir'),
(2, 'draco', '2024-07-07 00:00:00', 'Hadir'),
(3, 'Venti', '2024-07-07 00:00:00', 'Tidak Hadir'),
(4, 'admin', '2024-07-19 00:00:00', 'Hadir'),
(5, 'admin', '2024-07-19 00:00:00', 'Hadir'),
(6, 'admin', '2024-07-19 00:00:00', 'Hadir');

-- --------------------------------------------------------

--
-- Table structure for table `tblbuku`
--

CREATE TABLE `tblbuku` (
  `id_buku` int(12) NOT NULL,
  `ISBN` varchar(12) NOT NULL,
  `nama_buku` varchar(100) NOT NULL,
  `pengarang` varchar(100) NOT NULL,
  `penerbit` varchar(100) NOT NULL,
  `thn_terbit` varchar(55) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblbuku`
--

INSERT INTO `tblbuku` (`id_buku`, `ISBN`, `nama_buku`, `pengarang`, `penerbit`, `thn_terbit`) VALUES
(1, '978', 'ORV', 'Sing song', 'naver line webtoon', '2019'),
(2, '983', 'TCF', 'ppan4', 'kakaopage', '2019'),
(3, '992', 'Without doubt', 'serenity', 'kakaopage', '2020'),
(4, '994', 'Solo Leveling', 'kakaopage', 'kakaopage', '2022');

-- --------------------------------------------------------

--
-- Table structure for table `tblpeminjam`
--

CREATE TABLE `tblpeminjam` (
  `id_peminjam` int(12) NOT NULL,
  `id_user` varchar(199) NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `no_telp` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tgl_pinjam` date NOT NULL,
  `id_buku` varchar(100) NOT NULL,
  `status_buku` enum('tersedia','dipinjam') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblpeminjam`
--

INSERT INTO `tblpeminjam` (`id_peminjam`, `id_user`, `alamat`, `no_telp`, `email`, `tgl_pinjam`, `id_buku`, `status_buku`) VALUES
(1, 'dokja', 'starstream', '12345', 'reader@gmail.com', '2024-07-07', 'orv', 'dipinjam'),
(2, 'Nisa', 'Wismamas 2', '083153521938', 'Whatshine01', '2024-07-18', 'Solo Leveling', 'dipinjam'),
(3, 'User3', 'Sepatan', '0987654321', 'User@gmail.com', '2024-07-19', 'TCF', 'dipinjam'),
(4, 'darari', 'asdfghjk', '0987657890', 'seghjk', '2024-07-19', 'Solo Leveling', 'tersedia');

-- --------------------------------------------------------

--
-- Table structure for table `tblpetugas`
--

CREATE TABLE `tblpetugas` (
  `id_petugas` int(12) NOT NULL,
  `nama_petugas` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `no_telp` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tblpetugas`
--

INSERT INTO `tblpetugas` (`id_petugas`, `nama_petugas`, `password`, `alamat`, `no_telp`, `email`) VALUES
(1, 'admin', 'admin', 'mana aja', '0987654321', 'admin@gmail.com'),
(2, 'draco', '12345', 'wiltshire', '98332418271', 'Dray@gmail.com'),
(3, 'Venti', 'Barbaventy', 'moonstad', '083321743321', 'Venti@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `tbluser`
--

CREATE TABLE `tbluser` (
  `id_user` int(12) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbluser`
--

INSERT INTO `tbluser` (`id_user`, `username`, `password`) VALUES
(1, 'dokja', '12345'),
(2, 'Junghyuk', '12345'),
(3, 'Nisa', '12345'),
(4, 'User', '12345'),
(5, 'user1', '12345'),
(6, 'User2', '12345'),
(7, 'User3', '12345'),
(8, 'darari', '00000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblabsensi`
--
ALTER TABLE `tblabsensi`
  ADD PRIMARY KEY (`id_absensi`);

--
-- Indexes for table `tblbuku`
--
ALTER TABLE `tblbuku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indexes for table `tblpeminjam`
--
ALTER TABLE `tblpeminjam`
  ADD PRIMARY KEY (`id_peminjam`);

--
-- Indexes for table `tblpetugas`
--
ALTER TABLE `tblpetugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `tbluser`
--
ALTER TABLE `tbluser`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblabsensi`
--
ALTER TABLE `tblabsensi`
  MODIFY `id_absensi` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tblbuku`
--
ALTER TABLE `tblbuku`
  MODIFY `id_buku` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tblpeminjam`
--
ALTER TABLE `tblpeminjam`
  MODIFY `id_peminjam` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tblpetugas`
--
ALTER TABLE `tblpetugas`
  MODIFY `id_petugas` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbluser`
--
ALTER TABLE `tbluser`
  MODIFY `id_user` int(12) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
