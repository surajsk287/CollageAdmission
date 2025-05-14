package com.CollageAdmissionForm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CollageAdmissionForm.model.CollegeMaster;
import com.CollageAdmissionForm.repo.CollegeMasterRepo;
@Service
public class CollegeMasterServiceImpl implements CollegeMasterService {

	@Autowired
	CollegeMasterRepo collegeMasterRepo;
	

	@Override
	public List<CollegeMaster> findAllCollege() {
		
		return collegeMasterRepo.findAll();
	}

	@Override
	public CollegeMaster getCollegeByCollegeId(Integer cId) {
	
		return collegeMasterRepo.findById(cId).get();
	}

	
}
