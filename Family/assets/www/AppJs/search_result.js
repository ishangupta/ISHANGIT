document.addEventListener("deviceready", startCode, false);

function startCode() {
	
	var result=localStorage.getItem("search_result");
	var data=JSON.parse(result);
	var output="";
	var ni = document.getElementById('mainUl');
	var jsonArray=data.response;
	for(i=0;i<jsonArray.length;i++)
	{
			output+= '<li id="'+jsonArray[i].idOfList+'" onclick=gotoDetailPage(this.id)>'+
             '<div class="media">'+
             '<div class="media-left"> <a href="#"> <img alt="64x64" src="'+jsonArray[i].thumbImageOfList+'" class="media-object" style="width: 64px; height: 64px;"> </a> </div>'+
             '<div class="media-body">'+
             '<h4 class="media-heading" id="media-heading">'+jsonArray[i].nameOfList+'<a href="#media-heading" class="anchorjs-link"></a></h4>'+
             '<p>'+jsonArray[i].descOfList+'<a href="#">'+
			 '<img src="images/next.png" class="pull-right" alt="stay"></a></p>'+
             '</div>'+
             '</div>'+
             '</li>';
	}
	ni.innerHTML=output;
}


function gotoDetailPage(id)
{
	var url="http://www.familyfunplanner.com.au/demofamily/iphone/family_fun_search_details.php?requestSendFrom=iPhone&searchId="+id;
	$.ajax({
		url : url,
		data : {
		},
		type : 'post',
		async : 'true',
		dataType : 'json',
		beforeSend : function() {
			toaster.showDialog(); 
		},
		complete : function() {
			toaster.dismissDialog(); 
		},
		success : function(result) {
			if(result.status.noOfRecords==0)
			alert("No record found");
		    else
			{
				localStorage.setItem("item_detail",JSON.stringify(result));
				window.location="result-info.html";
			}
			
		},
		error : function(request, error) {
			alert("Some error occured");
			// This callback function will trigger on unsuccessful action
		}
	});
	
	//window.location="result-info.html";
}