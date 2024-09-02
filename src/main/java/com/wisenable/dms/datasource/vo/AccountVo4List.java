package com.wisenable.dms.datasource.vo;

import com.wisenable.dms.datasource.entities.Account;

public class AccountVo4List extends Account {

    private String thisMonthAmount;

    public String getThisMonthAmount() {
        return thisMonthAmount;
    }

    public void setThisMonthAmount(String thisMonthAmount) {
        this.thisMonthAmount = thisMonthAmount;
    }
}