package shop.bookbom.front.domain.order.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import shop.bookbom.front.domain.order.dto.request.BeforeOrderRequest;
import shop.bookbom.front.domain.order.dto.request.WrapperSelectBookRequest;
import shop.bookbom.front.domain.payment.dto.PaymentRequest;

public class OrderUtil {
    // BeforeOrderRequest 객체 리스트를 문자열로 변환하는 메서드
    public static String convertBeforeOrderListToString(List<BeforeOrderRequest> requestList) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(requestList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 문자열을 BeforeOrderRequest 객체 리스트로 변환하는 메서드
    public static List<BeforeOrderRequest> convertStringToBeforeOrderList(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString,
                    mapper.getTypeFactory().constructCollectionType(List.class, BeforeOrderRequest.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // WrapperSelectBookRequest 객체 리스트를 문자열로 변환하는 메서드
    public static String convertWrapperSelectListToString(List<WrapperSelectBookRequest> requestList) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(requestList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 문자열을 WrapperSelectBookRequest 객체 리스트로 변환하는 메서드
    public static List<WrapperSelectBookRequest> convertStringToWrapperSelectList(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString,
                    mapper.getTypeFactory().constructCollectionType(List.class, WrapperSelectBookRequest.class));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // PaymentRequest 객체를 JSON 문자열로 변환하는 메서드
    public static String convertPaymentRequestToString(PaymentRequest request) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // JSON 문자열을 PaymentRequest 객체로 변환하는 메서드
    public static PaymentRequest convertStringToPaymentRequest(String jsonString) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(jsonString, PaymentRequest.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
