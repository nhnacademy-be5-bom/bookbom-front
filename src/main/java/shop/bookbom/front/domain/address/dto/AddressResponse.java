package shop.bookbom.front.domain.address.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressResponse implements Comparable<AddressResponse> {
    private Long id;
    private String nickname;
    private String zipCode;
    private String address;
    private String addressDetail;
    private boolean defaultAddress;

    @Override
    public int compareTo(AddressResponse other) {
        if (this.defaultAddress && !other.defaultAddress) {
            return -1;
        } else if (!this.defaultAddress && other.defaultAddress) {
            return 1;
        }
        return Long.compare(this.id, other.id);
    }
}
