package com.wisenable.dms.datasource.vo;

import com.wisenable.dms.datasource.entities.AccountItem;
import lombok.Data;

@Data
public class AccountItemVo4List extends AccountItem {
    private String accountName;
    private String inOutItemName;
    private String billNumber;
}