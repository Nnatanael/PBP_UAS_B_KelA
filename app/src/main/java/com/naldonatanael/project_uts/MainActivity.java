package com.naldonatanael.project_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.naldonatanael.project_uts.adapter.RecyclerViewAdapter;
import com.naldonatanael.project_uts.add.BookingLayanan;
import com.naldonatanael.project_uts.api.ApiClient;
import com.naldonatanael.project_uts.api.ApiInterface;
import com.naldonatanael.project_uts.dao.LayananDAO;
import com.naldonatanael.project_uts.response.LayananResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<LayananDAO> LayananList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private SwipeRefreshLayout swipeRefreshLayout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        loadLayanan();

    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0){
            if(requestCode == RESULT_OK) {
                finish();
                startActivity(getIntent());
            }
        }
    }

    private void loadLayanan() {
        ApiInterface apiGetLayanan = ApiClient.getClient().create(ApiInterface.class);
        Call<LayananResponse> callGetLayanan = apiGetLayanan.getAllLayanan("data");

        callGetLayanan.enqueue(new Callback<LayananResponse>() {
            @Override
            public void onResponse(Call<LayananResponse> call, @NotNull Response<LayananResponse> response) {
                assert response.body() != null;
                generateDataList(response.body().getLayanan());
            }

            @Override
            public void onFailure(Call<LayananResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Kesalahan Jaringan", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateDataList(List<LayananDAO> LayananList) {
        recyclerView = findViewById(R.id.recycler_view_layanan);
        adapter = new RecyclerViewAdapter(LayananList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.Home){
            startActivity(new Intent(this, MainActivity.class));
        }else if(item.getItemId()==R.id.Location){
            startActivity(new Intent(this, MapActivity.class));
        }else if(item.getItemId()==R.id.AboutUs){
            startActivity(new Intent(this, ContactUs.class));
        }else if(item.getItemId()==R.id.ProfilSaya){
            startActivity(new Intent(this, Profile.class));
        }else if(item.getItemId()==R.id.Booking){
            startActivity(new Intent(this, BookingLayanan.class));
        }else if (item.getItemId() == R.id.LogoutAkun) {
            if(firebaseUser != null) {
                firebaseAuth.signOut();
            }
            Intent intent = new Intent(this, LoginActivity.class);// New activity
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish(); // Call once you redirect to another activity
        }

        return true;
    }

    
}