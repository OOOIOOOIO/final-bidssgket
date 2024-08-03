package com.ssg.bidssgket.user.domain.product.view.dto.request;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductReqDto {
    private Long productNo;
    private String productName;
    private String category;
    private String productDesc;
    private String salesStatus;
    private Boolean imd_purchase;
    private Boolean auction_selected;
    private Integer buynowPrice;
    private Integer auctionStartPrice;
    private Integer bidSuccessPrice;
    private LocalDateTime auctionStartTime;
    private LocalDateTime auctionEndTime;
}
