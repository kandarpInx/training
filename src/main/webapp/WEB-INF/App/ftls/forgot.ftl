<#include "header.ftl">

<div class="container">	
      <form name="myform" class="form-horizontal" action="forgotData" method="post">
        <fieldset>

          <!-- Form Name -->
          <h1 align="center"> Forgot Password </h1>
			<br><br>

          <!-- Text input-->
          <div class="form-group">
            <label class="col-md-4 control-label">Insert Valid Email Address</label>  
            <div class="col-md-4">
              <div class="input-group">
                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                <input type="text" name="emailId" class="form-control" placeholder="abcd@def.com" required>
              </div>
            </div>
            <div class="col-md-4">
              <p id="error1"></p>
            </div>
          </div>
          
          
          
           <div class="form-group">
            <label class="col-md-4 control-label"></label>  
            <div class="col-md-4">
              <div class="input-group">
		          <#if error1??>
		          	${error1}
		          </#if>
		          <br>
		          <a href="index"> Back to Login page </a>
		       </div>
		     </div>
		    </div>

          <!-- Button -->
          <div class="form-group">
            <label class="col-md-4"></label>
            <div class="col-md-4">
              <button type="submit" name="btnValue" value="forgot" class="btn btn-warning forgot">Submit </button>
            </div>
          </div>

        </fieldset>
      </form>
    </div>
  <#include "footer.ftl">
