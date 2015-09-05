$(document).ready(function(){
	$("#menuButton:nth-child(1)").click(function(){
		$("#our_courses").addClass('animated');
		$("#our_courses").addClass('tada');
		function show_popup(){
      		$("#our_courses").removeClass('animated');
			$("#our_courses").removeClass('tada');
			};
      window.setTimeout( show_popup, 3000 ); // 5 seconds
	});
$("#menuButton:nth-child(2)").click(function(){
		$("#forms").addClass('animated');
		$("#forms").addClass('tada');
		function show_popup(){
      		$("#forms").removeClass('animated');
			$("#forms").removeClass('tada');
			};
      window.setTimeout( show_popup, 3000 ); // 5 seconds
	});$("#menuButton:nth-child(3)").click(function(){
		$("#results").addClass('animated');
		$("#results").addClass('tada');
		function show_popup(){
      		$("#results").removeClass('animated');
			$("#results").removeClass('tada');
			};
      window.setTimeout( show_popup, 3000 ); // 5 seconds
	});$("#menuButton:nth-child(4)").click(function(){
		$("#syllabus_and_old_papers").addClass('animated');
		$("#syllabus_and_old_papers").addClass('tada');
		function show_popup(){
      		$("#syllabus_and_old_papers").removeClass('animated');
			$("#syllabus_and_old_papers").removeClass('tada');
			};
      window.setTimeout( show_popup, 3000 ); // 5 seconds
	});$("#menuButton:nth-child(5)").click(function(){
		$("#team_members").addClass('animated');
		$("#team_members").addClass('tada');
		function show_popup(){
      		$("#team_members").removeClass('animated');
			$("#team_members").removeClass('tada');
			};
      window.setTimeout( show_popup, 3000 ); // 5 seconds
	});
	$("#menuButton:nth-child(6)").click(function(){
		$("#contact_us").addClass('animated');
		$("#contact_us").addClass('tada');
		function show_popup(){
      		$("#contact_us").removeClass('animated');
			$("#contact_us").removeClass('tada');
			};
      window.setTimeout( show_popup, 3000 ); // 5 seconds
	});

	$("#anish").onmouseover(function(){

		$("#ToBeShown").addClass('ShowInfo');

	});
	






});