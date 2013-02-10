package cz.zcu.kiv.eegdatabase.wui.core.person;

import java.util.List;
import java.util.Map;

import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.logic.controller.search.SearchRequest;
import cz.zcu.kiv.eegdatabase.logic.controller.social.SocialUser;
import cz.zcu.kiv.eegdatabase.wui.core.GenericService;

public interface PersonService extends GenericService<Person, Integer> {

    Person getPerson(String userName);

    Person getPersonByHash(String hashCode);

    Person getPersonByFbUid(String fbUid);

    void changeUserPassword(String userName, String newPass);

    boolean isPasswordEquals(String userName, String password);

    void forgottenPassword(Person person);

    List<Person> getPersonsWherePendingRequirement();

    boolean usernameExists(String userName);

    boolean fbUidExists(String id);

    List<Person> getSupervisors();

    Person getLoggedPerson();

    Map getInfoForAccountOverview(Person loggedPerson);

    List<Person> getRecordsNewerThan(long oracleScn);

    boolean userNameInGroup(String userName, int groupId);

    List<Person> getPersonSearchResults(List<SearchRequest> requests);

    int getCountForList();

    List<Person> getDataForList(int start, int limit);
    
    Person createPerson(SocialUser userFb, Integer educationLevelId);
}
