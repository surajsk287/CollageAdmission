package com.CollageAdmissionForm.service;

import java.util.List;

import com.CollageAdmissionForm.model.BranchMaster;

public interface BranchMasterService {

	List<BranchMaster> findBranchByCollegeId(Integer cId);

	BranchMaster getBranchByBranchId(Integer bId);

	Double getFeeByBranch(Integer bId);

}
