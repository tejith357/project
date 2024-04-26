package com.gym.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public  class Feedback {
  @Id
  @Column
  @NotBlank(message = "Name is required")
  @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
  @Pattern(regexp="^[a-zA-Z ]*$", message = "Name should only contain letters and spaces")
  private String name;

  public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
@NotBlank(message = "Email is required")
@Email(message = "Invalid email format")
@Column
private String email;
public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}


@NotBlank(message = "Feedback is required")
@Column
private String feedback;
public String getFeedback() {
	return feedback;
}

public void setFeedback(String feedback) {
	this.feedback = feedback;
}



}

