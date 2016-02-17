/*
 * We need to load all the data required for the map and graphs while loading page.
 */
$(document).ready(function() {
	//Load the map.
	loadMap();
	
	//Load the bar graph.
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/topfiveoverall",
		success: function(data) {
			//window.alert("Loaded");
		}
	})
});