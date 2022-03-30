package com.doru.dorutestproject.callbacks;

import org.reactivestreams.Publisher;
import org.springframework.core.Ordered;
import org.springframework.data.mapping.callback.EntityCallback;
import org.springframework.data.r2dbc.mapping.event.AfterConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;

@Component
public class PersonAfterDeleteCallback implements EntityCallback<Person>, Ordered {

	public Publisher<Person> onAfterDelete(Person entity, SqlIdentifier table) {
		return Mono.just(entity);
	}

	@Override
	public int getOrder() {
		return 100;
	}
}
