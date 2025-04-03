package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Member;
import com.example.scheduleproject.exception.LoginFailException;
import com.example.scheduleproject.exception.NullResponseException;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //아이디를 이용한 조회에 대한 null 예외처리
    default Member findMemberByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() ->
                new NullResponseException("Dose not exist member by id =" + id)
        );
    }

    //email을 이용한 조회는 로그인에
    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email){
        return findMemberByEmail(email).orElseThrow(() ->
                new LoginFailException("존재하지 않는 이메일입니다.")
        );
    }
}
