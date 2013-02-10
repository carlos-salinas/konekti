package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.dao.api.ConfigurationDao;
import com.thingtrack.konekti.domain.Configuration;
import com.thingtrack.konekti.domain.MenuResource;
import com.thingtrack.konekti.domain.Organization;
import com.thingtrack.konekti.service.api.ConfigurationService;

public class ConfigurationServiceImpl implements ConfigurationService {
	@Autowired
	private ConfigurationDao configurationDao;
	
	@Override
	public List<Configuration> getAll() throws Exception {
		return this.configurationDao.getAll();
	}

	@Override
	public Configuration get(Integer configurationId) throws Exception {
		return this.configurationDao.get(configurationId);
	}

	@Override
	public Configuration getByTag(String tag) throws Exception {
		return this.configurationDao.getByTag(tag);
	}
	

	@Override
	public Configuration getByTag(String tag, Organization organization, MenuResource menuResource) throws Exception {
		return this.configurationDao.getByTag(tag, organization, menuResource);
	}
	
	@Override
	public Configuration save(Configuration configuration) throws Exception {
		return this.configurationDao.save(configuration);
	}

	@Override
	public void delete(Configuration configuration) throws Exception {
		this.configurationDao.delete(configuration);
		
	}

}
