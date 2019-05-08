<#import "spring.ftl" as spring />
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Login Project</title>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/bootstrap.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/bootstrap-theme.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/style.css'/>"/>
	
	<link rel="stylesheet" type="text/css" href="<@spring.url 'https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/DataTable/dataTables.bootstrap.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/datatable.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<@spring.url '/resources/css/DataTable/responsive.bootstrap.min.css'/>"/>
	<link rel="stylesheet" type="text/css" href="<@spring.url 'https://use.fontawesome.com/releases/v5.7.2/css/all.css'/>"/>
</head>
<body>
<#-- <#if Session.userDetails.userId??>
	<div class="header">
		<div class="container">
		  <a href="<%=request.getContextPath()%>/AdminDataServlet" class="logo"><img class="logoHeader" src="image/logo.png"></a>
		  <div class="header-right">
		    <form action="RegistrationServlet"  method="post">
				<button class="btn btn-warning" name="btnValue" value="logout" type="submit"> Logout </button>
			</form>
		  </div>
		</div>
	</div>
</#if> -->