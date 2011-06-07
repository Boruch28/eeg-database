package cz.zcu.kiv.eegdatabase.data.pojo;
// Generated 19.1.2010 23:18:53 by Hibernate Tools 3.2.1.GA

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import cz.zcu.kiv.eegdatabase.logic.util.SignalProcessingUtils;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.hibernate.annotations.Entity;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Fields;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

/**
 * Experiment generated by hbm2java
 */
@Entity
@Indexed//Mark for indexing
@Analyzer(impl = StandardAnalyzer.class)
public class Experiment implements java.io.Serializable {

  @DocumentId                    //Mark id property shared by Core and Search
  private int experimentId;
  private Weather weather;
  private Person personBySubjectPersonId;
  private Scenario scenario;
  private Person personByOwnerId;
  private ResearchGroup researchGroup;
  @DateBridge(resolution = Resolution.DAY)//Precision stored in the index: day
  private Timestamp startTime;
  @DateBridge(resolution = Resolution.DAY)//Precision stored in the index: day
  private Timestamp endTime;
    @Fields({
    @Field(index = Index.UN_TOKENIZED), // same property indexed multiple times
    @Field(store = Store.YES), // weathernote value is stored in the index
    @Field(name = "temperature")})   // use a different field name
  private int temperature;
  @Fields({
    @Field(index = Index.UN_TOKENIZED), // same property indexed multiple times
    @Field(store = Store.YES), // weathernote value is stored in the index
    @Field(name = "weathernote")})   // use a different field name
  //@Boost(2)//Boost weathernote field
  private String weathernote;
  private Set<Person> persons = new HashSet<Person>(0);
  private Set<Hardware> hardwares = new HashSet<Hardware>(0);
  private Set<DataFile> dataFiles = new HashSet<DataFile>(0);
  private Set<ExperimentOptParamVal> experimentOptParamVals = new HashSet<ExperimentOptParamVal>(0);
  private Set<History> histories = new HashSet<History>(0);
  private boolean privateExperiment;

  public Experiment() {
  }

  public Experiment(int experimentId, Weather weather, Person personBySubjectPersonId, Scenario scenario, Person personByOwnerId, ResearchGroup researchGroup) {
    this.experimentId = experimentId;
    this.weather = weather;
    this.personBySubjectPersonId = personBySubjectPersonId;
    this.scenario = scenario;
    this.personByOwnerId = personByOwnerId;
    this.researchGroup = researchGroup;

  }

  public Experiment(int experimentId, Weather weather, Person personBySubjectPersonId, Scenario scenario, Person personByOwnerId, ResearchGroup researchGroup, Timestamp startTime, Timestamp endTime, int temperature, String weathernote, Set<Person> persons, Set<Hardware> hardwares, Set<DataFile> dataFiles, Set<ExperimentOptParamVal> experimentOptParamVals) {
    this.experimentId = experimentId;
    this.weather = weather;
    this.personBySubjectPersonId = personBySubjectPersonId;
    this.scenario = scenario;
    this.personByOwnerId = personByOwnerId;
    this.researchGroup = researchGroup;
    this.startTime = startTime;
    this.endTime = endTime;
    this.temperature = temperature;
    this.weathernote = weathernote;
    this.persons = persons;
    this.hardwares = hardwares;
    this.dataFiles = dataFiles;
    this.experimentOptParamVals = experimentOptParamVals;
  }

  public int getExperimentId() {
    return this.experimentId;
  }

  public void setExperimentId(int experimentId) {
    this.experimentId = experimentId;
  }

  public Weather getWeather() {
    return this.weather;
  }

  public void setWeather(Weather weather) {
    this.weather = weather;
  }

  public Person getPersonBySubjectPersonId() {
    return this.personBySubjectPersonId;
  }

  public void setPersonBySubjectPersonId(Person personBySubjectPersonId) {
    this.personBySubjectPersonId = personBySubjectPersonId;
  }

  public Scenario getScenario() {
    return this.scenario;
  }

  public void setScenario(Scenario scenario) {
    this.scenario = scenario;
  }

  public Person getPersonByOwnerId() {
    return this.personByOwnerId;
  }

  public void setPersonByOwnerId(Person personByOwnerId) {
    this.personByOwnerId = personByOwnerId;
  }

  public ResearchGroup getResearchGroup() {
    return this.researchGroup;
  }

  public void setResearchGroup(ResearchGroup researchGroup) {
    this.researchGroup = researchGroup;
  }

  public Timestamp getStartTime() {
    return this.startTime;
  }

  public void setStartTime(Timestamp startTime) {
    this.startTime = startTime;
  }

  public Timestamp getEndTime() {
    return this.endTime;
  }

  public void setEndTime(Timestamp endTime) {
    this.endTime = endTime;
  }

  public int getTemperature() {
    return this.temperature;
  }

  public void setTemperature(int temperature) {
    this.temperature = temperature;
  }
  // @Field(index=Index.TOKENIZED, store=Store.NO)

  public String getWeathernote() {
    return this.weathernote;
  }
//    @Field(name="weathernote")
//    public Reader getIndexedWeathernote();

  public void setWeathernote(String weathernote) {
    this.weathernote = weathernote;
  }

  public Set<Person> getPersons() {
    return this.persons;
  }

  public void setPersons(Set<Person> persons) {
    this.persons = persons;
  }

  public Set<Hardware> getHardwares() {
    return this.hardwares;
  }

  public void setHardwares(Set<Hardware> hardwares) {
    this.hardwares = hardwares;
  }

  public Set<DataFile> getDataFiles() {
    return this.dataFiles;
  }

  public void setDataFiles(Set<DataFile> dataFiles) {
    this.dataFiles = dataFiles;
  }

  public Set<ExperimentOptParamVal> getExperimentOptParamVals() {
    return this.experimentOptParamVals;
  }

  public void setExperimentOptParamVals(Set<ExperimentOptParamVal> experimentOptParamVals) {
    this.experimentOptParamVals = experimentOptParamVals;
  }

  public boolean isPrivateExperiment() {
    return this.privateExperiment;
  }

  public void setPrivateExperiment(boolean privateExperiment) {
    this.privateExperiment = privateExperiment;
  }

  public Set<History> getHistories() {
    return histories;
  }

  public void setHistories(Set<History> histories) {
    this.histories = histories;
  }

  public boolean isSuitable() {
    return SignalProcessingUtils.isSuitableExperiment(this);
  }
}


