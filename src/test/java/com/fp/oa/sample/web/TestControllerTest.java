package com.fp.oa.sample.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.test.BaseTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTest extends BaseTest{

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void helloTest() {
		ResponseEntity<String> resp=restTemplate.exchange("/hello2/michael", HttpMethod.GET,null,String.class);
		assertThat(resp.getBody()).contains("michael");
	}
}
