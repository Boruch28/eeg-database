package cz.zcu.kiv.eegdatabase.test.perf;

import cz.zcu.kiv.eegdatabase.data.dao.ExperimentDao;
import cz.zcu.kiv.eegdatabase.data.dao.HardwareDao;
import cz.zcu.kiv.eegdatabase.data.dao.HistoryDao;
import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.data.pojo.Hardware;
import cz.zcu.kiv.eegdatabase.data.pojo.History;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Kabourek
 * Date: 17.5.11
 * Time: 20:05
 * To change this template use File | Settings | File Templates.
 */
public class ExperimentsServicePerformanceTest extends PerformanceTest {

    @Autowired
    ExperimentDao experimentDao;

    @Autowired
    PersonDao personeDao;

    @Autowired
    HardwareDao hardwareDao;

    @Autowired
    HistoryDao historyDao;


    private Experiment experiment;
    private Hardware hardware;
    private History history;


    /**
     * Method test create experiment.
     */
    //@Test
    public void createExperimentTest(){
        experiment = new Experiment();
        hardware = new Hardware();
        history = new History();

        hardware.setTitle("hardware");
        hardware.setDescription("testovaci");
        hardware.setType("type");
        hardwareDao.create(hardware);

        history.setPerson(personeDao.getPerson("kaby"));
        historyDao.create(history);

        experiment.setHardwares((Set<Hardware>) hardware);
        experiment.setHistories((Set<History>) history);
        experiment.setPersons((Set<Person>) personeDao.getPerson("kaby"));
        experimentDao.create(experiment);

    }
}