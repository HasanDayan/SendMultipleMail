package com.hd.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hd.project.model.MailProperties;

import java.io.Serializable;

@Repository
public interface MailPropertiesRepository extends JpaRepository<MailProperties, Serializable> {

}
