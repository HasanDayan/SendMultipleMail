package com.hd.project.util;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CrudImplements<T> {

	JpaRepository<T, Serializable> getRepository();

	default void save(T t) {
		getRepository().save(t);
	}

	default List<T> findAll() {
		return getRepository().findAll();
	}

	default Optional<T> findById(Serializable id) {
		return getRepository().findById(id);
	}

	default void deleteById(Serializable id) {
		getRepository().deleteById(id);
	}

}
