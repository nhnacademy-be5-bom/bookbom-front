package shop.bookbom.front.domain.signup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.signup.adapter.SignUpAdapter;
import shop.bookbom.front.domain.signup.dto.SignUpDto;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SignUpAdapter signUpAdapter;

    public Long register(SignUpDto signUpDto) {
        return signUpAdapter.doRegister(signUpDto);
    }
}
