$(document).ready(function(){
	
});
var door="indoor";
var youngAge="0";
var oldAge="0";
var funType="play";
var distance="";
var postcode="";

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
}
function clearPostCode()
{
	document.getElementById("postcode").value="";
}

function gotoResultPage()
{
	window.location="eat.html";
	//alert("1");
	var val=document.getElementById("price-max-aoc").value;
	//alert(val);
}
      