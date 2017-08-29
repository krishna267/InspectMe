
package krishna.example.com.inspectme;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;


public class MainActivity extends Activity
        implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    public GoogleApiClient googleApiClient;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
//    public Handler mhandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what == MyIntentService.INTENTSERVICE_MESSAGE){
//                tv1.setText("a = "+msg.arg1);
//            }
//        }
//    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        tv3 = (TextView)findViewById(R.id.tv3);
        tv4 = (TextView)findViewById(R.id.tv4);
        tv5 = (TextView)findViewById(R.id.tv5);
        tv6 = (TextView)findViewById(R.id.tv6);
        tv7 = (TextView)findViewById(R.id.tv7);
        tv8 = (TextView)findViewById(R.id.tv8);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        googleApiClient.connect();

        MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyIntentService.INTENTSERVICE_ACTION);
        this.registerReceiver(myBroadcastReceiver,intentFilter);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Intent intent = new Intent( this, MyIntentService.class );
        PendingIntent pendingIntent = PendingIntent.getService( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( googleApiClient, 3000, pendingIntent );
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"Connection failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"Connection failed",Toast.LENGTH_SHORT).show();
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            tv1.setText("In vehicle : "+intent.getIntExtra("a",0));
            tv2.setText("On bicycle : "+intent.getIntExtra("b",0));
            tv3.setText("On foot : "+intent.getIntExtra("c",0));
            tv4.setText("Running : "+intent.getIntExtra("d",0));
            tv5.setText("Still : "+intent.getIntExtra("e",0));
            tv6.setText("Tilting : "+intent.getIntExtra("f",0));
            tv7.setText("Walking : "+intent.getIntExtra("g",0));
            tv8.setText("Unknown : "+intent.getIntExtra("h",0));
            Toast.makeText(getApplicationContext(),"Received",Toast.LENGTH_SHORT).show();
        }
    }
}