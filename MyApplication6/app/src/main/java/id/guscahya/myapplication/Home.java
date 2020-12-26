package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import id.guscahya.myapplication.penyimpanan.penyimpanan;


public class Home extends AppCompatActivity {
    TextView nama_user,email;
    SharedPreferences preferences;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        preferences = getSharedPreferences("nama", Context.MODE_PRIVATE);
        String nama = preferences.getString(penyimpanan.USERNAME, null);
//        String email = preferences.getString(penyimpanan.EMAIL,"");
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String name = user.get(sessionManager.NAME);

        TextView user_name=(TextView)findViewById(R.id.nameuser);
        user_name.setText(name);

        TextView myadver=(TextView)findViewById(R.id.myiklan);
        myadver.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Home.this,tambahiklan.class);
                startActivity(i);
            }
        });
        TextView pesanansaya=(TextView)findViewById(R.id.ordermy);
        pesanansaya.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Home.this,Pesanan_Saya.class);
                startActivity(i);
            }
        });

        ImageView join_us=(ImageView) findViewById(R.id.be_teacher);
        join_us.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(Home.this,Buat_Iklan.class);
                startActivity(i);
            }
        });

    }
}