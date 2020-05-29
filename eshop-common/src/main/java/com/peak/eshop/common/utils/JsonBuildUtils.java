package com.peak.eshop.common.utils;



public class JsonBuildUtils {

    public static String buildRabbitmqMsg(String eventType, String dateType, Long id) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"event_type\":").append("\"").append(eventType).append("\"").append(",");
        sb.append("\"data_type\":").append("\"").append(dateType).append("\"").append(",");
        sb.append("\"id\":").append(eventType).append("}");
        return sb.toString();

    }
}
