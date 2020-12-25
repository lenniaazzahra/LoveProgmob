package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import id.guscahya.myapplication.ui.main4.LogOutFragment;

public class Log_Out extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__out_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LogOutFragment.newInstance())
                    .commitNow();
        }
    }
}