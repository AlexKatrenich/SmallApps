package com.katrenich.alex.cashnote.model;

public class CashNote {
    private String caption;
    private int mFullSum; //  100 = 1.00 uah
    private int mPercentsSum; //  100 = 1.00 uah
    private int mFinalSum; // 100 = 1.00 uah
    private int mPercents; // 0..100 => 15 = 0.15%
    private String mCashlessPaymentDate; // dd.mm.yyyy
    private String mPaymentDate; // dd.mm.yyyy
}
