

var door="indoor";
var funType="play";
var distance="";
var postcode="";
var url;

function indoorClick()
{
	document.getElementById("img_indoor").src="images/indoor-selected.jpg";
	document.getElementById("img_outdoor").src="images/outdoor.jpg"
	door="indoor";
}
function outdoorClick()
{
	document.getElementById("img_indoor").src="images/indoor.jpg";
	document.getElementById("img_outdoor").src="images/outdoor-selected.jpg"
	door="outdoor";
}
function playClick()
{
	document.getElementById("img_eat").src="images/eat-btn.jpg";
	document.getElementById("img_stay").src="images/stay-btn.jpg";
	document.getElementById("img_play").src="images/play-btn-selected.jpg"
	funType="play";
}function stayClick()
{
	document.getElementById("img_eat").src="images/eat-btn.jpg";
	document.getElementById("img_stay").src="images/stay-btn-selected.jpg"
	document.getElementById("img_play").src="images/play-btn.jpg"
	funType="stay";
}
function eatClick()
{
	document.getElementById("img_eat").src="images/eat-btn-selected.jpg"
	document.getElementById("img_play").src="images/play-btn.jpg"
	document.getElementById("img_stay").src="images/stay-btn.jpg";
	funType="eat";
}

function clearRadioButton()
{
$('input:radio[name=vale]').each(function () { $(this).prop('checked', false); });
distance="";
}
function clearPostCode()
{
	document.getElementById("postcode").value="";
	distance=$("input[name='vale']:checked").val();
	postcode="";
}

function gotoResultPage()
{
	var youngAge=document.getElementById("price-max-ayc").value;
    var oldAge=document.getElementById("price-max-aoc").value;
	postcode=document.getElementById("postcode").value;
	if(distance=="" && postcode=="")
	{
		alert("Please select the distance or postcode");
		return;
	}else{
		if(distance!="")
			url="http://www.familyfunplanner.com.au/demofamily/iphone/family_fun_search.php?requestSendFrom=iPhone&searchType="+funType+"&ageYounger="+youngAge+"&ageOlder="+oldAge+"&distance="+distance+"&myLatitude=-33.7646874&myLongitude=150.9901097&locationType="+door;
		else
			url="http://www.familyfunplanner.com.au/demofamily/iphone/family_fun_search.php?requestSendFrom=iPhone&searchType="+funType+"&ageYounger="+youngAge+"&ageOlder="+oldAge+"&myPlace="+postcode+"&locationType="+door;
		
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
				localStorage.setItem("search_result",JSON.stringify(result));
				window.location=funType+".html";
			}
			
		},
		error : function(request, error) {
			alert("Some error occured");
			// This callback function will trigger on unsuccessful action
		}
	});
	}
}
      