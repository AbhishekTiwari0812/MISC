var images=[];
for (var i = 1; i<=13; i++) {
	images.push("images/"+i+".jpg");
};
var i=1;
function myFunction() {
    document.getElementById("myImg").src = images[(i++)%images.length];
}
window.setInterval(function(){
  myFunction();
}, 2500);



