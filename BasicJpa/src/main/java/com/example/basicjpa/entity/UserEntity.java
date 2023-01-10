package com.example.basicjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {
	@Id
	private String email;

	private String name;

	@Column(name = "create_at")
	private LocalDateTime createAt;

	@Column(name = "update_at")
	private LocalDateTime updateAt;

	public UserEntity(String email, String name, LocalDateTime createAt, LocalDateTime updateAt){
		this.email = email;
		this.name = name;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	public UserEntity() {

	}
}
