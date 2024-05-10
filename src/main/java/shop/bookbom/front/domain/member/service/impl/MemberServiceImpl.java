package shop.bookbom.front.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.member.adapter.MemberAdapter;
import shop.bookbom.front.domain.member.dto.request.WithDrawDTO;
import shop.bookbom.front.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberAdapter memberAdapter;

    @Override
    public void deleteMember(Long memberId, WithDrawDTO withDrawDTO) {
        memberAdapter.deleteMember(memberId, withDrawDTO);
    }
}
