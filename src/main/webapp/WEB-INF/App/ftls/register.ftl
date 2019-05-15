<#include "header.ftl">

  
    <div class="container">

		<div class="row">
			<div class="head">
				<div class="col-md-4">
					<h1></h1>
				</div>
				<div class="col-md-4">
					<#if Session.userDetails??>
						<h1 align="center">${data.firstName}'s Details</h1>
					<#else>
					  	<h1 align="center">Registration Form</h1>
					</#if>
				
				</div>
				<div class="col-md-4 logout">
	
				</div>
			</div>
		</div>

		
			<form name="myform" modelAttribute="userDetails" class="form-horizontal" action="saveUser" method="post">
				<fieldset>
				<br>
					<!-- Text input-->
					<input type="hidden" name="userId" <#if Session.userDetails??> value="${data.userId}" </#if> >
					<input type="hidden" name="role" <#if Session.userDetails??> value="${data.role}" </#if>>
		
					<div class="form-group">
						<label class="col-md-4 control-label">First Name *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									name="firstName" <#if Session.userDetails??> value="${data.firstName}" </#if> id="firstname"
									class="form-control" placeholder="First Name"
									onblur="validateFname()"
									oninput="maxLengthCheck(this)"
									maxlength = "45"
									onkeypress="return preventNumerics(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error"></p>
						</div>
					</div>
					
					
					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">Last Name *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-user"></i></span> <input type="text"
									name="lastName" 
									<#if Session.userDetails??> value="${data.lastName}" </#if>
									id="lastname" class="form-control" placeholder="Last Name"
									onblur="validateLname()"
									oninput="maxLengthCheck(this)"
									maxlength = "45"
									onkeypress="return preventNumerics(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error1"></p>
						</div>
					</div>

					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">Birth Date *</label>
						<div class="col-md-4">
							<div class="input-group datepick">
								<input type="text" class="form-control" placeholder="0000/00/00"
									<#if Session.userDetails??> value="${data.dateOfBirth?iso("GMT+05:30")}" </#if>
									name="dateOfBirth" id="birthdate" onblur="validateBirthdate()">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error2"></p>
						</div>
					</div>

					<!-- Text input-->

					<div class="form-group">
						<label class="col-md-4 control-label">Phone No. *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-earphone"></i></span> <input type="text"
									id="phoneno" name="contactNo"
									<#if Session.userDetails??> value="${data.contactNo}" </#if>
									class="form-control" value="" onblur="validatePhone()"
										placeholder="+910000000000"
									oninput="maxLengthCheck(this)"
									maxlength = "13"
									onkeypress="return preventAlphabets(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error3"></p>
						</div>
					</div>

					<!-- Text input-->
					<div class="form-group">
						<label class="col-md-4 control-label">E-Mail *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input type="text"
									name="emailId" 
									<#if Session.userDetails??> value="${data.emailId}" </#if> id="emailid"
									class="form-control" placeholder="abcd@def.com"
									onblur="validateEmail(),checkemail()"
									oninput="maxLengthCheck(this)"
									maxlength = "255"
									onkeypress="return preventSpace(event)">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error4"></p>
						</div>
					</div>


					<!-- password input-->
					<div class="form-group">
						<label class="col-md-4 control-label">Password *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input
									<#if Session.userDetails??> type="text" <#else> type="password" </#if>
									name="password" id="password"
									<#if Session.userDetails??> value="${data.password}" </#if> 
									onselectstart="return false"
									onpaste="return false;" onCopy="return false"
									onCut="return false" class="form-control"
									placeholder="password" data-placement="right"
									oninput="maxLengthCheck(this)"
									maxlength = "45" rel="txtTooltip"
									title="Password must have 8-12 characters & atleast hve one capital one small one one spacial char and one number."
									onblur="validatePassword()" onkeyup="validatePassword()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error5"></p>
						</div>
					</div>
					
				<#if !Session.userDetails??>
					<!-- Password input-->
					<div class="form-group">
						<label class="col-md-4 control-label">Confirm Password *</label>
						<div class="col-md-4">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="glyphicon glyphicon-envelope"></i></span> <input
									type="password" <#if Session.userDetails??> value="${data.password}" </#if>
									onpaste="return false;" onCopy="return false"
									onCut="return false" 
									oninput="maxLengthCheck(this)"
									maxlength = "45"
									id="confirmpassowrd" class="form-control"
									placeholder="confirm password" onblur="validateCpassword()">
							</div>
						</div>
						<div class="col-md-4">
							<p id="error6"></p>
						</div>
					</div>
				</#if>
				
				
				<!-- radio checks -->
					<div class="form-group">
					<#if Session.userDetails??>
						<#assign gender = "${data.gender}">
					</#if>
						<label class="col-md-4 control-label">Gender *</label>
						<div class="col-md-4">
							<div class="radio">
								<label> <input name="gender" type="radio" id="gender1"
									value="MALE"
									<#if Session.userDetails??>
									 	<#if gender=="MALE">
									 		checked
										 </#if>
									</#if>
									onblur="validateGender()" /> Male
								</label>
							</div>
							<div class="radio">
								<label> <input name="gender" type="radio" id="gender2"
									value="FEMALE"
									<#if Session.userDetails??>
										<#if gender=="FEMALE">
											checked
										</#if>
									</#if>	
									onblur="validateGender()" /> Female
								</label>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error7"></p>
						</div>
					</div>
					
					
					<!-- checkboxes-->

					<div class="form-group">
					<#if Session.userDetails??>
						<#assign language = "${data.language}">
					</#if>
						<label class="col-md-4 control-label">Languages *</label>
						<div class="col-md-4">
							<div class="input-group">
								<div class="checkbox">
									<label><input name="language" id="en" type="checkbox"
										value="English" onblur="validateLanguage()"
										<#if Session.userDetails??>
										 	<#if language?contains("English")>
										 		checked
											 </#if>
										</#if>>English</label>
								</div>
								<div class="checkbox">
									<label><input name="language" id="hi" type="checkbox"
										value="Hindi" onblur="validateLanguage()"
										<#if Session.userDetails??>
										 	<#if language?contains("Hindi")>
										 		checked
											 </#if>
										</#if>>Hindi</label>
								</div>
								<div class="checkbox">
									<label><input id="gj" name="language" type="checkbox"
										value="Gujarati" onblur="validateLanguage()"
										<#if Session.userDetails??>
										 	<#if language?contains("Gujarati")>
										 		checked
											 </#if>
										</#if>>Gujarati</label>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<p id="error8"></p>
						</div>
					</div>
					
					<#if Session.userDetails??>
						<#assign string1 = addressData?size>
					</#if>
					
						<#list addressData as addressData>
					  		
					  		<div id="sections">
								<div class="section">
									<fieldset>
									
										<input type="hidden" name="addrId" <#if Session.userDetails??> value="${addressData.addrId}" </#if>>
										<div class="form-group">
											<label class="col-md-4 control-label">Address *</label>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"></span> <input type="text"
														name="street1" <#if Session.userDetails??> id="street1_${addressData.addrId}" <#else> id="street1_" </#if> 
														class="form-control street1Class"
														placeholder="Street 1" <#if Session.userDetails??> value="${addressData.street1}" </#if>
														oninput="maxLengthCheck(this)"
														maxlength = "80"
														onblur="validateStreet1()">
												</div>
											</div>
											<div class="col-md-4">
												<p name="error_street1" <#if Session.userDetails??> id="error_street1_${addressData.addrId}"
												<#else> id="error_street1_" </#if>></p>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-md-4 control-label"></label>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"></span> <input type="text"
														name="street2" <#if Session.userDetails??> id="street2_${addressData.addrId}" <#else> id="street2_" </#if>
														class="form-control street2Class"
														placeholder="Street 2" <#if Session.userDetails??> value="${addressData.street2}" </#if>
														oninput="maxLengthCheck(this)"
														maxlength = "80"
														onblur="validateStreet2()">
												</div>
											</div>
											<div class="col-md-4">
												<p name="error_street2" <#if Session.userDetails??> id="error_street2_${addressData.addrId}" <#else>
												id="error_street2_" </#if>></p>
											</div>
										</div>
										
										<div class="form-group">
											<label class="col-md-4 control-label"></label>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"></span> <input type="text"
														name="city" <#if Session.userDetails??> id="city_${addressData.addrId}" <#else> id="city_" </#if>
														class="form-control cityClass"
														placeholder="City" <#if Session.userDetails??> value="${addressData.city}" </#if>
														oninput="maxLengthCheck(this)"
														maxlength = "50"
														onblur="validateCity()"
														onkeypress="return preventNumerics(event)">
												</div>
											</div>
											<div class="col-md-4">
												<p name="error_city" <#if Session.userDetails??> id="error_city_${addressData.addrId}" 
												<#else> id="error_city_" </#if>></p>
											</div>
										</div>
		
										<!-- Text input -->
		
										<div class="form-group">
											<label class="col-md-4 control-label">Pin Code *</label>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-home"></i></span> <input type="text"
														name="pincode" <#if Session.userDetails??> id="pincode_${addressData.addrId}" <#else> id="pincode_" </#if>
														class="form-control pincodeClass"
														<#if Session.userDetails??> value="${addressData.pincode?c}" </#if>
														placeholder="123456"
														oninput="maxLengthCheck(this)"
														maxlength = "6"
														onblur="validatePincode()"
														onkeypress="return preventAlphabets(event)">
												</div>
											</div>
											<div class="col-md-4">
												<p name="error_pincode" <#if Session.userDetails??> id="error_pincode_${addressData.addrId}"
												<#else> id="error_pincode_" </#if>></p>
											</div>
										</div>
		
										<!-- Select Basic -->
		
										<div class="form-group">
											<label class="col-md-4 control-label">State *</label>
											<#if Session.userDetails??>
												<#assign state = "${addressData.state}">
											</#if>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-list"></i></span> <select name="state"
														<#if Session.userDetails??> id="state_${addressData.addrId}" <#else> id="state_" </#if>
														 class="form-control selectpicker stateClass"
														onblur="validateState()">
														<option value="">Please select your state</option>
														<option value="gujarat"<#if Session.userDetails??> <#if state?contains("gujarat")>
										 		selected
											 </#if> </#if>>Gujarat</option>
														<option value="rajasthan"<#if Session.userDetails??> <#if state?contains("rajasthan")>
										 		selected
											 </#if> </#if>>Rajasthan</option>
													</select>
												</div>
											</div>
											<div class="col-md-4">
												<p name="error_state" <#if Session.userDetails??> id="error_state_${addressData.addrId}" <#else>
												id="error_state_" </#if>></p>
											</div>
										</div>
										
										<!--   Select Basic -->
		
										<div class="form-group">
											<label class="col-md-4 control-label">Country *</label>
											<#if Session.userDetails??>
												<#assign country = "${addressData.country}">
											</#if>
											<div class="col-md-4">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-list"></i></span> <select name="country"
														<#if Session.userDetails??> id="country_${addressData.addrId}" <#else> id="country_" </#if>
														class="form-control selectpicker countryClass"
														onblur="validateCountry()">
														<option value="">Please select your country</option>
														<option value="india" <#if Session.userDetails??> <#if country?contains("india")>
										 		selected
											 </#if> </#if>>India</option>
														<option value="usa" <#if Session.userDetails??> <#if country?contains("usa")>
										 		selected
											 </#if> </#if>>USA</option>
													</select>
												</div>
											</div>
											<div class="col-md-4">
												<p name="error_country" <#if Session.userDetails??> id="error_country_${addressData.addrId}"
												<#else> id="error_country_" </#if>></p>
											</div>
										</div>
		
		
										<div class="form-group">
											<div class="col-md-4"></div>
											<div class="col-md-4">
												<button type="button" class='remove btn btn-default'>Remove this address</button>
											</div>
										</div>
									</fieldset>
									
									
								</div>
							</div>
							
					</#list>
							<div id="limit"></div>
							<div class="form-group">
								<div class="col-md-4"></div>
								<div class="col-md-4">
									<button type="button" class="addsection btn btn-default">Add another
										Address</button>
								</div>
							</div>
							
						<#if Session.userDetails??>
							<!-- Button -->
							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4">

									<input type="hidden" name="userId" <#if Session.userDetails??>value="${data.userId}" </#if> />
									
									<button type="submit" name="btnValue" onclick="return validateForm(event);" value="update"
										id="submit" class="btn btn-primary">Update</button>
									<button type="submit" name="btnValue" value="retrieveData"
										class="btn btn-default">Cancel</button>
								</div>
							</div>
						<#else>
							<div class="form-group">
								<label class="col-md-4 control-label"> </label>
								<div class="col-md-4">
									<div class="input-group">
										<div class="checkbox">
											<label><input id="condition" type="checkbox"
												onclick="validateCheckbox()">Accept all
												terms and conditions</label>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4"></div>
								<div class="col-md-4">
									<p>Fields with (*) are mandatory to fill</p>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4"></label>
								<div class="col-md-4">
									<button type="submit" name="btnValue" value="insert"
										id="submit" onclick="return validateForm(event);" class="btn btn-primary" disabled> Submit </button>
									<button type="reset" class="btn btn-default">Reset</button>
								</div>
							</div>
						</#if>
				</fieldset>
			</form>
		</div>
  <!-- /.container -->
<br>
<br>
<br>

<script type="text/javascript">
	var str = <#if Session.userDetails??> ${string1} <#else> 1 </#if>	  		
</script>			
	
<#include "footer.ftl">

