package com.doru.dorutestproject.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;
import com.infobip.spring.data.r2dbc.QuerydslR2dbcFragment;
import com.querydsl.core.types.Predicate;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String>, ReactiveQuerydslPredicateExecutor<Person>,
		QuerydslR2dbcFragment<Person> {

	Mono<Person> findByName(String name);

//	@Query("SELECT * FROM person WHERE #{[1]}")
	@Query("SELECT * FROM person WHERE :predicate")
	Flux<Person> findBy(Pageable pageable, Predicate predicate);



	Flux<Person> findAllBy(Pageable pageable);



	@Query("SELECT * FROM person WHERE name = :name")
	Flux<Person> findAllBy(String name, Pageable pageable);

	Flux<Person> findAllByName(String name, Pageable pageable);

	default Mono<Person> create(Person person) {
//		person.setNew(true);
		person.setId(UUID.randomUUID().toString());
		return this.save(person);
	}

	default Mono<Person> update(Person person) {
//		person.setNew(false);
		return this.save(person);
	}
}
