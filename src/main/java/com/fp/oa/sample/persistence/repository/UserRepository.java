package com.fp.oa.sample.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fp.oa.sample.persistence.model.UserEntity;
import com.fp.oa.sample.persistence.model.UserPK;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,UserPK>{

	//use native query to delete,must add modify annotation,return can only be int, long invalid
	//better use deleteByCompanyId2 cause in deleteByCompanyId we must hardcode db schema(if have)
	//besides, if use curdRepository other than JpaRepository, the entity saved by repo yet not flushed will
	//not be deleted by this method,but can be deleted use deleteByCompanyId2
	@Modifying
	@Query(value="DELETE FROM TEST.USER_TABLE u WHERE u.COMPANY_ID= ?1",nativeQuery =true)
	public int deleteByCompanyId(String companyId);
	
	// JPQL query
	@Modifying
	@Query(value="DELETE FROM UserEntity u WHERE u.id.companyId= ?1")
	public int deleteByCompanyId2(String companyId);
}
