package com.katrenich.alex.klara;

import com.katrenich.alex.klara.net.NetworkService;

import org.jsoup.nodes.Document;
import org.junit.Test;

import io.reactivex.Flowable;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void parseIsCorrect(){
        Flowable<Document> documentFlowable = NetworkService.getInstance()
                .getKlaraWebSiteInfo()
                .getData("catalog");

        documentFlowable.subscribe(document -> {
            System.out.println("YEAP");
            String s = document.body().html();
            System.out.println(s);
        });
    }
}