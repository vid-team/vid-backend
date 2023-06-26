package com.vid.vidbackend.domain.auction.entity;

import com.vid.vidbackend.domain.bid.entity.Bid;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Auction extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer duration;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private AuctionStatus status = AuctionStatus.PENDING;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "auction", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

    @PrePersist
    public void calculateDeadline() {
        if (duration == null) {
            this.deadline = LocalDateTime.now().plusDays(7);
            return;
        }
        this.deadline = LocalDateTime.now().plusDays(duration);
    }

    public void setUser(User user) {
        this.user = user;
    }

    //TODO bid()
}
