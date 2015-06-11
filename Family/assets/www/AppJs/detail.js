document.addEventListener("deviceready", startCode, false);

function startCode() {
	
	var result=localStorage.getItem("item_detail");
	var data=JSON.parse(result);
	var output="";
	var ni = document.getElementById('mainUl');
	var jsonArray=data.response;
	var image1=jsonArray[0].ImageOfList;
	image1=image1.replace("demo", "demofamily");
	var image2=jsonArray[0].ImageOfList2;
	image2=image2.replace("demo", "demofamily");
	var image3=jsonArray[0].ImageOfList3;
	image3=image3.replace("demo", "demofamily");
	
	document.getElementById("image1").src=""+image1;
	document.getElementById("image2").src=""+image2;
	document.getElementById("image3").src=""+image3;
	document.getElementById("name").innerHTML=""+jsonArray[0].nameOfList;
	document.getElementById("description").innerHTML=""+jsonArray[0].descOfList;
	document.getElementById("address").innerHTML="Address: "+jsonArray[0].addressOfList+","+jsonArray[0].cityOfList+","+jsonArray[0].stateOfList+"-"+jsonArray[0].postcodeOfList;
	
	
}

function gotoSearchScreen()
{
	window.location="second-screen.html";
}