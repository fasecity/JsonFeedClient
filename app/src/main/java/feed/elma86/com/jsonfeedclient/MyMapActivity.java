package feed.elma86.com.jsonfeedclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import feed.elma86.com.jsonfeedclient.model.DataItem;
import feed.elma86.com.jsonfeedclient.services.Myservice;

public class MyMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    /////////------map vars---/////////////////////
    GoogleMap mMap;
    boolean mapReady = false;
    MarkerOptions mp;
    MarkerOptions md;
    Marker marker;

    public  static List<DataItem> data1 = Myservice.dataItemList;//persisting data

    /////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //////////////////////////--float button-////////////////////////////////////
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ////////////////////////////////////////////////////////////////////////////
        //instansiate markers add icon method to make custom markers
        mp = new MarkerOptions()
               .position(new LatLng(43.656729, -79.377162)).title("Home");
    }

    private void markerDisplay(String title,double lat,double lng){
        if (marker != null ){
            marker.setVisible(true);
        }
        MarkerOptions options = new MarkerOptions()
                .title(title)
                .position(new LatLng(lat,lng));
        marker = mMap.addMarker(options);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        mMap = googleMap;
        LatLng latLngToronto = new LatLng(43.733092, -79.264254);

        CameraPosition target = CameraPosition.builder().target(latLngToronto).zoom(9).tilt(65).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //persited the data no need for looping
        for (DataItem x:data1) {
            markerDisplay(x.getTitle(),x.getLatitude(),x.getLongitude());//------maps jobs marker-------new

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
