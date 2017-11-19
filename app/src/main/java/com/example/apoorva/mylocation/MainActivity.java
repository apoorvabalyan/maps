package com.example.apoorva.mylocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    /*Variable that holds the map
    Map = normal map with names of location
    Satellite = view of satellite with no names
    Hybrid  = combination of map + satellite(satellite view along with names)
     */
    GoogleMap m_map;
    boolean map_ready;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*These buttons when clicked will preview different map types.But first we need to check whether the map is ready
        or not whose value is set as true in onMapReady callback*/
        Button btnMap = (Button)findViewById(R.id.btnMap);
        Button btnSatellite = (Button)findViewById(R.id.btnSatellite);
        Button btnHybrid = (Button)findViewById(R.id.btnHybrid);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map_ready)
                    //setMapType takes a integer value,hence the constant is a integer
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map_ready)
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(map_ready)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });
        //We need to get the fragment and then in order to intialize we need to call getMapAsync on the fragment
        MapFragment fragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap map)
    {
        map_ready = true;
        m_map = map;
        LatLng india = new LatLng(40.36,20.45);
        CameraPosition target = CameraPosition.builder().target(india).zoom(17).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
