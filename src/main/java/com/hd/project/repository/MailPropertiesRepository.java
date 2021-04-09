package com.hd.project.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hd.project.model.MailProperties;

@Repository
public interface MailPropertiesRepository extends CrudRepository<MailProperties, Long> {

}
