package com.credit.ukraine.online.creditModule.model;

public class Credit {
    private String dateOfCredit;
    private String returnDate;
    private Integer loanAmount;

    public Credit() {

    }

    public String getDateOfCredit() {
        return dateOfCredit;
    }

    public void setDateOfCredit(String dateOfCredit) {
        this.dateOfCredit = dateOfCredit;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }
}
