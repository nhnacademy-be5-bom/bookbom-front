package shop.bookbom.front.domain.order.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class OpenOrderRequest {
    private List<WrapperSelectBookRequest> wrapperSelectRequestList;
    private String name;
    private String phoneNumber;
    private Integer totalCost;
    private Integer discountCost;
    private String email;
    private String password;
    private String estimatedDateTostring;
    private Integer deliveryCost;
    private String zipCode;
    private String deliveryAddress;
    private String addressDetail;

}
