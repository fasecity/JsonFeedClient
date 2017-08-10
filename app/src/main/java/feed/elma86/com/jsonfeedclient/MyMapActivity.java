package feed.elma86.com.jsonfeedclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    /////////------map vars---/////////////////////
    GoogleMap mMap;
    boolean mapReady = false;
    MarkerOptions mp;
    MarkerOptions md;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ///instansiate markers add icon method to make custom markers
        mp = new MarkerOptions()
                .position(new LatLng(43.656729, -79.377162)).title("Home");

        md = new MarkerOptions()
                .position(new LatLng(43.733092, -79.264254)).title("Dad's");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        mMap = googleMap;
        LatLng latLngToronto = new LatLng(43.733092, -79.264254);
        LatLng latLnghome = new LatLng(43.656729, -79.377162);

        CameraPosition target = CameraPosition.builder().target(latLngToronto).zoom(5).tilt(65).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //add markers and
        //instantiate
        mMap.addMarker(mp);
        mMap.addMarker(md);

    }
}
