package shop.bookbom.front.domain.address.service;

import java.util.List;
import shop.bookbom.front.domain.address.dto.AddressResponse;

public interface AddressService {
    List<AddressResponse> getAddressBook();
}
