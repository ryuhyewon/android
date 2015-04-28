package com.mapia;

        import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

public class MapFragment extends Fragment{

    protected ArrayList<MarkerData> markerDatas = new ArrayList<MarkerData>();
    protected GoogleMap backgroundMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(com.mapia.R.layout.fragment_map, container, false);
        final SupportMapFragment smf = (SupportMapFragment)getChildFragmentManager().findFragmentById(com.mapia.R.id.map_background);
        backgroundMap = smf.getMap();
        return view;
    }
    protected void drawMarker(ArrayList<MarkerData> markerDatas){
        for(int i=0;i<markerDatas.size();i++){
            markerDatas.get(i).marker = this.backgroundMap.addMarker(new MarkerOptions().position(markerDatas.get(i).location).title("Private"));
        }
    }
}

