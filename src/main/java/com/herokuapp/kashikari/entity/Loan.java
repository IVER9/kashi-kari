package com.herokuapp.kashikari.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Loan {
    private Long id;
    private Long fromMemberId;
    private Long toMemberId;
    private Long time;
    private String task;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
}
