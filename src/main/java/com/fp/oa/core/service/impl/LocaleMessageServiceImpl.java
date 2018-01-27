package com.fp.oa.core.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.fp.oa.core.service.LocaleMessageService;

@Service
public class LocaleMessageServiceImpl implements LocaleMessageService {

	@Autowired
    private MessageSource messageSource;

	@Override
	public String getMessage(String key) {
		String msg=messageSource.getMessage(key, null, Locale.SIMPLIFIED_CHINESE);
		return msg;
	}

	@Override
	public String getMessage(String key,Locale locale) {
		return messageSource.getMessage(key, null, locale);
	}
}
