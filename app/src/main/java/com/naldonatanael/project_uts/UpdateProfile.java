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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfile extends AppCompatActivity {

    Button btnSumbit, btnCancel;
    TextInputEditText inputNama, inputAlamat, inputNoTelp;
    private static String CHANNEL_ID = "Channel 1";
    private SharedPreferences preferences;
    public static final int mode = Activity.MODE_PRIVATE;
    private String nama, alamat, noTelp, userID;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        inputNama = findViewById(R.id.inNama);
        inputAlamat = findViewById(R.id.inAlamat);
        inputNoTelp = findViewById(R.id.inNoTelp);
        btnSumbit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        userID = firebaseUser.getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        // READ FROM DATABASE
        databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                inputNama.setText(dataSnapshot.child("nama").getValue().toString());
                inputAlamat.setText(dataSnapshot.child("alamat").getValue().toString());
                inputNoTelp.setText(dataSnapshot.child("telp").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama = inputNama.getText().toString().trim();
                alamat = inputAlamat.getText().toString().trim();
                noTelp = inputNoTelp.getText().toString().trim();

                // UPDATE TO DATABASE
                databaseReference.child(userID).child("nama").setValue(nama);
                databaseReference.child(userID).child("alamat").setValue(alamat);
                databaseReference.child(userID).child("telp").setValue(noTelp);

                createNotificationChannel();
                addNotification();
                onBackPressed();
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
        if (!inputNama.getText().toString().isEmpty() && !inputAlamat.getText().toString().isEmpty() &&
                !inputNoTelp.getText().toString().isEmpty()) {
            editor.putString("nama", inputNama.getText().toString());
            editor.putString("alamat", inputAlamat.getText().toString());
            editor.putString("noTelp", inputNoTelp.getText().toString());
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
                .setContentText("Change Profile Berhasil")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent( this, UpdateProfile.class);
        PendingIntent contentIntent = PendingIntent.getActivities(this, 0, new Intent[]{notificationIntent}, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }
}