package com.herokuapp.kashikari.repository;

import com.herokuapp.kashikari.dto.LoanDto;
import com.herokuapp.kashikari.entity.Loan;
import com.herokuapp.kashikari.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoanRepository {
    @Autowired
    private LoanMapper loanMapper;

    public void create(Loan loan) {
        loanMapper.create(loan);
    }

    public void deleteByLoanId(String loanId) {
        loanMapper.deleteByLoanId(loanId);
    }

    public List<LoanDto> selectByFromId(String id) {
        return loanMapper.selectByFromId(id);
    }

    public List<LoanDto> selectByToId(String id) {
        return loanMapper.selectByToId(id);
    }
}
