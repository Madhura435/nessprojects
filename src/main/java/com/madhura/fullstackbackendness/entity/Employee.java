package com.madhura.fullstackbackendness.entity;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description="Deatils about employee")
public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@ApiModelProperty(notes="The unique id of Employee")
private long id;
@Column(name="first_name")
@ApiModelProperty(notes="The Employee FirstName")
private String firstName;
@Column(name="last_name")
@ApiModelProperty(notes="The Employee lastName")
private String lastName;
@Column(name="email")
@ApiModelProperty(notes="The Employee Email")
private String emailId;

}