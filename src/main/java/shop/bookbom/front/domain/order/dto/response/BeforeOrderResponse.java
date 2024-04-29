package shop.bookbom.front.domain.order.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.bookbom.front.domain.order.dto.request.WrapperDto;

@Getter
@NoArgsConstructor
public class BeforeOrderResponse {
    private int totalOrderCount;
    private List<BeforeOrderBookResponse> beforeOrderBookResponseList;
    private List<WrapperDto> wrapperList;

    // WrapperDto 목록을 콤마로 구분된 문자열로 변환
    public static String convertWrapperListToString(List<WrapperDto> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (WrapperDto wrapper : list) {
            sb.append(wrapper.getId()).append(",")
                    .append(wrapper.getName()).append(",")
                    .append(wrapper.getCost()).append(",")
                    .append(wrapper.getImgUrl()).append(",");
        }
        // 마지막 콤마 제거
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // BeforeOrderBookResponse 목록을 콤마로 구분된 문자열로 변환
    public static String convertBookListToString(List<BeforeOrderBookResponse> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (BeforeOrderBookResponse book : list) {
            sb.append(book.getBookId()).append(",")
                    .append(book.getImageUrl()).append(",")
                    .append(book.getTitle()).append(",")
                    .append(book.getQuantity()).append(",")
                    .append(book.getCost()).append(",")
                    .append(book.getDiscountCost()).append(",");
        }
        // 마지막 콤마 제거
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 콤마로 구분된 문자열을 리스트로 변환하는 메서드
    public static List<WrapperDto> convertStringToWrapperList(String str) {
        List<WrapperDto> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] tokens = str.split(",");
            for (int i = 0; i < tokens.length; i += 4) {
                if (i + 3 < tokens.length) {
                    WrapperDto wrapper = new WrapperDto();
                    wrapper.setId(Long.valueOf(tokens[i]));
                    wrapper.setName(tokens[i + 1]);
                    wrapper.setCost(Integer.parseInt(tokens[i + 2]));
                    wrapper.setImgUrl(tokens[i + 3]);
                    list.add(wrapper);
                }
            }
        }
        return list;
    }

    public static List<BeforeOrderBookResponse> convertStringToBookList(String str) {
        List<BeforeOrderBookResponse> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] tokens = str.split(",");
            for (int i = 0; i < tokens.length; i += 6) {
                if (i + 5 < tokens.length) {
                    BeforeOrderBookResponse beforeOrderBookResponse = new BeforeOrderBookResponse();
                    beforeOrderBookResponse.setBookId(Long.valueOf(tokens[i]));
                    beforeOrderBookResponse.setImageUrl(tokens[i + 1]);
                    beforeOrderBookResponse.setTitle(tokens[i + 2]);
                    beforeOrderBookResponse.setQuantity(Integer.parseInt(tokens[i + 3]));
                    beforeOrderBookResponse.setCost(Integer.parseInt(tokens[i + 4]));
                    beforeOrderBookResponse.setDiscountCost(Integer.parseInt(tokens[i + 5]));
                    list.add(beforeOrderBookResponse);
                }
            }
        }
        return list;
    }
}
