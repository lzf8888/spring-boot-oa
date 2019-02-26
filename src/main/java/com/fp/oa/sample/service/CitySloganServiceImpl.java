/*
 * Copyright 2019 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.fp.oa.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fp.oa.sample.persistence.dao.CityDao;
import com.fp.oa.sample.persistence.domain.City;

@Service
public class CitySloganServiceImpl {

	@Autowired
	private CityDao cityDao;

	public String methodForTest(long id) {
		City city = cityDao.selectCityById(id);
		if (city.getName().equals("nanjing")) {
			return "haha";
		} else if (city.getName().equals("beijing")) {
			return "hehe";
		} else {
			throw new IllegalArgumentException("input id returns other results");
		}
	}

}
