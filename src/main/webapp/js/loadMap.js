/*
 * Function to load the map.
 */
function loadMap() {

	L.mapbox.accessToken = 'pk.eyJ1IjoiaW5pdGRvdCIsImEiOiJ3VkkxTldvIn0.7UPZ8q9fgBE70dMV7e0sLw';

	var map = L.mapbox.map('map').setView([ 21.836006, 87.824707 ], 5),
	// color reference from color brewer
	mapBrew = [ 'rgb(255,255,204)', 'rgb(217,240,163)', 'rgb(173,221,142)',
			'rgb(120,198,121)', 'rgb(65,171,93)', 'rgb(35,132,67)',
			'rgb(0,90,50)' ],
	// population density range used for choropleth and legend
	//mapRange = [ 5000, 1000, 800, 500, 300, 100, 0 ]; 
	mapRange = [ 5000, 1000, 800, 500, 300, 100, 0 ];

	// map legend for population density
	var legend = L.mapbox.legendControl({
		position : "bottomleft"
	}).addLegend(getLegendHTML()).addTo(map);
	var popup = new L.Popup({
		autoPan : false,
		className : 'statsPopup'
	}), closeTooltip;

	// fetch the state geojson data
	d3.json("js/india-states.json", function(statesData) {
		statesLayer = L.geoJson(statesData, {
			style : getStyle,
			onEachFeature : onEachFeature
		}).addTo(map);
	});

	function getStyle(feature) {
		return {
			weight : 2,
			opacity : 0.1,
			color : 'black',
			fillOpacity : 0.85,
			fillColor : getDensityColor(indiaCensus.states[feature.properties.code].details.casesDueToMalaria)
		};
	}

	// get color depending on population density value
	function getDensityColor(d) {
		var colors = Array.prototype.slice.call(mapBrew).reverse(), // creates a copy of the mapBrew array and reverses it
		range = mapRange;

		return d > range[0] ? colors[0] : d > range[1] ? colors[1]
				: d > range[2] ? colors[2] : d > range[3] ? colors[3]
						: d > range[4] ? colors[4] : d > range[4] ? colors[4]
								: d > range[5] ? colors[5] : colors[6];
	}

	function onEachFeature(feature, layer) {
		layer.on({
			mousemove : mousemove,
			mouseout : mouseout,
		//click: zoomToFeature
		});
	}

	function mousemove(e) {
		var layer = e.target;

		var popupData = {
			state : indiaCensus.states[layer.feature.properties.code].name,
		/*  density: indiaCensus.states[layer.feature.properties.code].density,
		  area: indiaCensus.states[layer.feature.properties.code].area,
		  growth: indiaCensus.states[layer.feature.properties.code].growth,
		  population: indiaCensus.states[layer.feature.properties.code].population,
		  capital: indiaCensus.states[layer.feature.properties.code].capital.name */
		};

		popup.setLatLng(e.latlng);

		var popContent = L.mapbox.template(d3.select("#popup-template").text(),
				popupData);
		popup.setContent(popContent);

		if (!popup._map)
			popup.openOn(map);
		window.clearTimeout(closeTooltip);

		// highlight feature
		layer.setStyle({
			weight : 2,
			opacity : 0.3,
			fillOpacity : 0.9
		});

		if (!L.Browser.ie && !L.Browser.opera) {
			layer.bringToFront();
		}

		// update the graph with literacy and sex ratio data
		updateGraph(indiaCensus.states[layer.feature.properties.code]);
	}

	function mouseout(e) {
		statesLayer.resetStyle(e.target);
		closeTooltip = window.setTimeout(function() {
			// ref: https://www.mapbox.com/mapbox.js/api/v2.1.6/l-map-class/
			map.closePopup(popup); // close only the state details popup
		}, 100);
	}

	function zoomToFeature(e) {
		map.fitBounds(e.target.getBounds());
	}

	function getLegendHTML() {
		var grades = Array.prototype.slice.call(mapRange).reverse(), // creates a copy of ranges and reverses it
		labels = [], from, to;
		// color reference from color brewer
		var brew = mapBrew;

		for (var i = 0; i < grades.length; i++) {
			from = grades[i];
			to = grades[i + 1];

			labels.push('<i style="background:' + brew[i] + '"></i> ' + from
					+ (to ? '&ndash;' + to : '+'));
		}

		return '<span>People per square km</span><br>' + labels.join('<br>');
	}

	var BarGraphControl = L.Control.extend({
		options : {
			position : 'bottomright'
		},

		onAdd : function(map) {
			// create the control container with a particular class name
			var container = L.DomUtil.create('div', 'bar-graph');
			// ... initialize other DOM elements, add listeners, etc.
			return container;
		}
	});

	// add the bar graph container
	map.addControl(new BarGraphControl());

	// START: Bar Graph (Literacy)
	var barWidth = 250, barHeight = 180, barSize = 50,
	// attach the literacy data for 'Delhi' initially
	literacyData = [ 86.21 ];

	var barName = d3.select(".bar-graph").append("div").text("Delhi").style(
			"color", "black").style("font-size", "15px").style("font-weight",
			"bold").style("margin", "6px 0");

	var barHolder = d3.select(".bar-graph").append("svg").attr("id",
			"literacy-bar").attr("width", barWidth).attr("height", barHeight)
			.append("rect").attr("width", barSize).attr("height", barHeight)
			.attr("x", (barWidth - barSize) / 2).style("color",
					getLiteracyColor(literacyData[0]));

	var barLegend = d3.select(".bar-graph").append("div").style("color",
			"black").style("font-weight", "bold").style("font-size", "15px")
			.text("Literacy: ").append("span").attr("id", "literacy-percent")
			.text(literacyData[0].toFixed(2) + "%").style("color",
					getLiteracyColor(literacyData[0]));

	var litBar = d3.select("#literacy-bar").selectAll("rect")
			.data(literacyData).attr("height", function(d) {
				var h = barHeight * (d / 100);
				return h;
			}).attr("y", function(d) {
				var h = barHeight * (d / 100), nh = barHeight - h;
				return nh;
			}).style("fill", function(d) {
				return getLiteracyColor(d);
			});
	// END: Bar Graph (Literacy)

	// START: Updates both Pie Graph and Bar Graph
	function updateGraph(graphData) {
		// Update Bar Graph
		barName.text(graphData.name);

		literacyData = [];
		literacyData.push(+graphData.details.casesDueToMalaria);

		d3.select("#literacy-bar").selectAll("rect").data(literacyData)
				.transition().duration(500).attr("height", function(d) {
					var h = barHeight * (d / 100);
					return h;
				}).attr("y", function(d) {
					var h = barHeight * (d / 100), nh = barHeight - h;
					return nh;
				}).style("fill", function(d) {
					return getLiteracyColor(d);
				});

		barLegend.text(graphData.details.casesDueToMalaria.toFixed(2) + "%")
				.transition().duration(500).style("color",
						getLiteracyColor(+graphData.details.casesDueToMalaria));
	}// END: updateChart()

	function getLiteracyColor(literacy) {
		// color from colorbrew
		var literacyBrew = [ 'rgb(215,25,28)', 'rgb(253,174,97)',
				'rgb(166,217,106)', 'rgb(26,150,65)' ].reverse(), literacyRange = [
				90, 80, 70, 60 ];

		literacy = +literacy;

		return literacy > literacyRange[0] ? literacyBrew[0]
				: literacy > literacyRange[1] ? literacyBrew[1]
						: literacy > literacyRange[2] ? literacyBrew[2]
								: literacyBrew[3];
	}
}