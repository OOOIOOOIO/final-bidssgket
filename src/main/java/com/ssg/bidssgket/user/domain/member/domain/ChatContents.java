package com.ssg.bidssgket.user.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatContents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatContentsNo;
    private String Contents;

    @Temporal(TemporalType.TIME)
    private Time time;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nickname")
    private Member nickname;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatImageNo")
    private ChatImage chatImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroomNo")
    private ChatRoom chatRoom;

//    public static ChatContents createChatContents(Member member,ChatImage chatImage,ChatRoom chatRoom){
//        ChatContents chatContents = new ChatContents();
//        chatContents.setNickname(member);
//        chatContents.setChatImage(chatImage);
//        chatContents.setChatRoom(chatRoom);
//        return chatContents;
//    }

    @Builder
    private ChatContents(String Contents, Time time, Member nickname, ChatImage chatImage, ChatRoom chatRoom) {
        this.Contents = Contents;
        this.time = time;
        this.nickname = nickname;
        this.chatImage = chatImage;
        this.chatRoom = chatRoom;
    }
}
