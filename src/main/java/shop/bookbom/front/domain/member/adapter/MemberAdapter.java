package shop.bookbom.front.domain.member.adapter;

import shop.bookbom.front.domain.member.dto.request.WithDrawDTO;

public interface MemberAdapter {

    void deleteMember(Long memberId, WithDrawDTO withDrawDTO);
}
