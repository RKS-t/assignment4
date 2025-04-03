package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext", nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Comment() {
    }

    public Comment(String contents) {
        this.contents = contents;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setPlan(Plan plan){
        this.plan = plan;
    }

    public void updateComment(String contents){
        this.contents =contents;
    }


}
