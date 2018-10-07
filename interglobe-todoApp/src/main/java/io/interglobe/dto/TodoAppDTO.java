package io.interglobe.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class TodoAppDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank
	@Size(max = 50)
	@Column(unique = true)
	private String title;

	private Boolean completed = false;

	private Date createdAt = new Date();

	public TodoAppDTO() {
	}

	public TodoAppDTO(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "TodoAppDTO [id=" + id + ", title=" + title + ", completed=" + completed + "]";
	}

}
