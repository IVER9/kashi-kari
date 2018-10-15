package com.herokuapp.kashikari.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LoanDto {
    private String id;
    private String fromMemberId;
    private String fromMemberName;
    private String toMemberId;
    private String toMemberName;
    private Long time;
    private String task;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
}
