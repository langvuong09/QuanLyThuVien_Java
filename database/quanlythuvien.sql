-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 30, 2024 lúc 05:24 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlythuvien`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieumuon`
--

CREATE TABLE `ctphieumuon` (
  `MaPhieuMuon` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `MaPhanSach` int(11) NOT NULL,
  `ThanhTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieumuon`
--

INSERT INTO `ctphieumuon` (`MaPhieuMuon`, `MaSach`, `MaPhanSach`, `ThanhTien`) VALUES
(1, 6, 1, 10000),
(2, 7, 2, 21000),
(2, 1, 1, 1500),
(2, 2, 1, 1500),
(3, 4, 1, 8500),
(3, 5, 1, 1500),
(4, 9, 1, 8000),
(4, 11, 1, 8100),
(5, 10, 1, 7900),
(5, 8, 1, 11800),
(6, 14, 1, 20000),
(7, 13, 1, 18000),
(7, 16, 1, 11500),
(7, 17, 1, 11000),
(7, 18, 2, 10000),
(8, 11, 3, 8100),
(8, 8, 2, 11800),
(10, 3, 4, 5200),
(10, 4, 2, 8500),
(11, 1, 1, 1500),
(1, 6, 0, 10000),
(2, 7, 0, 21000),
(2, 1, 0, 1500),
(2, 2, 0, 1500),
(3, 4, 0, 8500),
(3, 5, 0, 1500),
(4, 9, 0, 8000),
(4, 11, 0, 8100),
(5, 10, 0, 7900),
(5, 8, 0, 11800),
(6, 14, 0, 20000),
(7, 13, 0, 18000),
(7, 16, 0, 11500),
(7, 17, 0, 11000),
(7, 18, 0, 10000),
(8, 11, 0, 8100),
(8, 8, 0, 11800),
(10, 3, 0, 5200),
(10, 4, 0, 8500);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `MaPhieuNhap` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `MaMin` int(11) NOT NULL,
  `MaMax` int(11) NOT NULL,
  `Gia` int(50) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `ThanhTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`MaPhieuNhap`, `MaSach`, `MaMin`, `MaMax`, `Gia`, `SoLuong`, `ThanhTien`) VALUES
(1, 7, 1, 7, 210000, 7, 1470000),
(2, 4, 1, 1, 85000, 1, 85000),
(3, 5, 1, 1, 15000, 1, 15000),
(4, 2, 1, 1, 15000, 1, 15000),
(5, 3, 2, 4, 52000, 3, 156000),
(6, 19, 1, 5, 130000, 5, 650000),
(6, 20, 1, 5, 170000, 7, 1190000),
(6, 5, 5, 6, 15000, 2, 30000),
(7, 1, 8, 8, 15000, 1, 15000),
(8, 1, 9, 9, 15000, 1, 15000),
(9, 1, 10, 10, 15000, 1, 15000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieuphat`
--

CREATE TABLE `ctphieuphat` (
  `MaPhieuPhat` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `MaPhanSach` int(11) NOT NULL,
  `LyDo` varchar(255) NOT NULL,
  `TienPhat` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieutra`
--

CREATE TABLE `ctphieutra` (
  `MaPhieuTra` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `MaPhanSach` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieutra`
--

INSERT INTO `ctphieutra` (`MaPhieuTra`, `MaSach`, `MaPhanSach`) VALUES
(1, 9, 1),
(1, 11, 1),
(2, 1, 1),
(2, 2, 1),
(3, 4, 1),
(4, 13, 1),
(4, 16, 1),
(5, 14, 1),
(6, 11, 3),
(6, 8, 1),
(7, 3, 4),
(7, 4, 2),
(10, 10, 1),
(1, 9, 0),
(1, 11, 0),
(2, 1, 0),
(2, 2, 0),
(3, 4, 0),
(4, 13, 0),
(4, 16, 0),
(5, 14, 0),
(6, 11, 0),
(6, 8, 0),
(7, 3, 0),
(7, 4, 0),
(10, 10, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `docgia`
--

CREATE TABLE `docgia` (
  `MaDocGia` int(11) NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `GioiTinh` varchar(50) NOT NULL,
  `SDT` varchar(50) NOT NULL,
  `Gmail` varchar(255) NOT NULL,
  `Quyen` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `docgia`
--

INSERT INTO `docgia` (`MaDocGia`, `Ho`, `Ten`, `GioiTinh`, `SDT`, `Gmail`, `Quyen`) VALUES
(1, 'Nguyễn Thanh', 'Trúc', 'Nữ', '0648254837', 'tructruc@gmail.com', 1),
(2, 'Võ Đăng', 'Khoa', 'Nam', '0987654321', 'skt1top1server@gmail.com', 1),
(3, 'Nguyễn', 'Tuân', 'Nam', '0896745231', 'tuannguyen11022003@gmail.com', 1),
(4, 'Tiến', 'Cường', 'Nam', '0916707058\r\n', 'langvuong12345@gmail.com', 1),
(5, 'Lê Tạ Nguyệt', 'Minh', 'Nữ', '0787520167', 'nguyetminhcute@gmail.com', 1),
(6, 'Trịnh Hoàng', 'Tuấn', 'Nam', '0896745232', 'tht12082001@gmail.com', 1),
(7, 'Lê Hoàng', 'Nhật', 'Nam', '0932596952\r ', 'lehoangnhat10a07@gmail.com', 0),
(13, 'dcs', 'qưdsc', 'Nam', '1234567890', 'wsadfcxz@gmail.com', 0),
(14, 'ewds', 'ewfdc', 'Nam', '43512345678', 'cuong@gmail.com', 0),
(15, 'Cali khat', 'nuoc', 'Nam', '0945675183', 'manhnvl@gmail.com', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `kesach`
--

CREATE TABLE `kesach` (
  `MaKe` int(11) NOT NULL,
  `Khu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `kesach`
--

INSERT INTO `kesach` (`MaKe`, `Khu`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 2),
(5, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khuvuc`
--

CREATE TABLE `khuvuc` (
  `Khu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khuvuc`
--

INSERT INTO `khuvuc` (`Khu`) VALUES
(1),
(2),
(3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisach`
--

CREATE TABLE `loaisach` (
  `MaLoai` int(11) NOT NULL,
  `TenLoai` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisach`
--

INSERT INTO `loaisach` (`MaLoai`, `TenLoai`) VALUES
(1, 'Truyện'),
(2, 'Tiểu thuyết'),
(3, 'Văn học tự sự'),
(4, 'Văn học Việt Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `MaNhanVien` int(11) NOT NULL,
  `Ho` varchar(50) NOT NULL,
  `Ten` varchar(50) NOT NULL,
  `GioiTinh` varchar(50) NOT NULL,
  `SDT` varchar(50) NOT NULL,
  `ChucVu` varchar(50) NOT NULL,
  `Gmail` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`MaNhanVien`, `Ho`, `Ten`, `GioiTinh`, `SDT`, `ChucVu`, `Gmail`) VALUES
(1, 'Tiến', 'Cường', 'Nam', '0962385165', 'admin', 'cuongcanlop7a@gmail.com'),
(2, 'Lang', 'Vương', 'Nam', '0962385165', 'manage', 'cuongcaotien9a@gmail.com'),
(3, 'Nguyễn Hoàng Thanh', 'Phương', 'Nữ', '0123456789', 'staff', 'phuongthanh12345@gmail.com'),
(4, 'Huỳnh Thị Tuyết', 'Nhung', 'Nữ', '0945253645', 'staff', 'nhungnhinhanh123@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nxb`
--

CREATE TABLE `nxb` (
  `MaNXB` int(11) NOT NULL,
  `TenNXB` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nxb`
--

INSERT INTO `nxb` (`MaNXB`, `TenNXB`) VALUES
(1, 'Kim Đồng'),
(2, 'Phụ Nữ'),
(3, 'Chính trị quốc gia'),
(4, 'Trẻ'),
(5, 'Văn học');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phanquyen`
--

CREATE TABLE `phanquyen` (
  `Quyen` varchar(50) NOT NULL,
  `QLSach` int(1) NOT NULL,
  `QLNhanVien` int(1) NOT NULL,
  `QLDocGia` int(1) NOT NULL,
  `QLNhapSach` int(1) NOT NULL,
  `ThongKe` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phanquyen`
--

INSERT INTO `phanquyen` (`Quyen`, `QLSach`, `QLNhanVien`, `QLDocGia`, `QLNhapSach`, `ThongKe`) VALUES
('admin', 1, 1, 1, 1, 1),
('default', 0, 0, 0, 0, 0),
('manage', 1, 0, 1, 1, 0),
('staff', 1, 0, 1, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phansach`
--

CREATE TABLE `phansach` (
  `MaPhanSach` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `TrangThai` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phansach`
--

INSERT INTO `phansach` (`MaPhanSach`, `MaSach`, `TrangThai`) VALUES
(1, 1, 'tốt'),
(2, 1, 'tốt'),
(3, 1, 'tốt'),
(4, 1, 'tốt'),
(5, 1, 'tốt'),
(6, 1, 'tốt'),
(7, 1, 'tốt'),
(8, 1, 'tốt'),
(9, 1, 'tốt'),
(10, 1, 'tốt'),
(1, 2, 'tốt'),
(2, 2, 'tốt'),
(3, 2, 'tốt'),
(4, 2, 'tốt'),
(5, 2, 'tốt'),
(6, 2, 'tốt'),
(7, 2, 'tốt'),
(8, 2, 'tốt'),
(9, 2, 'tốt'),
(10, 2, 'tốt'),
(1, 3, 'tốt'),
(2, 3, 'tốt'),
(3, 3, 'tốt'),
(4, 3, 'tốt'),
(5, 3, 'tốt'),
(6, 3, 'tốt'),
(7, 3, 'tốt'),
(8, 3, 'tốt'),
(9, 3, 'tốt'),
(10, 3, 'tốt'),
(11, 3, 'tốt'),
(1, 4, 'tốt'),
(2, 4, 'tốt'),
(3, 4, 'tốt'),
(4, 4, 'tốt'),
(5, 4, 'tốt'),
(6, 4, 'tốt'),
(7, 4, 'tốt'),
(1, 5, 'tốt'),
(2, 5, 'tốt'),
(3, 5, 'tốt'),
(4, 5, 'tốt'),
(5, 5, 'tốt'),
(6, 5, 'tốt'),
(7, 5, 'tốt'),
(8, 5, 'tốt'),
(9, 5, 'tốt'),
(10, 5, 'tốt'),
(11, 5, 'tốt'),
(1, 6, 'tốt'),
(2, 6, 'tốt'),
(3, 6, 'tốt'),
(4, 6, 'tốt'),
(5, 6, 'tốt'),
(6, 6, 'tốt'),
(7, 6, 'tốt'),
(8, 6, 'tốt'),
(9, 6, 'tốt'),
(10, 6, 'tốt'),
(1, 7, 'tốt'),
(2, 7, 'tốt'),
(3, 7, 'tốt'),
(4, 7, 'tốt'),
(5, 7, 'tốt'),
(6, 7, 'tốt'),
(7, 7, 'tốt'),
(8, 7, 'tốt'),
(9, 7, 'tốt'),
(10, 7, 'tốt'),
(1, 8, 'tốt'),
(2, 8, 'tốt'),
(3, 8, 'tốt'),
(4, 8, 'tốt'),
(5, 8, 'tốt'),
(6, 8, 'tốt'),
(7, 8, 'tốt'),
(8, 8, 'tốt'),
(9, 8, 'tốt'),
(1, 9, 'tốt'),
(2, 9, 'tốt'),
(3, 9, 'tốt'),
(4, 9, 'tốt'),
(5, 9, 'tốt'),
(6, 9, 'tốt'),
(7, 9, 'tốt'),
(8, 9, 'tốt'),
(9, 9, 'tốt'),
(1, 10, 'tốt'),
(2, 10, 'tốt'),
(3, 10, 'tốt'),
(4, 10, 'tốt'),
(5, 10, 'tốt'),
(6, 10, 'tốt'),
(7, 10, 'tốt'),
(8, 10, 'tốt'),
(9, 10, 'tốt'),
(10, 10, 'tốt'),
(1, 11, 'tốt'),
(2, 11, 'tốt'),
(3, 11, 'tốt'),
(4, 11, 'tốt'),
(5, 11, 'tốt'),
(6, 11, 'tốt'),
(7, 11, 'tốt'),
(8, 11, 'tốt'),
(9, 11, 'tốt'),
(1, 12, 'tốt'),
(2, 12, 'tốt'),
(3, 12, 'tốt'),
(4, 12, 'tốt'),
(5, 12, 'tốt'),
(6, 12, 'tốt'),
(7, 12, 'tốt'),
(8, 12, 'tốt'),
(1, 13, 'tốt'),
(2, 13, 'tốt'),
(3, 13, 'tốt'),
(4, 13, 'tốt'),
(5, 13, 'tốt'),
(6, 13, 'tốt'),
(7, 13, 'tốt'),
(8, 13, 'tốt'),
(9, 13, 'tốt'),
(1, 14, 'tốt'),
(2, 14, 'tốt'),
(3, 14, 'tốt'),
(4, 14, 'tốt'),
(5, 14, 'tốt'),
(6, 14, 'tốt'),
(7, 14, 'tốt'),
(8, 14, 'tốt'),
(9, 14, 'tốt'),
(1, 15, 'tốt'),
(2, 15, 'tốt'),
(3, 15, 'tốt'),
(4, 15, 'tốt'),
(5, 15, 'tốt'),
(6, 15, 'tốt'),
(7, 15, 'tốt'),
(8, 15, 'tốt'),
(9, 15, 'tốt'),
(10, 15, 'tốt'),
(1, 16, 'tốt'),
(2, 16, 'tốt'),
(3, 16, 'tốt'),
(4, 16, 'tốt'),
(5, 16, 'tốt'),
(6, 16, 'tốt'),
(7, 16, 'tốt'),
(8, 16, 'tốt'),
(9, 16, 'tốt'),
(1, 17, 'tốt'),
(2, 17, 'tốt'),
(3, 17, 'tốt'),
(4, 17, 'tốt'),
(5, 17, 'tốt'),
(6, 17, 'tốt'),
(7, 17, 'tốt'),
(8, 17, 'tốt'),
(9, 17, 'tốt'),
(1, 18, 'tốt'),
(2, 18, 'tốt'),
(3, 18, 'tốt'),
(4, 18, 'tốt'),
(5, 18, 'tốt'),
(6, 18, 'tốt'),
(7, 18, 'tốt'),
(8, 18, 'tốt'),
(9, 18, 'tốt'),
(1, 19, 'tốt'),
(2, 19, 'tốt'),
(3, 19, 'tốt'),
(4, 19, 'tốt'),
(5, 19, 'tốt'),
(6, 19, 'tốt'),
(7, 19, 'tốt'),
(8, 19, 'tốt'),
(9, 19, 'tốt'),
(10, 19, 'tốt'),
(1, 20, 'tốt'),
(2, 20, 'tốt'),
(3, 20, 'tốt'),
(4, 20, 'tốt'),
(5, 20, 'tốt'),
(6, 20, 'tốt'),
(7, 20, 'tốt'),
(8, 20, 'tốt'),
(9, 20, 'tốt'),
(10, 20, 'tốt'),
(11, 1, 'tốt');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieumuon`
--

CREATE TABLE `phieumuon` (
  `MaPhieuMuon` int(11) NOT NULL,
  `MaDocGia` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `NgayMuon` date NOT NULL,
  `NgayTra` date NOT NULL,
  `TongTienMuon` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieumuon`
--

INSERT INTO `phieumuon` (`MaPhieuMuon`, `MaDocGia`, `MaNhanVien`, `NgayMuon`, `NgayTra`, `TongTienMuon`) VALUES
(1, 1, 1, '2024-03-20', '2024-04-04', 10000),
(2, 1, 2, '2024-03-30', '2024-04-19', 24000),
(3, 2, 2, '2024-03-15', '2024-03-30', 10000),
(4, 5, 2, '2024-04-02', '2024-04-22', 16100),
(5, 6, 1, '2024-05-15', '2024-06-04', 19700),
(6, 4, 4, '2024-05-15', '2024-06-04', 20000),
(7, 5, 1, '2024-05-19', '2024-06-08', 50500),
(8, 6, 1, '2024-05-19', '2024-06-08', 19900),
(10, 2, 1, '2024-05-19', '2024-06-08', 13700),
(11, 1, 1, '2024-09-26', '2024-10-16', 1500);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieunhap`
--

CREATE TABLE `phieunhap` (
  `MaPhieuNhap` int(11) NOT NULL,
  `MaNXB` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `NgayLap` date NOT NULL,
  `TongTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieunhap`
--

INSERT INTO `phieunhap` (`MaPhieuNhap`, `MaNXB`, `MaNhanVien`, `NgayLap`, `TongTien`) VALUES
(1, 1, 1, '2024-01-20', 1470000),
(2, 1, 1, '2024-05-18', 85000),
(3, 2, 1, '2024-05-18', 15000),
(4, 1, 1, '2024-05-18', 15000),
(5, 1, 1, '2024-05-18', 156000),
(6, 5, 1, '2024-05-19', 1870000),
(7, 1, 1, '2024-09-26', 15000),
(8, 1, 1, '2024-09-26', 15000),
(9, 1, 1, '2024-09-26', 15000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuphat`
--

CREATE TABLE `phieuphat` (
  `MaPhieuPhat` int(11) NOT NULL,
  `MaPhieuTra` int(11) NOT NULL,
  `MaDocGia` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `ThanhTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieuphat`
--

INSERT INTO `phieuphat` (`MaPhieuPhat`, `MaPhieuTra`, `MaDocGia`, `MaNhanVien`, `ThanhTien`) VALUES
(1, 1, 1, 1, 1500);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieutra`
--

CREATE TABLE `phieutra` (
  `MaPhieuTra` int(11) NOT NULL,
  `MaPhieuMuon` int(11) NOT NULL,
  `MaDocGia` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `NgayTraThuc` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieutra`
--

INSERT INTO `phieutra` (`MaPhieuTra`, `MaPhieuMuon`, `MaDocGia`, `MaNhanVien`, `NgayTraThuc`) VALUES
(1, 4, 5, 2, '2024-04-17'),
(2, 2, 1, 3, '2024-05-02'),
(3, 3, 2, 1, '2024-05-15'),
(4, 7, 5, 1, '2024-05-19'),
(5, 6, 4, 1, '2024-05-19'),
(6, 8, 6, 1, '2024-05-19'),
(7, 10, 2, 1, '2024-05-19'),
(10, 5, 6, 1, '2024-05-20');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

CREATE TABLE `sach` (
  `MaSach` int(11) NOT NULL,
  `MaLoai` int(11) NOT NULL,
  `MaTacGia` int(11) NOT NULL,
  `TenSach` varchar(50) NOT NULL,
  `GiaSach` int(50) NOT NULL,
  `HinhAnh` varchar(255) NOT NULL,
  `SoLuong` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`MaSach`, `MaLoai`, `MaTacGia`, `TenSach`, `GiaSach`, `HinhAnh`, `SoLuong`) VALUES
(1, 1, 1, 'Trạng Quỳnh tập 1', 15000, 'trangQuynh1.png', 10),
(2, 1, 1, 'Trạng Quỳnh tập 2', 15000, 'trangQuynh2.png', 10),
(3, 3, 2, 'Nhật ký trong tù', 52000, 'nhatKiTrongTu.png', 11),
(4, 1, 3, 'Tôi là Bêtô', 85000, 'toiLaBeTo.png', 7),
(5, 1, 1, 'Trạng Quỳnh tập 3', 15000, 'trangQuynh3.png', 11),
(6, 4, 5, 'I am Đàn bà', 99000, 'iAmDanBa.png', 10),
(7, 2, 4, 'The Lord of the Rings', 210000, 'theLordsOfTheRings.png', 10),
(8, 2, 6, 'Nhựa cây', 118000, 'nhuaCay.png', 9),
(9, 1, 7, 'Your name', 80000, 'yourName.png', 9),
(10, 1, 8, 'Thiên sứ nhà bên', 79000, 'thienSuNhaKeBen.png', 10),
(11, 3, 10, 'Điểm số không phải là tất cả', 81000, 'diemSoKhongPhaiLaTatca.png', 9),
(12, 3, 9, 'Trung Quốc - Lịch sử kế thừa', 251000, 'TQ-lichSuKeThua.png', 8),
(13, 1, 11, 'những vụ án của sherlock holmes', 180000, 'nhungVuAnCuaSherlockHolmes.png', 9),
(14, 1, 3, 'Những người hàng xóm', 200000, 'nhungNguoiHangXom.png', 9),
(15, 1, 3, 'Mắt biếc', 110000, 'matBiec.png', 10),
(16, 1, 11, 'Sherlock Holmes quyển 1', 115000, 'SherlockHolmes1.png', 9),
(17, 1, 11, 'Sherlock Holmes quyển 2', 110000, 'SherlockHolmes2.png', 9),
(18, 1, 11, 'Sherlock Holmes quyển 3', 100000, 'SherlockHolmes3.png', 9),
(19, 1, 3, 'Kính vạn hoa', 130000, 'kinhVanHoa.png', 10),
(20, 2, 12, 'Kẻ khôn đi lối khác', 170000, 'keKhonDiLoiKhac.png', 10);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sachquanly`
--

CREATE TABLE `sachquanly` (
  `MaSach` int(11) NOT NULL,
  `MaKe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `sachquanly`
--

INSERT INTO `sachquanly` (`MaSach`, `MaKe`) VALUES
(1, 1),
(2, 1),
(3, 1),
(5, 5),
(6, 2),
(7, 2),
(8, 3),
(9, 4),
(10, 5),
(11, 3),
(14, 4),
(15, 4),
(18, 5),
(20, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tacgia`
--

CREATE TABLE `tacgia` (
  `MaTacGia` int(11) NOT NULL,
  `TenTacGia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `tacgia`
--

INSERT INTO `tacgia` (`MaTacGia`, `TenTacGia`) VALUES
(1, 'Kim Khánh'),
(2, 'Hồ Chí Minh'),
(3, 'Nguyễn Nhật Ánh'),
(4, 'JRR Tolkien'),
(5, 'Y Ban'),
(6, 'Ane Riel'),
(7, 'Kanoh Arata'),
(8, ' Saekisan'),
(9, 'Nguyễn Ngọc Trường'),
(10, 'Chu Chí Minh'),
(11, 'Conan Doly'),
(12, 'Alex Banayan');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `MaNhanVien` int(11) NOT NULL,
  `TenDangNhap` varchar(50) NOT NULL,
  `MatKhau` varchar(50) NOT NULL,
  `Quyen` varchar(50) NOT NULL,
  `TrangThai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`MaNhanVien`, `TenDangNhap`, `MatKhau`, `Quyen`, `TrangThai`) VALUES
(1, 'langvuong', 'langvuong', 'admin', 1),
(2, 'quanly1', 'quanly1', 'manage', 1),
(3, 'nhanvien1', 'nhanvien1', 'staff', 1),
(4, 'nhanvien2', 'nhanvien2', 'staff', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ctphieumuon`
--
ALTER TABLE `ctphieumuon`
  ADD KEY `MaPhieuMuon` (`MaPhieuMuon`,`MaSach`),
  ADD KEY `ctphieumuon_ibfk_2` (`MaSach`);

--
-- Chỉ mục cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD KEY `fk_ctphieunhap_phieunhap` (`MaPhieuNhap`),
  ADD KEY `fk_ctphieunhap_sach` (`MaSach`);

--
-- Chỉ mục cho bảng `ctphieutra`
--
ALTER TABLE `ctphieutra`
  ADD KEY `fk_ctphieutra_phieutra` (`MaPhieuTra`),
  ADD KEY `fk_ctphieutra_sach` (`MaSach`);

--
-- Chỉ mục cho bảng `docgia`
--
ALTER TABLE `docgia`
  ADD PRIMARY KEY (`MaDocGia`);

--
-- Chỉ mục cho bảng `docgia`
--
ALTER TABLE `sach`
    ADD PRIMARY KEY (`MaSach`);

--
-- Chỉ mục cho bảng `kesach`
--
ALTER TABLE `kesach`
  ADD PRIMARY KEY (`MaKe`);

--
-- Chỉ mục cho bảng `khuvuc`
--
ALTER TABLE `khuvuc`
  ADD PRIMARY KEY (`Khu`);

--
-- Chỉ mục cho bảng `loaisach`
--
ALTER TABLE `loaisach`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`MaNhanVien`);

--
-- Chỉ mục cho bảng `nxb`
--
ALTER TABLE `nxb`
  ADD PRIMARY KEY (`MaNXB`);

--
-- Chỉ mục cho bảng `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`Quyen`);

--
-- Chỉ mục cho bảng `phieumuon`
--
ALTER TABLE `phieumuon`
  ADD PRIMARY KEY (`MaPhieuMuon`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPhieuNhap`);

--
-- Chỉ mục cho bảng `phieuphat`
--
ALTER TABLE `phieuphat`
  ADD PRIMARY KEY (`MaPhieuPhat`);

--
-- Chỉ mục cho bảng `phieutra`
--
ALTER TABLE `phieutra`
  ADD PRIMARY KEY (`MaPhieuTra`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`MaTacGia`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaNhanVien`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
