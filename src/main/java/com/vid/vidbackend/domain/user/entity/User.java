package com.vid.vidbackend.domain.user.entity;

import com.vid.vidbackend.exception.address.AddressNotFoundException;
import com.vid.vidbackend.global.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
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
import java.util.List;

@Entity
@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseTimeEntity {

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
    private boolean isAuthority;

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
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @Builder
    public User(Long id, String name, String email, String password, String phone, String imageUrl, int xp, Rank rank, OauthProvider oauthProvider, String oauthId, Role role, List<Address> addresses) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.imageUrl = imageUrl;
        this.xp = xp;
        this.rank = rank;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.role = role;
        this.addresses = addresses;
    }

    public void increaseXp(int amount) {
        this.xp += amount;
        updateRank();
    }

    public void decreaseXp(int amount) {
        this.xp -= amount;
        if (this.xp < 0) {
            this.xp = 0;
        }
        updateRank();
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void updateName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public void updatePassword(String password) {
        if (password != null) {
            this.password = password;
        }
    }

    public void updateImageUrl(String imageUrl) {
        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }

    private void updateRank() {
        this.rank = Rank.getRankForXp(this.xp);;
    }

    public void updateAddress(Address address) {
        int index = this.addresses.indexOf(address);
        if (index == -1) {
            throw new AddressNotFoundException();
        }
        this.addresses.set(index, address);
    }
}
