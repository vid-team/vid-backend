package com.vid.vidbackend.domain.order.entity;

import com.vid.vidbackend.domain.auction.entity.Auction;
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
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Order extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private boolean isDepositComplete;

    @Builder.Default
    @Column(nullable = false, length = 15)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id", nullable = false)
    private Auction auction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void completeDeposit() {
        if (!this.isDepositComplete) {
            this.isDepositComplete = true;
        }
    }

    public static Order createOrder(User user, Auction auction) {
        Order order = new Order();
        order.setUser(user);
        order.setAuction(auction);
        order.setStatus(OrderStatus.ORDER);

        //TODO setDelivery

        return order;
    }


    //TODO createReturnOrder()
    //TODO cancel() -> if (delivery status == COMP) -> 취소 불가

    private void setUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

    private void setAuction(Auction auction) {
        this.auction = auction;
        auction.setOrder(this);
    }

    private void setStatus(OrderStatus status) {
        this.status = status;
    }
}
