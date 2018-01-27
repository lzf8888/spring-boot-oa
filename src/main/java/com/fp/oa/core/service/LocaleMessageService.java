package com.fp.oa.core.service;

import java.util.Locale;

public interface LocaleMessageService {

	public String getMessage(String key);

	public String getMessage(String key, Locale locale);
}
