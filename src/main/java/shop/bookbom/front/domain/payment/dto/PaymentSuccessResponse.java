package shop.bookbom.front.domain.payment.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PaymentSuccessResponse {
    private String orderNumber;
    private String orderInfo;
    private Integer totalCount;
    private List<OrderBookInfoDto> orderBookInfoDtoList;
    private Integer totalAmount;
    private String paymentMethodName;
    private String deliveryName;
    private String deliveryPhoneNumber;
    private String zipCode;
    private String deliveryAddress;
    private String addressDetail;

    public static String convertOrderBookInfoDtoListToString(List<OrderBookInfoDto> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (OrderBookInfoDto bookInfoDto : list) {
            sb.append(bookInfoDto.getTitle()).append(",")
                    .append(bookInfoDto.getImgUrl()).append(",")
                    .append(bookInfoDto.getCost()).append(",")
                    .append(bookInfoDto.getQuantity()).append(",");
        }
        // 마지막 콤마 제거
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 콤마로 구분된 문자열을 리스트로 변환하는 메서드
    public static List<OrderBookInfoDto> convertStringToOrderBookInfoDtoList(String str) {
        List<OrderBookInfoDto> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] tokens = str.split(",");
            for (int i = 0; i < tokens.length; i += 4) {
                if (i + 3 < tokens.length) {
                    OrderBookInfoDto bookInfoDto = new OrderBookInfoDto();
                    bookInfoDto.setTitle(tokens[i]);
                    bookInfoDto.setImgUrl(tokens[i + 1]);
                    bookInfoDto.setCost(Integer.parseInt(tokens[i + 2]));
                    bookInfoDto.setQuantity(Integer.parseInt(tokens[i + 3]));
                    list.add(bookInfoDto);
                }
            }
        }
        return list;
    }
}
