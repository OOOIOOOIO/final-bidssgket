package com.ssg.bidssgket.user.domain.payment.domain;

import com.ssg.bidssgket.user.domain.payment.domain.enums.PayChangeType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="pay_change")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PayChange {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long payChangeNo; // 비스킷 페이 잔액 변경 내역 번호 [PK]

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayChangeType payChangeType; // 입금, 출금

    @Column(nullable = false)
    private int payChangeAmount; // 변경 금액 (양수: 입금 / 음수: 출금)

    @Column(nullable = false)
    private int balance; // 보유 잔액

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt; // 등록일

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "pay_no", nullable=false)
    private Pay pay; // 결제 [FK]

    @Builder
    public PayChange(PayChangeType payChangeType, int changeAmount, int initialBalance, Pay pay) {
        this.payChangeType = payChangeType;
        this.payChangeAmount = changeAmount;
        this.pay = pay;
        this.createdAt = LocalDateTime.now();
        setBalance(initialBalance);
    }

    /***
     * 잔액 설정 메서드
     * @param initialBalance 초기 잔액
     */
    public void setBalance(int initialBalance) {
        if (payChangeType == PayChangeType.DEPOSIT) {
            this.balance = initialBalance + payChangeAmount;
        } else if (payChangeType == PayChangeType.WITHDRAWAL) {
            if (payChangeAmount > initialBalance) {
                throw new IllegalArgumentException("잔액이 부족합니다.");
            } else {
                this.balance = initialBalance - payChangeAmount;
            }
        }
    }

    /***
     * 결제 객체 설정 메서드
     * @param pay 결제 객체
     */
    public void setPay(Pay pay) {
        this.pay = pay;
        if (!pay.getPayChangeList().contains(this)) {
            pay.getPayChangeList().add(this);
        }
    }
}
