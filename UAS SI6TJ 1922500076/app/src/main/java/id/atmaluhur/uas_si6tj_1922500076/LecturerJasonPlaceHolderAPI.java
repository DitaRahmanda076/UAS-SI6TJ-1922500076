package id.atmaluhur.uas_si6tj_1922500076;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface LecturerJasonPlaceHolderAPI {
    @GET("lecturer.php")
    Call<List<lecturerPost>> getPosts(

    );
    @GET("lecturer.php")
    Call<List<lecturerPost>> getPosts(@QueryMap Map<String, String> parameters);
}
