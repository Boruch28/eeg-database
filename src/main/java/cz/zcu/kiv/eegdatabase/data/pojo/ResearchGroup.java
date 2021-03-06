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
 *   ResearchGroup.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.pojo;

// Generated 2.12.2013 0:56:28 by Hibernate Tools 3.4.0.CR1
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import cz.zcu.kiv.formgen.annotation.Form;

import org.hibernate.annotations.GenericGenerator;

import cz.zcu.kiv.formgen.annotation.FormId;
import cz.zcu.kiv.formgen.annotation.FormItem;
import cz.zcu.kiv.formgen.annotation.FormItemRestriction;
import cz.zcu.kiv.formgen.annotation.PreviewLevel;

/**
 * ResearchGroup generated by hbm2java
 */
@Form
@Entity
@Table(name = "RESEARCH_GROUP")
public class ResearchGroup implements java.io.Serializable, Comparable<ResearchGroup> {

	@FormId
	private int researchGroupId;
	@FormItem(required = true, label = "Owner")
	private Person person;
	@FormItem(required = true, preview = PreviewLevel.MAJOR)
	@FormItemRestriction(maxLength = 100)
	private String title;
	@FormItem(required = true, preview = PreviewLevel.MINOR)
	@FormItemRestriction(maxLength = 250)
	private String description;
	@FormItem
	private boolean paidAccount;
	private boolean lock = false;
	
	private Set<ResearchGroupMembership> researchGroupMemberships = new HashSet<ResearchGroupMembership>(
					0);
	private Set<Keywords> keywords = new HashSet<Keywords>(0);
	private Set<HardwareGroupRel> hardwareGroupRels = new HashSet<HardwareGroupRel>(
					0);
	private Set<WeatherGroupRel> weatherGroupRels = new HashSet<WeatherGroupRel>(
					0);
	private Set<Scenario> scenarios = new HashSet<Scenario>(0);
	private Set<Experiment> experiments = new HashSet<Experiment>(0);
	private Set<GroupPermissionRequest> requests = new HashSet<GroupPermissionRequest>(
					0);
	private Set<Hardware> hardwares = new HashSet<Hardware>(0);
	private Set<Weather> weathers = new HashSet<Weather>(0);
	private Set<FileMetadataParamDefGroupRel> fileMetadataParamDefGroupRels = new HashSet<FileMetadataParamDefGroupRel>(
					0);
	private Set<FileMetadataParamDef> fileMetadataParamDefs = new HashSet<FileMetadataParamDef>(
					0);
	private Set<PersonOptParamDefGroupRel> personOptParamDefGroupRels = new HashSet<PersonOptParamDefGroupRel>(
					0);
	private Set<PersonOptParamDef> personOptParamDefs = new HashSet<PersonOptParamDef>(
					0);
	private Set<ExperimentOptParamDefGroupRel> experimentOptParamDefGroupRels = new HashSet<ExperimentOptParamDefGroupRel>(
					0);
	private Set<ExperimentOptParamDef> experimentOptParamDefs = new HashSet<ExperimentOptParamDef>(
					0);
	private Set<Person> articlesSubscribers = new HashSet<Person>(0);
	private Set<Artifact> artifacts = new HashSet<Artifact>(0);
	
	
	
	
    private Set<Analysis> analyses = new HashSet<Analysis>(0);
    private Set<ArtifactRemoveMethod> artifactRemoveMethods = new HashSet<ArtifactRemoveMethod>(0);
    private Set<Digitization> digitizations = new HashSet<Digitization>(0);
    private Set<Disease> diseases = new HashSet<Disease>(0);
    private Set<EducationLevel> educationLevels = new HashSet<EducationLevel>(0);
    private Set<ElectrodeFix> electrodeFixes = new HashSet<ElectrodeFix>(0);
    private Set<ElectrodeLocation> electrodeLocations = new HashSet<ElectrodeLocation>(0);
    private Set<ElectrodeSystem> electrodeSystems = new HashSet<ElectrodeSystem>(0);
    private Set<ElectrodeType> electrodeTypes = new HashSet<ElectrodeType>(0);
    private Set<Pharmaceutical> pharmaceuticals = new HashSet<Pharmaceutical>(0);
    private Set<ProjectType> projectTypes = new HashSet<ProjectType>(0);
    private Set<Software> softwares = new HashSet<Software>(0);
    private Set<StimulusType> stimulusTypes = new HashSet<StimulusType>(0);
    private Set<ResearchGroupMembershipPlan> researchGroupMembershipPlans = new HashSet<ResearchGroupMembershipPlan>(0);

	@ManyToMany(mappedBy = "researchGroups")
	public Set<Analysis> getAnalyses() {
		return analyses;
	}

	public void setAnalyses(Set<Analysis> analyses) {
		this.analyses = analyses;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<ArtifactRemoveMethod> getArtifactRemoveMethods() {
		return artifactRemoveMethods;
	}

	public void setArtifactRemoveMethods(Set<ArtifactRemoveMethod> artifactRemoveMethods) {
		this.artifactRemoveMethods = artifactRemoveMethods;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<Digitization> getDigitizations() {
		return digitizations;
	}

	public void setDigitizations(Set<Digitization> digitizations) {
		this.digitizations = digitizations;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<EducationLevel> getEducationLevels() {
		return educationLevels;
	}

	public void setEducationLevels(Set<EducationLevel> educationLevels) {
		this.educationLevels = educationLevels;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<ElectrodeFix> getElectrodeFixes() {
		return electrodeFixes;
	}

	public void setElectrodeFixes(Set<ElectrodeFix> electrodeFixes) {
		this.electrodeFixes = electrodeFixes;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<ElectrodeLocation> getElectrodeLocations() {
		return electrodeLocations;
	}

	public void setElectrodeLocations(Set<ElectrodeLocation> electrodeLocations) {
		this.electrodeLocations = electrodeLocations;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<ElectrodeSystem> getElectrodeSystems() {
		return electrodeSystems;
	}

	public void setElectrodeSystems(Set<ElectrodeSystem> electrodeSystems) {
		this.electrodeSystems = electrodeSystems;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<ElectrodeType> getElectrodeTypes() {
		return electrodeTypes;
	}

	public void setElectrodeTypes(Set<ElectrodeType> electrodeTypes) {
		this.electrodeTypes = electrodeTypes;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<Pharmaceutical> getPharmaceuticals() {
		return pharmaceuticals;
	}

	public void setPharmaceuticals(Set<Pharmaceutical> pharmaceuticals) {
		this.pharmaceuticals = pharmaceuticals;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<ProjectType> getProjectTypes() {
		return projectTypes;
	}

	public void setProjectTypes(Set<ProjectType> projectTypes) {
		this.projectTypes = projectTypes;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(Set<Software> softwares) {
		this.softwares = softwares;
	}

	@ManyToMany(mappedBy = "researchGroups")
	public Set<StimulusType> getStimulusTypes() {
		return stimulusTypes;
	}

	public void setStimulusTypes(Set<StimulusType> stimulusTypes) {
		this.stimulusTypes = stimulusTypes;
	}
		
		
		

	public ResearchGroup() {
	}

	public ResearchGroup(int researchGroupId, Person person, String title, String description) {
		this.researchGroupId = researchGroupId;
		this.person = person;
		this.title = title;
		this.description = description;
	}

	public ResearchGroup(Person person, String title, String description) {
		this.person = person;
		this.title = title;
		this.description = description;
	}

	public ResearchGroup(Person person, String title, String description,
					boolean paidAccount,
					Set<ResearchGroupMembership> researchGroupMemberships,
					Set<Keywords> keywords, Set<HardwareGroupRel> hardwareGroupRels,
					Set<WeatherGroupRel> weatherGroupRels, Set<Scenario> scenarios,
					Set<Experiment> experiments,
					Set<GroupPermissionRequest> requests, Set<Hardware> hardwares,
					Set<Weather> weathers,
					Set<FileMetadataParamDefGroupRel> fileMetadataParamDefGroupRels,
					Set<FileMetadataParamDef> fileMetadataParamDefs,
					Set<PersonOptParamDefGroupRel> personOptParamDefGroupRels,
					Set<PersonOptParamDef> personOptParamDefs,
					Set<ExperimentOptParamDefGroupRel> experimentOptParamDefGroupRels,
					Set<ExperimentOptParamDef> experimentOptParamDefs,
					Set<Person> articlesSubscribers, Set<Artifact> artifacts) {
		this.person = person;
		this.title = title;
		this.description = description;
		this.paidAccount = paidAccount;
		this.researchGroupMemberships = researchGroupMemberships;
		this.keywords = keywords;
		this.hardwareGroupRels = hardwareGroupRels;
		this.weatherGroupRels = weatherGroupRels;
		this.scenarios = scenarios;
		this.experiments = experiments;
		this.requests = requests;
		this.hardwares = hardwares;
		this.weathers = weathers;
		this.fileMetadataParamDefGroupRels = fileMetadataParamDefGroupRels;
		this.fileMetadataParamDefs = fileMetadataParamDefs;
		this.personOptParamDefGroupRels = personOptParamDefGroupRels;
		this.personOptParamDefs = personOptParamDefs;
		this.experimentOptParamDefGroupRels = experimentOptParamDefGroupRels;
		this.experimentOptParamDefs = experimentOptParamDefs;
		this.articlesSubscribers = articlesSubscribers;
		this.artifacts = artifacts;
	}

	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "RESEARCH_GROUP_ID", nullable = false, precision = 22, scale = 0)
	public int getResearchGroupId() {
		return this.researchGroupId;
	}

	public void setResearchGroupId(int researchGroupId) {
		this.researchGroupId = researchGroupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OWNER_ID", nullable = false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Column(name = "TITLE", nullable = false, length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "DESCRIPTION", nullable = false, length = 250)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PAID_ACCOUNT", precision = 1, scale = 0)
	public boolean isPaidAccount() {
		return this.paidAccount;
	}

	public void setPaidAccount(boolean paidAccount) {
		this.paidAccount = paidAccount;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<ResearchGroupMembership> getResearchGroupMemberships() {
		return this.researchGroupMemberships;
	}

	public void setResearchGroupMemberships(
					Set<ResearchGroupMembership> researchGroupMemberships) {
		this.researchGroupMemberships = researchGroupMemberships;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<Keywords> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Set<Keywords> keywords) {
		this.keywords = keywords;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<HardwareGroupRel> getHardwareGroupRels() {
		return this.hardwareGroupRels;
	}

	public void setHardwareGroupRels(Set<HardwareGroupRel> hardwareGroupRels) {
		this.hardwareGroupRels = hardwareGroupRels;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<WeatherGroupRel> getWeatherGroupRels() {
		return this.weatherGroupRels;
	}

	public void setWeatherGroupRels(Set<WeatherGroupRel> weatherGroupRels) {
		this.weatherGroupRels = weatherGroupRels;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<Scenario> getScenarios() {
		return this.scenarios;
	}

	public void setScenarios(Set<Scenario> scenarios) {
		this.scenarios = scenarios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<Experiment> getExperiments() {
		return this.experiments;
	}

	public void setExperiments(Set<Experiment> experiments) {
		this.experiments = experiments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<GroupPermissionRequest> getRequests() {
		return this.requests;
	}

	public void setRequests(Set<GroupPermissionRequest> requests) {
		this.requests = requests;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "researchGroups")
	public Set<Hardware> getHardwares() {
		return this.hardwares;
	}

	public void setHardwares(Set<Hardware> hardwares) {
		this.hardwares = hardwares;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "researchGroups")
	public Set<Weather> getWeathers() {
		return this.weathers;
	}

	public void setWeathers(Set<Weather> weathers) {
		this.weathers = weathers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<FileMetadataParamDefGroupRel> getFileMetadataParamDefGroupRels() {
		return this.fileMetadataParamDefGroupRels;
	}

	public void setFileMetadataParamDefGroupRels(
					Set<FileMetadataParamDefGroupRel> fileMetadataParamDefGroupRels) {
		this.fileMetadataParamDefGroupRels = fileMetadataParamDefGroupRels;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "researchGroups")
	public Set<FileMetadataParamDef> getFileMetadataParamDefs() {
		return this.fileMetadataParamDefs;
	}

	public void setFileMetadataParamDefs(
					Set<FileMetadataParamDef> fileMetadataParamDefs) {
		this.fileMetadataParamDefs = fileMetadataParamDefs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<PersonOptParamDefGroupRel> getPersonOptParamDefGroupRels() {
		return this.personOptParamDefGroupRels;
	}

	public void setPersonOptParamDefGroupRels(
					Set<PersonOptParamDefGroupRel> personOptParamDefGroupRels) {
		this.personOptParamDefGroupRels = personOptParamDefGroupRels;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "researchGroups")
	public Set<PersonOptParamDef> getPersonOptParamDefs() {
		return this.personOptParamDefs;
	}

	public void setPersonOptParamDefs(Set<PersonOptParamDef> personOptParamDefs) {
		this.personOptParamDefs = personOptParamDefs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "researchGroup")
	public Set<ExperimentOptParamDefGroupRel> getExperimentOptParamDefGroupRels() {
		return this.experimentOptParamDefGroupRels;
	}

	public void setExperimentOptParamDefGroupRels(
					Set<ExperimentOptParamDefGroupRel> experimentOptParamDefGroupRels) {
		this.experimentOptParamDefGroupRels = experimentOptParamDefGroupRels;
	}

    @OneToMany(mappedBy="researchGroup")
    public Set<ResearchGroupMembershipPlan> getResearchGroupMembershipPlans() {
        return researchGroupMembershipPlans;
    }

    public void setResearchGroupMembershipPlans(Set<ResearchGroupMembershipPlan> researchGroupMembershipPlans) {
        this.researchGroupMembershipPlans = researchGroupMembershipPlans;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "researchGroups")
	public Set<ExperimentOptParamDef> getExperimentOptParamDefs() {
		return this.experimentOptParamDefs;
	}

	public void setExperimentOptParamDefs(
					Set<ExperimentOptParamDef> experimentOptParamDefs) {
		this.experimentOptParamDefs = experimentOptParamDefs;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ARTICLES_GROUP_SUBSCRIBTIONS", joinColumns = {
		@JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
		@JoinColumn(name = "PERSON_ID", nullable = false, updatable = false)})
	public Set<Person> getArticlesSubscribers() {
		return this.articlesSubscribers;
	}

	public void setArticlesSubscribers(Set<Person> articlesSubscribers) {
		this.articlesSubscribers = articlesSubscribers;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ARTEFACT_GROUP_REL", joinColumns = {
		@JoinColumn(name = "RESEARCH_GROUP_ID", nullable = false, updatable = false)}, inverseJoinColumns = {
		@JoinColumn(name = "ARTEFACT_ID", nullable = false, updatable = false)})
	public Set<Artifact> getArtifacts() {
		return this.artifacts;
	}

	public void setArtifacts(Set<Artifact> artifacts) {
		this.artifacts = artifacts;
	}
	
	@Column(name="LOCK" ,nullable=false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	public boolean isLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    @Override
    public int compareTo(ResearchGroup o) {
        
        // easy compare for titles.
        if(o == null)
            return 1;
        
        if(getTitle() == null)
            return -1;
        
        if(o.getTitle() == null)
            return 1;
        
        return getTitle().compareTo(o.getTitle());
    }
}
