package feed.elma86.com.jsonfeedclient;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import feed.elma86.com.jsonfeedclient.model.DataItem;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    public TextView latText;
    public TextView lngText;
    public TextView cityText;
    public TextView provinceText;
    public TextView salarytext;
    public TextView phoneText;
    public TextView descriptionText;
    public TextView titletext;
    public TextView companyText;
    public TextView responsibilityText;

    //title views
    public TextView cityTextTitle;
    public TextView provinceTextTitle;
    public TextView salarytextTitle;
    public TextView phoneTextTitle;
    public TextView descriptionTextTitle;
    public TextView titletextTitle;
    public TextView companyTextTitle;
    public TextView responsibilityTextTitle;
    //tag for phone intent
    private static final int REQUEST_PHONE_CALL = 1;

    /////////------map vars---/////////////////////
    GoogleMap mMap;
    boolean mapReady = false;
    MarkerOptions mp;
    MarkerOptions md;
    Marker marker;

    DataItem itemMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        itemMap = item;
        if (item == null) {
            throw new AssertionError("null data recived");
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapDetails);
        mapFragment.getMapAsync(this);

        mp = new MarkerOptions()
                .position(new LatLng(item.getLatitude(), item.getLongitude())).title(item.getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //views
//        latText =(TextView) findViewById(R.id.latText);
//        lngText =(TextView) findViewById(R.id.lngText);
        cityText =(TextView) findViewById(R.id.cityText);
        provinceText =(TextView) findViewById(R.id.provinceText);
        salarytext =(TextView) findViewById(R.id.salaryText);
        phoneText =(TextView) findViewById(R.id.phoneText);
        descriptionText =(TextView) findViewById(R.id.descriptionText);
        titletext =(TextView) findViewById(R.id.titleText);
        companyText =(TextView) findViewById(R.id.companyText);
        responsibilityText=(TextView) findViewById(R.id.responsibilityText);

        //title views---- these are static textviews for titles
        //views
        cityTextTitle =(TextView) findViewById(R.id.cityboy);
        provinceTextTitle =(TextView) findViewById(R.id.provinceTitle_Txt);
        salarytextTitle =(TextView) findViewById(R.id.salarayTitleTxt);
        phoneTextTitle =(TextView) findViewById(R.id.phoneTitleTxt);
        descriptionTextTitle =(TextView) findViewById(R.id.descriptionTitleTxt);
        titletextTitle =(TextView) findViewById(R.id.titleTitleTxt);
        companyTextTitle =(TextView) findViewById(R.id.compantTitleTxt);
        responsibilityTextTitle=(TextView) findViewById(R.id.responsibilityTitleTxt);

        //underline phone ---apperiecnce
        phoneText.setPaintFlags(phoneText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //sets views for dynamic content in dataItem
        titletext.setText(item.getTitle());
        companyText.setText(item.getCompany());
        cityText.setText(item.getCity());
        salarytext.setText("$"+ item.getSalary() +"/hour");//---use "" to cast java is retarded
        descriptionText.setText(item.getDescription());
        responsibilityText.setText(item.getResponsibility());
        phoneText.setText(item.getPhone());
        provinceText.setText(item.getProvince());

        //makees phone call
        phoneText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = item.getPhone().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + uri));
                if (ContextCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    Toast.makeText(DetailsActivity.this, "You need to enable phone permissions to to call", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        mMap = googleMap;
        LatLng latLngToronto = new LatLng(itemMap.getLatitude(), itemMap.getLongitude());

        CameraPosition target = CameraPosition.builder().target(latLngToronto).zoom(15).tilt(65).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(target));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.addMarker(mp);

    }
}
