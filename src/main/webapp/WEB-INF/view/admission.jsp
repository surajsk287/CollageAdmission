<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>College Admission Form </title>
<script src="https://code.jquery.com/jquery-3.7.1.js"
        integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/831c05ff45.js" crossorigin="anonymous"></script>
    
    <!-- CSS -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" />

<!-- JavaScript -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    
</head>
<style>
 .box{
 border:4px solid black;
 }
 .title{
 position:relative;
 top:-31px;
 background: white;
 }
 .canvas{
 background-color: white;
 }
</style>
<body>
     
     <c:if test="${msg eq 'success' }">
        <input type="hidden" id="id" value="${data}">
        <script type="text/javascript">
        let rid = $("#id").val();
        toastr.success('Admission is successfull'+rid);
        </script>
     </c:if>
     <c:if test="${msg eq 'error' }">
        <script type="text/javascript">
        toastr.success('Admission is Failed');
        </script>
     </c:if>

    <div class="container">
    <div class="text-center text-primary h2">College Admission Form</div>
    <span class="text-danger" style="float:right">*mandatory fields</span>
    <form method="post" action="./saveAdmission" enctype="multipart/form-data" onsubmit="return validateForm();" name="admForm">
       <div class="row">
        <div class="col-md-4">
           
              <label class="">College Name<span class="text-danger">*</span></label>
              <select class="form-select" id="cId" onchange="getBranch()" name="college" required>
                <option value="-1">---select college---</option>
                 <c:forEach items="${cList}" var="c">
                    <option value="${c.collegeId}">${c.collegeName}</option>
                    
                 </c:forEach>
              </select>
              
           </div>
           <div class="col-md-4">
                <label>Branch Name<span class="text-danger">*</span></label>
              <select class="form-select" id="bId" name="branch" onchange="getFee()">
                 <option value="-1">--select Branch Name---</option>
              </select>
              </div>
              <div class="col-md-4">
                 <label>Fees (Rs.)</label>
                 <div id="feeInp">
                     <input type="text" class="form-control" disabled>
                 </div>
              </div>
        </div>
        
    <div class="canvas">
         
              <div class="box mt-4 p-3">
                 <div class="title px-3"><u><b>Applicant Details</b></u></div>
                 <div class="row">
                 <div class="col-md-4">
                <label>Name <span class="text-danger">*</span></label>
                <input type="text" class="form-control" name="name" placeholder="enter your name" id="name">
                </div>
               <div class="col-md-4">
                <label>Email <span class="text-danger">*</span></label>
                <input type="email" class="form-control" name="email" placeholder="enter your email" id="email">
                </div>
               <div class="col-md-4">
                <label>Mobile <span class="text-danger">*</span></label>
                <input type="text" class="form-control" name="phone" placeholder="enter your number" id="phone">
                </div>
               <div class="col-md-4">
                <label>Date of Birth <span class="text-danger">*</span></label>
                <input type="date" class="form-control" name="dob" id="dob">
                </div>
                 <div class="col-md-4 p-3">
                   <label>Gender<span class="text-danger">*</span></label>
                   <input type="radio" name="gender" value="m">Male
                   <input type="radio" name="gender" value="f">female
                 </div>
                 <div class="col-md-4">
                   <label>Upload Photo<span class="text-danger">*</span></label>
                   <input type="file" class="form-control" name="photo" id="photo">
                 </div>
              </div>
              <div class="text-center ">
                 <button type="submit" class="btn btn-success" Style="box-shadow:5px 5px lightgrey">Submit</button>
                 <button type="reset" class="btn btn-secondary mx-3" Style="box-shadow:5px 5px lightgrey">Reset</button>
              </div>
            </div>
        </div>
    </form>
    <div class="mt-4">
       <div class="h2 text-center text-primary">Registration Details</div>
           <table class="table table-bordered table-striped">
	       <thead class="bg-info">
	          <tr> 
	             <th>Sr.No.</th>
	             <th>Name</th>
	             <th>Email</th>
	             <th>Mobile No</th>
	             <th>DOB</th>
	             <th>Image</th>
	             <th>Gender</th>
	             <th>College Name</th>
	             <th>Branch</th>
	             <th>Action</th>
	          </tr>
	       </thead>
	       <tbody>
	          <c:forEach var="reg" items="${regList}" varStatus="counter">
	          <tr>
	          
	             <td>${counter.count}</td>
	             <td>${reg.applicantName}</td>
	             <td>${reg.email}</td>
	             <td>${reg.mobile}</td>
	             <td>${reg.dob}</td>
	             <td>${reg.imagePath}</td>
	             <td>${reg.gender}</td>
	             <td>${reg.collegeMaster.collegeName}</td>
	             <td>${reg.collegeMaster.collegeName}-${reg.branchMaster.branchName}</td>
	             <td><a href="./deleteRegDetails?registrationId=${reg.registrationId}"><span class="btn btn-danger"><i class="fa-solid fa-trash-can fa-beat"></i></span></a></td>
	          </tr>
	          </c:forEach>
	       </tbody>
	     </table>
    </div>
  </div>
    
   <script type="text/javascript">
         function getBranch(){
        	 let collegeId=$("#cId").val();
        	 
        	 $.ajax({
        		 url:'./getBranch',
        		 type:'post',
        		 data:"cId="+collegeId,
        		 success: function(response){
        			 $("#bId").empty();
        			 $("#bId").append(response);
        		 },
        		 error: function(){
        			 alert("Error");
        		 }
        	 });
         }
         
         function validateForm(){
        	 let college=$("#cId").val();
        	 let branch=$("#bId").val();
        	 let name=$("#name").val();
        	 let email=$("#email").val();
        	 let mobile=$("#phone").val();
        	 let dob=$("#dob").val();
        	 let gender=$('input[name="gender"]:checked').val();
        	 let photo=$("#photo").val();
        	 
        	 if (college==="-1"){
        		 toastr.error("Please select a College")
        		 return false;
        	 }
        	 
        	 if (branch==="-1"){
        		 toastr.error("Please select a Branch");
        		 return false;
        	 }
        	 if (!name || name.trim()===""){
        		 toastr.error("Name is required");
        		 $("#name").focus();
        		 return false;
        	 }
        	 if (!email || email.trim()===""){
        		 toastr.error("Email is required");
        		 $("#email").focus();
        		 return false;
        	 }
        	 let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        	    if (!emailPattern.test(email)) {
        	    	toastr.error("Invalid email format");
        	        $("#email").focus();
        	        return false;
        	    }

        	    if (!mobile || mobile.trim() === "") {
        	    	toastr.error("Mobile number is required");
        	        $("#phone").focus();
        	        return false;
        	    }

        	    if (!/^\d{10}$/.test(mobile)) {
        	    	toastr.error("Mobile number is invalid");
        	        $("#phone").focus();
        	        return false;
        	    }
        	 if (mobile && mobile.length<10){
        		 toastr.error("Mobile number is invalid");
        		 $("#phone").focus();
        		 return false;
        	 }
        	 if (!dob || dob.trim()===""){
        		 toastr.error("Date of birth is required");
        		 $("#dob").focus();
        		 return false;
        	 }
        	 if (!gender){
        		 toastr.error("Please select Gender");
        		
        		 return false;
        	 }
        	 if (!photo || photo.trim()===""){
        		 toastr.error("Please upload a photo");
        		 $("#photo").focus();
        		 return false;
        	 }
        	 return true;
         }
         
         function getFee(){
        	 let branchId=$("#bId").val();
        	 
        	 $.ajax({
        		 url:"./getFeeByBranch",
        		 type:"post",
        		 data:"bId="+branchId,
        		 success: function(response){
        			 $("#feeInp").empty();
        			 $("#feeInp").append(response);
        		 }
        	 });
         }
   </script>
</body>
</html>