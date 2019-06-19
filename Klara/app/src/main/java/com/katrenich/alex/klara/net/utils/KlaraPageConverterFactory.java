package com.katrenich.alex.klara.net.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.annotation.Nullable;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class KlaraPageConverterFactory implements Converter<ResponseBody, Document> {

        public static final Converter.Factory FACTORY = new Converter.Factory() {
            @Override
            public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                if (type == Document.class) return new KlaraPageConverterFactory();
                return null;
            }
        };

    @Nullable
    @Override
    public Document convert(ResponseBody responseBody) throws IOException {
        Document doc = Jsoup.parse(responseBody.string());
        return doc;
    }

}
