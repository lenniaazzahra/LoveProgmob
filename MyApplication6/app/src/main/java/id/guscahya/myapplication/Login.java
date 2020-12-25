package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import id.guscahya.myapplication.penyimpanan.*;
import id.guscahya.myapplication.SessionManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText email, password;
    private Button masuk;
    private ProgressBar pb;
    private TextView tombolregister;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    SessionManager sessionManager;
    private static String URL_LOGIN = "http://"+ip.ipc+"/pratikum/public/api/login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        masuk = findViewById(R.id.masuk);
        pb = findViewById(R.id.pb);
        tombolregister = findViewById(R.id.tombolregister);


        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String logemail = email.getText().toString().trim();
                String logpassword = password.getText().toString().trim();

                if (!logemail.isEmpty() || !logpassword.isEmpty()){
                    loginn(logemail,logpassword);
                }else{
                    email.setError("Masukkan Email terlebih dahulu");
                    password.setError("Masukkan Password terlebih dahulu");
                }
            }
        });

        tombolregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }

    private void loginn(final String logemail, String logpassword) {
        pb.setVisibility(View.VISIBLE);


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responnya",response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){


                                pb.setVisibility(View.GONE);
                                masuk.setVisibility(View.VISIBLE);
                                sharedPreferences = getSharedPreferences(penyimpanan.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
                                editor = sharedPreferences.edit();
                                editor.putString(sessionManager.NAME,jsonObject.getString("name"));
                                editor.putString(sessionManager.EMAIL,jsonObject.getString("email"));
//                                    editor.putString(penyimpanan.TOKEN,jsonObject.getString("token"));
                                editor.apply();
                                HashMap<String, String> user = sessionManager.getUserDetail();
                                String name = user.get(sessionManager.NAME);
                                String email = user.get(sessionManager.EMAIL);
                                sessionManager.createSession(name, email);
                                startActivity(new Intent(Login.this, menunavigasi.class));
                                Toast.makeText(Login.this,sharedPreferences.getString(penyimpanan.USERNAME,"tidak ada"), Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(Login.this, "Gagal Masuk", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Login.this, "Gagal MAsuk"+ e.toString(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                            masuk.setVisibility(View.VISIBLE);

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this, "Gagal MAsuk"+ error.toString(), Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        masuk.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("email",email.getText().toString().trim());
                params.put("password",password.getText().toString().trim());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
}