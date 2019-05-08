<c:if test="${sessionScope.userDetails.userId ne null}">

	<div class="footer">
	  <div class="container">
		  <p> &copy; 2019 All rights are reserved </p> 
		  <div class="header-right">
		    <p> <i class="fab fa-facebook-f"></i> </p>
		    <p><i class="fab fa-twitter"></i> </p>
		    <p><i class="fab fa-instagram"></i></p>
		  </div>
		</div>
	</div>

</c:if>

<script src="<c:url value="/resources/js/DataTable/jquery-3.3.1.min.js" />"></script>
<script src="<c:url value="/resources/js/DataTable/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/resources/js/DataTable/dataTables.bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/DataTable/dataTables.responsive.min.js" />"></script>
<script src="<c:url value="/resources/js/DataTable/responsive.bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="https://momentjs.com/downloads/moment.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/js/bootstrap-datetimepicker.min.js"></script>
<script src="<c:url value="/resources/js/register.js" />"></script>
<script src="<c:url value="/resources/js/upload.js" />"></script>
<script src="<c:url value="/resources/js/app.js" />"></script>
<script src="<c:url value="/resources/js/datatablePopup.js" />"></script>

</body>



</html>