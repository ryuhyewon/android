package com.mapia.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

class MarkerData{
	LatLng location;
	Marker marker;
	
	public MarkerData(LatLng location){
		super();
		this.location = location;
	}
}
