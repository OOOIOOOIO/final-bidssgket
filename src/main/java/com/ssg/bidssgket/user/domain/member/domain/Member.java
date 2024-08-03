package com.ssg.bidssgket.user.domain.member.domain;

import com.ssg.bidssgket.user.domain.member.view.DTO.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo; // 사용자 고유번호
    private String memberName; // 사용자 이름
    private String id; // 사용자 아이디
    private String pwd; // 사용자 비밀번호
    private String phone; // 핸드폰 번호
    private String nickname;
//    private String provider;
//    private String providerId;
    private Integer biscuit; // 비스킷 온도
    private boolean isDeleted; // 탈퇴 여부
    private boolean isPenalty; // 패널티 여부

    @Embedded
    private Address address; // 주소

    @PrePersist
    protected void onCreate() {
        if (this.biscuit == null) {
            this.biscuit = 50;
        }
        if (!this.isDeleted) {
            this.isDeleted = false;
        }
        if (!this.isPenalty) {
            this.isPenalty = false;
        }
    }

    @OneToMany(mappedBy = "member",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    private List<WishProduct> wishProducts =new ArrayList<>();

    @OneToOne(mappedBy = "member",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    private Review review;

    @OneToOne(mappedBy = "member",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    private ChatRoom chatRoom;

    @OneToOne(mappedBy = "member",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    private ChatContents chatContents;


    @Builder
    private Member(String memberName, String id, String pwd, String nickname, String phone,Integer biscuit, Address address,Boolean isDeleted,Boolean isPenalty) {
        this.memberName = memberName;
        this.id = id;
        this.pwd = pwd;
        this.nickname = nickname;
        this.phone = phone;
        this.biscuit = biscuit;
        this.address = address;
        this.isDeleted = isDeleted;
        this.isPenalty = isPenalty;
    }

}
