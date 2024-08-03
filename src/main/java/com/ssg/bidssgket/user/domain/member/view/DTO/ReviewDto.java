package com.ssg.bidssgket.user.domain.member.view.DTO;

import com.ssg.bidssgket.user.domain.member.domain.Member;
import com.ssg.bidssgket.user.domain.product.domain.Product;
import jakarta.persistence.criteria.CriteriaBuilder;

public class ReviewDto {
    private Long reviewNo;
    private String comment;
    private Integer biscuitRating;
    private Member reviewer;
    private Member reviewee;
    private Product productNo;

}
