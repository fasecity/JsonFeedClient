package feed.elma86.com.jsonfeedclient.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import feed.elma86.com.jsonfeedclient.model.DataItem;
import feed.elma86.com.jsonfeedclient.utils.HttpHelper;

import static android.content.ContentValues.TAG;

public class Myservice extends IntentService {

    //filter
    public static final String My_SERVICE_MESSAGE ="my_service_message";
    //msg
    public static final String My_SERVICE_PAYLOAD ="my_service_payload";

    //persist
    public  static List<DataItem> dataItemList = new ArrayList<>();//persisting data


    public Myservice() {
        super("");
    }

    //this method gets called asap when the intent service is started
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    //get data from uri intent
        Uri uri = intent.getData();
        Log.i(TAG, "onhandleIntetent " + uri.toString());

        //use web api endpoint url and put it in httphelper
        String response;
        try {
            response = HttpHelper.downloadUrl(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();

            return;
        }

        Gson gson = new Gson();
       // DataItem[] dataItem = gson.fromJson(response,DataItem[].class);
        DataItem[] dataItems = gson.fromJson(response,DataItem[].class);

        Intent messageIntent = new Intent(My_SERVICE_MESSAGE);
        messageIntent.putExtra(My_SERVICE_PAYLOAD,dataItems);

        for (DataItem x:dataItems) {
            dataItemList.add(x);
        }

        LocalBroadcastManager localBroadcastManager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        //sends intent
        localBroadcastManager.sendBroadcast(messageIntent);

    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
