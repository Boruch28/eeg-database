package cz.zcu.kiv.eegdatabase.data.pojo;
// Generated 19.1.2010 23:18:53 by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ResearchGroup generated by hbm2java
 */
//@Entity
//@Indexed
public class ResearchGroup implements Serializable {
    //@DocumentId
    private int researchGroupId;
    private Person person;
    //  @Fields({
//    @Field(index = Index.TOKENIZED), //same property indexed multiple times
//    @Field(name = "RESEARCHGROUPTITLE")}) //use a different field name
    private String title;
    //  @Fields({
//    @Field(index = Index.TOKENIZED), //same property indexed multiple times
//    @Field(name = "RESEARCHGROUPDESCRIPTION")}) //use a different field name
    private String description;
    private Set<ResearchGroupMembership> researchGroupMemberships = new HashSet<ResearchGroupMembership>(0);
    private Set<Scenario> scenarios = new HashSet<Scenario>(0);
    private Set<Experiment> experiments = new HashSet<Experiment>(0);
    private Set<Person> usersWithThisAsDefault = new HashSet<Person>(0);
    private Set<GroupPermissionRequest> requests = new HashSet<GroupPermissionRequest>(0);
    private Set<Person> articlesSubscribers = new HashSet<Person>(0);
    private long scn;

    public ResearchGroup() {
    }

    public ResearchGroup(int researchGroupId, Person person, String title, String description) {
        this.researchGroupId = researchGroupId;
        this.person = person;
        this.title = title;
        this.description = description;
    }

    public ResearchGroup(int researchGroupId, Person person, String title, String description, Set<ResearchGroupMembership> researchGroupMemberships, Set<Scenario> scenarios, Set<Experiment> experiments) {
        this.researchGroupId = researchGroupId;
        this.person = person;
        this.title = title;
        this.description = description;
        this.researchGroupMemberships = researchGroupMemberships;
        this.scenarios = scenarios;
        this.experiments = experiments;
    }

    public int getResearchGroupId() {
        return this.researchGroupId;
    }

    public void setResearchGroupId(int researchGroupId) {
        this.researchGroupId = researchGroupId;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public long getScn() {
        return scn;
    }

    public void setScn(long scn) {
        this.scn = scn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ResearchGroupMembership> getResearchGroupMemberships() {
        return this.researchGroupMemberships;
    }

    public void setResearchGroupMemberships(Set<ResearchGroupMembership> researchGroupMemberships) {
        this.researchGroupMemberships = researchGroupMemberships;
    }

    public Set<Scenario> getScenarios() {
        return this.scenarios;
    }

    public void setScenarios(Set<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public Set<Experiment> getExperiments() {
        return this.experiments;
    }

    public void setExperiments(Set<Experiment> experiments) {
        this.experiments = experiments;
    }

    public Set<Person> getUsersWithThisAsDefault() {
        return usersWithThisAsDefault;
    }

    public void setUsersWithThisAsDefault(Set<Person> usersWithThisAsDefault) {
        this.usersWithThisAsDefault = usersWithThisAsDefault;
    }

    public Set<GroupPermissionRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<GroupPermissionRequest> requests) {
        this.requests = requests;
    }

    public Set<Person> getArticlesSubscribers() {
        return articlesSubscribers;
    }

    public void setArticlesSubscribers(Set<Person> articlesSubscribers) {
        this.articlesSubscribers = articlesSubscribers;
    }


}


