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
 *   ArtifactFacadeImpl.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.wui.core.common;

import cz.zcu.kiv.eegdatabase.data.pojo.Artifact;
import cz.zcu.kiv.eegdatabase.data.pojo.ResearchGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ArtifactFacadeImpl implements ArtifactFacade {

    protected Log log = LogFactory.getLog(getClass());

    ArtifactService service;

    @Required
    public void setService(ArtifactService service) {
        this.service = service;
    }

    @Override
    public Integer create(Artifact newInstance) {
        return service.create(newInstance);
    }

    @Override
    public Artifact read(Integer id) {
        return service.read(id);
    }

    @Override
    public List<Artifact> readByParameter(String parameterName, Object parameterValue) {
        return service.readByParameter(parameterName, parameterValue);
    }

    @Override
    public void update(Artifact transientObject) {
        service.update(transientObject);
    }

    @Override
    public void delete(Artifact persistentObject) {
        service.delete(persistentObject);
    }

    @Override
    public List<Artifact> getAllRecords() {
        return service.getAllRecords();
    }

    @Override
    public List<Artifact> getRecordsAtSides(int first, int max) {
        return service.getRecordsAtSides(first, max);
    }

    @Override
    public int getCountRecords() {
        return service.getCountRecords();
    }

    @Override
    public List<Artifact> getUnique(Artifact example) {
        return service.getUnique(example);
    }

    @Override
    public void createGroupRel(Artifact persistent, ResearchGroup researchGroup) {
        service.createGroupRel(persistent, researchGroup);
    }

    @Override
    public List<Artifact> getItemsForList() {
        return service.getItemsForList();
    }

    @Override
    public List<Artifact> getRecordsByGroup(int groupId) {
        return service.getRecordsByGroup(groupId);
    }

    @Override
    public boolean canDelete(int id) {
        return service.canDelete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean hasGroupRel(int id) {
        return service.hasGroupRel(id);
    }

    @Override
    public void deleteGroupRel(Artifact persistent, ResearchGroup researchGroup) {
        service.deleteGroupRel(persistent, researchGroup);
    }
    
}
