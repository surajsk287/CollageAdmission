package com.CollageAdmissionForm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CollageAdmissionForm.model.BranchMaster;
import com.CollageAdmissionForm.model.CollegeMaster;

@Repository
public interface BranchMasterRepo extends JpaRepository<BranchMaster, Integer>{
	
	List<BranchMaster> findAllBranchMasterByCollegeMaster(CollegeMaster collegeMaster);

}
