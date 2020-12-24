                                                                       package id.guscahya.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;
import id.guscahya.myapplication.penyimpanan.*;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText nama, email, password,confirm,alamat;
    private Button registrasi;
    private ProgressBar pb;
    private static String URL_REGIST = "http://"+ip.ipc+"/pratikum/public/api/register";

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText texttanggal;
    private Button btntanggal;
    private RadioGroup radiogrup;
    private String jenis_kelamin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        texttanggal = (EditText) findViewById(R.id.texttanggal);
        btntanggal = (Button) findViewById(R.id.btntanggal);
        btntanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        radiogrup = findViewById(R.id.radiogrup);

        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        pb = findViewById(R.id.pb);
        registrasi = findViewById(R.id.registrasi);
        alamat = findViewById(R.id.alamat);



        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regis();
            }
        });

        radiogrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.radiomale:
                        jenis_kelamin = "laki-laki";
                        break;
                    case R.id.radiofemale:
                        jenis_kelamin = "perempuan";
                        break;

                }
            }
        });
    }



    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                texttanggal.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }


    private void regis(){
        pb.setVisibility(View.VISIBLE);
//        registrasi.setVisibility(View.GONE);

        final String nama = this.nama.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();
        final String texttanggal = this.texttanggal.getText().toString().trim();
        final String jenis_kelamin = this.jenis_kelamin;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST, new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("responnya",response);
                        pb.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            Log.d("sukses",success);
                            if(success.equals("1")){
                                Toast.makeText(MainActivity.this, "Register Berhasil!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Register Gagal" + e.toString(), Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.GONE);
                            registrasi.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Register Gagal" + error.toString(), Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.GONE);
                        registrasi.setVisibility(View.VISIBLE);

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params = new HashMap<>();
                params.put("name",nama);
                params.put("email",email);
                params.put("password",password);
                params.put("alamat",alamat);
                params.put("tanggal_lahir",texttanggal);
                params.put("jenis_kelamin",jenis_kelamin);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
