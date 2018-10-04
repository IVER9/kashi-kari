package com.herokuapp.kashikari.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private Long id;
    private Long teamId;
    private String name;
    private String money;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
}
