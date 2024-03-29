/*
 * Copyright 2011 Thingtrack, S.L.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.thingtrack.konekti.service.impl.internal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thingtrack.konekti.domain.ProductUnit;
import com.thingtrack.konekti.dao.api.ProductUnitDao;
import com.thingtrack.konekti.service.api.ProductUnitService;

/**
 * @author Thingtrack S.L.
 *
 */
public class ProductUnitServiceImpl implements ProductUnitService {
	@Autowired
	private ProductUnitDao productUnitDao;

	@Override
	public List<ProductUnit> getAll() throws Exception {
		return this.productUnitDao.getAll();
		
	}

	@Override
	public ProductUnit get(Integer productUnitId) throws Exception {
		return this.productUnitDao.get(productUnitId);
		
	}

	@Override
	public ProductUnit getByCode(String code) throws Exception {
		return this.productUnitDao.getByCode(code);
		
	}

	@Override
	public ProductUnit save(ProductUnit productUnit) throws Exception {
		return this.productUnitDao.save(productUnit);
		
	}

	@Override
	public void delete(ProductUnit productUnit) throws Exception {
		this.productUnitDao.delete(productUnit);
		
	}

}
