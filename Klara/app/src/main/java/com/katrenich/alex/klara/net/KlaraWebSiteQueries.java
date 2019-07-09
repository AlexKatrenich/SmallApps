package com.katrenich.alex.klara.net;

import org.jsoup.nodes.Document;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface KlaraWebSiteQueries {

    @GET("catalog")
    Single<Document> getProductsCatalog();

    @GET("catalog/deserti/")
    Single<Document> getCakeProductsCatalog();

    @GET("catalog/pirizhki/")
    Single<Document> getPattyProductsCatalog();

    @GET("catalog/napoi/")
    Single<Document> getDrinkProductsCatalog();

    @GET("catalog/salaty/")
    Single<Document> getSaladProductsCatalog();

    @GET("contacts/")
    Single<Document> getCoffeeShopsCatalog();

    @GET("vacancies/")
    Single<Document> getVacancyCatalog();

}
