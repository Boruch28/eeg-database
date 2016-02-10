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
 *   SimpleLicenseDao.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.eegdatabase.data.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cz.zcu.kiv.eegdatabase.data.pojo.License;
import cz.zcu.kiv.eegdatabase.data.pojo.LicenseType;
import cz.zcu.kiv.eegdatabase.data.pojo.PersonalLicenseState;

/**
 *
 * @author bydga
 */
public class SimpleLicenseDao extends SimpleGenericDao<License, Integer> implements LicenseDao {
    
    protected Log log = LogFactory.getLog(getClass());

    public SimpleLicenseDao() {
	super(License.class);
    }

	@Override
	public List<License> getLicensesByType(LicenseType licenseType) {
		List<LicenseType> types = new ArrayList<LicenseType>(1);
		types.add(licenseType);
		return this.getLicensesByType(types);
	}

	@Override
	public List<License> getLicensesByType(List<LicenseType> licenseType) {
		String hqlQuery = "select l from License l where l.licenseType IN (:licenseType)";
		return this.currentSession().createQuery(hqlQuery).setParameterList("licenseType", licenseType).list();
	}

	@Override
    public byte[] getLicenseAttachmentContent(int licenseId) {
        String query = "from License l where l.licenseId = :id";
        License result =  (License) this.currentSession().createQuery(query).setInteger("id", licenseId).uniqueResult();
        try {
            return result.getAttachmentContent() != null ? result.getAttachmentContent().getBytes(1, (int) result.getAttachmentContent().length()) : new byte[0];
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            return new byte[0];
        }
    }

    @Override
    public List<License> getLicenseForPackageAndOwnedByPerson(int personId, int packageId) {

        String query = "select pl.license from PersonalLicense pl "
                + "where pl.person.personId = :personId and pl.licenseState = :state and "
                + "pl.license.licenseId IN ("
                + "select epl.license.licenseId from ExperimentPackageLicense as epl where epl.experimentPackage.experimentPackageId = :packageId"
                + ") and pl.license.researchGroup IS NOT NULL";

        return (List<License>) this.currentSession().createQuery(query)
                .setParameter("personId", personId)
                .setParameter("state", PersonalLicenseState.AUTHORIZED)
                .setParameter("packageId", packageId).list();
    }

    @Override
    public void update(License transientObject) {
        this.currentSession().merge(transientObject);
    }

    @Override
    public License getLicenseForPurchasedExperiment(int experimentId, int personId) {

        // original SQL : select eoi.license from eeg_order as eo
        // left join eeg_order_item as eoi on eo.order_id = eoi.order_id
        // where eoi.experiment = :experimentId and eo.person = :personId order by eo.date asc

        String query = "select eoi.license from OrderItem as eoi "
                + "join eoi.order as eo "
                + " where eo.id = eoi.order.id and eoi.experiment.experimentId = :experimentId and eo.person.personId = :personId "
                + "order by eo.date asc";

        License license = (License) this.getSessionFactory().getCurrentSession().createQuery(query)
                .setParameter("personId", personId)
                .setParameter("experimentId", experimentId).setMaxResults(1)
                .uniqueResult();

        return license;
    }

    @Override
    public License getLicenseForPurchasedExpPackage(int experimentPackageId, int personId) {

        // original SQL : select eoi.license from eeg_order as eo
        // left join eeg_order_item as eoi on eo.order_id = eoi.order_id
        // where eoi.experiment_package = :experimentPackageId and eo.person = :personId order by eo.date asc

        String query = "select eoi.license from OrderItem as eoi "
                + "join eoi.order as eo "
                + "where eo.id = eoi.order.id and eoi.experimentPackage.experimentPackageId = :experimentPackageId and eo.person.personId = :personId "
                + "order by eo.date asc";

        License license = (License) this.getSessionFactory().getCurrentSession().createQuery(query)
                .setParameter("personId", personId)
                .setParameter("experimentPackageId", experimentPackageId).setMaxResults(1)
                .uniqueResult();



        return license;
    }

    @Override
    public List<License> getLicensesForExperiment(int experimentId) {
        List<License> ret;
        String query = "select l from License l where l.licenseId IN(select license.licenseId from ExperimentLicence where experiment.experimentId = :exId)";
        ret = (List<License>) this.currentSession().createQuery(query).setParameter("exId",experimentId).list();

        return ret;

    }

    @Override
    public List<License> getPersonLicenses(int personId)  {
        List<License> ret;
        String query = "select distinct l.license from ExperimentLicence l where l.experiment.personByOwnerId.personId = :personId";
        ret = (List<License>) this.currentSession().createQuery(query).setParameter("personId",personId).list();

        return ret;

    }
    
}
