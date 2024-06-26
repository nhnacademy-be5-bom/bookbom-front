package shop.bookbom.front.domain.member.service;

import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;

public interface MemberService {

    /**
     * 마이페이지 회원 정보를 조회하는 메서드입니다.
     */
    UserInfoResponse getMemberInfo();

}
