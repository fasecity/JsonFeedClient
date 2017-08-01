package feed.elma86.com.jsonfeedclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import feed.elma86.com.jsonfeedclient.model.DataItem;

public class DetailsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);

        if (item == null) {
            throw new AssertionError("null data recived");
        }


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
        latText =(TextView) findViewById(R.id.latText);
        lngText =(TextView) findViewById(R.id.lngText);
        cityText =(TextView) findViewById(R.id.cityText);
        provinceText =(TextView) findViewById(R.id.provinceText);
        salarytext =(TextView) findViewById(R.id.salaryText);
        phoneText =(TextView) findViewById(R.id.phoneText);
        descriptionText =(TextView) findViewById(R.id.descriptionText);
        titletext =(TextView) findViewById(R.id.titleText);
        companyText =(TextView) findViewById(R.id.companyText);
        responsibilityText=(TextView) findViewById(R.id.responsibilityText);

        //view sets
        titletext.setText(item.getTitle());
        companyText.setText(item.getCompany());
        cityText.setText(item.getCity());
        salarytext.setText(""+ item.getSalary());
        descriptionText.setText(item.getDescription());
        responsibilityText.setText(item.getResponsibility());
        latText.setText(""+ item.getLatitude());
        lngText.setText(""+ item.getLongitude());
        phoneText.setText(item.getPhone());
        provinceText.setText(item.getProvince());
    }

}
