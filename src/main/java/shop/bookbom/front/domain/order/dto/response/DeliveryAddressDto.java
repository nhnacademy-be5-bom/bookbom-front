package shop.bookbom.front.domain.order.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeliveryAddressDto {
    private Long id;
    private String zipCode;
    private String address;
    private String detailAddress;
}
