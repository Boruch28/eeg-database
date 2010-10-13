CREATE TABLE "SCENARIO" (
	"SCENARIO_ID" INTEGER NOT NULL ,
	"OWNER_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"TITLE" VARCHAR2 (40) CONSTRAINT "UNIQUE_SCENARIO_TITLE" UNIQUE ,
	"SCENARIO_LENGTH" SMALLINT,
	"SCENARIO_XML" CLOB,
	"DESCRIPTION" VARCHAR2 (255),
	"PRIVATE" INTEGER DEFAULT 0 NOT NULL ,
 CONSTRAINT "PK_SCENARIO" PRIMARY KEY ("SCENARIO_ID")
)
;