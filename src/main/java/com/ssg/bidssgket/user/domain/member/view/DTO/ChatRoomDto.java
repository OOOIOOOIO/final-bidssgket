package com.ssg.bidssgket.user.domain.member.view.DTO;

import com.ssg.bidssgket.user.domain.member.domain.ChatContents;
import com.ssg.bidssgket.user.domain.member.domain.Member;

import java.util.List;

public class ChatRoomDto {
    private Long chatroomNo;
    private Member sender;
    private Member receiver;
    private List<ChatContents> chatContents;
}
