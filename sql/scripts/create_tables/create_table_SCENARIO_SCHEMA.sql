CREATE TABLE "SCENARIO_SCHEMA" (
  "SCHEMA_ID" NUMBER(*,0) NOT NULL ENABLE,
  "SCHEMA_NAME" VARCHAR2(20 BYTE),
  "HBM_XML" CLOB,
  "POJO" CLOB,
  "BEAN" VARCHAR2 (1000),
  "SQL" CLOB,
  "DESCRIPTION" VARCHAR2 (2000),
  "APPROVED" CHAR,
  CONSTRAINT "SCENARIO_SCHEMAS_PK" PRIMARY KEY ("SCHEMA_ID")

  );