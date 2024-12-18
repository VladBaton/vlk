package com.bivgroup.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Contract {

    private String contractNumber;
    private Long contractId;
    private Date startDate;
    private Date finishDate;
    private Date signDate;
    private List<Payment> payments;
}
