
function initMap(idComponent){
	var map = new OpenLayers.Map(idComponent);
	map.addLayer(new OpenLayers.Layer.OSM());
	console.log("Map projection: " + map.getProjectionObject());
	
	// Coordinate1
	var positionCenter = new OpenLayers.LonLat(9.2071947, 45.8078329)
		.transform(
				new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
				map.getProjectionObject()
		);
		
	// Set zoom
	var zoom=10;
	map.setCenter(positionCenter, zoom);
	
	var markers = new OpenLayers.Layer.Markers("Markers");
	map.addLayer(markers);
	
	return map;
}

function addTestPath(map){
	// Coordinate1
	var position1 = new OpenLayers.LonLat(9.2071947, 45.8078329)
		.transform(
					new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
					map.getProjectionObject()
			);

	console.log("First coordinate: " + position1.lon+","+position1.lat);

	// Coordinate2
	var position2 = new OpenLayers.LonLat(9.2071947, 45.80)
		.transform(
					new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
					map.getProjectionObject()
			);

	console.log("Second coordinate: " + position2.lon+","+position2.lat);


	// Add markers
	var markers = new OpenLayers.Layer.Markers("Markers");
	markers.addMarker(new OpenLayers.Marker(position1));
	markers.addMarker(new OpenLayers.Marker(position2));
	map.addLayer(markers);

	// Draw line
	var myStyle = {"strokeColor":"red", "strokeWidth":2};

	var start_point = new OpenLayers.Geometry.Point(position1.lon, position1.lat);
	var end_point = new OpenLayers.Geometry.Point(position2.lon, position2.lat);
	console.log("Distance between the coordinates: " + start_point.distanceTo(end_point));
	var vector = new OpenLayers.Layer.Vector("vector", {style: myStyle});
	vector.addFeatures([new OpenLayers.Feature.Vector(new OpenLayers.Geometry.LineString([start_point, end_point]))]);
	map.addLayer(vector);
}

function addMarker(map, latitude, longitude){
	let layers = map.layers;
	let markerLayer;
	
	for(let i = 0; i < layers.length; i++){
		if(layers[i].name == "Markers"){
			markerLayer = layers[i];
		}
	}
	
	var position = new OpenLayers.LonLat(longitude, latitude)
		.transform(
				new OpenLayers.Projection("EPSG:4326"), // transform from WGS 1984
				map.getProjectionObject()
		);
	
	markerLayer.addMarker(new OpenLayers.Marker(position));
}

