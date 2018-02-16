package xyz.jhughes.epsteinandroid.networking;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import xyz.jhughes.epsteinandroid.models.Me;

public interface EpsteinApi {
    @FormUrlEncoded
    @POST("androidlogin")
    Call<String> login(@Field("androidIdToken") String androidIdToken);

    @GET("androidme")
    Call<Me> getMe(@Header("email") String email, @Header("idToken") String idToken);
}
