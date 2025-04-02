package com.example.scheduleproject.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "plan")
public class Plan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate targetDate;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Plan() {
    }

    public Plan(String title, LocalDate targetDate, String contents) {
        this.title = title;
        this.targetDate = targetDate;
        this.contents = contents;
    }

    public void setMember(Member member){
        this.member = member;
    }
}
