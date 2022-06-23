package id.atmaluhur.uas_si6tj_1922500076;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uas_si6tj_1922500076.R;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    private LecturerJasonPlaceHolderAPI jasonPlaceHolderAPI;
    private FloatingActionButton btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_tambah =findViewById(R.id.btn_tambah);
        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EDosenActivity.class);
                startActivity(i);
            }
        });
        textViewResult = findViewById(R.id.text_lecturer_result);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.58/Dosen/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jasonPlaceHolderAPI = retrofit.create(LecturerJasonPlaceHolderAPI.class);
        getPosts();
    }
    private void getPosts(){
        Map<String, String> parameters = new HashMap<>();
        Call<List<lecturerPost>> call = jasonPlaceHolderAPI.getPosts();
        call.enqueue(new Callback<List<lecturerPost>>() {
            @Override
            public void onResponse(Call<List<lecturerPost>> call, Response<List<lecturerPost>> response) {
              if (!response.isSuccessful()){
                  textViewResult.setText("Code:  " +response.code());
                  return;
              }
              List<lecturerPost> posts = response.body();
              for (lecturerPost post : posts){
                  String content = "";
                  content += "NIDN : " + post.getNidn() + "\n";
                  content += "NAMA DOSEN : " + post.getNama_dosen() + "\n";
                  content += "JABATAN : " + post.getJabatan() + "\n";
                  content += "GOL PANG : " + post.getGol_pang() + "\n";
                  content += "KEAHLIAN : " + post.getKeahlian() + "\n";
                  content += "PROGRAM STUDI : " + post.getProgram_studi() + "\n \n";
                  textViewResult.append(content);
              }
            }

            @Override
            public void onFailure(Call<List<lecturerPost>> call, Throwable t) {
                textViewResult.setText(t.getMessage());

            }
        });
    }
}