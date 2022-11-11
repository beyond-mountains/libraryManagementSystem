???prompt PL/SQL Developer import file
prompt Created on 2022年6月30日 by beyond-mountains
set feedback off
set define off
prompt Dropping BOOK...
drop table BOOK cascade constraints;
prompt Dropping STUDENT...
drop table STUDENT cascade constraints;
prompt Dropping BORROW...
drop table BORROW cascade constraints;
prompt Dropping MANAGER...
drop table MANAGER cascade constraints;
prompt Dropping MAINTAIN...
drop table MAINTAIN cascade constraints;
prompt Dropping OPERATE...
drop table OPERATE cascade constraints;
prompt Creating BOOK...
create table BOOK
(
  id       CHAR(20) not null,
  name     VARCHAR2(20) not null,
  category VARCHAR2(20) not null,
  author   VARCHAR2(20) not null,
  place    VARCHAR2(20) not null,
  photo    VARCHAR2(20),
  state    CHAR(6) not null
)
tablespace XXULIBRARY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BOOKAUTHOR on BOOK (AUTHOR)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BOOKCATEGORY on BOOK (CATEGORY)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BOOKNAME on BOOK (NAME)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BOOK
  add constraint BOOKID_PK primary key (ID)
  using index 
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
grant select, insert, update, delete, references, alter, index on BOOK to LIBVISITOR;

prompt Creating STUDENT...
create table STUDENT
(
  id        CHAR(8) not null,
  name      VARCHAR2(20) not null,
  pwd       CHAR(20) not null,
  institute VARCHAR2(40),
  email     VARCHAR2(20)
)
tablespace XXULIBRARY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index STUINSTITUTE on STUDENT (INSTITUTE)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index STUPWDINDEX on STUDENT (PWD)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table STUDENT
  add constraint STUID_PK primary key (ID)
  using index 
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
grant select, insert, update, delete, references, alter, index on STUDENT to LIBVISITOR;

prompt Creating BORROW...
create table BORROW
(
  stuid      CHAR(8) not null,
  bookid     CHAR(20) not null,
  lenddate   CHAR(20) not null,
  duedate    CHAR(20),
  returndate CHAR(20),
  id         NUMBER(12) not null
)
tablespace XXULIBRARY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BORR_BOOKID on BORROW (BOOKID)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BORR_DUEDATE on BORROW (DUEDATE)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BORR_LENDDATE on BORROW (LENDDATE)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
create index BORR_STUID on BORROW (STUID)
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BORROW
  add constraint BORR_ID_PK primary key (ID)
  using index 
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table BORROW
  add constraint BORR_BOOKID_FK foreign key (BOOKID)
  references BOOK (ID);
alter table BORROW
  add constraint BORR_STUID_FK foreign key (STUID)
  references STUDENT (ID);
grant select, insert, update, delete, references, alter, index on BORROW to LIBVISITOR;

prompt Creating MANAGER...
create table MANAGER
(
  id   CHAR(20) not null,
  pwd  CHAR(20) not null,
  name CHAR(20),
  ago  NUMBER(3)
)
tablespace XXULIBRARY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MANAGER
  add constraint MANID_PK primary key (ID)
  using index 
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
grant select, insert, update, delete, references, alter, index on MANAGER to LIBVISITOR;

prompt Creating MAINTAIN...
create table MAINTAIN
(
  manid        CHAR(20) not null,
  bookid       CHAR(20) not null,
  maintaintime CHAR(20) not null,
  maintainbook CHAR(6) not null,
  id           NUMBER(12) not null
)
tablespace XXULIBRARY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MAINTAIN
  add constraint MAINTAIN_ID_PK primary key (ID)
  using index 
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table MAINTAIN
  add constraint BOOKID_FK foreign key (BOOKID)
  references BOOK (ID);
alter table MAINTAIN
  add constraint MANID_FK foreign key (MANID)
  references MANAGER (ID);
grant select, insert, update, delete, references, alter, index on MAINTAIN to LIBVISITOR;

prompt Creating OPERATE...
create table OPERATE
(
  manid       CHAR(20) not null,
  stuid       CHAR(8) not null,
  operatetime CHAR(20) not null,
  operatestu  CHAR(6) not null,
  id          NUMBER(12) not null
)
tablespace XXULIBRARY
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OPERATE
  add constraint OPER_ID_PK primary key (ID)
  using index 
  tablespace XXULIBRARY
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table OPERATE
  add constraint OPER_MANID_FK foreign key (MANID)
  references MANAGER (ID);
alter table OPERATE
  add constraint OPER_STUID_FK foreign key (STUID)
  references STUDENT (ID);
grant select, insert, update, delete, references, alter, index on OPERATE to LIBVISITOR;

prompt Disabling triggers for BOOK...
alter table BOOK disable all triggers;
prompt Disabling triggers for STUDENT...
alter table STUDENT disable all triggers;
prompt Disabling triggers for BORROW...
alter table BORROW disable all triggers;
prompt Disabling triggers for MANAGER...
alter table MANAGER disable all triggers;
prompt Disabling triggers for MAINTAIN...
alter table MAINTAIN disable all triggers;
prompt Disabling triggers for OPERATE...
alter table OPERATE disable all triggers;
prompt Disabling foreign key constraints for BORROW...
alter table BORROW disable constraint BORR_BOOKID_FK;
alter table BORROW disable constraint BORR_STUID_FK;
prompt Disabling foreign key constraints for MAINTAIN...
alter table MAINTAIN disable constraint BOOKID_FK;
alter table MAINTAIN disable constraint MANID_FK;
prompt Disabling foreign key constraints for OPERATE...
alter table OPERATE disable constraint OPER_MANID_FK;
alter table OPERATE disable constraint OPER_STUID_FK;
prompt Loading BOOK...
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu011              ', '算法导论', '计算机', '作者11', '鼎新1楼', './image/image11', '在库');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu013              ', '机器学习', '计算机', '作者13', '鼎新2楼', './image/image13', '在库');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu003              ', '世界通史', '历史', '作者3', '中心馆3楼', './image/image3', '借出');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu002              ', 'html', '计算机', '作者2', '鼎新4楼', './image/image2', '借出');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu004              ', '计量经济学', '经济', '作者4', '中心馆2楼', './image/image4', '借出');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu001              ', 'java', '计算机', '作者1', '鼎新3楼', './image/image1', '借出');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu008              ', '软件系统开发', '计算机', '作者8', '鼎新5楼', './image/image8', '在库');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu005              ', '机械原理', '机械', '作者5', '鼎新3楼', './image/image5', '删除');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu010              ', '计算机导论', '计算机', '作者10', '鼎新1楼', './image/image10', '在库');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu006              ', 'css', '计算机', '作者6', '鼎新1楼', './image/image6', '在库');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu012              ', '模糊数学', '计算机', '作者12', '鼎新2楼', './image/image12', '在库');
insert into BOOK (id, name, category, author, place, photo, state)
values ('xxu007              ', '软件工程', '计算机', '作者7', '中心馆4楼', './image/image7', '删除');
commit;
prompt 12 records loaded
prompt Loading STUDENT...
insert into STUDENT (id, name, pwd, institute, email)
values ('99999001', '亦亦', '123456              ', '软件学院', 'yiyi@xxu.edu.cn');
insert into STUDENT (id, name, pwd, institute, email)
values ('99999002', '尔尔 ', '123456              ', '计算机科学与技术学院', 'erer@xxu.edu.cn');
insert into STUDENT (id, name, pwd, institute, email)
values ('99999003', '叁叁', '123456              ', '机械学院', 'san@xxu.edu.cn');
insert into STUDENT (id, name, pwd, institute, email)
values ('99999004', '思思', '123456              ', '法学院', 'sisi@xxu.edu.cn');
insert into STUDENT (id, name, pwd, institute, email)
values ('99999005', '五五', '123456              ', '体育学院', 'wuwu@xxu.edu.cn');
insert into STUDENT (id, name, pwd, institute, email)
values ('99999006', '柳柳', '123456              ', '人工智能学院', 'liuliu@xxu.edu.cn');
insert into STUDENT (id, name, pwd, institute, email)
values ('99999007', '七七', '123456              ', '软件学院', 'qiqi@xxu.edu.cn');
commit;
prompt 11 records loaded
prompt Loading BORROW...
insert into BORROW (stuid, bookid, lenddate, duedate, returndate, id)
values ('99999002', 'xxu004              ', '2022/06/30 13:40:30 ', '2022/08/29 01:40:30 ', null, 1048);
insert into BORROW (stuid, bookid, lenddate, duedate, returndate, id)
values ('99999001', 'xxu002              ', '2022/04/26 14:33:33 ', '2022/05/25 02:33:33 ', null, 1028);
insert into BORROW (stuid, bookid, lenddate, duedate, returndate, id)
values ('99999001', 'xxu001              ', '2022/04/29 22:08:17 ', '2022/05/28 22:08:17 ', null, 1042);
insert into BORROW (stuid, bookid, lenddate, duedate, returndate, id)
values ('99999002', 'xxu003              ', '2022/06/30 13:38:20 ', '2022/07/30 13:38:20 ', null, 1047);
insert into BORROW (stuid, bookid, lenddate, duedate, returndate, id)
values ('99999003', 'xxu006              ', '2022/06/30 13:47:07 ', '2022/07/30 13:47:07 ', '2022/06/30 13:48:02 ', 1049);
commit;
prompt 5 records loaded
prompt Loading MANAGER...
insert into MANAGER (id, pwd, name, ago)
values ('manager1            ', '123456              ', '管理员           ', 22);
commit;
prompt 1 records loaded
prompt Loading MAINTAIN...
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu005              ', '2022/06/29 00:49:27 ', '删除', 1032);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu013              ', '2022/06/30 13:53:32 ', '入库', 1044);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu011              ', '2022/06/30 13:53:32 ', '入库', 1042);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu003              ', '2022/06/26 16:25:46 ', '入库', 1000);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu001              ', '2022/06/26 17:28:50 ', '入库', 1027);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu008              ', '2022/06/30 13:52:54 ', '入库', 1040);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu004              ', '2022/06/26 17:28:50 ', '入库', 1028);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu002              ', '2022/06/26 17:28:50 ', '入库', 1029);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu005              ', '2022/06/26 17:28:50 ', '入库', 1030);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu006              ', '2022/06/29 01:31:13 ', '入库', 1022);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu010              ', '2022/06/30 13:53:32 ', '入库', 1041);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu012              ', '2022/06/30 13:53:32 ', '入库', 1043);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu007              ', '2022/06/30 13:40:05 ', '入库', 1038);
insert into MAINTAIN (manid, bookid, maintaintime, maintainbook, id)
values ('manager1            ', 'xxu007              ', '2022/06/30 13:50:18 ', '删除', 1039);
commit;
prompt 14 records loaded
prompt Loading OPERATE...
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999001', '2022/06/26 18:15:34 ', '入库', 1024);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999002', '2022/06/26 18:15:34 ', '入库', 1000);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99991016', '2022/06/30 13:51:57 ', '入库', 1033);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99991020', '2022/06/30 13:51:57 ', '入库', 1034);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99991024', '2022/06/30 13:51:57 ', '入库', 1031);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99991019', '2022/06/30 13:51:57 ', '入库', 1032);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999003', '2022/06/29 01:52:12 ', '入库', 1020);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999004', '2022/06/29 02:52:43 ', '入库', 1021);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999005', '2022/06/29 02:52:43 ', '入库', 1022);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999006', '2022/06/29 02:52:43 ', '入库', 1023);
insert into OPERATE (manid, stuid, operatetime, operatestu, id)
values ('manager1            ', '99999007', '2022/06/30 13:51:08 ', '入库', 1030);
commit;
prompt 11 records loaded
prompt Enabling foreign key constraints for BORROW...
alter table BORROW enable constraint BORR_BOOKID_FK;
alter table BORROW enable constraint BORR_STUID_FK;
prompt Enabling foreign key constraints for MAINTAIN...
alter table MAINTAIN enable constraint BOOKID_FK;
alter table MAINTAIN enable constraint MANID_FK;
prompt Enabling foreign key constraints for OPERATE...
alter table OPERATE enable constraint OPER_MANID_FK;
alter table OPERATE enable constraint OPER_STUID_FK;
prompt Enabling triggers for BOOK...
alter table BOOK enable all triggers;
prompt Enabling triggers for STUDENT...
alter table STUDENT enable all triggers;
prompt Enabling triggers for BORROW...
alter table BORROW enable all triggers;
prompt Enabling triggers for MANAGER...
alter table MANAGER enable all triggers;
prompt Enabling triggers for MAINTAIN...
alter table MAINTAIN enable all triggers;
prompt Enabling triggers for OPERATE...
alter table OPERATE enable all triggers;
set feedback on
set define on
prompt Done.
