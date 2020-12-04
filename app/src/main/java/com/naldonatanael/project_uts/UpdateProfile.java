package com.naldonatanael.project_uts;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.textfield.TextInputEditText;

public class UpdateProfile extends AppCompatActivity {

    Button btnSubmit, btnCancel;
    TextInputEditText inNama, inAlamat, inNoTelp;
    private static String CHANNEL_ID = "Channel 1";
    private SharedPreferences preferences;
    public static final int mode = Activity.MODE_PRIVATE;
    private String nama = "";
    private String alamat = "";
    private String noTelp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        loadPreferences();

        inNama = findViewById(R.id.inNama);
        inAlamat = findViewById(R.id.inAlamat);
        inNoTelp = findViewById(R.id.inNoTelp);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        inNama.setText(nama);
        inAlamat.setText(alamat);
        inNoTelp.setText(noTelp);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfile.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePreferences();
                createNotificationChannel();
                addNotification();
                Intent intent = new Intent(UpdateProfile.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadPreferences() {
        String name = "profile";
        preferences = getSharedPreferences(name, mode);
        if (preferences!=null) {
            nama = preferences.getString("nama", "");
            alamat = preferences.getString("alamat", "");
            noTelp = preferences.getString("noTelp", "");
        }
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = preferences.edit();
        if (!inNama.getText().toString().isEmpty() && !inAlamat.getText().toString().isEmpty() &&
                !inNoTelp.getText().toString().isEmpty()) {
            editor.putString("nama", inNama.getText().toString());
            editor.putString("alamat", inAlamat.getText().toString());
            editor.putString("noTelp", inNoTelp.getText().toString());
            editor.apply();
            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Fill correctly", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNotificationChannel () {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name ="Channel 1";
            String description ="This is Channel 1";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, UpdateProfile.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Berhasil")
                .setContentText("Update Profile Berhasil")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent( this, UpdateProfile.class);
        PendingIntent contentIntent = PendingIntent.getActivities(this, 0, new Intent[]{notificationIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }
}