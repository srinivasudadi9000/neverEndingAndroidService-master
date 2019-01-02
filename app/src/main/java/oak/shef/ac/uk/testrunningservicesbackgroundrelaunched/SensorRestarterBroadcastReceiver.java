package oak.shef.ac.uk.testrunningservicesbackgroundrelaunched;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by fabio on 24/01/2016.
 */
public class SensorRestarterBroadcastReceiver extends BroadcastReceiver {

    @SuppressLint("NewApi")
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            getURLRegister();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(SensorRestarterBroadcastReceiver.class.getSimpleName(), "Service Stops! Oooooooooooooppppssssss!!!!");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.startForegroundService(new Intent(context, SensorService.class));
            createNotificationChannel(context);


            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "1251")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("dad")
                    .setContentText("354354354")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

// notificationId is a unique int for each notification that you must define
            notificationManager.notify(12, mBuilder.build());

        } else {
            context.startService(new Intent(context, SensorService.class));
        }

    }

    @SuppressLint("NewApi")
    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
              NotificationChannel channel = new NotificationChannel("1251", "dad", importance);
            channel.setDescription("asdasdf112454");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void getURLRegister() throws IOException {
        // avoid creating several instances, should be singleon
        OkHttpClient client = new OkHttpClient();

      //  HttpUrl.Builder urlBuilder = HttpUrl.parse("http://visakhadairy.in/trackernew.asmx/UpdateLocationNew?").newBuilder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("http://125.62.194.181/vdnew/trackernew.asmx/UpdateLocationNew?").newBuilder();
        urlBuilder.addQueryParameter("Token", "VVD@14");
        urlBuilder.addQueryParameter("DeviceID", "2");
        urlBuilder.addQueryParameter("Lat", "22.22");
        urlBuilder.addQueryParameter("Long", "18.22");
        urlBuilder.addQueryParameter("Altitude", "2");
        urlBuilder.addQueryParameter("Speed", "20");
        urlBuilder.addQueryParameter("Course", "0");
        urlBuilder.addQueryParameter("Battery", "10");
        urlBuilder.addQueryParameter("Address", "dad");
        urlBuilder.addQueryParameter("LocationProvider", "dadi");
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String dateToStr = format.format(today);
        System.out.println(dateToStr);

        urlBuilder.addQueryParameter("UpdatedDateTime", dateToStr);
        urlBuilder.addQueryParameter("AppStatus", "1");
        urlBuilder.addQueryParameter("MobileDeviceID", "866c88530edb48c0");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .addHeader("appversion","1.4")
                .url(url)
                .build();
        System.out.println("dadiiiiiiii " + urlBuilder.toString());
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                //Log.d("result", e.getMessage().toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                if (!response.isSuccessful()) {
                    //Log.d("result", response.toString());
                    throw new IOException("Unexpected code " + response);
                } else {
                    //Log.d("result", response.toString());
                    String responseBody = response.body().string();

                    JSONObject obj;
                    try {
                        obj = new JSONObject(responseBody);
                        System.out.println("srinivasu srinivasu srinivasu" + obj.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }


}
