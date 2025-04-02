package com.example.scheduleproject.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "plan")
public class Plan extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Plan() {
    }

    public Plan(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void setMember(Member member){
        this.member = member;
    }
}
