package shop.bookbom.front.domain.order.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PreOrderResponse {
    private Object beforeOrderResponse;
    private boolean isSuccessful;
    private String resultMessage;

    @Builder(builderMethodName = "SuccessBuilder")
    public static PreOrderResponse createSuccessResponse(Object beforeOrderResponse, boolean isSuccessful) {
        PreOrderResponse preOrderResponse = new PreOrderResponse();
        preOrderResponse.isSuccessful = isSuccessful;
        preOrderResponse.beforeOrderResponse = beforeOrderResponse;
        preOrderResponse.resultMessage = null;

        return preOrderResponse;
    }

    @Builder(builderMethodName = "FailBuilder")
    public static PreOrderResponse createFailResponse(boolean isSuccessful, String resultMessage) {
        PreOrderResponse preOrderResponse = new PreOrderResponse();
        preOrderResponse.beforeOrderResponse = null;
        preOrderResponse.isSuccessful = isSuccessful;
        preOrderResponse.resultMessage = resultMessage;

        return preOrderResponse;
    }
}
