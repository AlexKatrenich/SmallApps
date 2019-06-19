package com.katrenich.alex.klara.net;

import org.jsoup.nodes.Document;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface KlaraWebSiteQueries {

    @GET("{path}")
    Flowable<Document> getData(@Path("path") String path);

}
