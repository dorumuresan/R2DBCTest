package com.doru.dorutestproject.repository.impl;

import java.util.function.BiFunction;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import reactor.core.publisher.Flux;

import com.doru.dorutestproject.model.Person;

@Repository
public class PersonRepositoryImpl {

	private final DatabaseClient databaseClient;

	public PersonRepositoryImpl(DatabaseClient databaseClient) {
		this.databaseClient = databaseClient;
	}

	public static final BiFunction<Row, RowMetadata, Person> MAPPING_FUNCTION = (row, rowMetaData) -> Person.builder()
			.id(row.get("id", String.class))
			.name(row.get("name", String.class))
			.build();

	public Flux<Person> findAll(String name) {
		return this.databaseClient
				.sql("SELECT * FROM person WHERE name LIKE :name")
				.bind("name", "%" + name + "%")
				.map(MAPPING_FUNCTION)
				.all();
	}
}
