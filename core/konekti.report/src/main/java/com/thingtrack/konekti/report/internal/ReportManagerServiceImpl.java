package com.thingtrack.konekti.report.internal;

/*
 * #%L
 * Konekti Report
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2010 - 2013 Thingtrack s.l.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.Report;
import com.thingtrack.konekti.report.ReportManagerService;
import com.thingtrack.konekti.service.api.ReportService;

public class ReportManagerServiceImpl implements ReportManagerService {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ReportService reportService;
		
	private final static String DEFAULT_REPORT_PATH = "/template/konekti/";
	
	@SuppressWarnings("unused")
	@Override
	public JasperPrint executeReport(String code, Map<String,Object> parameters) throws Exception {
		// get  report to execute
		Report report = reportService.getByCode(code);
		
		if (report == null)
			throw new Exception("The Report with code " + code + " not exist!");
		
		// get connection from jpa transaction manager to inject to jasper report template
		Connection connection = entityManager.unwrap(java.sql.Connection.class);
				
		if (connection == null)
			throw new Exception("We can not get the JPA connection!");
		
		// get report path from the fragment report templates bundle
		String pathReport = DEFAULT_REPORT_PATH + report.getCode() + "/" + report.getFileName();		
		URL urlResource = getClass().getResource(pathReport);
		
		File fileResource = new File(urlResource.toURI());
		
		if (fileResource == null)
			throw new Exception("The report templeate on " + pathReport + " not exist!");
		
		// load jasper report xml template
    	JasperDesign jasperDesign = JRXmlLoader.load(fileResource);
    	
    	// compile jasper report template
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        
        // generate jasper report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
        
        return jasperPrint;
			            					
	}
}
