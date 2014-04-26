/***********************************************************************************************************************
 *
 * This file is part of the EEG-database project
 *
 * =============================================
 *
 * Copyright (C) 2014 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 *
 * FormServiceImpl.java, 8. 1. 2014 12:11:20, Jakub Krauz
 *
 **********************************************************************************************************************/
package cz.zcu.kiv.eegdatabase.webservices.rest.forms;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cz.zcu.kiv.eegdatabase.data.dao.FormLayoutDao;
import cz.zcu.kiv.eegdatabase.data.dao.GenericDao;
import cz.zcu.kiv.eegdatabase.data.dao.PersonDao;
import cz.zcu.kiv.eegdatabase.data.pojo.FormLayout;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.webservices.rest.common.wrappers.RecordCountData;
import cz.zcu.kiv.eegdatabase.webservices.rest.forms.FormServiceException.Cause;
import cz.zcu.kiv.eegdatabase.webservices.rest.forms.wrappers.AvailableFormsDataList;
import cz.zcu.kiv.eegdatabase.webservices.rest.forms.wrappers.AvailableLayoutsData;
import cz.zcu.kiv.eegdatabase.webservices.rest.forms.wrappers.AvailableLayoutsDataList;
import cz.zcu.kiv.formgen.FormNotFoundException;
import cz.zcu.kiv.formgen.LayoutGenerator;
import cz.zcu.kiv.formgen.LayoutGeneratorException;
import cz.zcu.kiv.formgen.Writer;
import cz.zcu.kiv.formgen.core.SimpleDataGenerator;
import cz.zcu.kiv.formgen.core.SimpleLayoutGenerator;
import cz.zcu.kiv.formgen.model.Form;
import cz.zcu.kiv.formgen.odml.OdmlWriter;


/**
 * Service implementation for form layouts. Used in RESTful API.
 * 
 * @author Jakub Krauz
 */
@Service
@Transactional(readOnly = true)
public class FormServiceImpl implements FormService, InitializingBean, ApplicationContextAware {
	
	/** The DAO object providing form-layout data. */
	@Autowired
	@Qualifier("formLayoutDao")
	private FormLayoutDao formLayoutDao;
	
	/** The DAO object providing person-related data - used for getting the logged user. */
    @Autowired
    @Qualifier("personDao")
    private PersonDao personDao;
	
	/** Base package of POJO classes. */
	private static final String POJO_BASE = "cz.zcu.kiv.eegdatabase.data.pojo";
	
	/** Logger object. */
	private static final Logger logger = LoggerFactory.getLogger(FormServiceImpl.class);
	
	private ApplicationContext context;
	
	
	
	/**
	 * Loads the data model and transforms it to defined form-layouts.
	 * Generated form-layouts are updated in the persistent storage.
	 * 
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void afterPropertiesSet() {
		LayoutGenerator generator = new SimpleLayoutGenerator();
		try {
			Writer writer = new OdmlWriter();
			for (Form form : generator.loadPackage(POJO_BASE)) {
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				try {
					writer.writeLayout(form, stream);
					FormLayout layout = new FormLayout(form.getName(), form.getLayoutName(),
							stream.toByteArray(), null);
					formLayoutDao.createOrUpdateByName(layout);
				} catch (LayoutGeneratorException e) {
					logger.error("Could not update the following layout: " + form.getLayoutName(), e);
				}
			}
		} catch (FormNotFoundException e) {
			logger.error("Could not transform the data model to form-layouts.", e);
		}
	}
	
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public RecordCountData availableFormsCount() {
		RecordCountData count = new RecordCountData();
		count.setPublicRecords(formLayoutDao.getAllFormsCount());
		count.setMyRecords(formLayoutDao.getFormsCount(personDao.getLoggedPerson()));
		return count;
	}
	
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public AvailableFormsDataList availableForms(boolean mineOnly) {
		List<String> list = mineOnly ? formLayoutDao.getFormNames(personDao.getLoggedPerson()) 
					: formLayoutDao.getAllFormNames();
		return new AvailableFormsDataList(list);
	}
	
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public RecordCountData availableLayoutsCount() {
		RecordCountData count = new RecordCountData();
		count.setPublicRecords(formLayoutDao.getAllLayoutsCount());
		count.setMyRecords(formLayoutDao.getLayoutsCount(personDao.getLoggedPerson()));
		return count;
	}
	
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public RecordCountData availableLayoutsCount(String formName) {
		if (formName == null)
			return null;
		RecordCountData count = new RecordCountData();
		count.setPublicRecords(formLayoutDao.getLayoutsCount(formName));
		count.setMyRecords(formLayoutDao.getLayoutsCount(personDao.getLoggedPerson(), formName));
		return count;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AvailableLayoutsDataList availableLayouts(boolean mineOnly) {
		List<FormLayout> list = mineOnly ? formLayoutDao.getLayouts(personDao.getLoggedPerson()) 
					: formLayoutDao.getAllLayouts();
		
		List<AvailableLayoutsData> dataList = new ArrayList<AvailableLayoutsData>(list.size());
		for (FormLayout formLayout : list)
			dataList.add(new AvailableLayoutsData(formLayout.getFormName(), formLayout.getLayoutName()));
		
		return new AvailableLayoutsDataList(dataList);
	}
	
	
	/**
     * {@inheritDoc}
	 */
	@Override
	public AvailableLayoutsDataList availableLayouts(String formName, boolean mineOnly) {
		List<FormLayout> list = mineOnly ? formLayoutDao.getLayouts(personDao.getLoggedPerson(), formName) 
				: formLayoutDao.getLayouts(formName);
		
		List<AvailableLayoutsData> dataList = new ArrayList<AvailableLayoutsData>(list.size());
		for (FormLayout formLayout : list)
			dataList.add(new AvailableLayoutsData(formLayout.getFormName(), formLayout.getLayoutName()));
		
		return new AvailableLayoutsDataList(dataList);
	}
	
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public FormLayout getLayout(String formName, String layoutName) throws FormServiceException {
		FormLayout formLayout = formLayoutDao.getLayout(formName, layoutName);
		if (formLayout == null)
			throw new FormServiceException(Cause.NOT_FOUND);
		else
			return formLayout;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void createLayout(String formName, String layoutName, byte[] content) throws FormServiceException {
		
		// check whether the layout exists
		if (formLayoutDao.getLayout(formName, layoutName) != null) {
			logger.debug("Cannot create layout, key already exists [form: {}, layout: {}].", formName, layoutName);
			throw new FormServiceException(Cause.CONFLICT);
		}
		
		// create
		FormLayout layout = new FormLayout(formName, layoutName, content, personDao.getLoggedPerson());
		formLayoutDao.create(layout);
	}
	
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void updateLayout(String formName, String layoutName, byte[] content) throws FormServiceException {
		FormLayout formLayout;
		
		// check whether the layout exists
		if ((formLayout = formLayoutDao.getLayout(formName, layoutName)) == null) {
			logger.debug("Layout not found [form: {}, layout: {}].", formName, layoutName);
			throw new FormServiceException(Cause.NOT_FOUND);
		}
		
		// check the owner
		Person current = personDao.getLoggedPerson();
		if (current == null  ||  !current.equals(formLayout.getPerson())) {
			logger.debug("Missing permissions to update layout [form: {}, layout: {}].", formName, layoutName);
			throw new FormServiceException(Cause.PERMISSION);
		}
		
		// update
		formLayout.setContent(content);
		formLayoutDao.update(formLayout);
	}
	
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void deleteLayout(String formName, String layoutName) throws FormServiceException {
		FormLayout formLayout;
		
		// check whether the layout exists
		if ((formLayout = formLayoutDao.getLayout(formName, layoutName)) == null) {
			logger.debug("Layout not found [form: {}, layout: {}].", formName, layoutName);
			throw new FormServiceException(Cause.NOT_FOUND);
		}
		
		// check the owner
		Person current = personDao.getLoggedPerson();
		if (current == null  ||  !current.equals(formLayout.getPerson())) {
			logger.debug("Missing permissions to delete layout [form: {}, layout: {}].", formName, layoutName);
			throw new FormServiceException(Cause.PERMISSION);
		}
		
		// delete
		formLayoutDao.delete(formLayout);
	}
	

	/**
	 * {@inheritDoc} 
	 */
	@Override
	public byte[] getOdmlData(String entity) throws FormServiceException {
		if (entity == null)
			throw new NullPointerException("Entity cannot be null.");
		
		// get all records
		GenericDao<?, Integer> dao = daoForEntity(entity);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) dao.getAllRecords();
		
		// transform data to odML
		try {
			SimpleDataGenerator generator = new SimpleDataGenerator();
			generator.load(list, false);
			Writer writer = new OdmlWriter();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			writer.writeData(generator.getLoadedModel(), out);
			return out.toByteArray();
		} catch (LayoutGeneratorException e) {
			logger.error("Unable to transform data to odML format.", e);
			throw new FormServiceException(Cause.OTHER);
		}
	}
	
	
	/**
	 * {@inheritDoc} 
	 */
	@Override
	public byte[] getOdmlData(String entity, Integer id) throws FormServiceException {
		if (entity == null)
			throw new NullPointerException("Entity cannot be null.");
		
		// get the record
		GenericDao<?, Integer> dao = daoForEntity(entity);
		Object data = dao.read(id);
		
		// transform data to odML
		try {
			SimpleDataGenerator generator = new SimpleDataGenerator();
			generator.load(data, false);
			Writer writer = new OdmlWriter();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			writer.writeData(generator.getLoadedModel(), out);
			return out.toByteArray();
		} catch (LayoutGeneratorException e) {
			logger.error("Unable to transform data to odML format.", e);
			throw new FormServiceException(Cause.OTHER);
		}
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}
	
	
	
	/**
	 * Retrieve DAO object for the given entity from Spring's context.
	 * @param entityName - the name of the entity
	 * @return the DAO object
	 * @throws FormServiceException if the DAO object cannot be retrieved
	 */
	@SuppressWarnings("unchecked")
	private GenericDao<?, Integer> daoForEntity(String entityName) throws FormServiceException {
		try {
			// get the DAO object from spring context
			return (GenericDao<?, Integer>) context.getBean(daoName(entityName), GenericDao.class);
		} catch (BeansException e) {
			logger.warn("Unable to get bean \"" + daoName(entityName) + "\" from the application context.", e);
			throw new FormServiceException(Cause.NOT_FOUND);
		}
	}
	
	
	/**
	 * Guess name of DAO object for given entity.
	 * @param entityClassName - classname of the entity
	 * @return name of the DAO object
	 */
	private String daoName(String entityClassName) {
		// get simple name if the name is fully qualified
		String[] split = entityClassName.split("\\.");
		String entity = split[split.length - 1];
		
		// first character to lower case
		char[] array = entity.toCharArray();
		array[0] = Character.toLowerCase(array[0]);
		entity = new String(array);
		
		// append the "Dao" suffix
		return entity + "Dao";
	}


}
