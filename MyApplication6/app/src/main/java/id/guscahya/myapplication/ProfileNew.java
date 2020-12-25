package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.guscahya.myapplication.ui.main1.ProfileNewFragment;

public class ProfileNew extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_new_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ProfileNewFragment.newInstance())
                    .commitNow();
        }
    }
}