package com.herokuapp.kashikari.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Team {
    private Long id;
    private String url;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
}
