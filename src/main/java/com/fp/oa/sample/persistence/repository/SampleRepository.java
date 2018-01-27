package com.fp.oa.sample.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.fp.oa.sample.persistence.model.SampleEntity;

public interface SampleRepository extends CrudRepository<SampleEntity,Long>{

}
