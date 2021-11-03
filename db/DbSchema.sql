# DB 생성
DROP DATABASE IF EXISTS BulletinBoard_Oct_SpringBoot;
CREATE DATABASE BulletinBoard_Oct_SpringBoot;
USE BulletinBoard_Oct_SpringBoot;

# 게시물 테이블 생성
CREATE TABLE article(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물 테스트 데이터 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3';

# 멤버 테이블 생성
CREATE TABLE `member`(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    loginId CHAR(20) NOT NULL,
    loginPw CHAR(100) NOT NULL,
    authLevel SMALLINT(2) UNSIGNED NOT NULL DEFAULT 3 COMMENT '권한레벨(3=일반,7=관리자)',
    `name` CHAR(100) NOT NULL,
    nickname CHAR(100) NOT NULL,
    cellPhoneNo CHAR(100) NOT NULL,
    email CHAR(100) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴여부(0=탈퇴전,1=탈퇴)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

# 회원 테스트 데이터 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPw = 'admin',
authLevel = 7,
`name` = '관리자',
nickname = '관리자',
cellPhoneNo = '01000000000',
email = 'admin@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPw = 'user1',
`name` = '홍길동',
nickname = '멋쟁이',
cellPhoneNo = '01012345678',
email = 'abc@gmail.com';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPw = 'user2',
`name` = '홍길순',
nickname = '도라에몽',
cellPhoneNo = '01011112222',
email = '123@gmail.com';

# 게시물 테이블에 회원 정보 추가
ALTER TABLE article ADD COLUMN memberId INT(10) UNSIGNED NOT NULL AFTER updateDate;

# 기존 게시물의 작성자를 2번으로 수정
UPDATE article SET memberId = 2 WHERE memberId = 0; 

SELECT * 
FROM article; 

SELECT * 
FROM `member`;

SELECT COUNT(*) > 0
FROM `member`
WHERE loginId = 'user1' AND email = abc@gmail.com;