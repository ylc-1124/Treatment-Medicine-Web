package edu.sust.alipay.query;

import lombok.Data;

/**
 * 路参的封装类
 */
@Data
public class AliPay {
    private String traceNo;  //订单号 数据库中的orderNo字段
    private double totalAmount;  //总金额
    private String subject;  //订单名称
    private String alipayTraceNo;  //支付宝生成的订单号
}