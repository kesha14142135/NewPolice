package com.klg.newpolice.data.api;

import com.klg.newpolice.data.api.model.CommentResponce;
import com.klg.newpolice.data.api.model.MissChildResponce;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataApi {

    @GET("search")
    Flowable<List<MissChildResponce>> getChilds(@Header("Authorization") String header,
                                                  @Query("offset") int offset, @Query("limit") int limit);

    @GET("search/{searchId}/comments")
    Flowable<List<CommentResponce>> getComments(@Header("Authorization") String header,
                                                @Path("searchId") int searchId,
                                                @Query("offset") int offset, @Query("limit") int limit);

}
