package com.madhura.fullstackbackendness.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
@Column(name="first_name")
private String firstName;
@Column(name="last_name")
private String lastName;
@Column(name="email")
private String emailId;



}