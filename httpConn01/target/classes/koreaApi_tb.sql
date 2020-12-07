create table koreaApi(
contentId number(10) primary key not null
,subject varchar(500 byte)
,viewCnt number(5)
,originUrl varchar(500 byte)
,atchfileNm varchar(1000 byte)
,atchfileUrl varchar(1000 byte)
,contentsKor clob
,publishOrg varchar(255 byte)
,regDate date
);