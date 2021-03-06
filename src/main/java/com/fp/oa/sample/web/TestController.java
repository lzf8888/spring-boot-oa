package com.fp.oa.sample.web;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fp.oa.core.exception.ApiException;
import com.fp.oa.core.result.ResultBean;
import com.fp.oa.sample.TestService;
import com.fp.oa.sample.persistence.dao.CityDao;
import com.fp.oa.sample.persistence.model.SampleEntity;
import com.fp.oa.sample.persistence.repository.SampleRepository;

@RestController
public class TestController {
	
	@Autowired
	SampleRepository sampleRepo;
	
	@Autowired
	CityDao dao;
	
	@Autowired
	TestService testService;

	//@PreAuthorize("hasAuthority('user')")
	@RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name) {
		SampleEntity entity=new SampleEntity();
		entity.setDesc("this is desc");
		entity.setStartTime(Timestamp.from(Instant.now()));
		entity.setName("micahel");
		sampleRepo.save(entity);
        return "Hello " + name ;
    }
	
	@RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
	public String city(@PathVariable String id) {
		
        return "Hello " + dao.selectCityById(1);
    }
	
	//ideal api style example
	@RequestMapping(value = "/testfail", method = RequestMethod.GET)
	public ResultBean<?> apiException(@RequestParam int id) {
		try {
			ResultBean<?> result = testService.getResultByIdButFail(id);
			return result;
		} catch (Exception e) {// 未知错误
			throw new ApiException(e);
		}
	}
	
	@GetMapping("/hello2/{name}")
	public String hello2(@PathVariable String name) {
		return "Hello " + name;
	}
	
}
