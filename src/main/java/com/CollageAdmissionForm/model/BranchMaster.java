package com.CollageAdmissionForm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
//import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="branch_master")
public class BranchMaster {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="branch_id")
	private Integer branchId;
	
	@Column(name="branch_name")
	private String branchName;
	
	@Column(name="fees")
	private Double fees;
	
	@ManyToOne
	@JoinColumn(name="college_id")
	private CollegeMaster collegeMaster;
}
