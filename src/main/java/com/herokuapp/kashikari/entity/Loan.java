package com.herokuapp.kashikari.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Loan {
    private String id;
    private String fromMemberId;
    private String toMemberId;
    private Long time;
    private String task;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
}
