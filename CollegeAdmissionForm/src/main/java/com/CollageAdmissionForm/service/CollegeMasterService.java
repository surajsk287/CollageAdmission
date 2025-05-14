package com.CollageAdmissionForm.service;

import java.util.List;

import com.CollageAdmissionForm.model.CollegeMaster;

public interface CollegeMasterService {


	List<CollegeMaster> findAllCollege();

	CollegeMaster getCollegeByCollegeId(Integer cId);

}
