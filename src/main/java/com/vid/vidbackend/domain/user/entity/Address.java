package com.vid.vidbackend.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int zipcode;

    @Column(nullable = false)
    private String line;

    @Column(nullable = false)
    private String detail;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Address(Long id, String title, int zipcode, String line, String detail, String description, User user) {
        this.id = id;
        this.title = title;
        this.zipcode = zipcode;
        this.line = line;
        this.detail = detail;
        this.description = description;
        this.user = user;
    }
}
