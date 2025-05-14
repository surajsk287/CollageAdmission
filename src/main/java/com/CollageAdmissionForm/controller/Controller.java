package com.CollageAdmissionForm.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.CollageAdmissionForm.FileUpload;
import com.CollageAdmissionForm.model.BranchMaster;
import com.CollageAdmissionForm.model.CollegeMaster;
import com.CollageAdmissionForm.model.RegistrationDetails;
import com.CollageAdmissionForm.service.AdmissionService;
import com.CollageAdmissionForm.service.BranchMasterService;
import com.CollageAdmissionForm.service.CollegeMasterService;

import com.CollageAdmissionForm.service.RegistrationDetailsService;


import jakarta.servlet.http.HttpServletResponse;

@org.springframework.stereotype.Controller
@RequestMapping("/main")
public class Controller {
	
	@Autowired
	RegistrationDetailsService registrationDetailsService;
	
	@Autowired
	BranchMasterService branchMasterService;
	
	@Autowired
	CollegeMasterService collegeMasterService;
	
	@Autowired
	AdmissionService admissionService;

	@GetMapping("/home")
	public String goToHome(Model model) {
		 
		List<CollegeMaster> collegeList=collegeMasterService.findAllCollege();
		List<RegistrationDetails> regList=registrationDetailsService.getAllRegistration();
		model.addAttribute("cList",collegeList);
		model.addAttribute("regList",regList);
		return "admission";
	}
	@PostMapping("/getBranch")
	public void getBranch(@RequestParam("cId") Integer cId,HttpServletResponse response)throws IOException {
		List<BranchMaster> branchList=branchMasterService.findBranchByCollegeId(cId);
		
		String option = "<option value='-1'>---select---</option>";
		
		for(BranchMaster x:branchList) {
			option = option+"<option value='"+x.getBranchId()+"'>"+x.getBranchName()+"<option>";
		}
		System.out.println(option);
		response.getWriter().print(option);
	}
	
	@PostMapping("/saveAdmission")
	public String saveAdmission(
			@RequestParam("college") Integer cId,
			@RequestParam("branch") Integer bId,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			@RequestParam("dob") String dob,
			@RequestParam("gender") Character gender,
			@RequestParam("photo") MultipartFile photo,
			RedirectAttributes rd
			) throws ParseException{
		
		
		RegistrationDetails details=new RegistrationDetails();
		
		details.setApplicantName(name);
		details.setEmail(email);
		details.setMobile(phone);
		details.setGender(gender);	
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		Date birthDate = format.parse(dob);
		
		details.setDob(birthDate);
		details.setGender(gender);
	

		details.setImagePath(photo.getOriginalFilename());
		details.setCollegeMaster(collegeMasterService.getCollegeByCollegeId(cId));
		details.setBranchMaster(branchMasterService.getBranchByBranchId(bId));
		
		//0.0 - 1.0 Math.random() generate
		Integer randomNumber=(int)(Math.random()*10000);
		String uniqueId="";
		try {
			uniqueId = details.getCollegeMaster().getCollegeName().substring(0, 3)+details.getBranchMaster().getBranchName().substring(0, 3)+randomNumber;
		} catch (Exception e) {
			uniqueId = details.getCollegeMaster().getCollegeName().substring(0, 1)+details.getBranchMaster().getBranchName().substring(0, 1)+randomNumber;
		}
		details.setAdmissionId(uniqueId);
		
		try {
			RegistrationDetails saveDetails= registrationDetailsService.saveAdmission(details);
			try {
				FileUpload.uploadFile(photo);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("failed in upload");
			}
			
			rd.addFlashAttribute("msg", "success");
		} catch (Exception e) {
			rd.addFlashAttribute("msg","error");
		}
		
		
		return "redirect:./home";
		
	}
	
	@PostMapping("/getFeeByBranch")
	public void getFee(@RequestParam("bId") Integer bId, HttpServletResponse response) throws IOException{
		Double fees=branchMasterService.getFeeByBranch(bId);
		
		String input= "<input type='text' class='form-control text-dark' value='"+fees+"' disabled>";
		response.getWriter().print(input);
		
	}
	
	@GetMapping("/deleteRegDetails")
	public String deleteRegDetails(@RequestParam("registrationId") Integer registrationId) {
		RegistrationDetails deleteDetails= registrationDetailsService.getRegiDetailById(registrationId);
		
		//registrationDetailsService.deleteRegistrationDetails(deleteDetails);
		
		return "redirect:./home";
	}

	
}
