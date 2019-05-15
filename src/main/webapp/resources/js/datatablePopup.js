$(window).bind("pageshow", function(event) {
    if (event.originalEvent.persisted) {
        window.location.reload(); 
    }
});

$(document).ready(function() {
		$('#example').DataTable({
			"aLengthMenu" : [ [ 5, 10, 25, -1 ], [ 5, 10, 25, "All" ] ],
			"iDisplayLength" : 5,
			responsive: true,
		});
	});

	$(document).find('.theButton').on('click',function(){
//	 alert($(this).closest("tr").find(".nr").text()); 
    $.ajax({
        type: "POST",
        url: "ListAddress",
        data: { userId: $(this).closest("tr").find(".nr").text() },
        success:function(result){
        	$("#address").find("tr:not(:first)").remove();
        	for(var i = 0; i < result.length; i++)
            {  
              $('#address tr:nth-child(1)').after(
                 '<tr> <td>' + result[i].addrId + '</td> '+
                 '<td>' + result[i].street1 + '</td> '+
                 '<td>' + result[i].street2 + '</td> '+
                 '<td>' + result[i].pincode + '</td> '+
                 '<td>' + result[i].city + '</td> '+
                 '<td>' + result[i].state + '</td> '+
                 '<td>' + result[i].country + '</td> '+
                 '</tr>');
            }
        }
    });
});
	
	
	$(document).find('.forgot').on('click', function() {
	var emailId = $('#emailId').val();
	$.ajax({
		type : "POST",
		url : "forgotData",
		data : {
			emailId : document.getElementById('emailId').value
		},
		success : function(result) {
			if (result==null)
				alert('Please Enter valid E-mail Address');
			else {
				alert('Your password is :: '+ result);
			}
		}
	});
});
	
	
	// popover demo
	$('#example').find('.popovers')
	.popover({html:true,
		trigger: "focus"})