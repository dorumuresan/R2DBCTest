package com.doru.dorutestproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

import com.querydsl.sql.SQLTemplates;

@Configuration
@EnableR2dbcRepositories
public class R2dbcConfiguration {

	@Bean
	public SQLTemplates sqlTemplates() {
		return SQLTemplates.DEFAULT;
	}
}
