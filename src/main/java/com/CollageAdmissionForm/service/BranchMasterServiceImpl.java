package com.CollageAdmissionForm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CollageAdmissionForm.model.BranchMaster;
import com.CollageAdmissionForm.model.CollegeMaster;
import com.CollageAdmissionForm.repo.BranchMasterRepo;
import com.CollageAdmissionForm.repo.CollegeMasterRepo;
@Service
public class BranchMasterServiceImpl implements BranchMasterService {
	
	@Autowired
	BranchMasterRepo branchMasterRepo;
	
	@Autowired
	CollegeMasterRepo  collegeMasterRepo;

	@Override
	public List<BranchMaster> findBranchByCollegeId(Integer cId) {
		
		CollegeMaster collegeMaster=collegeMasterRepo.findById(cId).get();
		return branchMasterRepo.findAllBranchMasterByCollegeMaster(collegeMaster);
	}

	@Override
	public BranchMaster getBranchByBranchId(Integer bId) {
		
		return branchMasterRepo.findById(bId).get();
	}

	@Override
	public Double getFeeByBranch(Integer bId) {
		
		return branchMasterRepo.findById(bId).get().getFees();
	}

}
