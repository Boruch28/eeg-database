CREATE TABLE "STIMULUS_TYPE_GROUP_REL" (
	"STIMULUS_TYPE_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL,
PRIMARY KEY ("RESEARCH_GROUP_ID", "STIMULUS_TYPE_ID") 
)
;
