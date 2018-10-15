package com.herokuapp.kashikari.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private String id;
    private String teamId;
    private String name;
    private Long money;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
}
