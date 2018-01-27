package com.fp.oa.sample.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.sample.persistence.model.SampleEntity;
import com.fp.oa.sample.persistence.repository.SampleRepository;
import com.fp.oa.test.BaseTest;


/*
 * when want to use system's database for test, activate those config
 * @ActiveProfiles("local")
@AutoConfigureTestDatabase(replace = Replace.NONE)*/
@RunWith(SpringRunner.class)
@DataJpaTest
@OverrideAutoConfiguration(enabled = true)
//@Import(DataSourceConfig.class)
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SampleRepositoryTest  extends BaseTest{
	
	@Autowired
	SampleRepository repo;
	
	@Test
	public void persistTest() throws Exception{
		SampleEntity entity=new SampleEntity();
		entity.setDesc("haha desc");
		entity.setName("michael");
		entity.setStartTime(Timestamp.from(Instant.now()));
		repo.save(entity);
		assertThat(entity.getId()).isNotNull();
		
	}
}
