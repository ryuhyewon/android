package com.mapia;

        import java.util.ArrayList;

        import com.google.android.gms.maps.model.LatLng;

        import android.os.Bundle;
        import android.widget.Toast;

public class MapFollowFragment extends MapFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity().getApplicationContext(), "FollowFragment", 500).show();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        for(int i=0;i<markerDatas.size();i++){
            markerDatas.get(i).marker.remove();
        }
        markerDatas.clear();
        markerDatas = getMarker();
        drawMarker(markerDatas);
    }

    private ArrayList<MarkerData> getMarker() {
        ArrayList<MarkerData> markerList = new ArrayList<MarkerData>();
        markerList.add(new MarkerData(new LatLng(0, 0)));
        markerList.add(new MarkerData(new LatLng(0, 30)));
        return markerList;
    }
}
