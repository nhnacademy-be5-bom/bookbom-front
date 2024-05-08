package shop.bookbom.front.domain.member.adapter;

import shop.bookbom.front.domain.member.dto.response.MemberInfoResponse;

public interface MemberAdapter {
    /**
     * 마이페이지 회원 정보를 조회하는 메서드입니다.
     */
    MemberInfoResponse myPage();
}
