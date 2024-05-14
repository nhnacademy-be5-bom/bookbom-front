package shop.bookbom.front.domain.address.service;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.bookbom.front.domain.address.adapter.AddressAdapter;
import shop.bookbom.front.domain.address.dto.AddressResponse;
import shop.bookbom.front.domain.address.dto.request.AddressRequest;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressAdapter addressAdapter;

    @Override
    public List<AddressResponse> getAddressBook() {
        List<AddressResponse> addresses = addressAdapter.getAddressBook();
        Collections.sort(addresses);
        return addresses;
    }

    @Override
    public void saveAddress(AddressRequest request) {
        addressAdapter.saveAddress(request);
    }
}
