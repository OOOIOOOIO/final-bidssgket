package com.ssg.bidssgket.user.domain.member.domain;

import com.ssg.bidssgket.user.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNo;
    private String comment;
    private Integer biscuitRating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewerNo")
    private Member reviewer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewedNo")
    private Member reviewee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productNo")
    private Product productNo;

//    public static Review createReview(Member member, Product product) {
//        Review review = new Review();
//        review.setReviewer(member);
//        review.setReviewed(member);
//        review.setProductNo(product);
//        return review;
//    }

    @Builder
    private Review(String comment, Integer biscuitRating,Member reviewer, Member reviewee, Product productNo) {
        this.comment = comment;
        this.biscuitRating = biscuitRating;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
        this.productNo = productNo;
    }
}
