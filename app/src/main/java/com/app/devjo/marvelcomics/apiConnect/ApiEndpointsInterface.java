package com.app.devjo.marvelcomics.apiConnect;

import com.app.devjo.marvelcomics.models.ComicDataWrapperModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kjoha on 17/3/2017.
 */

public interface ApiEndpointsInterface {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

        @GET("/v1/public/comics")
        Observable<ComicDataWrapperModel> getListComics(
                @Query("apikey") String apikey,
                @Query("ts") String ts,
                @Query("hash") String hash,
                @Query("limit") int limit
        );

        @GET("/v1/public/comics")
        Observable<ComicDataWrapperModel> getListComicsByTitleStartsWith(
                @Query("apikey") String apikey,
                @Query("ts") String ts,
                @Query("hash") String hash,
                @Query("limit") int limit,
                @Query("titleStartsWith") String titleStartsWith
        );

        @GET("/v1/public/comics")
        Observable<ComicDataWrapperModel> getListComicsOrderBy(
                @Query("apikey") String apikey,
                @Query("ts") String ts,
                @Query("hash") String hash,
                @Query("limit") int limit,
                @Query("orderBy") String orderBy
        );

        ///@GET("/v1/public/comics/{comicId} ")
        ///Call <> getComicsById(@Path("comicId") int comicId, @Query("apikey") String apikey, @Query("ts") String ts, @Query("hash") String hash );

}
