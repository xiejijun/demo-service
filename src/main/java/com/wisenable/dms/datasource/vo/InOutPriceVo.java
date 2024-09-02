package com.wisenable.dms.datasource.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class InOutPriceVo {

    private Long id;

    private BigDecimal discountLastMoney;

    private BigDecimal totalPrice;

    private String type;

    private String subType;

    private Date operTime;

}
