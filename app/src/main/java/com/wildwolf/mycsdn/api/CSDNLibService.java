package com.wildwolf.mycsdn.api;


import com.wildwolf.mycsdn.constant.Api;
import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ${wild00wolf} on 2016/11/25.
 */
public interface CSDNLibService {

    //http://lib.csdn.net/bases/Programming-Language
    String BASE_URL = Api.URL_GET_CSDNLIB;

    @GET("bases/{article}")
    Observable<String> getCSDNLibData(@Path("article") String article, @Query("type") String type, @Query("page") int page);
}
