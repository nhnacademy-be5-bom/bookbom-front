package shop.bookbom.front.domain.member.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.member.adapter.MemberAdapter;
import shop.bookbom.front.domain.member.service.MemberService;
import shop.bookbom.front.domain.user.dto.response.UserInfoResponse;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberAdapter memberAdapter;

    @Override
    public UserInfoResponse getMemberInfo() {
        return memberAdapter.getMemberInfo();
    }
}
