package shop.bookbom.front.domain.address.adapter;

import java.util.List;
import shop.bookbom.front.domain.address.dto.AddressResponse;

public interface AddressAdapter {
    /**
     * 회원의 주소록을 조회하는 메서드입니다.
     *
     * @return 주소록
     */
    List<AddressResponse> getAddressBook();
}
