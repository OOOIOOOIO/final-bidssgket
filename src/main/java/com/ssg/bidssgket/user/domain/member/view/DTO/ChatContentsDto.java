package com.ssg.bidssgket.user.domain.member.view.DTO;

import com.ssg.bidssgket.user.domain.member.domain.ChatImage;
import com.ssg.bidssgket.user.domain.member.domain.ChatRoom;
import com.ssg.bidssgket.user.domain.member.domain.Member;

import java.sql.Time;

public class ChatContentsDto {
    private Long chatContentsNo;
    private String Contents;
    private Time time;
    private Member nickname;
    private ChatImage chatImage;
    private ChatRoom chatRoom;
}
