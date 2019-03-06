delete from SAMPLE_ROLE_RESOURCE
GO

delete from SAMPLE_RESOURCE
GO

delete from SAMPLE_SYSTEM_LOG
GO

delete from SAMPLE_USER
GO

delete from SAMPLE_ROLE
GO


Insert into SAMPLE_ROLE (ID,INFO,LIST,NAME,SYSTEM) values ('admin',null,1,'系统管理员',1)
GO


Insert into SAMPLE_USER (ID,EMAIL,FAX,LOGIN_NAME,MALE,MOBILE,NAME,NAME_PY,PASSWD,STATUS,TEL,ROLE_ID) values ('admin','peream@gmail.com','89056115','admin',1,'13691517931','管理员','gly','ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413','NORMAL','89055717','admin')
GO


Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('sample_column1',12,'default.gif',null,'COLUMN','栏目10','B_SAMPLE','app/sample/column1','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('sample_column2',2,'default.gif',null,'COLUMN','栏目2','B_SAMPLE','app/sample/column2','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('sample_column2_tab1',1,'default.gif','sample_column2','BOX_TAB','标签1','B_SAMPLE','app/sample/boxtab1','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('sample_column2_tab2',2,'default.gif','sample_column2','BOX_TAB','标签2','B_SAMPLE','app/sample/boxtab2','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('sample_column2_tab4',3,'default.gif','sample_column2','BOX_TAB','标签3','B_SAMPLE','app/sample/boxtab4','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_czrz',4,'default.gif',null,'COLUMN','操作日志','A_XTGL','app/system/oplog/manage','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_czrz_cx',4,'default.gif','xtgl_czrz','NOT_MENU','操作日志_查询','A_XTGL','app/system/oplog/manage','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_ddgl',5,'default.gif',null,'COLUMN','调度管理','A_XTGL','app/system/cron/ddgl','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_ddrw',5,null,'xtgl_ddgl','BOX_TAB','调度任务','A_XTGL','app/system/cron/manage','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_ddrw_plqd',99,'default.gif','xtgl_ddrw','NOT_MENU','调度任务_批量启动','A_XTGL','app/system/cron/startall','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_ddrw_pltz',99,'default.gif','xtgl_ddrw','NOT_MENU','调度任务_批量停止','A_XTGL','app/system/cron/stopall','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_ddrw_post',99,'default.gif','xtgl_ddrw','NOT_MENU','调度任务_查询','A_XTGL','app/system/cron/manage','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_ddrz',6,'default.gif','xtgl_ddgl','BOX_TAB','调度日志','A_XTGL','app/system/schedulelog/manage','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl',1,'default.gif',null,'COLUMN','角色管理','A_XTGL','app/acl/role/manage','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_add',99,null,'xtgl_jsgl','NOT_MENU','角色管理_添加','A_XTGL','app/acl/role/add','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_add_post',99,'default.gif','xtgl_jsgl','NOT_MENU','角色管理_添加','A_XTGL','app/acl/role/add','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_conf',99,null,'xtgl_jsgl','NOT_MENU','角色管理_配置','A_XTGL','app/acl/role/conf/{id}','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_conf_post',99,'default.gif','xtgl_jsgl','NOT_MENU','角色管理_配置','A_XTGL','app/acl/role/conf','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_cx',1,'default.gif','xtgl_jsgl','NOT_MENU','角色管理_查询','A_XTGL','app/acl/role/manage','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_del',99,null,'xtgl_jsgl','NOT_MENU','角色管理_删除','A_XTGL','app/acl/role/delete/{id}','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_mod',99,null,'xtgl_jsgl','NOT_MENU','角色管理_修改','A_XTGL','app/acl/role/edit/{id}','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_jsgl_mod_post',99,'default.gif','xtgl_jsgl','NOT_MENU','角色管理_修改','A_XTGL','app/acl/role/edit','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl',2,null,null,'COLUMN','用户管理','A_XTGL','app/acl/user/manage','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl_add',99,null,'xtgl_yhgl','NOT_MENU','用户管理_添加','A_XTGL','app/acl/user/add','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl_add_post',99,'default.gif','xtgl_yhgl','NOT_MENU','用户管理_添加','A_XTGL','app/acl/user/add','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl_cx',2,'default.gif','xtgl_yhgl','NOT_MENU','用户管理_查询','A_XTGL','app/acl/user/manage','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl_mod',99,null,'xtgl_yhgl','NOT_MENU','用户管理_修改','A_XTGL','app/acl/user/edit/{id}','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl_mod_post',99,'default.gif','xtgl_yhgl','NOT_MENU','用户管理_修改','A_XTGL','app/acl/user/edit','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_yhgl_status',99,null,'xtgl_yhgl','NOT_MENU','用户管理_状态','A_XTGL','app/acl/user/status/{id}/{status}','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl',3,null,null,'COLUMN','资源管理','A_XTGL','app/acl/resource/manage','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl_add',99,null,'xtgl_zygl','NOT_MENU','资源管理_添加','A_XTGL','app/acl/resource/add','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl_add_post',99,'default.gif','xtgl_zygl','NOT_MENU','资源管理_添加','A_XTGL','app/acl/resource/add','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl_cx',3,'default.gif','xtgl_zygl','NOT_MENU','资源管理_查询','A_XTGL','app/acl/resource/manage','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl_del',99,null,'xtgl_zygl','NOT_MENU','资源管理_删除','A_XTGL','app/acl/resource/delete/{id}','POST')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl_mod',99,null,'xtgl_zygl','NOT_MENU','资源管理_修改','A_XTGL','app/acl/resource/edit/{id}','GET')
GO
Insert into SAMPLE_RESOURCE (ID,LIST,LOGO_PIC,MENU_ID,MENU_TYPE,NAME,TYPE_CODE,URL,REQUEST_METHOD) values ('xtgl_zygl_mod_post',99,'default.gif','xtgl_zygl','NOT_MENU','资源管理_修改','A_XTGL','app/acl/resource/edit','POST')
GO


Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('sample_column1','sample_column1','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('sample_column2','sample_column2','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('sample_column2_tab1','sample_column2_tab1','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('sample_column2_tab2','sample_column2_tab2','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_czrz','xtgl_czrz','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl','xtgl_jsgl','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_add','xtgl_jsgl_add','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_add_post','xtgl_jsgl_add_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_conf','xtgl_jsgl_conf','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_conf_post','xtgl_jsgl_conf_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_del','xtgl_jsgl_del','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_mod','xtgl_jsgl_mod','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_mod_post','xtgl_jsgl_mod_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl','xtgl_yhgl','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl_add','xtgl_yhgl_add','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl_add_post','xtgl_yhgl_add_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl_mod','xtgl_yhgl_mod','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl_mod_post','xtgl_yhgl_mod_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl_status','xtgl_yhgl_status','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl','xtgl_zygl','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl_add','xtgl_zygl_add','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl_add_post','xtgl_zygl_add_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl_del','xtgl_zygl_del','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl_mod','xtgl_zygl_mod','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl_mod_post','xtgl_zygl_mod_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_ddrw','xtgl_ddrw','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_ddrw_post','xtgl_ddrw_post','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_ddgl','xtgl_ddgl','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_ddrw_pltz','xtgl_ddrw_pltz','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_ddrw_plqd','xtgl_ddrw_plqd','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_czrz_cx','xtgl_czrz_cx','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_jsgl_cx','xtgl_jsgl_cx','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_yhgl_cx','xtgl_yhgl_cx','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_zygl_cx','xtgl_zygl_cx','admin')
GO
Insert into SAMPLE_ROLE_RESOURCE (ID,RESOURCE_ID,ROLE_ID) values ('xtgl_ddrz','xtgl_ddrz','admin')
GO
