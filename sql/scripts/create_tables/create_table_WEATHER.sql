CREATE TABLE "WEATHER" (
	"WEATHER_ID" INTEGER NOT NULL ,
	"DESCRIPTION" VARCHAR2 (30) CONSTRAINT "UNIQUE_WEATHER_DESCRIPTION" UNIQUE ,
	"TITLE" VARCHAR2 (30) NOT NULL  CONSTRAINT "UNIQUE_WHEATER_TITLE" UNIQUE ,
 CONSTRAINT "PK_WEATHER" PRIMARY KEY ("WEATHER_ID")
)
;