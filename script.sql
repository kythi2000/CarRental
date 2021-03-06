USE [CarRental]
GO
/****** Object:  StoredProcedure [dbo].[getAvailableCar]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP PROCEDURE [dbo].[getAvailableCar]
GO
/****** Object:  StoredProcedure [dbo].[countPage]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP PROCEDURE [dbo].[countPage]
GO
ALTER TABLE [dbo].[RentalDetail] DROP CONSTRAINT [FK_RentalDetail_Rental]
GO
ALTER TABLE [dbo].[RentalDetail] DROP CONSTRAINT [FK_RentalDetail_Car]
GO
ALTER TABLE [dbo].[Rental] DROP CONSTRAINT [FK_Rental_Registration]
GO
ALTER TABLE [dbo].[Car] DROP CONSTRAINT [FK_Car_Category]
GO
/****** Object:  Table [dbo].[RentalDetail]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP TABLE [dbo].[RentalDetail]
GO
/****** Object:  Table [dbo].[Rental]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP TABLE [dbo].[Rental]
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP TABLE [dbo].[Registration]
GO
/****** Object:  Table [dbo].[Rating]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP TABLE [dbo].[Rating]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP TABLE [dbo].[Category]
GO
/****** Object:  Table [dbo].[Car]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP TABLE [dbo].[Car]
GO
USE [master]
GO
/****** Object:  Database [CarRental]    Script Date: 5/5/2021 5:00:50 PM ******/
DROP DATABASE [CarRental]
GO
/****** Object:  Database [CarRental]    Script Date: 5/5/2021 5:00:50 PM ******/
CREATE DATABASE [CarRental]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CarRental', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarRental.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CarRental_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CarRental_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CarRental] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CarRental].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CarRental] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CarRental] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CarRental] SET ARITHABORT OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CarRental] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CarRental] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CarRental] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CarRental] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CarRental] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CarRental] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CarRental] SET  DISABLE_BROKER 
GO
ALTER DATABASE [CarRental] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CarRental] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CarRental] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CarRental] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CarRental] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CarRental] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CarRental] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CarRental] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [CarRental] SET  MULTI_USER 
GO
ALTER DATABASE [CarRental] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CarRental] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CarRental] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CarRental] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [CarRental] SET DELAYED_DURABILITY = DISABLED 
GO
USE [CarRental]
GO
/****** Object:  Table [dbo].[Car]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Car](
	[CarID] [varchar](50) NOT NULL,
	[Name] [varchar](50) NULL,
	[Color] [varchar](50) NULL,
	[Year] [varchar](50) NULL,
	[Category] [varchar](50) NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Car] PRIMARY KEY CLUSTERED 
(
	[CarID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[Category] [varchar](50) NOT NULL,
	[Description] [varchar](50) NULL,
 CONSTRAINT [PK_Category] PRIMARY KEY CLUSTERED 
(
	[Category] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Rating]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Rating](
	[CarID] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[Rating] [int] NULL,
	[Comment] [varchar](50) NULL,
	[DateOfCreate] [datetime] NULL,
 CONSTRAINT [PK_Ranking] PRIMARY KEY CLUSTERED 
(
	[CarID] ASC,
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[Email] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[Address] [varchar](50) NULL,
	[DateOfCreate] [datetime] NULL,
	[Role] [bit] NULL,
	[Status] [varchar](50) NULL,
	[VerifyCode] [int] NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Rental]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Rental](
	[RentalID] [varchar](50) NOT NULL,
	[Email] [varchar](50) NULL,
	[Total] [float] NULL,
	[DateOfCreate] [datetime] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_Rental] PRIMARY KEY CLUSTERED 
(
	[RentalID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RentalDetail]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RentalDetail](
	[RentalDetailID] [varchar](50) NOT NULL,
	[RentalID] [varchar](50) NULL,
	[Quantity] [int] NULL,
	[Car_ID] [varchar](50) NULL,
	[Price] [float] NULL,
	[RentalDate] [date] NULL,
	[ReturnDate] [date] NULL,
 CONSTRAINT [PK_RentalDetail] PRIMARY KEY CLUSTERED 
(
	[RentalDetailID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Car] ([CarID], [Name], [Color], [Year], [Category], [Price], [Quantity], [Status]) VALUES (N'1', N'crv', N'blue', N'2000', N'honda', 50, 1, 1)
INSERT [dbo].[Car] ([CarID], [Name], [Color], [Year], [Category], [Price], [Quantity], [Status]) VALUES (N'2', N'crv', N'red', N'2008', N'yamaha', 50, 1, 1)
INSERT [dbo].[Car] ([CarID], [Name], [Color], [Year], [Category], [Price], [Quantity], [Status]) VALUES (N'3', N'audi-r8', N'black', N'2005', N'audi', 50, 1, 1)
INSERT [dbo].[Car] ([CarID], [Name], [Color], [Year], [Category], [Price], [Quantity], [Status]) VALUES (N'4', N'mitsubishi', N'yellow', N'2018', N'mitsubishi', 50, 7, 1)
INSERT [dbo].[Car] ([CarID], [Name], [Color], [Year], [Category], [Price], [Quantity], [Status]) VALUES (N'5', N'aventador', N'blue', N'2021', N'lamborghini', 50, 3, 1)
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'audi', N'asdasd')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'honda', N'asdas')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'lamborghini', N'dfsdf')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'mitsubishi', N'asdsa')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'suzuki', N'adas')
INSERT [dbo].[Category] ([Category], [Description]) VALUES (N'yamaha', N'asdasd')
INSERT [dbo].[Rating] ([CarID], [Email], [Rating], [Comment], [DateOfCreate]) VALUES (N'2', N'anhtai570@gmail.com', 5, N'calm down bro', CAST(N'2021-03-18 11:01:24.490' AS DateTime))
INSERT [dbo].[Rating] ([CarID], [Email], [Rating], [Comment], [DateOfCreate]) VALUES (N'2', N'kythi2000@gmail.com', 10, N'luv <3', CAST(N'2021-03-18 13:52:04.257' AS DateTime))
INSERT [dbo].[Rating] ([CarID], [Email], [Rating], [Comment], [DateOfCreate]) VALUES (N'3', N'anhtai570@gmail.com', 4, N'thang khanh ngu', CAST(N'2021-03-24 09:22:12.927' AS DateTime))
INSERT [dbo].[Rating] ([CarID], [Email], [Rating], [Comment], [DateOfCreate]) VALUES (N'3', N'kythi2000@gmail.com', 5, N'', CAST(N'2021-03-19 22:26:02.973' AS DateTime))
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [DateOfCreate], [Role], [Status], [VerifyCode]) VALUES (N'an', N'123', N'An', N'1', N'1', CAST(N'2021-01-01 00:00:00.000' AS DateTime), 1, N'Active', 1)
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [DateOfCreate], [Role], [Status], [VerifyCode]) VALUES (N'anhtai570@gmail.com', N'123456', N'anh tai', N'0989639227', N'B2/1T tá»? 6', CAST(N'2021-03-08 09:56:44.067' AS DateTime), 0, N'Active', 7514)
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [DateOfCreate], [Role], [Status], [VerifyCode]) VALUES (N'ban', N'123', N'Ban', N'1', N'1', CAST(N'2021-01-01 00:00:00.000' AS DateTime), 0, N'New', 1)
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [DateOfCreate], [Role], [Status], [VerifyCode]) VALUES (N'kythi12000@gmail.com', N'123456', N'annnnnnnnn', N'0989639227', N'B2/1T tÃ¡Â»Â? 6', CAST(N'2021-03-24 09:14:48.457' AS DateTime), 0, N'Active', 6193)
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [DateOfCreate], [Role], [Status], [VerifyCode]) VALUES (N'kythi2000@gmail.com', N'123456', N'annnnnnnnn', N'0989639227', N'B2/1T tá»? 6', CAST(N'2021-03-07 16:27:15.807' AS DateTime), 0, N'Active', 7902)
INSERT [dbo].[Registration] ([Email], [Password], [Fullname], [Phone], [Address], [DateOfCreate], [Role], [Status], [VerifyCode]) VALUES (N'minhnguyen220200@gmail.com', N'123456', N'Minh Nguyá»?n', N'0989639227', N'B2/1T tá»? 6', CAST(N'2021-05-04 13:37:08.690' AS DateTime), 0, N'Active', 8141)
INSERT [dbo].[Rental] ([RentalID], [Email], [Total], [DateOfCreate], [Status]) VALUES (N'RENT-anhtai570@gmail.com-1', N'anhtai570@gmail.com', 100, CAST(N'2021-03-24 09:20:28.497' AS DateTime), 1)
INSERT [dbo].[Rental] ([RentalID], [Email], [Total], [DateOfCreate], [Status]) VALUES (N'RENT-anhtai570@gmail.com-2', N'anhtai570@gmail.com', 200, CAST(N'2021-04-29 08:04:54.810' AS DateTime), 1)
INSERT [dbo].[Rental] ([RentalID], [Email], [Total], [DateOfCreate], [Status]) VALUES (N'RENT-kythi12000@gmail.com-1', N'kythi12000@gmail.com', 50, CAST(N'2021-03-24 09:20:24.577' AS DateTime), 1)
INSERT [dbo].[Rental] ([RentalID], [Email], [Total], [DateOfCreate], [Status]) VALUES (N'RENT-kythi2000@gmail.com-1', N'kythi2000@gmail.com', 150, CAST(N'2021-03-20 19:25:45.067' AS DateTime), 0)
INSERT [dbo].[RentalDetail] ([RentalDetailID], [RentalID], [Quantity], [Car_ID], [Price], [RentalDate], [ReturnDate]) VALUES (N'RENT-anhtai570@gmail.com-1-0', N'RENT-anhtai570@gmail.com-1', 1, N'3', 50, CAST(N'2021-03-24' AS Date), CAST(N'2021-03-26' AS Date))
INSERT [dbo].[RentalDetail] ([RentalDetailID], [RentalID], [Quantity], [Car_ID], [Price], [RentalDate], [ReturnDate]) VALUES (N'RENT-anhtai570@gmail.com-2-0', N'RENT-anhtai570@gmail.com-2', 2, N'4', 50, CAST(N'2021-04-29' AS Date), CAST(N'2021-04-30' AS Date))
INSERT [dbo].[RentalDetail] ([RentalDetailID], [RentalID], [Quantity], [Car_ID], [Price], [RentalDate], [ReturnDate]) VALUES (N'RENT-anhtai570@gmail.com-2-1', N'RENT-anhtai570@gmail.com-2', 2, N'5', 50, CAST(N'2021-04-29' AS Date), CAST(N'2021-04-30' AS Date))
INSERT [dbo].[RentalDetail] ([RentalDetailID], [RentalID], [Quantity], [Car_ID], [Price], [RentalDate], [ReturnDate]) VALUES (N'RENT-kythi12000@gmail.com-1-0', N'RENT-kythi12000@gmail.com-1', 1, N'3', 50, CAST(N'2021-03-24' AS Date), CAST(N'2021-03-25' AS Date))
INSERT [dbo].[RentalDetail] ([RentalDetailID], [RentalID], [Quantity], [Car_ID], [Price], [RentalDate], [ReturnDate]) VALUES (N'RENT-kythi2000@gmail.com-1-0', N'RENT-kythi2000@gmail.com-1', 1, N'1', 50, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date))
INSERT [dbo].[RentalDetail] ([RentalDetailID], [RentalID], [Quantity], [Car_ID], [Price], [RentalDate], [ReturnDate]) VALUES (N'RENT-kythi2000@gmail.com-1-1', N'RENT-kythi2000@gmail.com-1', 2, N'2', 50, CAST(N'2021-03-20' AS Date), CAST(N'2021-03-21' AS Date))
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_Category] FOREIGN KEY([Category])
REFERENCES [dbo].[Category] ([Category])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_Car_Category]
GO
ALTER TABLE [dbo].[Rental]  WITH CHECK ADD  CONSTRAINT [FK_Rental_Registration] FOREIGN KEY([Email])
REFERENCES [dbo].[Registration] ([Email])
GO
ALTER TABLE [dbo].[Rental] CHECK CONSTRAINT [FK_Rental_Registration]
GO
ALTER TABLE [dbo].[RentalDetail]  WITH CHECK ADD  CONSTRAINT [FK_RentalDetail_Car] FOREIGN KEY([Car_ID])
REFERENCES [dbo].[Car] ([CarID])
GO
ALTER TABLE [dbo].[RentalDetail] CHECK CONSTRAINT [FK_RentalDetail_Car]
GO
ALTER TABLE [dbo].[RentalDetail]  WITH CHECK ADD  CONSTRAINT [FK_RentalDetail_Rental] FOREIGN KEY([RentalID])
REFERENCES [dbo].[Rental] ([RentalID])
GO
ALTER TABLE [dbo].[RentalDetail] CHECK CONSTRAINT [FK_RentalDetail_Rental]
GO
/****** Object:  StoredProcedure [dbo].[countPage]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create proc [dbo].[countPage](@rentalDate date, @returnDate date, @name varchar(50), @category varchar(50))
 as 
select COUNT(k.CarID)
from(
 select CarID, Name, Category, Year, Color, Price, Available
From(((select c.CarID,c.Name,c.Year,c.category,c.color,c.price,c.Available
from
	(select CarID,Name,Year,category,color,price,(quantity-RentalCar) as Available
	 from Car a,(select sum(quantity) as RentalCar,Car_ID
					from RentalDetail
					 where  (@rentalDate between RentalDate and ReturnDate) or (@returnDate between RentalDate and ReturnDate)
					 group by Car_ID) b
	 where a.CarID=b.Car_ID) c
where c.Available>0)
union
(select c.CarID,c.Name,c.Year,c.category,c.color,c.price,c.quantity
 from Car c
 where  not EXISTS (select *
                    from (select sum(quantity) as RentalCar,Car_ID
						  from RentalDetail
                          where(@rentalDate between RentalDate and ReturnDate) or (@returnDate between RentalDate and ReturnDate)
                          group by Car_ID) b
                          where c.CarID=b.Car_ID )
                  ))


) v
where Name like @name and Category like @category) k
GO
/****** Object:  StoredProcedure [dbo].[getAvailableCar]    Script Date: 5/5/2021 5:00:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE proc [dbo].[getAvailableCar](@rentalDate date, @returnDate date, @name varchar(50), @category varchar(50), @row int, @limit int)
 as 

 select CarID, Name, Category, Year, Color, Price, Available
From(((select c.CarID,c.Name,c.Year,c.category,c.color,c.price,c.Available
from
	(select CarID,Name,Year,category,color,price,(quantity-RentalCar) as Available
	 from Car a,(select sum(quantity) as RentalCar,Car_ID
					from RentalDetail
					 where  (@rentalDate between RentalDate and ReturnDate) or (@returnDate between RentalDate and ReturnDate)
					 group by Car_ID) b
	 where a.CarID=b.Car_ID) c
where c.Available>0)
union
(select c.CarID,c.Name,c.Year,c.category,c.color,c.price,c.quantity
 from Car c
 where  not EXISTS (select *
                    from (select sum(quantity) as RentalCar,Car_ID
						  from RentalDetail
                          where(@rentalDate between RentalDate and ReturnDate) or (@returnDate between RentalDate and ReturnDate)
                          group by Car_ID) b
                          where c.CarID=b.Car_ID )
                  ))


) v
where Name like @name and Category like @category
order by CarID
OFFSET @row ROWS FETCH NEXT @limit ROWS ONLY; 
GO
USE [master]
GO
ALTER DATABASE [CarRental] SET  READ_WRITE 
GO
