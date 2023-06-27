package com.vid.vidbackend.domain.user.entity;

import com.vid.vidbackend.domain.auction.entity.Auction;
import com.vid.vidbackend.domain.bid.entity.Bid;
import com.vid.vidbackend.domain.favoriteproduct.entity.FavoriteProduct;
import com.vid.vidbackend.domain.notification.entity.Notification;
import com.vid.vidbackend.domain.order.entity.Order;
import com.vid.vidbackend.domain.priceoffer.entity.PriceOffer;
import com.vid.vidbackend.domain.product.entity.Product;
import com.vid.vidbackend.domain.product.entity.ProductClickLog;
import com.vid.vidbackend.domain.userreview.entity.UserReview;
import com.vid.vidbackend.exception.address.AddressNotFoundException;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class User extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25, unique = true)
    private String name;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 20, unique = true)
    private String phone;

    @Column(length = 65535)
    private String imageUrl;

    @Column(nullable = false)
    private boolean mobileAuthenticated;

    @Column(nullable = false)
    private int xp;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Rank rank = Rank.UNRANK;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private OauthProvider oauthProvider;

    @Column(length = 250, unique = true)
    private String oauthId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Role role = Role.GUEST;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "blockedUser", cascade = REMOVE, orphanRemoval = true)
    private Set<UserBlock> blockedUsers = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<FavoriteProduct> favoriteProducts = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<ProductClickLog> productClickLogs = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "reviewer")
    private List<UserReview> givenReviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "reviewee", cascade = REMOVE, orphanRemoval = true)
    private List<UserReview> receivedReviews = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "offeringUser", cascade = ALL, orphanRemoval = true)
    private List<PriceOffer> offeredPriceOffers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "receivingUser", cascade = ALL, orphanRemoval = true)
    private List<PriceOffer> receivedPriceOffers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
    private List<Auction> auctions = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = REMOVE, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    public void updateName(final String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void updatePassword(final String password) {
        if (password != null) {
            this.password = password;
        }
    }

    public void updateImageUrl(final String imageUrl) {
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }

    public void authenticateMobile() {
        if (!this.mobileAuthenticated) {
            this.mobileAuthenticated = true;
        }
    }

    private void updateRank() {
        this.rank = Rank.getRankForXp(this.xp);
    }

    public void updateAddress(final Address address) {
        int index = this.addresses.indexOf(address);
        if (index == -1) {
            throw new AddressNotFoundException();
        }
        this.addresses.set(index, address);
    }

    public boolean isAuthenticated() {
        return this.mobileAuthenticated;
    }

    public void increaseXp(final int amount) {
        this.xp += amount;
        updateRank();
    }

    public void decreaseXp(final int amount) {
        this.xp -= amount;
        if (this.xp < 0) {
            this.xp = 0;
        }
        updateRank();
    }

    public void addNotification(final Notification notification) {
        this.notifications.add(notification);
    }

    public void addAuction(final Auction auction) {
        auction.setUser(this);
        this.auctions.add(auction);
    }

    public void blockUser(final User blockedUser, final BlockReason reason) {
        blockedUsers.add(UserBlock.builder()
                .user(this)
                .blockedUser(blockedUser)
                .reason(reason)
                .build());
    }

    public void unblockUser(final User blockedUser) {
        blockedUsers.removeIf(userBlock -> userBlock.getBlockedUser().equals(blockedUser));
    }

    public void addFavoriteProduct(final Product product) {
        FavoriteProduct favoriteProduct = FavoriteProduct.builder()
                .user(this)
                .product(product)
                .build();
        this.favoriteProducts.add(favoriteProduct);
        product.getFavoriteUsers().add(favoriteProduct);
    }

    public void removeFavoriteProduct(final Product product) {
        FavoriteProduct favoriteProduct = FavoriteProduct.builder()
                .user(this)
                .product(product)
                .build();
        this.favoriteProducts.remove(favoriteProduct);
        product.getFavoriteUsers().remove(favoriteProduct);
    }
}
