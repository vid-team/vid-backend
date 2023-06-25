package com.vid.vidbackend.domain.product.entity;

import com.vid.vidbackend.domain.auction.entity.Auction;
import com.vid.vidbackend.domain.favoriteproduct.entity.FavoriteProduct;
import com.vid.vidbackend.domain.priceoffer.entity.PriceOffer;
import com.vid.vidbackend.domain.producttag.entity.ProductTag;
import com.vid.vidbackend.domain.user.entity.User;
import com.vid.vidbackend.global.domain.MutableBaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class Product extends MutableBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private int startPrice;

    @Column(nullable = false)
    private boolean isParcelPrepayment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status;

    @Column(nullable = false)
    private long viewCount;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FavoriteProduct> favoriteUsers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PriceOffer> priceOffers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ProductTag> productTags = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Auction auction;

    public void increaseViewCount() {
        this.viewCount += 1;
    }

    public void update(Product updateProduct) {
        updateTitle(updateProduct.getTitle());
        updateDescription(updateProduct.getDescription());
        updateImages(updateProduct.getImages());
        updateStatus(updateProduct.getStatus());
        updateCategory(updateProduct.getCategory());
    }

    private void updateTitle(final String title) {
        if (title != null) {
            this.title = title;
        }
    }

    private void updateDescription(final String description) {
        if (description != null) {
            this.description = description;
        }
    }

    private void updateImages(final List<ProductImage> images) {
        if (images != null) {
            this.images = images;
        }
    }

    private void updateStatus(final ProductStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    private void updateCategory(final ProductCategory category) {
        if (category != null) {
            this.category = category;
        }
    }
}
