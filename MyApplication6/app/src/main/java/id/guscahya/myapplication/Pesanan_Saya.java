package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.guscahya.myapplication.ui.main3.PesananSayaFragment;

public class Pesanan_Saya extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesanan__saya_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, PesananSayaFragment.newInstance())
                    .commitNow();
        }
    }
}