package com.doru.dorutestproject.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("person")
public class Person {

// implements Persistable<String> {

	@Id
	@Column("id")
	private String id;

	private String name;

//	@Transient
//	@JsonIgnore
//	private boolean isNew;

//	@Override
//	public boolean isNew() {
//		return this.isNew;
//	}
}
