package com.ssg.bidssgket.user.domain.member.domain;

import com.ssg.bidssgket.user.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class WishProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishProductNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNo")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo")
    private Product productNo;

//    public static WishProduct createWishProduct(Member member, Product product) {
//        WishProduct wishProduct = new WishProduct();
//        wishProduct.setMember(member);
//        wishProduct.setProductNo(product);
//        return wishProduct;
//    }

    @Builder
    public WishProduct(Member member, Product productNo) {
        this.member = member;
        this.productNo = productNo;
    }





}
