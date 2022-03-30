package com.doru.dorutestproject.callbacks;

import org.reactivestreams.Publisher;
import org.springframework.core.Ordered;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;

@Component
public class PersonBeforeSaveCallback implements BeforeSaveCallback<Person>, Ordered {

	@Override
	public Publisher<Person> onBeforeSave(Person entity, OutboundRow row, SqlIdentifier table) {
		return Mono.just(entity);
	}

	@Override
	public int getOrder() {
		return 2;
	}
}
