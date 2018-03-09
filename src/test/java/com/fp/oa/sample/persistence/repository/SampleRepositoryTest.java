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

import com.fp.oa.config.DataSourceConfig;
import com.fp.oa.sample.persistence.model.SampleEntity;
import com.fp.oa.sample.persistence.repository.SampleRepository;
import com.fp.oa.test.BaseTest;


/*
 * when want to use system's database for test, activate those config
@AutoConfigureTestDatabase(replace = Replace.NONE) //do not use embededProxyDataSource
@Import(DataSourceConfig.class) //choose the data source you really want,in test,it h2 config,
Be NOTE THAT if DataSourceConfig has @Bean h2 webconlse it will cause port bind error,
in that case better make @Bean h2 webconlse in another config file;
*/
// current annotations works with spring.jpa.properties.hibernate.default_schema in properties
@RunWith(SpringRunner.class)
@DataJpaTest
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
