package shop.bookbom.front.domain.address.adapter;

import java.util.List;
import shop.bookbom.front.domain.address.dto.AddressResponse;
import shop.bookbom.front.domain.address.dto.request.AddressRequest;

public interface AddressAdapter {
    /**
     * 회원의 주소록을 조회하는 메서드입니다.
     *
     * @return 주소록
     */
    List<AddressResponse> getAddressBook();

    /**
     * 주소를 저장하는 메서드입니다.
     *
     * @param request 주소 저장 요청
     */
    void saveAddress(AddressRequest request);
}
