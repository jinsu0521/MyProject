CREATE TABLE JINTB_BOARD
(
IDX NUMBER PRIMARY KEY,
PARENT_IDX NUMBER,
TITLE VARCHAR2(100) NOT NULL,
CONTENTS VARCHAR2(4000) NOT NULL,
HIT_CNT NUMBER NOT NULL,
DEL_GB VARCHAR2(1) DEFAULT 'N' NOT NULL,
CREA_DTM DATE DEFAULT SYSDATE NOT NULL,
CREA_ID VARCHAR2(30) NOT NULL
);

COMMENT ON TABLE TB_BOARD IS '게시판';
COMMENT ON COLUMN TB_BOARD.IDX IS '인덱스';
COMMENT ON COLUMN TB_BOARD.PARENT_IDX IS '부모글 인덱스';
COMMENT ON COLUMN TB_BOARD.TITLE IS '제목';
COMMENT ON COLUMN TB_BOARD.CONTENTS IS '내용';
COMMENT ON COLUMN TB_BOARD.HIT_CNT IS '조회수';
COMMENT ON COLUMN TB_BOARD.DEL_GB IS '삭제구분';
COMMENT ON COLUMN TB_BOARD.CREA_DTM IS '생성일자';
COMMENT ON COLUMN TB_BOARD.CREA_ID IS '생성자 ID';

CREATE SEQUENCE SEQ_JINTB_BOARD_IDX
START WITH 1
INCREMENT BY 1
NOMAXVALUE
NOCACHE;

INSERT INTO JINTB_BOARD(IDX,TITLE,CONTENTS,HIT_CNT,DEL_GB,CREA_DTM,CREA_ID)
VALUES(SEQ_JINTB_BOARD_IDX.NEXTVAL,'제목','내용',0,'N',SYSDATE,'Admin');

COMMIT;