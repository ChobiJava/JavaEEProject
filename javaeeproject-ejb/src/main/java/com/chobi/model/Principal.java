package com.chobi.model;

import com.chobi.model.superclasses.EmployeeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Chobii on 17/09/15.
 */
@Entity
@Table(name = "principal")
public class Principal extends EmployeeEntity {



}
