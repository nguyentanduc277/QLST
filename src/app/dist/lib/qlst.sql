-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th8 10, 2021 lúc 08:20 AM
-- Phiên bản máy phục vụ: 10.4.17-MariaDB
-- Phiên bản PHP: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlst`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brand`
--

CREATE TABLE `brand` (
  `brand_id` int(11) NOT NULL,
  `brand_name` varchar(100) NOT NULL,
  `cat_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `cus_id` int(11) NOT NULL,
  `cus_name` varchar(100) NOT NULL,
  `cus_address` varchar(200) NOT NULL,
  `cus_issue` date NOT NULL,
  `last_purchase_date` date NOT NULL,
  `cus_point` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `emp_birthday` date NOT NULL,
  `emp_gender` varchar(100) NOT NULL,
  `emp_cmnd` varchar(40) NOT NULL,
  `emp_address` varchar(100) NOT NULL,
  `emp_phone` varchar(40) NOT NULL,
  `emp_start_date` datetime NOT NULL DEFAULT current_timestamp(),
  `title_id` int(11) NOT NULL,
  `emp_user` varchar(100) NOT NULL,
  `emp_pass` varchar(100) NOT NULL,
  `emp_status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`emp_id`, `emp_name`, `emp_birthday`, `emp_gender`, `emp_cmnd`, `emp_address`, `emp_phone`, `emp_start_date`, `title_id`, `emp_user`, `emp_pass`, `emp_status`) VALUES
(2, 'Minh Huy', '2002-01-01', 'male', '', '', '', '0000-00-00 00:00:00', 1, 'a', '1', 1),
(3, 'Trần Minh Huy', '2002-01-01', 'male', '', '', '', '2021-08-09 00:00:00', 2, 'b', '1', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `input`
--

CREATE TABLE `input` (
  `input_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `input_detail`
--

CREATE TABLE `input_detail` (
  `input_id` int(11) NOT NULL,
  `wh_id` int(11) NOT NULL,
  `sup_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `inventory`
--

CREATE TABLE `inventory` (
  `inv_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `pro_id` int(11) NOT NULL,
  `quantity_stalls` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `total` int(11) NOT NULL,
  `cus_id` int(11) NOT NULL,
  `point` int(11) NOT NULL,
  `discount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders_detail`
--

CREATE TABLE `orders_detail` (
  `order_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `output`
--

CREATE TABLE `output` (
  `output_id` int(11) NOT NULL,
  `emp_id` int(11) NOT NULL,
  `time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `output_detail`
--

CREATE TABLE `output_detail` (
  `output_id` int(11) NOT NULL,
  `wh_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `pro_id` int(11) NOT NULL,
  `pro_name` varchar(100) NOT NULL,
  `pro_sale_price` int(11) NOT NULL,
  `pro_expiry` date NOT NULL,
  `pro_unit` varchar(100) NOT NULL,
  `brand_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `sup_id` int(11) NOT NULL,
  `sup_name` varchar(100) NOT NULL,
  `sup_address` varchar(100) NOT NULL,
  `sup_phone` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `title`
--

CREATE TABLE `title` (
  `title_id` int(11) NOT NULL,
  `tittke_name` varchar(100) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `title`
--

INSERT INTO `title` (`title_id`, `tittke_name`, `description`) VALUES
(1, 'mana', 'manage'),
(2, 'emp', 'employee');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ware_house`
--

CREATE TABLE `ware_house` (
  `wh_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  `amount_stock` int(11) NOT NULL,
  `amount_input` int(11) NOT NULL,
  `price_input` int(11) NOT NULL,
  `date_input` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`brand_id`),
  ADD KEY `FK_brand_cat` (`cat_id`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`cus_id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`),
  ADD KEY `FK_emp_title_id` (`title_id`);

--
-- Chỉ mục cho bảng `input`
--
ALTER TABLE `input`
  ADD PRIMARY KEY (`input_id`),
  ADD KEY `FK_input_emp` (`emp_id`);

--
-- Chỉ mục cho bảng `input_detail`
--
ALTER TABLE `input_detail`
  ADD KEY `FK_inputdetail_input` (`input_id`),
  ADD KEY `FK_inputdetail_sup` (`sup_id`),
  ADD KEY `FK_inputdetail_warehouse` (`wh_id`);

--
-- Chỉ mục cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inv_id`),
  ADD KEY `FK_inv_emp` (`emp_id`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `FK_order_emp` (`emp_id`),
  ADD KEY `FK_order_cus` (`cus_id`);

--
-- Chỉ mục cho bảng `orders_detail`
--
ALTER TABLE `orders_detail`
  ADD KEY `FK_orderdetaild_order` (`order_id`),
  ADD KEY `FK_orderdetaild_pro` (`pro_id`);

--
-- Chỉ mục cho bảng `output`
--
ALTER TABLE `output`
  ADD PRIMARY KEY (`output_id`),
  ADD KEY `FK_output_emp` (`emp_id`);

--
-- Chỉ mục cho bảng `output_detail`
--
ALTER TABLE `output_detail`
  ADD KEY `FK_outputdetail_output` (`output_id`),
  ADD KEY `FK_outputdetail_warehouse` (`wh_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`pro_id`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`sup_id`);

--
-- Chỉ mục cho bảng `title`
--
ALTER TABLE `title`
  ADD PRIMARY KEY (`title_id`);

--
-- Chỉ mục cho bảng `ware_house`
--
ALTER TABLE `ware_house`
  ADD PRIMARY KEY (`wh_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `brand`
--
ALTER TABLE `brand`
  MODIFY `brand_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `cus_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `emp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `input`
--
ALTER TABLE `input`
  MODIFY `input_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `inventory`
--
ALTER TABLE `inventory`
  MODIFY `inv_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `output`
--
ALTER TABLE `output`
  MODIFY `output_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `pro_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `supplier`
--
ALTER TABLE `supplier`
  MODIFY `sup_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `title`
--
ALTER TABLE `title`
  MODIFY `title_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `ware_house`
--
ALTER TABLE `ware_house`
  MODIFY `wh_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `brand`
--
ALTER TABLE `brand`
  ADD CONSTRAINT `FK_brand_cat` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `FK_emp_title_id` FOREIGN KEY (`title_id`) REFERENCES `title` (`title_id`);

--
-- Các ràng buộc cho bảng `input`
--
ALTER TABLE `input`
  ADD CONSTRAINT `FK_input_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);

--
-- Các ràng buộc cho bảng `input_detail`
--
ALTER TABLE `input_detail`
  ADD CONSTRAINT `FK_inputdetail_input` FOREIGN KEY (`input_id`) REFERENCES `input` (`input_id`),
  ADD CONSTRAINT `FK_inputdetail_sup` FOREIGN KEY (`sup_id`) REFERENCES `supplier` (`sup_id`),
  ADD CONSTRAINT `FK_inputdetail_warehouse` FOREIGN KEY (`wh_id`) REFERENCES `ware_house` (`wh_id`);

--
-- Các ràng buộc cho bảng `inventory`
--
ALTER TABLE `inventory`
  ADD CONSTRAINT `FK_inv_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK_order_cus` FOREIGN KEY (`cus_id`) REFERENCES `customer` (`cus_id`),
  ADD CONSTRAINT `FK_order_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);

--
-- Các ràng buộc cho bảng `orders_detail`
--
ALTER TABLE `orders_detail`
  ADD CONSTRAINT `FK_orderdetaild_order` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  ADD CONSTRAINT `FK_orderdetaild_pro` FOREIGN KEY (`pro_id`) REFERENCES `product` (`pro_id`);

--
-- Các ràng buộc cho bảng `output`
--
ALTER TABLE `output`
  ADD CONSTRAINT `FK_output_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`);

--
-- Các ràng buộc cho bảng `output_detail`
--
ALTER TABLE `output_detail`
  ADD CONSTRAINT `FK_outputdetail_output` FOREIGN KEY (`output_id`) REFERENCES `output` (`output_id`),
  ADD CONSTRAINT `FK_outputdetail_warehouse` FOREIGN KEY (`wh_id`) REFERENCES `ware_house` (`wh_id`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_pro_brand` FOREIGN KEY (`pro_id`) REFERENCES `brand` (`brand_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
