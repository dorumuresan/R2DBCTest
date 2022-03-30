package com.doru.dorutestproject.repository;

import java.util.UUID;

import org.springframework.data.querydsl.ReactiveQuerydslPredicateExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;
import com.infobip.spring.data.r2dbc.QuerydslR2dbcFragment;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String>,
		ReactiveQuerydslPredicateExecutor<Person>, QuerydslR2dbcFragment<Person> {

	Mono<Person> findByName(String name);

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
