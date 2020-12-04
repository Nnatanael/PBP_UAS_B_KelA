package com.naldonatanael.project_uts.UnitTest;

import android.content.Context;
import android.content.Intent;

import com.naldonatanael.project_uts.MainActivity;
import com.naldonatanael.project_uts.Profile;
import com.naldonatanael.project_uts.dao.UserDAO;

public class ActivityUtil {

    private Context context;
    public ActivityUtil(Context context) {
        this.context = context;
    }
    public void startMainActivity() {
        context.startActivity(new Intent(context, MainActivity.class));
    }
    public void startUserProfile(UserDAO user) {
        Intent i = new Intent(context, Profile.class);
        i.putExtra("id",user.getId());
        i.putExtra("name",user.getNama());
        i.putExtra("alamat",user.getAlamat());
        i.putExtra("noTelp",user.getNoTelp());
        i.putExtra("image",user.getImage());
        context.startActivity(i);
    }

}
