package com.fp.oa.sample.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fp.oa.sample.persistence.model.UserEntity;
import com.fp.oa.sample.persistence.model.UserPK;
import com.fp.oa.sample.persistence.repository.UserRepository;
import com.fp.oa.test.BaseTest;

@RunWith(SpringRunner.class)
@DataJpaTest
@OverrideAutoConfiguration(enabled = true)
public class UserRepositoryTest extends BaseTest {
	
	@Autowired
	private UserRepository repo;
	
	@Test
	public void persistAndDeleteTest() throws Exception{
		UserEntity user=new UserEntity();
		user.setCompanyName("fp");
		UserPK id=new UserPK();
		id.setCompanyId("ht001");
		id.setUserId("001");
		user.setId(id);
		repo.saveAndFlush(user);
		UserEntity result=repo.findOne(id);
		assertThat(result.getCompanyName()).isEqualTo("fp");
		
		//delete test 
		int i=repo.deleteByCompanyId("ht001");
		assertThat(i).isEqualTo(1);
		
		//delete test
		int j=repo.deleteByCompanyId2("ht001");
		assertThat(j).isEqualTo(0);
	}
	
	
	
	

}
