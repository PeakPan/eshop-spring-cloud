package com.peak.eshop.product.uitl;

public class JsonBuildUtils {

    public static String buildRabbitmqMsg(String eventType, String dateType, Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"event_type\":").append("\"").append(eventType).append("\"").append(",");
        sb.append("\"data_type\":").append("\"").append(dateType).append("\"").append(",");
        sb.append("\"id\":").append(id).append("}");
        return sb.toString();

    }
}
