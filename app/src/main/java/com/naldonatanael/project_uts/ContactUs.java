package com.naldonatanael.project_uts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ContactUs extends AppCompatActivity {
    private String CHANNEL_ID = "Channel 1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Button btnNotification = findViewById(R.id.btnContact);
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotificationChannel();
                addNotification();
            }
        });
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Channel 1";
            String description = "This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //Register the channel with the system; you can't change the importance
            //or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification(){
        //konstruktor notificationCompat.Builder harus diberi CHANNEL_ID untuk API level 26+
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Contact Pertshop Woof ")
                .setContentText("Silahkan hubungi WA : 0815123321123" +
                        " / Jl. Lawu 3 No.15, Kledokan, Caturtunggal, Sleman Regency, Special Region of Yogyakarta")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        //Membuat intent yang menampilkan notifikasi
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        //Menampilkan notifikasi
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.Home){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } /*else if (item.getItemId() == R.id.ProfilSaya) {
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }*/ else if (item.getItemId() == R.id.AboutUs) {
            startActivity(new Intent(this, ContactUs.class));
            finish();
        }else if (item.getItemId() == R.id.Location) {
            startActivity(new Intent(this, MapActivity.class));
            finish();
        }

        return true;
    }
}