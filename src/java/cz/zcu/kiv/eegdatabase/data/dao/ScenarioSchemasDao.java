/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.pojo.ScenarioSchemas;
import java.util.List;

/**
 *
 * @author Jan Koreň
 */
public interface ScenarioSchemasDao extends GenericDao<ScenarioSchemas, Integer> {

  public List<ScenarioSchemas> getScenarioSchemaNames();
}
