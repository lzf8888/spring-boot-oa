package com.fp.oa.test;

import org.junit.BeforeClass;

public class BaseTest {
	@BeforeClass
	public static void setUp() {
		System.setProperty("db.schema.name", "TEST");
	}

}
