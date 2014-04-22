alter session set nls_language = 'AMERICAN';
alter session set nls_territory = 'AMERICA';
drop table lilacs_account cascade constraints purge ;
drop table lilacs_bill cascade constraints purge;
drop table lilacs_bill_item cascade constraints purge;
drop table lilacs_cost cascade constraints purge;
drop table lilacs_host cascade constraints purge;
drop table lilacs_service cascade constraints purge;
drop table lilacs_service_detail cascade constraints purge;
drop table lilacs_age_segment cascade constraints purge;
drop table lilacs_ability cascade constraints purge;
drop table lilacs_admin_info cascade constraints purge;
drop table lilacs_ADMIN_ROLE cascade constraints purge;
drop table lilacs_ROLE_INFO cascade constraints purge;
drop table lilacs_ROLE_PRIVILEGE cascade constraints purge;
drop sequence lilacs_cost_id;
drop sequence lilacs_admin_info_id;
drop sequence lilacs_account_id;
drop sequence lilacs_service_id;
drop sequence lilacs_ROLE_INFO_id;
--服务器信息表(基础)o
create table lilacs_host
(id			varchar2(15)	constraint lilacs_host_id_pk primary key,
 name			varchar2(20)	not null constraint lilacs_hose_name_uk unique,
 location		varchar2(20));
--资费信息表(基础)o
create table lilacs_cost
(id 			number(4)	constraint lilacs_cost_id_pk primary key,
 name			varchar2(50)	not null,
 base_duration		number(11),
 base_cost		number(7,2),
 unit_cost		number(7,4),
 status			char(1)		check(status in (0,1)),
 descr			varchar2(100),
 creattime		date		default sysdate,
 costtype		number(1)	check(costtype in(1,2,3)),
 startime		date);
--账务信息表(账单信息表的父表)o
create table lilacs_account
(id 			number(9) 	constraint lilacs_account_id_pk primary key,
 recommender_id		number(9) 	constraint lilacs_account_recid_fk references lilacs_account(id),
 login_name		varchar2(30)	not null constraint lilacs_account_login_name_uk unique,
 longin_passwd		varchar2(8)	not null,
 status			char(1)		not null,
 create_date 		date		default sysdate,
 pause_date		date,
 close_date		date,
 real_name		varchar2(20)	not null,
 idcard_no		char(18)	not null constraint lilacs_account_idcard_uk unique,
 birthdate		date,
 gender			char(1)		check(gender in(0,1)),
 occupation		varchar2(50),
 telephone		varchar2(15)	not null,
 email			varchar2(50),
 mailaddress		varchar2(50),
 zipcooe		char(6),
 qq			varchar2(15),
 last_login_time	date,
 last_login_ip		varchar2(15));
--账单信息表(账单条目标的父表)
create table lilacs_bill
(id 			number(11)	constraint lilacs_bill_id_pk primary key,
 account_id		number(9)	constraint lilacs_account_id_fk references lilacs_account(id),
 bill_month		char(6),
 cost			number(13,2),
 payment_mode 		char(1)		check (payment_mode in(0,1,2,3)),
 pay_state		char(1)		check (pay_state in(0,1)));
--账务信息表o
create table lilacs_service
(id 			number(10)	constraint lilacs_serid_pk primary key,
 account_id		number(9)	constraint lilacs_service_accid_fk references lilacs_account(id) on delete cascade,
 unix_host		varchar2(15)	constraint lilacs_service_host_fk references lilacs_host(id) on delete cascade,
 os_username		varchar2(8)	not null,
 login_passwd		varchar2(8)	not null,
 status			char(1)		check(status in (0,1,2)),
 create_date		date		default sysdate,
 pause_date		date,
 close_date		date,
 cost_id		number(4)	constraint lilacs_service_cosdid_fk references lilacs_cost(id) on delete set null,
 constraint lilacs_shostname_uk unique(unix_host,os_username));
--业务详单表
create table lilacs_service_detail
(id			number(11)	constraint lilacs_detail_id_pk primary key,
 service_id		number(10)	constraint lilacs_detail_serviceid_fk references lilacs_account(id),
 client_host		varchar2(15),
 os_username		varchar2(8),
 pid			number(11),
 login_time		date,
 logout_time		date,
 duration		number(20,9),
 cost			number(20,6));	
 --账单条目表					
create table lilacs_bill_item
(item_id 		number(11)	constraint lilacs_bill_item_id_pk primary key,
 bill_id		number(11)	not null constraint lilacs_billit_id_fk references lilacs_bill(id),
 service_id		number(10)	not null constraint lilacs_billit_serid_fk references lilacs_service(id),
 cost			number(13,2));	
--时长信息表
--create table lilacs_month_duration
--(service_id		number(10),
-- month_id		char(6),
--service_datail,
--sofar_duration 	number(11) 	null);
--权限表
create table lilacs_ability(id number(1)  not null primary key, ablity_type varchar2(20) not null constraint lilacs_ability_type unique);
--管理员表
create table lilacs_admin_info
(id 			number(4)	constraint lilacs_admin_id_pk primary key,
 admin_code		varchar2(30)	not null constraint lilacs_admin_code_uk unique,
 password		varchar2(8)	not null,
 name			varchar2(20)	not null,
 telephone		varchar2(15)	null,
 email			varchar2(50)	null,
 enrolldate		date		not null);
--角色表
 create table lilacs_ROLE_INFO
 (id		       number(11)	not null constraint lilacs_roleinfo_id_pk primary key,
  name	       varchar2(20)	not null);
--角色权限表
create table lilacs_ROLE_PRIVILEGE
(ROLE_ID	       	number(4)	not null constraint lilacs_proleid references lilacs_role_info(id) on delete cascade,
 PRIVILEGE_ID 		number(4)	not null constraint lilacs_privilegeid references lilacs_ability(id) on delete cascade,
 constraint lilacs_rp_uk unique(ROLE_ID,PRIVILEGE_ID));
--管理员角色表
create table lilacs_ADMIN_ROLE
(ADMIN_ID		number(4)	constraint lilacs_adminrid_pk references lilacs_admin_info(id) on delete cascade, 
 ROLE_ID			number(4)	constraint lilacs_adminr_pk references lilacs_role_info(id) on delete cascade,
 constraint lilacs_adminr_uk unique(admin_id,role_id));

--年龄分段信息表
create table lilacs_age_segment
(id			number(1)	constraint lilacs_segment_id_pk primary key,
 name			varchar2(20)	not null,
 lowage			number(2),
 hinge			number(2));
--插入lilacs_ability记录
insert into lilacs_ability values(1,'管理员管理');
insert into lilacs_ability values(2,'角色管理');
insert into lilacs_ability values(3,'资费管理');
insert into lilacs_ability values(4,'账务账号管理');
insert into lilacs_ability values(5,'业务账号管理');
insert into lilacs_ability values(6,'账单管理');
insert into lilacs_ability values(7,'报表管理');

insert into lilacs_host values ('192.168.0.26','服务器26','beijing');
insert into lilacs_host values ('192.168.0.20','服务器20','beijing');
insert into lilacs_host values ('192.168.0.23','服务器23','beijing');
insert into lilacs_host values ('192.168.0.200','服务器200','beijing');

alter session set nls_language = 'SIMPLIFIED CHINESE';
alter session set nls_territory = 'CHINA';
--(cost)资费id主键
create sequence lilacs_cost_id start with 1 increment by 1; 
--(lilacs_admin_info)管理员id主键
create sequence lilacs_admin_info_id start with 2000 increment by 1;
--(lilacs_account)账务账号id主键
create sequence lilacs_account_id start with 4000 increment by 1;
--(lilacs_service)业务账号id主键
create sequence lilacs_service_id start with 6000 increment by 1;
--(lilacs_ROLE_INFO)角色id主键
create sequence lilacs_role_info_id start with 1000 increment by 1;
commit;
select * from lilacs_cost;
select * from lilacs_host; 
select * from lilacs_account;
select * from lilacs_service;
select * from lilacs_ability;
select * from lilacs_admin_info;
select * from lilacs_ADMIN_ROLE;
select * from lilacs_ROLE_INFO;
select * from lilacs_ROLE_PRIVILEGE;
