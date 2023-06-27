package com.vid.vidbackend.domain.bid.entity;

import com.vid.vidbackend.domain.auction.entity.Auction;
import com.vid.vidbackend.domain.auction.entity.AuctionStatus;
import com.vid.vidbackend.domain.user.entity.User;
import com.vid.vidbackend.exception.auction.AuctionNotFoundException;
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
import javax.persistence.PrePersist;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Bid extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int count;

    @Column(nullable = false)
    private int rank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    @PrePersist
    public void setCountAndRank() {
        validateAuction();
        updateCount();
        updateRank();
    }

    private void updateCount() {
        this.count = auction.getBids().size();
    }

    private void updateRank() {
        List<Bid> sortedBids = this.auction.getBids().stream()
                .sorted(Comparator.comparingInt(Bid::getPrice).reversed())
                .collect(Collectors.toList());

        this.rank = sortedBids.indexOf(this) + 1;
    }

    private void validateAuction() {
        if (this.auction == null || this.auction.getStatus() != AuctionStatus.IN_PROGRESS) {
            throw new AuctionNotFoundException();
        }
    }
}
