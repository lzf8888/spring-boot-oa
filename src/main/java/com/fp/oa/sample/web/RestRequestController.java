package com.fp.oa.sample.web;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fp.oa.core.result.ResultBean;
import com.fp.oa.sample.persistence.domain.Hotel;

@RestController
public class RestRequestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;
	
	/*
	 * this demonstrate how to use resttemplate to call rest api, even the result itself is a generic type.
	 * according to stackoverflow, we can only use ParameterizedTypeReference to do that job, postforentity will only return List<LinkedHashMap>, will cause cast exception
	 * please notice the api url is not implemented, its just for show.
	 */
	@RequestMapping(value = "/hello/test", method = RequestMethod.GET)
	public String hello()  {
		JSONObject obj=new JSONObject();
		try {
			obj.put("usernameList", "012378,011114");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpEntity<String> body=new HttpEntity<String>(obj.toString());
		ResponseEntity<ResultBean<List<Hotel>>> resp=restTemplate.exchange("http://localhost:8082/api/user/queryUsers", HttpMethod.POST,body, new ParameterizedTypeReference<ResultBean<List<Hotel>>>() {});
		ResultBean<List<Hotel>> result=resp.getBody();
		if(result.getCode()==ResultBean.SUCCESS) {
			
			List<Hotel> info=result.getData();
			if(info==null) {
				return "hello";
			}
			logger.info("has size {}",info.size());
			Hotel user1=info.get(0);
			return "hello";
		}
		//restTemplate.postforo
		return "hell";
	}
}
