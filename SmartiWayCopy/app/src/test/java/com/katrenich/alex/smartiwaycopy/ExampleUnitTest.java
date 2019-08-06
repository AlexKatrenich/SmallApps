package com.katrenich.alex.smartiwaycopy;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

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
    public void phoneCheckRequest(){
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DATE, 1);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(format.format(calendar.getTime()));
    }
}