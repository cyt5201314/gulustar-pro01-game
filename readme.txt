#数据库连接为 3306端口 账号root 密码abc123 如果不一样请修改JDBC配置文件
#运行 src / view / MainInterFace 开始游戏
#因为环境不同可能会在文件路径报错，如果IO报错修改路径即可 特别是苹果系统

#以下为SQL语句、游戏需要先执行SQL语句创建数据库才能正常运行

CREATE DATABASE IF NOT EXISTS gulufishing;

USE DATABASE gulufishing;

CREATE TABLE USER(
    id INT PRIMARY KEY AUTO_INCREMENT ,
    username VARCHAR(20) NOT NULL,
    PASSWORD VARCHAR(20) ,
    curExp INT,
    points INT,
    LEVEL INT
);

CREATE TABLE FISHPOND(
    capacity INT,  #鱼塘容量
    userId INT #用户ID
);


CREATE TABLE fishes(
    id INT PRIMARY KEY AUTO_INCREMENT,     #鱼编号
    TYPE INT,# 鱼种类
    NAME VARCHAR(20),  #名称
    price INT, #鱼价格
    userId INT, #0表示为商城商品，不为0表示为具体用户道具
    startTime DATETIME,#开始养的时间
    EXP INT, #获得的经验
    sell INT, #售价
    matureTime TIME,#成熟时间
    isMature INT#1成熟 0未成熟
);

create table `grade` (
	`grade` int (11),
	`exp` int (11)
); 

#以上是4个表的创建-----------------------------------

#数据↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

insert into `grade` (`grade`, `exp`) values('1','0');
insert into `grade` (`grade`, `exp`) values('2','60');
insert into `grade` (`grade`, `exp`) values('3','120');
insert into `grade` (`grade`, `exp`) values('4','200');
insert into `grade` (`grade`, `exp`) values('5','300');
insert into `grade` (`grade`, `exp`) values('6','420');
insert into `grade` (`grade`, `exp`) values('7','560');
insert into `grade` (`grade`, `exp`) values('8','720');
insert into `grade` (`grade`, `exp`) values('9','900');
insert into `grade` (`grade`, `exp`) values('10','1100');
insert into `grade` (`grade`, `exp`) values('11','1320');
insert into `grade` (`grade`, `exp`) values('12','1560');
insert into `grade` (`grade`, `exp`) values('13','1820');
insert into `grade` (`grade`, `exp`) values('14','2100');
insert into `grade` (`grade`, `exp`) values('15','2400');
insert into `grade` (`grade`, `exp`) values('16','2720');
insert into `grade` (`grade`, `exp`) values('17','3060');
insert into `grade` (`grade`, `exp`) values('18','3420');
insert into `grade` (`grade`, `exp`) values('19','3800');
insert into `grade` (`grade`, `exp`) values('20','4200');
insert into `grade` (`grade`, `exp`) values('21','4620');
insert into `grade` (`grade`, `exp`) values('22','5060');
insert into `grade` (`grade`, `exp`) values('23','5520');
insert into `grade` (`grade`, `exp`) values('24','6000');
insert into `grade` (`grade`, `exp`) values('25','6500');
insert into `grade` (`grade`, `exp`) values('26','7020');
insert into `grade` (`grade`, `exp`) values('27','7560');
insert into `grade` (`grade`, `exp`) values('28','8120');
insert into `grade` (`grade`, `exp`) values('29','8700');
insert into `grade` (`grade`, `exp`) values('30','9300');
insert into `grade` (`grade`, `exp`) values('31','9920');
insert into `grade` (`grade`, `exp`) values('32','10560');
insert into `grade` (`grade`, `exp`) values('33','11220');
insert into `grade` (`grade`, `exp`) values('34','11900');
insert into `grade` (`grade`, `exp`) values('35','12600');
insert into `grade` (`grade`, `exp`) values('36','13320');
insert into `grade` (`grade`, `exp`) values('37','14060');
insert into `grade` (`grade`, `exp`) values('38','14820');
insert into `grade` (`grade`, `exp`) values('39','15600');
insert into `grade` (`grade`, `exp`) values('40','16400');
insert into `grade` (`grade`, `exp`) values('41','17220');
insert into `grade` (`grade`, `exp`) values('42','18060');
insert into `grade` (`grade`, `exp`) values('43','18920');
insert into `grade` (`grade`, `exp`) values('44','19800');
insert into `grade` (`grade`, `exp`) values('45','20700');
insert into `grade` (`grade`, `exp`) values('46','21620');
insert into `grade` (`grade`, `exp`) values('47','22560');
insert into `grade` (`grade`, `exp`) values('48','23520');
insert into `grade` (`grade`, `exp`) values('49','24500');
insert into `grade` (`grade`, `exp`) values('50','25500');

INSERT INTO fishes VALUES(0,1,'剑鱼',10,0,NULL,5,20,'00:01:00',0);
INSERT INTO fishes VALUES(0,2,'金鱼',20,0,NULL,10,30,'00:02:00',0);
INSERT INTO fishes VALUES(0,3,'鱼虾',50,0,NULL,15,80,'00:03:00',0);
INSERT INTO fishes VALUES(0,4,'金花鱼',80,0,NULL,20,100,'00:03:00',0);
INSERT INTO fishes VALUES(0,5,'虎斑鱼',100,0,NULL,25,150,'00:05:00',0);
INSERT INTO fishes VALUES(0,6,'短翅蝙蝠鱼',150,0,NULL,30,200,'00:10:00',0);
INSERT INTO fishes VALUES(0,7,'蓝鱼',180,0,NULL,35,250,'00:20:00',0);
INSERT INTO fishes VALUES(0,8,'小丑鱼',210,0,NULL,40,300,'00:25:00',0);
INSERT INTO fishes VALUES(0,9,'鱿鱼',250,0,NULL,45,500,'00:30:00',0);
INSERT INTO fishes VALUES(0,10,'海马',300,0,NULL,50,600,'00:45:00',0);
