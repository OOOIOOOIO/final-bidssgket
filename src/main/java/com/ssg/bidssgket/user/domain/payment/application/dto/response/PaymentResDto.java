package com.ssg.bidssgket.user.domain.payment.application.dto.response;

import com.ssg.bidssgket.user.domain.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResDto {
    private Long paymentNo;
    private Long memberNo;
    private String paymentType;
    private String transactionType;
    private int amount;
    private String paymentStatus;

    private Long payChangeNo;
    private Long purchaseOrderNo;
    private Long saleOrderNo;

    public PaymentResDto(Payment payment) {
        if (payment.getPayChange() == null || payment.getPurchaseOrder() == null || payment.getSaleOrder() == null) {
            throw new IllegalArgumentException("Null을 허용하지 않는 필드에서 Null이 감지되었습니다.");
        }
        this.paymentNo = payment.getPaymentNo();
        this.memberNo = payment.getMember().getMemberNo();
        this.paymentType = payment.getPaymentType().name();
        this.transactionType = payment.getTransactionType().name();
        this.amount = payment.getAmount();
        this.paymentStatus = payment.getPaymentStatus().name();

        this.payChangeNo = payment.getPayChange().getPayChangeNo();
        this.purchaseOrderNo = payment.getPurchaseOrder().getPurchaseOrderNo();
        this.saleOrderNo = payment.getSaleOrder().getSaleOrderNo();
    }
}
