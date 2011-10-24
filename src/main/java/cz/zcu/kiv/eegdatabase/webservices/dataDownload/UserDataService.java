/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.eegdatabase.webservices.dataDownload;


import cz.zcu.kiv.eegdatabase.data.pojo.SyncChanges;
import cz.zcu.kiv.eegdatabase.webservices.dataDownload.wrappers.*;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.util.List;

/**
 * Interface for web service providing user's data remotely.
 *
 * @author Petr Miko
 */
@WebService
public interface UserDataService {

    /**
     * Method just for checking web service availability
     * (user needs to connect through Spring security but doesn't wish to do anything more)
     *
     * @return true
     */
    public boolean isServiceAvailable();

    /**
     * Method returning List of available weather information.
     *
     * @return weather information
     */
    public List<WeatherInfo> getWeather();

    /**
     * Method returning List of information about available experiments.
     *
     * @return List of information about available experiments
     */
    public List<ExperimentInfo> getExperiments();

    /**
     * Method returning List of information about available scenarios.
     *
     * @return list of scenarios
     */
    public List<ScenarioInfo> getScenarios();

    /**
     * Method for obtaining list of all EEG base users.
     *
     * @return list of users
     */
    public List<PersonInfo> getPeople();

    /**
     * Method for obtaining list of all EEG base research groups.
     *
     * @return list of research groups
     */
    public List<ResearchGroupInfo> getResearchGroups();

    /**
     * Method returning List of information about available data files.
     *
     * @return list of data files
     */
    public List<DataFileInfo> getDataFiles() throws DataDownloadException;

    /**
     * Method for obtaining information about last changes upon monitored data tables.
     *
     * @return table names and timestamps of their last change
     */
    public List<SyncChangesInfo> getSyncChanges();

    /**
     * Method streaming desired file back to user.
     *
     * @param dataFileId Id of file to download
     * @return Stream of bytes (file)
     * @throws DataDownloadException exception occurred on side of web service
     */
    @XmlMimeType("application/octet-stream")
    public DataHandler downloadDataFile(int dataFileId) throws DataDownloadException;
}