package feed.elma86.com.jsonfeedclient.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.net.URI;

import static android.content.ContentValues.TAG;

public class Myservice extends IntentService {

    //filter
    public static final String My_SERVICE_MESSAGE ="my_service_message";
    //msg
    public static final String My_SERVICE_PAYLOAD ="my_service_payload";

    public Myservice() {
        super("");
    }

    //this method gets called asap when the intent service is started
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    //get data from uri intent
        Uri uri = intent.getData();
        Log.i(TAG, "onhandleIntetent " + uri.toString());

        Intent messageIntent = new Intent(My_SERVICE_MESSAGE);
        messageIntent.putExtra(My_SERVICE_PAYLOAD,"service all done");

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
