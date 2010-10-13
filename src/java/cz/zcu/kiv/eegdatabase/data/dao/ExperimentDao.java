package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import cz.zcu.kiv.eegdatabase.logic.controller.search.SearchRequest;
import java.io.Serializable;
import java.util.List;

/**
 * DAO for fetching and saving experiments.
 * 
 * @author Jindrich Pergler
 */
public interface ExperimentDao<T, PK extends Serializable> extends GenericDao<T, PK> {

  public List<Experiment> getExperimentsWhereSubject(int personId);

  public List<Experiment> getExperimentsWhereOwner(int personId);

  public List getExperimentsWhereOwner(Person person, int i);

  public List getExperimentsWhereSubject(Person person, int i);

  public List<Experiment> getExperimentsWhereMember(int groupId);

  public List<Experiment> getExperimentSearchResults(List<SearchRequest> requests);
}