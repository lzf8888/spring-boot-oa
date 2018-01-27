package com.fp.oa.core.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.Main;
import com.fp.oa.core.service.LocaleMessageService;
import com.fp.oa.test.BaseTest;

//@Ignore
@RunWith(SpringRunner.class)
//@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = Main.class)
public class I18nTest extends BaseTest{
	@Autowired
	private LocaleMessageService service;

	@Test
	public void test() {
		String msg = service.getMessage("context.name");
		assertThat(msg).isEqualTo("乔峰");
	}
}
