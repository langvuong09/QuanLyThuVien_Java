-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 12, 2024 lúc 11:47 AM
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
  `ThanhTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieumuon`
--

INSERT INTO `ctphieumuon` (`MaPhieuMuon`, `MaSach`, `ThanhTien`) VALUES
(1, 6, 10000),
(2, 7, 21000),
(2, 1, 1500),
(2, 2, 1500),
(3, 4, 8500),
(3, 5, 1500),
(4, 9, 8000),
(4, 11, 8100),
(5, 10, 7900),
(5, 8, 11800),
(6, 14, 20000),
(7, 13, 18000),
(7, 16, 11500),
(7, 17, 11000),
(7, 18, 10000),
(8, 11, 8100),
(8, 8, 11800),
(10, 3, 5200),
(10, 4, 8500);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `MaPhieuNhap` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `Gia` int(50) NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `ThanhTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`MaPhieuNhap`, `MaSach`, `Gia`, `SoLuong`, `ThanhTien`) VALUES
(1, 7, 210000, 7, 1470000),
(2, 4, 85000, 1, 85000),
(3, 5, 15000, 1, 15000),
(4, 2, 15000, 1, 15000),
(5, 3, 52000, 3, 156000),
(6, 19, 130000, 5, 650000),
(6, 20, 170000, 7, 1190000),
(6, 5, 15000, 2, 30000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctphieutra`
--

CREATE TABLE `ctphieutra` (
  `MaPhieuTra` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `ctphieutra`
--

INSERT INTO `ctphieutra` (`MaPhieuTra`, `MaSach`) VALUES
(1, 9),
(1, 11),
(2, 1),
(2, 2),
(3, 4),
(4, 13),
(4, 16),
(5, 14),
(6, 11),
(6, 8),
(7, 3),
(7, 4),
(10, 10);

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
(10, 2, 1, '2024-05-19', '2024-06-08', 13700);

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
(6, 5, 1, '2024-05-19', 1870000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieuphat`
--

CREATE TABLE `phieuphat` (
  `MaPhieuPhat` int(11) NOT NULL,
  `MaPhieuTra` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `MaDocGia` int(11) NOT NULL,
  `MaNhanVien` int(11) NOT NULL,
  `LyDo` varchar(100) NOT NULL,
  `ThanhTien` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phieuphat`
--

INSERT INTO `phieuphat` (`MaPhieuPhat`, `MaPhieuTra`, `MaSach`, `MaDocGia`, `MaNhanVien`, `LyDo`, `ThanhTien`) VALUES
(1, 1, 9, 5, 1, 'Ướt sách + Trả muộn 9 ngày', 88000),
(2, 2, 2, 1, 1, 'Mất trang + Trả muộn 7 ngày', 9000),
(3, 2, 2, 1, 1, 'Rách sách + Trả muộn 11 ngày', 3750),
(4, 3, 4, 2, 1, 'Rách sách + Trả muộn 31 ngày', 21250);

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
  ADD PRIMARY KEY (`MaPhieuMuon`),
  ADD KEY `MaDocGia` (`MaDocGia`,`MaNhanVien`),
  ADD KEY `MaNhanVien` (`MaNhanVien`);

--
-- Chỉ mục cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`MaPhieuNhap`),
  ADD KEY `fk_phieunhap_nxb` (`MaNXB`),
  ADD KEY `fk_phieunhap_nhanvien` (`MaNhanVien`);

--
-- Chỉ mục cho bảng `phieuphat`
--
ALTER TABLE `phieuphat`
  ADD PRIMARY KEY (`MaPhieuPhat`),
  ADD KEY `fk_phieuphat_phieutra` (`MaPhieuTra`),
  ADD KEY `fk_phieuphat_sach` (`MaSach`),
  ADD KEY `fk_phieuphat_nhanvien` (`MaNhanVien`),
  ADD KEY `fk_phieuphat_docgia` (`MaDocGia`);

--
-- Chỉ mục cho bảng `phieutra`
--
ALTER TABLE `phieutra`
  ADD PRIMARY KEY (`MaPhieuTra`),
  ADD KEY `fk_phieutra_phieumuon` (`MaPhieuMuon`),
  ADD KEY `fk_phieutra_nhanvien` (`MaNhanVien`),
  ADD KEY `fk_phieutra_docgia` (`MaDocGia`);

--
-- Chỉ mục cho bảng `sach`
--
ALTER TABLE `sach`
  ADD PRIMARY KEY (`MaSach`),
  ADD KEY `MaLoai` (`MaLoai`),
  ADD KEY `sach_ibfk_3` (`MaTacGia`);

--
-- Chỉ mục cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  ADD PRIMARY KEY (`MaTacGia`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`MaNhanVien`),
  ADD KEY `MaNhanVien` (`MaNhanVien`),
  ADD KEY `fk_taikhoan_phanquyen` (`Quyen`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `docgia`
--
ALTER TABLE `docgia`
  MODIFY `MaDocGia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT cho bảng `loaisach`
--
ALTER TABLE `loaisach`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `MaNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT cho bảng `nxb`
--
ALTER TABLE `nxb`
  MODIFY `MaNXB` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `phieumuon`
--
ALTER TABLE `phieumuon`
  MODIFY `MaPhieuMuon` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `phieuphat`
--
ALTER TABLE `phieuphat`
  MODIFY `MaPhieuPhat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `phieutra`
--
ALTER TABLE `phieutra`
  MODIFY `MaPhieuTra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `sach`
--
ALTER TABLE `sach`
  MODIFY `MaSach` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT cho bảng `tacgia`
--
ALTER TABLE `tacgia`
  MODIFY `MaTacGia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctphieumuon`
--
ALTER TABLE `ctphieumuon`
  ADD CONSTRAINT `ctphieumuon_ibfk_1` FOREIGN KEY (`MaPhieuMuon`) REFERENCES `phieumuon` (`MaPhieuMuon`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ctphieumuon_ibfk_2` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD CONSTRAINT `fk_ctphieunhap_phieunhap` FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhap` (`MaPhieuNhap`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ctphieunhap_sach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `ctphieutra`
--
ALTER TABLE `ctphieutra`
  ADD CONSTRAINT `fk_ctphieutra_phieutra` FOREIGN KEY (`MaPhieuTra`) REFERENCES `phieutra` (`MaPhieuTra`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ctphieutra_sach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `docgia`
--
ALTER TABLE `docgia`
  ADD CONSTRAINT `docgia_ibfk_1` FOREIGN KEY (`MaDocGia`) REFERENCES `phieumuon` (`MaDocGia`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `taikhoan` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieumuon`
--
ALTER TABLE `phieumuon`
  ADD CONSTRAINT `fk_phieumuon_docgia` FOREIGN KEY (`MaDocGia`) REFERENCES `docgia` (`MaDocGia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `phieumuon_ibfk_1` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `fk_phieunhap_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieunhap_nxb` FOREIGN KEY (`MaNXB`) REFERENCES `nxb` (`MaNXB`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieuphat`
--
ALTER TABLE `phieuphat`
  ADD CONSTRAINT `fk_phieuphat_docgia` FOREIGN KEY (`MaDocGia`) REFERENCES `docgia` (`MaDocGia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieuphat_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieuphat_phieutra` FOREIGN KEY (`MaPhieuTra`) REFERENCES `phieutra` (`MaPhieuTra`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieuphat_sach` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `phieutra`
--
ALTER TABLE `phieutra`
  ADD CONSTRAINT `fk_phieutra_docgia` FOREIGN KEY (`MaDocGia`) REFERENCES `docgia` (`MaDocGia`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieutra_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_phieutra_phieumuon` FOREIGN KEY (`MaPhieuMuon`) REFERENCES `phieumuon` (`MaPhieuMuon`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `sach`
--
ALTER TABLE `sach`
  ADD CONSTRAINT `sach_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaisach` (`MaLoai`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sach_ibfk_3` FOREIGN KEY (`MaTacGia`) REFERENCES `tacgia` (`MaTacGia`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `fk_taikhoan_nhanvien` FOREIGN KEY (`MaNhanVien`) REFERENCES `nhanvien` (`MaNhanVien`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_taikhoan_phanquyen` FOREIGN KEY (`Quyen`) REFERENCES `phanquyen` (`Quyen`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
