package com.herokuapp.kashikari.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MemberDto {
    private String id;
    private String teamId;
    private String name;
    private Long money;
    private Long fromMoneySum;
    private Long toMoneySum;
    private Date createdAt;
    private Date updatedAt;
    private String deletedFlag;
    private List<LoanDto> loanFrom;
    private List<LoanDto> loanTo;
}
