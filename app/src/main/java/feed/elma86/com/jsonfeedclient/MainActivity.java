package feed.elma86.com.jsonfeedclient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import feed.elma86.com.jsonfeedclient.model.DataItem;
import feed.elma86.com.jsonfeedclient.services.Myservice;
import feed.elma86.com.jsonfeedclient.utils.NetworkHelper;

import static feed.elma86.com.jsonfeedclient.DataItemAdapter.ITEM_KEY;

public class MainActivity extends AppCompatActivity {

    private static final String jsonurl ="http://moesjsonfeedapi.azurewebsites.net/api/values";
    private boolean networkOk;
    List<DataItem> dataItemList;//make into list
    RecyclerView.LayoutManager layoutManager;
    DataItemAdapter dataItemAdapter;

    //recive broadcast
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //GET MY DATA -- gets  data in parcalable strongly typed
            DataItem[] dataItems = (DataItem[])intent.getParcelableArrayExtra(Myservice.My_SERVICE_PAYLOAD);

            dataItemList = Arrays.asList(dataItems);//make into list
            // make recycler view
            displayDataItems();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle("Jobify");
        getSupportActionBar().setIcon(R.drawable.logom);

        //revcive
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(broadcastReceiver,new IntentFilter(Myservice.My_SERVICE_MESSAGE));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        networkOk = NetworkHelper.hasNetworkAccess(this);
        if (networkOk) {
            Intent intent = new Intent(MainActivity.this, Myservice.class);
            intent.setData(Uri.parse(jsonurl));
            startService(intent);
        }

        else
            Toast.makeText(MainActivity.this, "you need internet bud", Toast.LENGTH_SHORT).show();
    }

    private void displayDataItems(){

        if (dataItemList != null) {
            dataItemAdapter = new DataItemAdapter(this,dataItemList);
            RecyclerView rc = (RecyclerView) findViewById(R.id.rcView);
            layoutManager = new LinearLayoutManager(this);
            rc.setHasFixedSize(false);//only use this for fixed ammount
            rc.setLayoutManager(layoutManager);
            rc.setAdapter(dataItemAdapter);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }
        else if(id ==R.id.action_settings_maps){
            //networkOk = NetworkHelper.hasNetworkAccess(this);
            if (networkOk) {
                Intent intent = new Intent(this, MyMapActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(this, "You need internet connection to display maps", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //kill reciver to avoid leaks
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(broadcastReceiver);

    }
}
