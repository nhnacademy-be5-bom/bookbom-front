package shop.bookbom.front.domain.member.service;

import shop.bookbom.front.domain.member.dto.request.WithDrawDTO;

public interface MemberService {

    void deleteMember(Long memberId, WithDrawDTO withDrawDTO);
}
