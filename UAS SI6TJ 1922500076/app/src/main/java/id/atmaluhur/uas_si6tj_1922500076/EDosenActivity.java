package id.atmaluhur.uas_si6tj_1922500076;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import uas_si6tj_1922500076.R;

public class EDosenActivity extends AppCompatActivity {
    private EditText txtnidn, txtnama_dosen, txtjabatan, txtgol_pang, txtkeahlian, txtprogram_studi;
    private Button btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edosen);
        btn_kembali = findViewById(R.id.btn_kembali);
        btn_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EDosenActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        txtnidn = (EditText) findViewById(R.id.et_nidn);
        txtnama_dosen = (EditText) findViewById(R.id.et_nama_dosen);
        txtjabatan = (EditText) findViewById(R.id.et_jabatan);
        txtgol_pang = (EditText) findViewById(R.id.et_gol_pang);
        txtkeahlian = (EditText) findViewById(R.id.et_keahlian);
        txtprogram_studi =(EditText) findViewById(R.id.et_program_studi);
    }
    public void tambahdosen(View View) {
        final String nidn = txtnidn.getText().toString().trim();
        final String nama_dosen = txtnama_dosen.getText().toString().trim();
        final String jabatan = txtjabatan.getText().toString().trim();
        final String gol_pang = txtgol_pang.getText().toString().trim();
        final String keahlian = txtkeahlian.getText().toString().trim();
        final String program_studi = txtprogram_studi.getText().toString().trim();

        class tambahdosen extends AsyncTask<Void, Void, String> {
            ProgressDialog load;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                load = ProgressDialog.show(
                        EDosenActivity.this,"Add ...","Wait..",false,false
                );
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put("nidn", nidn);
                params.put("nama_dosen", nama_dosen);
                params.put("jabatan", jabatan);
                params.put("gol_pang", gol_pang);
                params.put("keahlian",keahlian);
                params.put("program_studi",program_studi);
                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest("http://192.168.100.58/Dosen/tambah_dosen.php/", params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                load.dismiss();
                Toast.makeText(EDosenActivity.this, s,Toast.LENGTH_LONG).show();
            }
        }
        tambahdosen tb = new tambahdosen();
        tb.execute();
    }
}