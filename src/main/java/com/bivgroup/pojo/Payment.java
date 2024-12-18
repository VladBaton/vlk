package com.bivgroup.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Payment {

    private Long PayId;
    private Double amount;
    private Date payDate;
    private Date startDate;
    private Date finishDate;
    private Long orderNum;
    private Long status;
}
