package shop.bookbom.front.domain.order.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WrapperSelectResponse {
    private int totalOrderCount;
    private List<WrapperSelectBookResponse> wrapperSelectResponseList;
    private List<String> estimatedDateList;
    private int deliveryCost;
    private int wrapCost;

    @Builder
    public WrapperSelectResponse(int totalOrderCount, List<WrapperSelectBookResponse> wrapperSelectResponseList,
                                 List<String> estimatedDateList, int deliveryCost, int wrapCost) {
        this.totalOrderCount = totalOrderCount;
        this.wrapperSelectResponseList = wrapperSelectResponseList;
        this.estimatedDateList = estimatedDateList;
        this.deliveryCost = deliveryCost;
        this.wrapCost = wrapCost;
    }


    public static String convertWrapperSelectListToString(List<WrapperSelectBookResponse> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (WrapperSelectBookResponse wrapperSelectBookResponse : list) {
            sb.append(wrapperSelectBookResponse.getBookId()).append(",")
                    .append(wrapperSelectBookResponse.getBookTitle()).append(",")
                    .append(wrapperSelectBookResponse.getImgUrl()).append(",")
                    .append(wrapperSelectBookResponse.getWrapperName()).append(",")
                    .append(wrapperSelectBookResponse.getQuantity()).append(",")
                    .append(wrapperSelectBookResponse.getCost()).append(",")
                    .append(wrapperSelectBookResponse.getDiscountCost()).append(",");
        }
        // 마지막 콤마 제거
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String convertEstimatedDateListToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String estimatedDate : list) {
            sb.append(estimatedDate).append(",");
        }
        // 마지막 콤마 제거
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // 콤마로 구분된 문자열을 리스트로 변환하는 메서드
    public static List<WrapperSelectBookResponse> convertStringToWrapperSelectList(String str) {
        List<WrapperSelectBookResponse> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] tokens = str.split(",");
            for (int i = 0; i < tokens.length; i += 7) {
                if (i + 6 < tokens.length) {
                    WrapperSelectBookResponse wrapperSelectBookResponse = WrapperSelectBookResponse.builder()
                            .bookId(Long.valueOf(tokens[i]))
                            .bookTitle(tokens[i + 1])
                            .imgUrl(tokens[i + 2])
                            .wrapperName(tokens[i + 3])
                            .quantity(Integer.parseInt(tokens[i + 4]))
                            .cost(Integer.parseInt(tokens[i + 5]))
                            .discountCost(Integer.parseInt(tokens[i + 6]))
                            .build();

                    list.add(wrapperSelectBookResponse);
                }
            }
        }
        return list;
    }

    public static List<String> convertStringToEsitmatedDateList(String str) {
        List<String> list = new ArrayList<>();
        if (str != null && !str.isEmpty()) {
            String[] tokens = str.split(",");
            for (int i = 0; i < tokens.length; i++) {
                list.add(tokens[i]);
            }
        }
        return list;
    }
}
