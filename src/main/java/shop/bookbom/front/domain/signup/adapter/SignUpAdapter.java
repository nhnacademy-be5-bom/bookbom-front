package shop.bookbom.front.domain.signup.adapter;

import shop.bookbom.front.domain.signup.dto.SignUpDto;

public interface SignUpAdapter {
    Long doRegister(SignUpDto signUpDto);
}
