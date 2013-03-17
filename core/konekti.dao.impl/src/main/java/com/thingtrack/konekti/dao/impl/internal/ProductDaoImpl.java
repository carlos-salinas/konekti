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
package com.thingtrack.konekti.dao.impl.internal;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.thingtrack.konekti.dao.template.JpaDao;
import com.thingtrack.konekti.dao.api.ProductDao;
import com.thingtrack.konekti.domain.Product;
import com.thingtrack.konekti.domain.Warehouse;

/**
 * @author Thingtrack S.L.
 *
 */
@Repository
public class ProductDaoImpl extends JpaDao<Product, Integer> implements ProductDao {
	@Override
	public Product getByCode(String code) throws Exception {
		Product product = (Product)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code")
				.setParameter("code", code).getSingleResult();

		return product;
		
	}

	@Override
	public Product getByCode(String code, String version) throws Exception {
		Product product = (Product)getEntityManager()
				.createQuery("SELECT p FROM " + getEntityName() + " p WHERE p.code = :code AND p.version = :version")
				.setParameter("code", code)
				.setParameter("version", version).getSingleResult();

		return product;
		
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductByWarehouse(Warehouse warehouse) throws Exception {
		String queryString = "SELECT pr";
		queryString += " FROM Product pr";
		queryString += " WHERE pr.warehouse = :warehouse";

		Query query = (Query) getEntityManager().createQuery(queryString)
				.setParameter("warehouse", warehouse);

		return query.getResultList();
		
	}
	
}
