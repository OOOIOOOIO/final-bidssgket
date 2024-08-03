package com.ssg.bidssgket.user.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class ChatImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatImageNo;

    @Column
    private String chatImage;

    @OneToOne(mappedBy = "chatImage",cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE})
    private ChatContents chatContents;

//    public static ChatImage createChatImage(ChatContents chatContents) {
//        ChatImage chatImage = new ChatImage();
//        chatImage.setChatContents(chatContents);
//        return chatImage;
//    }

    @Builder
    private ChatImage(String chatImage, ChatContents chatContents) {
        this.chatImage = chatImage;
        this.chatContents = chatContents;
    }


}
