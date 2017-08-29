package krishna.example.com.inspectme;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

public class MyIntentService extends IntentService {

    public static int INTENTSERVICE_MESSAGE =  5;
    public static String INTENTSERVICE_ACTION = "Action_sending_msg";
    //private LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

    public MyIntentService(){
        super("MyIntentService");
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }

    private int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        for( DetectedActivity activity : probableActivities ) {
            switch( activity.getType() ) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e( "ActivityRecogition", "In Vehicle: " + activity.getConfidence() );
                    a = activity.getConfidence();
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.e( "ActivityRecogition", "On Bicycle: " + activity.getConfidence() );
                    b = activity.getConfidence();
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.e( "ActivityRecogition", "On Foot: " + activity.getConfidence() );
                    c = activity.getConfidence();
                    break;
                }
                case DetectedActivity.RUNNING: {
                    Log.e( "ActivityRecogition", "Running: " + activity.getConfidence() );
                    d = activity.getConfidence();
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e( "ActivityRecogition", "Still: " + activity.getConfidence() );
                    e = activity.getConfidence();
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e( "ActivityRecogition", "Tilting: " + activity.getConfidence() );
                    f = activity.getConfidence();
                    break;
                }
                case DetectedActivity.WALKING: {
                    Log.e( "ActivityRecogition", "Walking: " + activity.getConfidence() );
                    g = activity.getConfidence();
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e( "ActivityRecogition", "Unknown: " + activity.getConfidence() );
                    h = activity.getConfidence();
                    break;
                }
            }
        }
        //MainActivity.mhandler.obtainMessage(INTENTSERVICE_MESSAGE,a,b).sendToTarget();
        Intent intent = new Intent();
        intent.setAction(MyIntentService.INTENTSERVICE_ACTION);
        intent.putExtra("a",a);
        intent.putExtra("b",b);
        intent.putExtra("c",c);
        intent.putExtra("d",d);
        intent.putExtra("e",e);
        intent.putExtra("f",f);
        intent.putExtra("g",g);
        intent.putExtra("h",h);
        sendBroadcast(intent);
    }
}
