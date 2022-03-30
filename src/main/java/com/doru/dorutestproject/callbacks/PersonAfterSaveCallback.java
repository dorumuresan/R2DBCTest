package com.doru.dorutestproject.callbacks;

import org.reactivestreams.Publisher;
import org.springframework.core.Ordered;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.AfterSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

import com.doru.dorutestproject.model.Person;

@Component
public class PersonAfterSaveCallback implements AfterSaveCallback<Person>, Ordered {

	@Override
	public Publisher<Person> onAfterSave(Person entity, OutboundRow outboundRow, SqlIdentifier table) {
		return Mono.just(entity);
	}

	@Override
	public int getOrder() {
		return 3;
	}
}
