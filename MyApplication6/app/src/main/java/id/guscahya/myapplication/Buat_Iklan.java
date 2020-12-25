package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.guscahya.myapplication.ui.main2.BuatIklanFragment;

public class Buat_Iklan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buat__iklan_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, BuatIklanFragment.newInstance())
                    .commitNow();
        }
    }
}