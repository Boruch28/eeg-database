/*******************************************************************************
 * This file is part of the EEG-database project
 * 
 *   ==========================================
 *  
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *  
 *  ***********************************************************************************************************************
 *  
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *  
 *       http://www.apache.org/licenses/LICENSE-2.0
 *  
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *  
 *  ***********************************************************************************************************************
 *  
 *   Scenario.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;
// Generated 19.1.2010 23:18:53 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import cz.zcu.kiv.eegdatabase.data.annotation.Indexed;
import cz.zcu.kiv.eegdatabase.data.annotation.SolrField;
import cz.zcu.kiv.eegdatabase.data.annotation.SolrId;
import cz.zcu.kiv.eegdatabase.logic.indexing.IndexField;

/**
 * Scenario generated by hbm2java
 */
@Entity
@Indexed
@javax.persistence.Table(name = "SCENARIO")
//@Analyzer(impl = StandardAnalyzer.class)
public class Scenario implements Serializable, Comparable<Scenario> {

    private static final long serialVersionUID = -7936938040997855699L;
    
    //@DocumentId
    @SolrId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SCENARIO_ID")
    private int scenarioId;
     @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "RESEARCH_GROUP_ID")
    private ResearchGroup researchGroup;
    /*
    @Fields({
            @Field(name = "title"),
            @Field(store = Store.YES)}) //use a different field name
    */
    @SolrField(name = IndexField.TITLE)
    @Column(name = "TITLE")
    private String title;
    /*
    @Fields({
            @Field(name = "scenarioLength"),
            @Field(store = Store.YES)})
    */
    @Column(name = "SCENARIO_LENGTH")
    private int scenarioLength;
    /*
    @Fields({
            @Field(name = "description"),
            @Field(store = Store.YES)}) //use a different field name
    */
    @SolrField(name = IndexField.TEXT)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(mappedBy = "scenario")
    private Set<Experiment> experiments = new HashSet<Experiment>(0);
    @OneToMany(mappedBy = "scenario")
    private Set<History> histories = new HashSet<History>(0);
    @Column(name = "PRIVATE")
    private boolean privateScenario;
    private boolean userMemberOfGroup;
    @SolrField(name = IndexField.NAME)
    @Column(name = "SCENARIO_NAME")
    private String scenarioName;
    @Column(name = "MIMETYPE")
    private String mimetype;
    @OneToMany(mappedBy = "scenario")
    private Set<StimulusRel> stimulusRels = new HashSet<StimulusRel>(0);
    @ManyToOne
    @JoinColumn(name = "SCENARIO_ID")
    @Type(type = "cz.zcu.kiv.eegdatabase.data.pojo.ScenarioType")
    private IScenarioType scenarioType;
    @Column(name = "ORA_ROWSCN", insertable = false, updatable = false)
    private long scn;

    @Transient
    private String group;
    @Transient
    private Boolean availableFile;
    @Transient
    private Object dataFile;

    public Scenario() {
    }

    public Scenario(int scenarioId, Person person, ResearchGroup researchGroup) {
        this.scenarioId = scenarioId;
        this.person = person;
        this.researchGroup = researchGroup;
    }

    public Scenario(int scenarioId, Person person, ResearchGroup researchGroup,
                    String title, int scenarioLength, String description,
                    Set<Experiment> experiments, IScenarioType scenarioType) {
        this.scenarioId = scenarioId;
        this.person = person;
        this.researchGroup = researchGroup;
        this.title = title;
        this.scenarioLength = scenarioLength;
        this.description = description;
        this.experiments = experiments;
        this.scenarioType = scenarioType;
    }

    public int getScenarioId() {
        return this.scenarioId;
    }

    public void setScenarioId(int scenarioId) {
        this.scenarioId = scenarioId;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ResearchGroup getResearchGroup() {
        return this.researchGroup;
    }

    public void setResearchGroup(ResearchGroup researchGroup) {
        this.researchGroup = researchGroup;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScenarioLength() {
        return this.scenarioLength;
    }

    public void setScenarioLength(int scenarioLength) {
        this.scenarioLength = scenarioLength;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getScn() {
        return scn;
    }

    public Set<Experiment> getExperiments() {
        return this.experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    public void setPrivateScenario(boolean privateScenario) {
        this.privateScenario = privateScenario;
    }

    public boolean isPrivateScenario() {
        return this.privateScenario;
    }

    public void setUserMemberOfGroup(boolean userIsMember) {
        this.userMemberOfGroup = userIsMember;
    }

    public boolean isUserMemberOfGroup() {
        return userMemberOfGroup;
    }

    public Set<History> getHistories() {
        return histories;
    }

    public void setHistories(Set<History> histories) {
        this.histories = histories;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }

    public IScenarioType getScenarioType() {
        return scenarioType;
    }

    public void setScenarioType(IScenarioType scenarioDoc) {
        this.scenarioType = scenarioDoc;
    }

    public Set<StimulusRel> getStimulusRels() {
        return stimulusRels;
    }

    public void setStimulusRels(Set<StimulusRel> stimulusRels) {
        this.stimulusRels = stimulusRels;
    }

    public int compareTo(Scenario scen) {
        return this.title.compareTo(scen.getTitle());
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Boolean getAvailableFile() {
        return availableFile;
    }

    public void setAvailableFile(Boolean availableFile) {
        this.availableFile = availableFile;
    }

    public Object getDataFile() {
        return dataFile;
    }

    public void setDataFile(Object dataFile) {
        this.dataFile = dataFile;
    }

}


