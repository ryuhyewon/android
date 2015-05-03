package com.mapia.map;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.mapia.R;

public class MapPublicFragment extends MapFragment {
	
	Button btnLocCurrent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.type_num = 2;
		LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout relLayout = (RelativeLayout)inflater.inflate(R.layout.overview_map_public, null);
		RelativeLayout.LayoutParams paramrel = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);
		getActivity().getWindow().addContentView(relLayout, paramrel);
		Toast.makeText(getActivity().getApplicationContext(), "PublicFragment", Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		markerDatas = getMarker();
		super.onResume();
		/*for(int i=0;i<markerDatas.size();i++){
			markerDatas.get(i).marker.remove();
		}
		markerDatas.clear();
		markerDatas = getMarker();
		drawMarker(markerDatas);*/
	}
	
	private ArrayList<MarkerData> getMarker() {
		ArrayList<MarkerData> markerList = new ArrayList<MarkerData>();
		markerList.add(new MarkerData(new LatLng(0, 0)));
		markerList.add(new MarkerData(new LatLng(0, 40)));
		return markerList;
	}
}
