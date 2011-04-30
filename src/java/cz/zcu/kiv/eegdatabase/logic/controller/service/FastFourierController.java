package cz.zcu.kiv.eegdatabase.logic.controller.service;

import cz.zcu.kiv.eegdatabase.data.dao.GenericDao;
import cz.zcu.kiv.eegdatabase.data.pojo.DataFile;
import cz.zcu.kiv.eegdatabase.data.pojo.Experiment;
import cz.zcu.kiv.eegdatabase.logic.signal.ChannelInfo;
import cz.zcu.kiv.eegdatabase.logic.signal.DataTransformer;
import cz.zcu.kiv.eegdatabase.logic.signal.VhdrReader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Honza
 * Date: 29.4.2011
 * Time: 10:00:26
 * To change this template use File | Settings | File Templates.
 */
public class FastFourierController extends SimpleFormController {
     private GenericDao<Experiment, Integer> experimentDao;
    private DataTransformer transformer;
    private VhdrReader reader;
    Experiment experiment;

    public FastFourierController() {
        setCommandClass(FastFourierCommand.class);
        setCommandName("fastFourier");
    }

    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map map = new HashMap<String, Object>();
        experiment = experimentDao.read(Integer.parseInt(request.getParameter("experimentId")));
        reader = new VhdrReader();
        DataFile vhdr = null;
        for (DataFile d : experiment.getDataFiles()) {
            if (d.getFilename().endsWith(".vhdr")) {
            vhdr = d;
            break;
            }
        }
        reader.readVhdr(vhdr);
        List<ChannelInfo> channels = reader.getChannels();
        map.put("channels", channels);

        return map;
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        FastFourierCommand cmd = (FastFourierCommand) command;
        return new ModelAndView(getSuccessView());
    }


    public GenericDao<Experiment, Integer> getExperimentDao() {
        return experimentDao;
    }

    public void setExperimentDao(GenericDao<Experiment, Integer> experimentDao) {
        this.experimentDao = experimentDao;
    }

    public DataTransformer getTransformer() {
        return transformer;
    }

    public void setTransformer(DataTransformer transformer) {
        this.transformer = transformer;
    }
}