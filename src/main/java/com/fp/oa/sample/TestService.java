package com.fp.oa.sample;

import org.springframework.stereotype.Service;

import com.fp.oa.core.result.ResultBean;
import com.google.common.base.Preconditions;

@Service
public class TestService {

	public ResultBean<?> getResultByIdButFail(int id) {
		Preconditions.checkNotNull(id);
		throw new RuntimeException("do it on purpose");
	}
	

}
