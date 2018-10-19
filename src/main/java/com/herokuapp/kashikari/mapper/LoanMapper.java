package com.herokuapp.kashikari.mapper;

import com.herokuapp.kashikari.dto.LoanDto;
import com.herokuapp.kashikari.entity.Loan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LoanMapper {
    @Insert("INSERT INTO loan( id, from_member_id, to_member_id, time, task, created_at, updated_at, deleted_flag) VALUES ( #{id}, #{fromMemberId}, #{toMemberId}, #{time}, #{task}, now(), now(), '0')")
    void create(Loan loan);

    @Update("UPDATE loan SET deleted_flag = '1', updated_at = now() WHERE id = #{id}")
    void deleteByLoanId(String loanId);

    @Select("SELECT l.*, m.name as 'fromMemberName', m2.name as 'toMemberName', m2.money as 'toMemberMoney' FROM loan  l\n" +
            "\tLEFT JOIN member m ON l.from_member_id = m.id\n" +
            "\tLEFT JOIN member m2 ON l.to_member_id = m2.id\n" +
            "\tWHERE l.from_member_id = #{id} and l.deleted_flag = '0' ORDER BY l.updated_at;")
    List<LoanDto> selectByFromId(String id);

    @Select("SELECT l.*, m.name as 'fromMemberName', m2.name as 'toMemberName' FROM loan  l\n" +
            "\tLEFT JOIN member m ON l.from_member_id = m.id\n" +
            "\tLEFT JOIN member m2 ON l.to_member_id = m2.id\n" +
            "\tWHERE l.to_member_id = #{id} and l.deleted_flag = '0' ORDER BY l.updated_at;")
    List<LoanDto> selectByToId(String id);
}
