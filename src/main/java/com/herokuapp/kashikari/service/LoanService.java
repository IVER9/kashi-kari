package com.herokuapp.kashikari.service;

import com.herokuapp.kashikari.dto.LoanDto;
import com.herokuapp.kashikari.entity.Loan;
import com.herokuapp.kashikari.form.LoanCreateForm;
import com.herokuapp.kashikari.repository.LoanRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public void create(LoanCreateForm form) {
        Loan loan = new Loan();
        BeanUtils.copyProperties(form, loan);
        String createId = UUID.randomUUID().toString();
        loan.setId(createId);
        loanRepository.create(loan);
    }

    public void delete(String loanId) {
        loanRepository.deleteByLoanId(loanId);
    }

    public List<LoanDto> getLoanFrom(String id) {
        return loanRepository.selectByFromId(id);
    }

    public List<LoanDto> getLoanTo(String id) {
        return loanRepository.selectByToId(id);
    }
}
