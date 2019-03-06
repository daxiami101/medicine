--------------------------------------------------------
--  文件已创建 - 星期四-五月-26-2016   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table SAMPLE_APP_CLIENT
--------------------------------------------------------

   CREATE TABLE "SAMPLE_APP_CLIENT" ("ID" VARCHAR2(32), "NAME" VARCHAR2(200), "SECRET" VARCHAR2(20), "REDIRECT_URI" VARCHAR2(200), "SCOPE" VARCHAR2(200), "CREATE_TIME" DATE, "STATUS" VARCHAR2(20), "CLIENT_ID" VARCHAR2(20)); 
   COMMENT ON COLUMN "SAMPLE_APP_CLIENT"."NAME" IS '应用名称';
   COMMENT ON COLUMN "SAMPLE_APP_CLIENT"."SECRET" IS '秘钥';
   COMMENT ON COLUMN "SAMPLE_APP_CLIENT"."SCOPE" IS '权限范围';
   COMMENT ON COLUMN "SAMPLE_APP_CLIENT"."CREATE_TIME" IS '创建时间';
   COMMENT ON COLUMN "SAMPLE_APP_CLIENT"."STATUS" IS '状态';
   COMMENT ON COLUMN "SAMPLE_APP_CLIENT"."CLIENT_ID" IS 'client_id';
   commit;
--------------------------------------------------------
--  DDL for Table SAMPLE_OAUTH_REFRESH_TOKEN
--------------------------------------------------------

  CREATE TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" ("ID" VARCHAR2(32), "TOKEN" VARCHAR2(32), "USER_ID" VARCHAR2(32), "CLIENT_ID" VARCHAR2(20), "CREATE_TIME" DATE);
  commit;
--------------------------------------------------------
--  DDL for Table SAMPLE_PERSON_ACCOUNT
--------------------------------------------------------

  CREATE TABLE "SAMPLE_PERSON_ACCOUNT" ("ID" VARCHAR2(32), "NAME" VARCHAR2(200), "MOBILE" VARCHAR2(20), "PASSWD" VARCHAR2(32), "ADDRESS" VARCHAR2(200), "ID_TYPE" VARCHAR2(20), "ID_CODE" VARCHAR2(32), "REGISTER_TIME" DATE, "POST_CODE" VARCHAR2(20), "EMAIL" VARCHAR2(100));
  commit;
--------------------------------------------------------
--  DDL for Index SAMPLE_CLIENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAMPLE_CLIENT_PK" ON "SAMPLE_APP_CLIENT" ("ID");
--------------------------------------------------------
--  DDL for Index SAMPLE_OAUTH_REFRESH_TOKEN_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAMPLE_OAUTH_REFRESH_TOKEN_PK" ON "SAMPLE_OAUTH_REFRESH_TOKEN" ("ID");
--------------------------------------------------------
--  DDL for Index SAMPLE_APP_CLIENT_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAMPLE_APP_CLIENT_UK1" ON "SAMPLE_APP_CLIENT" ("CLIENT_ID");
--------------------------------------------------------
--  DDL for Index TABLE1_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAMPLE_PERSON_ACCOUNT_PK" ON "SAMPLE_PERSON_ACCOUNT" ("ID");
--------------------------------------------------------
--  DDL for Index SAMPLE_USER_UK1
--------------------------------------------------------

  CREATE UNIQUE INDEX "SAMPLE_PERSON_ACCOUNT_UK1" ON "SAMPLE_PERSON_ACCOUNT" ("MOBILE");
--------------------------------------------------------
--  Constraints for Table SAMPLE_APP_CLIENT
--------------------------------------------------------

  ALTER TABLE "SAMPLE_APP_CLIENT" ADD CONSTRAINT "SAMPLE_APP_CLIENT_UK1" UNIQUE ("CLIENT_ID") USING INDEX  ENABLE;
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("CLIENT_ID" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" ADD CONSTRAINT "SAMPLE_CLIENT_PK" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("STATUS" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("CREATE_TIME" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("SCOPE" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("REDIRECT_URI" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("SECRET" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_APP_CLIENT" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SAMPLE_OAUTH_REFRESH_TOKEN
--------------------------------------------------------

  ALTER TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" ADD CONSTRAINT "SAMPLE_OAUTH_REFRESH_TOKEN_PK" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" MODIFY ("CREATE_TIME" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" MODIFY ("CLIENT_ID" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" MODIFY ("TOKEN" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_OAUTH_REFRESH_TOKEN" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table SAMPLE_PERSON_ACCOUNT
--------------------------------------------------------

  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("REGISTER_TIME" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("ID_CODE" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("ID_TYPE" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" ADD CONSTRAINT "SAMPLE_PERSON_ACCOUNT_UK1" UNIQUE ("MOBILE") USING INDEX  ENABLE;
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" ADD CONSTRAINT "SAMPLE_PERSON_ACCOUNT_PK" PRIMARY KEY ("ID") USING INDEX  ENABLE;
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("PASSWD" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("MOBILE" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SAMPLE_PERSON_ACCOUNT" MODIFY ("ID" NOT NULL ENABLE);
  commit;
  
  Insert into SAMPLE_PERSON_ACCOUNT (ID,NAME,MOBILE,PASSWD,ADDRESS,ID_TYPE,ID_CODE,REGISTER_TIME,POST_CODE,EMAIL) values ('a1db0298308341a1990c88b301020e78','luhj','15210058800','6hC112n8P801e81006ei6CeACeCCnnn6','深圳市','JMSFZ','370782199010014770',to_date('2016-05-16 17:27:40','YYYY-MM-DD HH24:MI:SS'),'510000','123456@qq.com');
  Insert into SAMPLE_APP_CLIENT (ID,NAME,SECRET,REDIRECT_URI,SCOPE,CREATE_TIME,STATUS,CLIENT_ID) values ('eb696343e18c4f678c5f3d5f98cd8a1c','百度','123456','http://localhost','CARINFO,USERINFO',to_date('2006-05-12 15:34:35','YYYY-MM-DD HH24:MI:SS'),'NORMAL','000001');
  commit;
