package com.ssg.bidssgket.user.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderNo")
    private Member sender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiverNo")
    private Member receiver;

    @OneToMany(mappedBy = "chatRoom",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    private List<ChatContents> chatContents = new ArrayList<>();

//    public static ChatRoom createChatRoom(Member member) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setSender(member);
//        chatRoom.setReceiver(member);
//        return chatRoom;
//    }
    @Builder
    private ChatRoom(Long chatroomNo, Member sender, Member receiver) {
        this.chatroomNo = chatroomNo;
        this.sender = sender;
        this.receiver = receiver;
    }

}
