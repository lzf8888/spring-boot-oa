/*
 * Copyright 2019 didichuxing.com All right reserved. This software is the
 * confidential and proprietary information of didichuxing.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with didichuxing.com.
 */
package com.fp.oa.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.sample.persistence.dao.CityDao;
import com.fp.oa.sample.persistence.domain.City;
import com.fp.oa.sample.service.CitySloganServiceImpl;
import com.fp.oa.test.BaseTest;

/**
 * mock examples to show the usage of 1 mockBean in spring boot env, you don't
 * need the modify service to constructor injection 2 any anyint anyString usage
 * to simplify the mock 3 bddmockito usage 4 using assertJ to handle the
 * exception assertions
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MocKExapleTest extends BaseTest {

	@MockBean
	CityDao cityDao;// bean that service use for dependency

	@Autowired
	CitySloganServiceImpl service;

	@Test
	public void test() {
		long id1 = 1L;
		City city1 = new City();
		city1.setName("nanjing");
		given(cityDao.selectCityById(id1)).willReturn(city1);
		assertThat(service.methodForTest(id1)).isEqualTo("haha");
		city1.setName("xian");
		assertThatThrownBy(() -> {
			service.methodForTest(id1);
		}).isInstanceOf(IllegalArgumentException.class).hasMessage("input id returns other results");

		City city2 = new City();
		city2.setName("beijing");
		given(cityDao.selectCityById(anyLong())).willReturn(city2);
		assertThat(service.methodForTest(9L)).isEqualTo("hehe");

	}

}
