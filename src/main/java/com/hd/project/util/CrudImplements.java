package com.hd.project.util;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CrudImplements<T> {

	CrudRepository<T, Long> getRepository();

	default T save(T t) {
		return getRepository().save(t);
	}

	default Iterable<T> findAll() {
		return getRepository().findAll();
	}

	default Optional<T> findById(Long id) {
		return getRepository().findById(id);
	}

	default void deleteById(Long id) {
		getRepository().deleteById(id);
	}

}
