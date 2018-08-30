function buildLines(clients){
	$tbody = $('#userTable tbody');
	$tbody.empty();
	for(var count=0;count<clients.length;count++){
		var client=clients[count];
		$tr=$('<tr>');
		$login=$('<td>');
		$firstName=$('<td>');
		$lastName=$('<td>');
		
		$login.html(client.login);
		$firstName.html(client.firstName);
		$lastName.html(client.lastName);
		
		$tr.append($login);
		$tr.append($firstName);
		$tr.append($lastName);
		$tbody.append($tr);
	}
}

function buildEmptyLine(){
	$tbody = $('#userTable tbody');
	$tbody.empty();
	$tbody.append($("<tr><th colspan='100%'>No data found</th></tr>"))
}
$( document ).ready(function() {
	$.ajax({
		url:"../ClientServlet",
		data:{
			 action:"getClients"
		 }
	})
	.done(function(data) {
		if(data!=undefined && data.clients!=undefined && data.clients.length>0 ){
			buildLines(data.clients);
		}
		else{
			buildEmptyLine();
		}
  })
})