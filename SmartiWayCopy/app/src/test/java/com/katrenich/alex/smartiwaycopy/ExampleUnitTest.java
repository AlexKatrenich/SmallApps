package com.katrenich.alex.smartiwaycopy;

import com.katrenich.alex.smartiwaycopy.creditModule.presentation.presenter.CreditFragmentPresenter;
import com.katrenich.alex.smartiwaycopy.creditModule.presentation.ui.CreditFragment;
import com.katrenich.alex.smartiwaycopy.creditModule.util.UserInfo;
import com.katrenich.alex.smartiwaycopy.network.NetworkService;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
    public void phoneCheckRequest(){
        String s = "АБВГДЄ";

        System.out.println(s.substring(1));

    }
}