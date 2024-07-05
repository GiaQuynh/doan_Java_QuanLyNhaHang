use master
go
Create database QL_NhaHang2
go
use QL_NhaHang2
go
drop database QL_NhaHang

CREATE TABLE NhaCungCapNguyenLieu (
    idNhaCungCap int IDENTITY(1,1) PRIMARY KEY,
    tenNCC nvarchar(200),
    diaChi nvarchar(255),
    soDienThoai nvarchar(20),
    email nvarchar(100),      
    chuthichnhacungcap nvarchar(255),
	
);

-- Bảng Nhân viên
CREATE TABLE NhanVien (
    Staff_id INT IDENTITY PRIMARY KEY,
	ten NVARCHAR(100),
	NgaySinh DATE,
	email VARCHAR(100),
	socancuoccd VARCHAR(20),
	so_dien_thoai VARCHAR(15)
);

CREATE TABLE LichLamViec (
	MaLichLam INT IDENTITY PRIMARY KEY,
	staff_id INT,
	ten NVARCHAR(100),
	NgayLam DATE,
	GioBatDau TIME,
	GioKetThuc TIME
	FOREIGN KEY (staff_id) REFERENCES NhanVien(staff_id)
);
CREATE TABLE TinhLuong (
	MaLuong INT IDENTITY PRIMARY KEY,
	staff_id INT,
	SoGioLam DECIMAL(18, 2),
	LuongCoBan DECIMAL(18, 2),
	Tongluong DECIMAL(18, 2)
	FOREIGN KEY (staff_id) REFERENCES NhanVien(staff_id)
);

CREATE TABLE Account (
    account_id INT identity PRIMARY KEY ,
	idNhanVien int ,
    username VARCHAR(100) UNIQUE,
    password_hash VARCHAR(255),
    role VARCHAR(50),
	FullName NVARCHAR(255),
	constraint fk_account_nhanvien foreign key (idNhanVien) references NhanVien(staff_id)
);


CREATE TABLE DanhMucMonAn (
    danh_muc_id INT identity PRIMARY KEY ,
    ten_danh_muc NVARCHAR(100)
);

-- Bảng MonAn (Menu)
CREATE TABLE MonAn (
    monan_id INT identity PRIMARY KEY ,
    ten_mon NVARCHAR(255),
    danh_muc_id INT,
    gia DECIMAL(18, 2),
   mo_ta nvarchar(255),
    hinh_anh nVARCHAR(255),
    FOREIGN KEY (danh_muc_id) REFERENCES DanhMucMonAn(danh_muc_id) ON DELETE CASCADE
);


-- Bảng Đặt bàn
CREATE TABLE Ban (
    id_ban INT identity PRIMARY KEY ,
    ten_ban text,
    ngay_dat DATETIME null,
    trang_thai NVARCHAR(100) DEFAULT N'Trống',
    ghi_chu NVARCHAR(255)
);

-- Bảng Đơn hàng
CREATE TABLE DonHang (
    order_id INT identity PRIMARY KEY  ,
    id_ban INT,
	gia_giam decimal(18,2)  DEFAULT 0,
	ngay_dat DATETIME null,
	nguoiThucHien nvarchar(200),
	TongBill DECIMAL(18, 2) ,
	tongtien  DECIMAL(18, 2),
	 trang_thai_thanhtoan VARCHAR(50),
    FOREIGN KEY (id_ban) REFERENCES Ban(id_ban)
);



-- Bảng ChiTietDonHang (OrderDetail)
CREATE TABLE ChiTietDonHang (
    detail_id INT identity PRIMARY KEY ,
    order_id INT,
	id_monan int,
	tenmonan nvarchar(255),
    so_luong INT,
	dongia DECIMAL(18, 2),
    Thanhtien DECIMAL(18, 2),
    FOREIGN KEY (order_id) REFERENCES DonHang(order_id) ON DELETE CASCADE,
    FOREIGN KEY (id_monan) REFERENCES MonAn(monan_id)
);

---- Bảng Khách hàng
--CREATE TABLE KhachHang (
--    customer_id INT identity PRIMARY KEY ,
--    so_dien_thoai VARCHAR(15)  
--);


Create table NguyenLieu(
	idnguyenlieu int identity primary key,
	tennguyenlieu nvarchar(255),
	donvitinh nvarchar(20)
);

-- Bảng Quản lý kho
CREATE TABLE Kho (
    stock_id INT identity PRIMARY KEY  ,
	idnguyenlieu int ,
    ten_nguyenlieu NVARCHAR(255),
    so_luong_ton float,
	donvitinh nvarchar(100),
	constraint fk_kho_nguyenlieu foreign key (idnguyenlieu) references NguyenLieu(idnguyenlieu) 
);



-- Thiết lập các nguyên liệu mặc định cho từng món ăn thuận tiện cho việc thêm món
CREATE TABLE MonAn_NguyenLieu_Default (
    monan_id INT,
    nguyenlieu_id INT,
	tennguyenlieu nvarchar(255),
	donvitinh NVARCHAR(20),
    soluong float,
    PRIMARY KEY (monan_id, nguyenlieu_id),
    FOREIGN KEY (monan_id) REFERENCES MonAn(monan_id) ON DELETE CASCADE,
    FOREIGN KEY (nguyenlieu_id) REFERENCES NguyenLieu(idnguyenlieu) ON DELETE CASCADE
);

CREATE TABLE KetCaBaoCao (
    idbaocao INT identity PRIMARY KEY ,
	nhanvienketca nvarchar(255) ,
	gioVoCa date,
	TienDauCa decimal(18,2),
	TongSoHoaDon decimal(18,2) ,
	TienHang decimal(18,2) ,
	PhiDichVu decimal(18,2),
	TienGiamGia decimal(18,2),
	DoanhThu decimal(18,2),
	TienCuoiCa decimal(18,2),
	GioXuatKetCa date,
);
CREATE TABLE PhieuNhap (
    id_phieu_nhap INT identity PRIMARY KEY ,
	idNhaCungCap int,
	 TenNhaCungCap nvarchar(200),
	nguoiThucHien nvarchar(200),
    ten_phieu NVARCHAR(255),
    tongtiennhap decimal(18,2),
    ngay_nhap_nguyenlieu DATE,
	 constraint fk_phieuNhap_nhacungcap foreign key (idNhaCungCap) references NhaCungCapNguyenLieu(idNhaCungCap)
);


CREATE TABLE ChitietPhieuNhap (
    id_chitietphieu INT identity PRIMARY KEY ,
	id_phieu_nhap int,
	idnguyenlieu int, 
	tennguyenlieu nvarchar(255),
    soluongnhap float,
	dongia decimal(18,2),
	thanhtien decimal(18,2),
	CONSTRAINT fk_phieuNhap_chitietphieu FOREIGN KEY (id_phieu_nhap) REFERENCES PhieuNhap(id_phieu_nhap) ON DELETE CASCADE,
	constraint Fk_nguyenlieu_ctpn foreign key(idnguyenlieu) references NguyenLieu(idnguyenlieu)
);



INSERT INTO NhaCungCapNguyenLieu (tenNCC, diaChi, soDienThoai, email, chuthichnhacungcap)
VALUES 
    (N'Vựa rau Bình Dương', N'168/42 DX006, KP. 8, P. Phú Mỹ, TP. Thủ Dầu Một, Bình Dương, Việt Nam', '0123456789', 'ncc_a@example.com', 'Ghi chú về nhà cung cấp A'),
    (N'Kho thịt Tân Phú', N'2B Đ. TMT 13, Trung Mỹ Tây, Quận 12, Thành phố Hồ Chí Minh', '0987654321', 'ncc_b@example.com', 'Ghi chú về nhà cung cấp B'),
    (N'Hải sản Vũng Tàu', N'Đường Nguyễn Biểu, phường Thắng Tam, TP. Vũng Tàu, Bà Rịa - Vũng Tàu', '0365478912', 'ncc_c@example.com', 'Ghi chú về nhà cung cấp C');

-- Thêm dữ liệu vào bảng Ban
INSERT INTO Ban (ten_ban, ghi_chu)
VALUES 
    ('Bàn 1', N'Ghi chú cho bàn số 1'),
    ('Bàn 2', N'Ghi chú cho bàn số 2');
	INSERT INTO Ban (ten_ban, ghi_chu)
VALUES 
    ('Bàn 3',  N'Ghi chú cho bàn số 3');
	INSERT INTO Ban (ten_ban, ghi_chu)VALUES ('Bàn 4', N'Ghi chú cho bàn số 4');
	INSERT INTO Ban (ten_ban, ghi_chu)VALUES 
    ('Bàn 5', N'Ghi chú cho bàn số 5'),
	 ('Bàn 6', N'Ghi chú cho bàn số 6'),
	  ('Bàn 7', N'Ghi chú cho bàn số 7'),
	   ('Bàn 8', N'Ghi chú cho bàn số 8'),
	    ('Bàn 9', N'Ghi chú cho bàn số 9'),
		 ('Bàn 10', N'Ghi chú cho bàn số 10');
INSERT INTO Ban (ten_ban, ghi_chu)VALUES 
('Bàn 11', N'Ghi chú cho bàn số 11'),
('Bàn 12', N'Ghi chú cho bàn số 12'),
('Bàn 13', N'Ghi chú cho bàn số 13'),
('Bàn 14', N'Ghi chú cho bàn số 14'),
('Bàn 15', N'Ghi chú cho bàn số 15'),
('Bàn 16', N'Ghi chú cho bàn số 16'),
('Bàn 17', N'Ghi chú cho bàn số 17'),
('Bàn 18', N'Ghi chú cho bàn số 18'),
('Bàn 19', N'Ghi chú cho bàn số 19'),
('Bàn 20', N'Ghi chú cho bàn số 20');



INSERT INTO NhanVien (ten, NgaySinh, email, socancuoccd ,so_dien_thoai) values
(N'Nguyễn Văn A','09-13-2003','nguyenvana@example.com','123456789','0987654321')
INSERT INTO NhanVien (ten, NgaySinh, email, socancuoccd ,so_dien_thoai) values
(N'Trần Thị B','09-01-2003','tranthib@example.com','987654321','0123456789')


Insert into LichLamViec(staff_id, ten, NgayLam, GioBatDau, GioKetThuc) 
                     values(1,N'Nguyễn Văn A', '2024-05-01', '08:00:00', '14:00:00'),
                     (1,N'Nguyễn Văn A', '2024-05-01', '15:00:00', '22:00:00'),
                     (2,N'Trần Thị B' , '2024-05-01', '08:00:00', '16:00:00'),
                     (2,N'Trần Thị B' , '2024-05-02', '08:00:00', '14:00:00'),
                     (2,N'Trần Thị B' , '2024-05-02', '16:00:00', '22:00:00'),
                     (1,N'Nguyễn Văn A' , '2024-05-02', '10:00:00', '18:00:00');

Insert into TinhLuong(staff_id, SoGioLam, LuongCoBan, TongLuong) 
                             values(1, 0, 23.000, 0),
       (2, 0, 23.000, 0);

	  
	  

INSERT INTO Account (username, password_hash, role,FullName) 
VALUES 
('admin', 'admin', 'admin','admin')

INSERT INTO Account (username, password_hash, role,FullName,idNhanVien) 
VALUES 
('staff1', 'nhanvien123', 'staff','Nhân Viên 1',1),
('staff2', 'nhanvien123', 'staff','Nhân Viên 2',2);


--setup server
INSERT INTO DanhMucMonAn (ten_danh_muc) VALUES 
(N'Cơm trưa'), (N'Cơm tấm'), (N'Thịt'), (N'Đồ uống');

-- Thêm dữ liệu vào bảng NguyenLieu
INSERT INTO NguyenLieu (tennguyenlieu, donvitinh) VALUES 
(N'Gạo', 'kg'), (N'Thịt gà', 'kg'), (N'Thịt bò', 'kg'), (N'Cá', 'kg'), (N'Dầu ăn', 'lit'), 
(N'Muối', 'kg'), (N'Đường', 'kg'), (N'Bột mì', 'kg'), (N'Nước mắm', 'lit'), (N'Rau cải', 'kg'), 
(N'Cà chua', 'kg'), (N'Cà rốt', 'kg'), (N'Bắp cải', 'kg'), (N'Bơ', 'kg'), (N'Trứng gà', 'quả'), 
(N'Sữa', 'lit'), (N'Bia', 'lon'), (N'Rượu vang', 'chai'), (N'Cà phê', 'kg'), (N'Trà', 'kg'), 
(N'Ớt', 'kg'), (N'Hành', 'kg'), (N'Tỏi', 'kg'), (N'Gừng', 'kg'), (N'Mắc khén', 'kg'), 
(N'Nấm', 'kg'), (N'Tương', 'lit'), (N'Mì chính', 'kg'), (N'Hành tây', 'kg');

-- Thêm dữ liệu vào bảng MonAn
INSERT INTO MonAn (ten_mon, danh_muc_id, gia, mo_ta, hinh_anh) VALUES 
(N'Cơm gà', 1, 50000.00, N'Món cơm gà', 'ComGa.JPG'),
(N'Phở bò', 1, 45000.00, N'Món phở bò', 'PhoBo.JPG'),
(N'Trứng ốp-la', 1, 30000.00, N'Món trứng ốp-la', 'trung_op.jpg'),
(N'Canh rau cải', 1, 30000.00, N'Canh rau cải tươi', 'canhrau.jpg'),
(N'Cơm trắng', 1, 15000.00, N'Cơm trắng thêm', 'comtrang.jpg'),
(N'Cơm bình dân thập cẩm', 1, 30000.00, N'Cơm bình dân', 'combinhdan.jpg'),

(N'Cơm tấm sườn', 2, 35000.00, N'Cơm tấm sườn thịt heo', 'ComTam.JPG'),
(N'Cơm tấm sườn chả', 2, 35000.00, N'Cơm tấm sườn thịt heo chả', 'ComSuon.JPG'),
(N'Cơm tấm sườn trứng', 2, 35000.00, N'Cơm tấm sườn thịt heo trứng', 'ComSuonTrung.jpg'),
(N'Cơm tấm sườn lạp xưởng', 2, 35000.00, N'Cơm tấm sườn thịt heo lạp xưởng', 'ComSuonLapXuong.jpg'),

(N'Thịt gà', 3, 45000.00, N'Thịt gà nguyên con', 'thitga.jpg'),
(N'Thịt heo xào hành', 3, 45000.00, N'Thịt heo xào hành tây', 'ThitLuoc.JPG'),
(N'Thịt bò lá lốt', 3, 45000.00, N'Thịt bò ướt cuốn lá  lót', 'thitbolaluot.jpg'),

(N'Sprite', 4, 20000, N'Nước ngọt có gas', 'sprite.jpg'),
(N'Coca cola', 4, 20000, N'Nước ngọt có gas', 'cocacola.jpg'),
(N'Mirinda cam', 4, 20000, N'Nước ngọt có gas', 'mirindacam.jpg'),
(N'Pepsi', 4, 20000, N'Nước ngọt có gas', 'pepsi.jpg'),
(N'Siting', 4, 20000, N'Nước ngọt có gas', 'siting.jpg'),
(N'Mirinda nho', 4, 20000, N'Nước ngọt có gas', 'mirindanho.jpg');


-- Thêm dữ liệu vào bảng MonAn_NguyenLieu_Default
INSERT INTO MonAn_NguyenLieu_Default (monan_id, nguyenlieu_id, tennguyenlieu, donvitinh, soluong) VALUES 
-- Cơm gà
(1, 1, N'Gạo', N'kg', 0.2), 
(1, 2, N'Thịt gà', N'kg', 0.3),

-- Phở bò
(2, 3, N'Thịt bò', N'kg', 0.2), 
(2, 10, N'Rau cải', N'kg', 0.1),

-- Trứng ốp-la
(3, 15, N'Trứng gà', N'quả', 2), 
(3, 5, N'Dầu ăn', N'lit', 0.05),

-- Canh rau cải
(4, 10, N'Rau cải', N'kg', 0.3), 
(4, 11, N'Cà chua', N'kg', 0.1),

-- Cơm trắng
(5, 1, N'Gạo', N'kg', 0.2), 
(5, 6, N'Muối', N'kg', 0.05),

-- Cơm bình dân thập cẩm
(6, 1, N'Gạo', N'kg', 0.2), 
(6, 2, N'Thịt gà', N'kg', 0.3),

-- Cơm tấm sườn
(7, 1, N'Gạo', N'kg', 0.2), 
(7, 3, N'Thịt bò', N'kg', 0.3),

-- Cơm tấm sườn chả
(8, 1, N'Gạo', N'kg', 0.2), 
(8, 2, N'Thịt gà', N'kg', 0.3),

-- Cơm tấm sườn trứng
(9, 1, N'Gạo', N'kg', 0.2), 
(9, 15, N'Trứng gà', N'quả', 2),

-- Cơm tấm sườn lạp xưởng
(10, 1, N'Gạo', N'kg', 0.2), 
(10, 6, N'Muối', N'kg', 0.05),

-- Thịt gà
(11, 2, N'Thịt gà', N'kg', 1), 
(11, 5, N'Dầu ăn', N'lit', 0.1),

-- Thịt heo xào hành
(12, 2, N'Thịt gà', N'kg', 0.5), 
(12, 29, N'Hành tây', N'kg', 0.2),

-- Thịt bò lá lốt
(13, 3, N'Thịt bò', N'kg', 0.3), 
(13, 10, N'Rau cải', N'kg', 0.2),

-- Sprite
(14, 17, N'Sữa', N'lit', 0.5), 
(14, 19, N'Cà phê', N'kg', 0.1),

-- Coca cola
(15, 17, N'Sữa', N'lit', 0.5), 
(15, 19, N'Cà phê', N'kg', 0.1),

-- Mirinda cam
(16, 17, N'Sữa', N'lit', 0.5), 
(16, 19, N'Cà phê', N'kg', 0.1),

-- Pepsi
(17, 17, N'Sữa', N'lit', 0.5), 
(17, 19, N'Cà phê', N'kg', 0.1),

-- Siting
(18, 17, N'Sữa', N'lit', 0.5), 
(18, 19, N'Cà phê', N'kg', 0.1),

-- Mirinda nho
(19, 17, N'Sữa', N'lit', 0.5), 
(19, 19, N'Cà phê', N'kg', 0.1);



-- Insert data into the Kho table
INSERT INTO Kho (idnguyenlieu, ten_nguyenlieu, so_luong_ton, donvitinh) VALUES 
(1, N'Gạo', 50, 'kg'),
(2, N'Thịt gà', 30, 'kg'),
(3, N'Thịt bò', 75, 'kg'),
(4, N'Cá', 20, 'kg'),
(5, N'Dầu ăn', 60, 'lit'),
(6, N'Muối', 25, 'kg'),
(7, N'Đường', 40, 'kg'),
(8, N'Bột mì', 35, 'kg'),
(9, N'Nước mắm', 50, 'lit'),
(10, N'Rau cải', 45, 'kg'),
(11, N'Cà chua', 30, 'kg'),
(12, N'Cà rốt', 20, 'kg'),
(13, N'Bắp cải', 25, 'kg'),
(14, N'Bơ', 15, 'kg'),
(15, N'Trứng gà', 60, 'quả'),
(16, N'Sữa', 55, 'lit'),
(17, N'Bia', 100, 'lon'),
(18, N'Rượu vang', 40, 'chai'),
(19, N'Cà phê', 35, 'kg'),
(20, N'Trà', 50, 'kg'),
(21, N'Ớt', 30, 'kg'),
(22, N'Hành', 40, 'kg'),
(23, N'Tỏi', 35, 'kg'),
(24, N'Gừng', 20, 'kg'),
(25, N'Mắc khén', 15, 'kg'),
(26, N'Nấm', 25, 'kg'),
(27, N'Tương', 50, 'lit'),
(28, N'Mì chính', 45, 'kg'),
(29, N'Hành tây', 30, 'kg');

SELECT
     ChiTietDonHang."detail_id" AS ChiTietDonHang_detail_id,
     ChiTietDonHang."order_id" AS ChiTietDonHang_order_id,
     ChiTietDonHang."id_monan" AS ChiTietDonHang_id_monan,
     ChiTietDonHang."tenmonan" AS ChiTietDonHang_tenmonan,
     ChiTietDonHang."so_luong" AS ChiTietDonHang_so_luong,
     ChiTietDonHang."dongia" AS ChiTietDonHang_dongia,
     ChiTietDonHang."Thanhtien" AS ChiTietDonHang_Thanhtien,
     DonHang."order_id" AS DonHang_order_id,
     DonHang."id_ban" AS DonHang_id_ban,
     DonHang."gia_giam" AS DonHang_gia_giam,
     DonHang."ngay_dat" AS DonHang_ngay_dat,
     DonHang."nguoiThucHien" AS DonHang_nguoiThucHien,
     DonHang."TongBill" AS DonHang_TongBill,
     DonHang."tongtien" AS DonHang_tongtien,
     DonHang."trang_thai_thanhtoan" AS DonHang_trang_thai_thanhtoan
FROM
     "dbo"."DonHang" DonHang INNER JOIN "dbo"."ChiTietDonHang" ChiTietDonHang ON DonHang."order_id" = ChiTietDonHang."order_id"
WHERE DAY(DonHang."ngay_dat") = DAY('05-23-2024')



GO
Create PROCEDURE [dbo].[TinhLuongNV]
    @staff_id INT
AS
BEGIN
    DECLARE @TongSoGioLam DECIMAL(10, 2)
    DECLARE @LuongCoBan DECIMAL(10, 2)
    DECLARE @TongLuong DECIMAL(10, 2)

    -- Tính tổng số giờ làm trong một tháng
      set @TongSoGioLam = ( SELECT SUM(DATEDIFF(MINUTE, GioBatDau, GioKetThuc)) / 60.0
    FROM LichLamViec
    WHERE staff_id = @staff_id
    AND MONTH(NgayLam) = MONTH(GETDATE()))

    -- Lấy mức lương cơ bản của nhân viên từ bảng TinhLuong
    SELECT @LuongCoBan = LuongCoBan
    FROM TinhLuong
    WHERE staff_id = @staff_id

    -- Tính tổng lương
    SET @TongLuong = @LuongCoBan * @TongSoGioLam
	

    -- Cập nhật hoặc thêm mới thông tin lương vào bảng TinhLuong
    IF EXISTS (SELECT 1 FROM TinhLuong WHERE staff_id = @staff_id)
    BEGIN
        -- Nếu đã có, cập nhật tổng giờ làm và tổng lương mới
        UPDATE TinhLuong
        SET SoGioLam = @TongSoGioLam,
            TongLuong = @TongLuong
        WHERE staff_id = @staff_id
    END
    ELSE
    BEGIN
        -- Nếu chưa có, thêm mới một bản ghi vào bảng TinhLuong
        INSERT INTO TinhLuong (staff_id, SoGioLam, LuongCoBan, TongLuong)
        VALUES (@staff_id, @TongSoGioLam, @LuongCoBan, @TongLuong)
    END
END
exec TinhLuongNV '1'

--/////Báo cáo doanh thu theo tháng
SELECT 
    YEAR(GioVoCa) AS Nam,
    MONTH(GioVoCa) AS Thang,
    DATEPART(WEEK, GioVoCa) AS Tuan,
    SUM(TongSoHoaDon) AS TongSoHoaDon,
    SUM(TienHang) AS TongTienHang,
    SUM(PhiDichVu) AS TongPhiDichVu,
    SUM(TienGiamGia) AS TongTienGiamGia,
    SUM(DoanhThu) AS TongDoanhThu
FROM 
    KetCaBaoCao
WHERE
    YEAR(GioVoCa) = 2024  
    AND MONTH(GioVoCa) = 5 
GROUP BY
    YEAR(GioVoCa),
    MONTH(GioVoCa),
    DATEPART(WEEK, GioVoCa)
ORDER BY
    YEAR(GioVoCa),
    MONTH(GioVoCa),
    DATEPART(WEEK, GioVoCa);

	INSERT INTO KetCaBaoCao (nhanvienketca, gioVoCa, TienDauCa, TongSoHoaDon, TienHang, PhiDichVu, TienGiamGia, DoanhThu, TienCuoiCa, GioXuatKetCa)
VALUES ('Tên nhân viên 1', '2024-05-24 07:00:00', 100, 10, 500, 20.00, 50, 450, 600, '2024-05-24 18:00:00');
INSERT INTO KetCaBaoCao (nhanvienketca, gioVoCa, TienDauCa, TongSoHoaDon, TienHang, PhiDichVu, TienGiamGia, DoanhThu, TienCuoiCa, GioXuatKetCa)
VALUES ('Tên nhân viên 2', '2024-05-25 07:00:00', 120, 15, 600, 25, 60, 550, 700, '2024-05-25 18:00:00');
INSERT INTO KetCaBaoCao (nhanvienketca, gioVoCa, TienDauCa, TongSoHoaDon, TienHang, PhiDichVu, TienGiamGia, DoanhThu, TienCuoiCa, GioXuatKetCa)
VALUES ('Tên nhân viên 3', '2024-05-26 07:00:00', 150, 20, 700, 30, 70, 650, 800, '2024-05-26 18:00:00');
INSERT INTO KetCaBaoCao (nhanvienketca, gioVoCa, TienDauCa, TongSoHoaDon, TienHang, PhiDichVu, TienGiamGia, DoanhThu, TienCuoiCa, GioXuatKetCa)
VALUES ('Tên nhân viên 4', '2024-05-01 07:00:00', 180, 25, 800, 35, 80, 750, 900, '2024-05-01 18:00:00');
INSERT INTO KetCaBaoCao (nhanvienketca, gioVoCa, TienDauCa, TongSoHoaDon, TienHang, PhiDichVu, TienGiamGia, DoanhThu, TienCuoiCa, GioXuatKetCa)
VALUES ('Tên nhân viên 4', '2024-05-10 07:00:00', 180, 25, 800, 35, 80, 750, 900, '2024-05-10 18:00:00');

SELECT
     YEAR(GioVoCa) AS Nam,
     MONTH(GioVoCa) AS Thang,
     DATEPART(WEEK,GioVoCa) AS Tuan,
     SUM(TongSoHoaDon) AS TongSoHoaDon,
     SUM(TienHang) AS TongTienHang,
     SUM(PhiDichVu) AS TongPhiDichVu,
     SUM(TienGiamGia) AS TongTienGiamGia,
     SUM(DoanhThu) AS TongDoanhThu,
     KetCaBaoCao."idbaocao" AS KetCaBaoCao_idbaocao,
     KetCaBaoCao."nhanvienketca" AS KetCaBaoCao_nhanvienketca,
     KetCaBaoCao."gioVoCa" AS KetCaBaoCao_gioVoCa,
     KetCaBaoCao."TienDauCa" AS KetCaBaoCao_TienDauCa,
     KetCaBaoCao."TongSoHoaDon" AS KetCaBaoCao_TongSoHoaDon,
     KetCaBaoCao."TienHang" AS KetCaBaoCao_TienHang,
     KetCaBaoCao."PhiDichVu" AS KetCaBaoCao_PhiDichVu,
     KetCaBaoCao."DoanhThu" AS KetCaBaoCao_DoanhThu,
     KetCaBaoCao."TienGiamGia" AS KetCaBaoCao_TienGiamGia,
     KetCaBaoCao."TienCuoiCa" AS KetCaBaoCao_TienCuoiCa,
     KetCaBaoCao."GioXuatKetCa" AS KetCaBaoCao_GioXuatKetCa
FROM
     "KetCaBaoCao" KetCaBaoCao


	select * from DonHang
	select * from ChiTietDonHang

	delete DonHang where order_id =?