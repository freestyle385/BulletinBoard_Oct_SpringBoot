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

# 게시물 테스트 데이터 추가
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = '제목4',
`body` = '내용4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
title = '제목5',
`body` = '내용5';

# 기존 게시물의 작성자를 2번으로 수정
UPDATE article SET memberId = 2 WHERE memberId = 0;

# 게시판 테이블 생성
CREATE TABLE board(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    boardCode CHAR(20) NOT NULL UNIQUE COMMENT 'notice(공지사항), free1(자유게시판1), free2(자유게시판2), ...',
    `name` CHAR(20) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '비활성 여부(0=활성,1=비활성)',
    delDate DATETIME COMMENT '비활성날짜'
); 

# 기본 게시판 생성
INSERT INTO board 
SET regDate = NOW(), 
updateDate = NOW(), 
boardCode = 'notice',
`name` = '공지사항';

INSERT INTO board 
SET regDate = NOW(), 
updateDate = NOW(), 
boardCode = 'free1',
`name` = '자유게시판1';

# 게시판 테이블에 boardId 컬럼 추가
ALTER TABLE article ADD COLUMN boardId INT(10) UNSIGNED NOT NULL AFTER memberId;

# ~3번 게시물에 게시판 정보 추가
UPDATE article SET boardId=1 WHERE id <= 3;

# 4~번 게시물에 게시판 정보 추가
UPDATE article SET boardId=2 WHERE id > 3;

# 게시물 갯수 늘리기
INSERT INTO article
(
    regDate, updateDate, memberId, boardId, title, `body`
)
SELECT NOW(), NOW(), FLOOR(RAND() * 3 + 1), FLOOR(RAND() * 2 + 1), CONCAT('제목_',RAND()), CONCAT('내용_',RAND())
FROM article;

SELECT FLOOR(RAND() * 2 + 1);

#게시물 테이블에 hitCount 칼럼 추가
ALTER TABLE article
ADD COLUMN hitCount INT(10) UNSIGNED NOT NULL DEFAULT 0;

# 게시판 테이블에 goodReactionPoint 컬럼 추가
ALTER TABLE article ADD COLUMN goodReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;

# 게시판 테이블에 badReactionPoint 컬럼 추가
ALTER TABLE article ADD COLUMN badReactionPoint INT(10) UNSIGNED NOT NULL DEFAULT 0;

# 리액션포인트 테이블
CREATE TABLE reactionPoint(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL COMMENT '게시판/댓글 식별 코드',
    articleId INT(10) UNSIGNED NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    pointTypeCode TINYINT(1) NOT NULL COMMENT '좋아요/취소=1/-1,싫어요/취소=2/-2' 
);

### reactionPoint 테스트 데이터
INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
articleId = 1,
memberId = 1,
pointTypeCode = 1;

INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
articleId = 2,
memberId = 1,
pointTypeCode = 2;

INSERT INTO reactionPoint
SET regDate = NOW(),
updateDate = NOW(),
boardId = 1,
articleId = 1,
memberId = 2,
pointTypeCode = 1;


# 기타 
SHOW TABLES;
DESC article;

SELECT *
FROM article;

SELECT COUNT(*) 
FROM article;

SELECT * 
FROM `member`;

SELECT * 
FROM board;

SELECT * 
FROM reactionPoint;

UPDATE article SET goodReactionPoint = 3 WHERE id = 2;
UPDATE article SET goodReactionPoint = 17 WHERE id = 4;
UPDATE article SET goodReactionPoint = 2 WHERE id = 1;

UPDATE article SET badReactionPoint = 1 WHERE id = 2;
UPDATE article SET badReactionPoint = 6 WHERE id = 4;
UPDATE article SET badReactionPoint = 9 WHERE id = 1;

DROP TABLE reactionPoint;
DELETE FROM reactionPoint;

SELECT pointTypeCode FROM reactionPoint WHERE memberId = 1;
SELECT SUM(pointTypeCode) FROM reactionPoint WHERE memberId = 1;

