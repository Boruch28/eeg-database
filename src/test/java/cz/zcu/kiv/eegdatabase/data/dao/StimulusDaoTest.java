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
 *   StimulusDaoTest.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.AbstractDataAccessTest;
import cz.zcu.kiv.eegdatabase.data.pojo.Stimulus;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

import static org.testng.Assert.*;

/**
 * User: Tomas Pokryvka
 * Date: 26.4.13
 */
public class StimulusDaoTest extends AbstractDataAccessTest {

    @Autowired
    private StimulusDao stimulusDao;
    private Stimulus stimulus;


    @BeforeMethod(groups = "unit")
    public void setUp() throws Exception {
        stimulus = new Stimulus();
        stimulus.setDescription("test-description");
    }

    @Test(groups = "unit")
    public void testCreateStimulus() throws Exception {
        int count = stimulusDao.getCountRecords();
        System.out.println(count);
        stimulusDao.create(stimulus);
        assertEquals(count + 1, stimulusDao.getAllRecords().size());
    }

    @Test(groups = "unit")
    public void testCanSaveDescription() throws Exception {
        stimulusDao.create(stimulus);
        assertFalse(stimulusDao.canSaveDescription("test-description"));
        assertTrue(stimulusDao.canSaveDescription(String.valueOf(new Random().nextLong())));
    }

    @Test(groups = "unit")
    public void testEditStimulus() {
        int count = stimulusDao.getCountRecords();
        int id = stimulusDao.create(stimulus);
        assertEquals(count + 1, stimulusDao.getCountRecords());
        stimulus.setDescription("new desc");
        stimulusDao.update(stimulus);
        assertEquals(count + 1, stimulusDao.getCountRecords());

        assertEquals("new desc", stimulusDao.read(id).getDescription());
    }
}
