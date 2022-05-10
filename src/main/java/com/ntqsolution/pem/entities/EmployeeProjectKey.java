package com.ntqsolution.pem.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class EmployeeProjectKey implements Serializable {
    private static final long serialVersionUID = 1857575127796014083L;
    @Column(name = "employee_id")
    private Long employeeID;
    @Column(name = "project_id")
    private Long projectID;
}
