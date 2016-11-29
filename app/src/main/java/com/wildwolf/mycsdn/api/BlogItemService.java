package com.wildwolf.mycsdn.api;


import com.wildwolf.mycsdn.constant.Api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/21.
 */
public interface BlogItemService {
    String BASE_URL = Api.URL_GET_BLOG;

    @GET("/jujishou521/article/category/{id}/{p}")
    Observable<String> getBlogItemData(@Path("id") String id, @Path("p") int p);


}
