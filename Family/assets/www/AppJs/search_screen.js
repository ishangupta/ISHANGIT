$(document).ready(function(){
	
});

function indoorClick()
{
	document.getElementById("img_indoor").src="images/indoor-selected.jpg";
	document.getElementById("img_outdoor").src="images/outdoor.jpg"
}
function outdoorClick()
{
	document.getElementById("img_indoor").src="images/indoor.jpg";
	document.getElementById("img_outdoor").src="images/outdoor-selected.jpg"
}
function playClick()
{
	document.getElementById("img_eat").src="images/eat-btn.jpg";
	document.getElementById("img_stay").src="images/stay-btn.jpg";
	document.getElementById("img_play").src="images/play-btn-selected.jpg"
}function stayClick()
{
	document.getElementById("img_eat").src="images/eat-btn.jpg";
	document.getElementById("img_stay").src="images/stay-btn-selected.jpg"
	document.getElementById("img_play").src="images/play-btn.jpg"
}
function eatClick()
{
	document.getElementById("img_eat").src="images/eat-btn-selected.jpg"
	document.getElementById("img_play").src="images/play-btn.jpg"
	document.getElementById("img_stay").src="images/stay-btn.jpg";
}
function gotoResultPage()
{
	window.location="eat.html";
	//alert("1");
	var val=document.getElementById("price-max-aoc").value;
	//alert(val);
}
      