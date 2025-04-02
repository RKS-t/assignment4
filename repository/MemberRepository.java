package com.example.scheduleproject.repository;

import com.example.scheduleproject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    default Member findMemberByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dose not exist id =" + id
                )
        );
    }

    Optional<Member> findMemberByEmail(String email);

    default Member findMemberByEmailOrElseThrow(String email){
        return findMemberByEmail(email).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dose not exist name =" + email
                )
        );
    }
}
