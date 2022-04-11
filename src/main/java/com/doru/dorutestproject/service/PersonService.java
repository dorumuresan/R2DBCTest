package com.doru.dorutestproject.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;
import com.doru.dorutestproject.model.PersonRequest;
import com.doru.dorutestproject.repository.PersonRepository;
import com.querydsl.core.types.Predicate;

@Slf4j
@AllArgsConstructor
@Service
public class PersonService {

	private PersonRepository personRepository;

	public Flux<Person> getAll() {
		return personRepository.findAll();
	}

	public Mono<Person> create(PersonRequest personRequest) {
		Person person = new Person();
		person.setName(personRequest.getName());

		return personRepository.create(person);
	}

	@Transactional
	public Mono<Person> testTransactions(PersonRequest personRequest) {
		Person person1 = new Person();
		person1.setName(personRequest.getName() + " Transactional 1");

		Person person2 = new Person();
		person2.setName(personRequest.getName() + " Transactional 2");

		Person person3 = new Person();
		person3.setName(personRequest.getName() + " Transactional 3");

		return personRepository.create(person1)
				.then(personRepository.create(person2))
				.then(personRepository.create(person3))
				.then(Mono.error(new RuntimeException("Random exception.")));
	}

	public Mono<Void> delete(String id) {
		return personRepository.deleteById(id);
	}

	public Flux<Person> findAll(Predicate predicate) {
		return personRepository.findAll(predicate);
	}

	public Mono<Page<Person>> findAllPaginated(Predicate predicate, Pageable pageable) {
//		return this.personRepository.findAllBy("Doru Muresan 2", pageable)
//				.collectList()
//				.zipWith(this.personRepository.count())
//				.map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));

		return this.personRepository.findBy(pageable, predicate)
				.collectList()
				.zipWith(this.personRepository.count())
				.map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));


//		return this.personRepository.findAllBy(pageable)
//				.collectList()
//				.zipWith(this.personRepository.count())
//				.map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));

//		return this.personRepository.findAllByName("Doru Muresan 4", pageable)
//				.collectList()
//				.zipWith(this.personRepository.count())
//				.map(t -> new PageImpl<>(t.getT1(), pageable, t.getT2()));
	}

	private final R2dbcEntityTemplate template;

	public Flux<Person> findAllPaginated(String name) {
		return this.template.select(Person.class)
				.matching(Query.query(Criteria.where("title").like("%" + name + "%")).limit(10).offset(0))
				.all();
	}
}
