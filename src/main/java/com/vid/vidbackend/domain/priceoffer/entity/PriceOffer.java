package com.vid.vidbackend.domain.priceoffer.entity;

import com.vid.vidbackend.domain.product.entity.Product;
import com.vid.vidbackend.domain.user.entity.User;
import com.vid.vidbackend.global.domain.MutableBaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PriceOffer extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean isAccepted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "offering_user_id", nullable = false)
    private User offeringUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiving_user_id", nullable = false)
    private User receivingUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
