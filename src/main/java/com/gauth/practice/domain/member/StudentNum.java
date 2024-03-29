package com.gauth.practice.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
public class StudentNum {
    @Column(nullable = false, length = 1)
    private Integer grade;

    @Column(nullable = false, length = 1)
    private Integer classNum;

    @Column(nullable = false, length = 2)
    private Integer number;

    public StudentNum(Integer grade, Integer classNum, Integer number) {
        this.grade = grade;
        this.classNum = classNum;
        this.number = number;
    }

    public StudentNum() {

    }
}
