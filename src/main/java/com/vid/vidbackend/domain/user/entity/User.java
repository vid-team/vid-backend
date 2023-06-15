package com.vid.vidbackend.domain.user.entity;

import com.vid.vidbackend.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name="users")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String phone;

    @Enumerated
    private Rank rank;

    private int xp;

    @Enumerated
    private Role role;

    @Embedded
    private Address address;

    @Embedded
    private Account account;

    //TODO 리뷰 리스트
    //TODO 상품 리스트
    //TODO 관심 상품 리스트
    //TODO 입찰 상품 리스트
    //TODO builder
    public void changeName(String name) {
        this.name = name;
    }
    public void changePassword(String password) {
        this.password = password;
    }
}
