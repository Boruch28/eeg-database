CREATE TABLE "ELECTRODE_FIX_GROUP_REL" (
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"ELECTRODE_FIX_ID" INTEGER NOT NULL ,
PRIMARY KEY ("ELECTRODE_FIX_ID", "RESEARCH_GROUP_ID") 
)
;
