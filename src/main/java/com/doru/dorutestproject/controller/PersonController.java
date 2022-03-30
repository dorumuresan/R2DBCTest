package com.doru.dorutestproject.controller;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;
import com.doru.dorutestproject.model.PersonRequest;
import com.doru.dorutestproject.service.PersonService;
import com.querydsl.core.types.Predicate;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

	private final PersonService personService;

	@PostMapping
	public Mono<Person> create(@RequestBody PersonRequest personRequest) {
		return personService.create(personRequest);
//		return personService.testTransactions(personRequest);
	}

	@DeleteMapping(path = "/{id}")
	public Mono<Void> delete(@PathVariable String id) {
		return personService.delete(id);
	}

	@GetMapping
	public Flux<Person> getAll(@QuerydslPredicate(root = Person.class) Predicate predicate) {
		return personService.findAll(predicate);
	}
}
