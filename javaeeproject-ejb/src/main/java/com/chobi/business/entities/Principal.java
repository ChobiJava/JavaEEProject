package com.chobi.business.entities;

import com.chobi.business.entities.superclasses.EmployeeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Chobii on 17/09/15.
 */
@Entity
@Table(name = "principal")
public class Principal extends EmployeeEntity {



}
