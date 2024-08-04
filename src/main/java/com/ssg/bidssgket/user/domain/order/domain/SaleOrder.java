package com.ssg.bidssgket.user.domain.order.domain;

import com.ssg.bidssgket.common.domain.BaseTimeAndDeleteEntity;
import com.ssg.bidssgket.user.domain.member.domain.Member;
import com.ssg.bidssgket.user.domain.order.domain.enums.DeliveryType;
import com.ssg.bidssgket.user.domain.order.domain.enums.OrderStatus;
import com.ssg.bidssgket.user.domain.order.domain.enums.TransactionType;
import com.ssg.bidssgket.user.domain.payment.domain.Payment;
import com.ssg.bidssgket.user.domain.product.domain.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_order")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaleOrder extends BaseTimeAndDeleteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleOrderNo; // 판매 주문 번호 [PK]

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // 경매, 즉시구매

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryType deliveryType; // 직거래, 안전거래

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus orderStatus; // 결제대기, 결제완료, 배송대기, 배송중, 배송완료, 주문완료, 결제취소, 주문취소

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_no", nullable = false)
    private Member seller; // 판매자 회원 번호 [FK]

    @OneToOne
    @JoinColumn(name = "product_no", nullable = false)
    private Product product; // 상품 번호 [FK]

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_no")
    private Payment payment; // 결제 정보 [FK]

    @OneToOne(mappedBy = "SaleOrder", cascade = CascadeType.ALL)
    private Parcel parcel; // 택배 정보 [FK]

    @Builder
    private SaleOrder(TransactionType transactionType, DeliveryType deliveryType, OrderStatus orderStatus, Member seller, Product product, Parcel parcel, Payment payment) {
        this.transactionType = transactionType;
        this.deliveryType = deliveryType;
        this.orderStatus = orderStatus;
        this.seller = seller;
        this.product = product;
        this.parcel = parcel;
        this.payment = payment;
    }

    /**
     * 판매 주문 생성 메서드
     * @param transactionType 거래 유형
     * @param deliveryType 배송 유형
     * @param orderStatus 주문 상태
     * @param seller 판매자
     * @param product 상품
     * @param parcel 택배 정보
     * @param payment 결제 정보
     * @return 생성된 SaleOrder 객체
     */
    public static SaleOrder createSaleOrder(TransactionType transactionType, DeliveryType deliveryType, OrderStatus orderStatus, Member seller, Product product, Parcel parcel, Payment payment) {
        return SaleOrder.builder()
                .transactionType(transactionType)
                .deliveryType(deliveryType)
                .orderStatus(orderStatus)
                .seller(seller)
                .product(product)
                .parcel(parcel)
                .payment(payment)
                .build();
    }

    /***
     * 택배 정보 설정 메서드
     * @param parcel 택배 정보
     */
    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
        if (parcel.getSaleOrder() != this) {
            parcel.setSaleOrder(this);
        }
    }

    /***
     * 결제 정보 설정 메서드
     * @param payment 결제 정보
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
        if (payment.getSaleOrder() != this) {
            payment.setSaleOrder(this);
        }
    }
}