package com.hgcw.pay.service.imp;

import com.hgcw.pay.config.BestPayConfig;
import com.hgcw.pay.dao.PayInfoMapper;
import com.hgcw.pay.enums.PayPlatformEnum;
import com.hgcw.pay.pojo.PayInfo;
import com.hgcw.pay.service.IPsyService;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.enums.BestPayPlatformEnum;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.enums.OrderStatusEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author hgcw
 * @date 2021/4/6 13:40
 */
@Slf4j
@Service
public class IPsyServiceImpl implements IPsyService {
    @Autowired
    private BestPayService bestPayService;
    @Autowired
    private PayInfoMapper payInfoMapper;

    /**
     * 发起支付
     *
     * @param orderId
     * @param amount
     */
    @Override
    public PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum) {
        /**
         * 订单写入数据库
         */
        PayInfo payInfo = new PayInfo(Long.parseLong(orderId), PayPlatformEnum.getByBestPayTypeEnum(bestPayTypeEnum).getCode(),
                OrderStatusEnum.NOTPAY.name(), amount);

        payInfoMapper.insertSelective(payInfo);


        /**
         * 支付请求
         */
        PayRequest request = new PayRequest();
        //支付名称
        request.setOrderName("8165592-微信支付sdk");
        //支付id(订单号)
        request.setOrderId(orderId);
        //支付金额
        request.setOrderAmount(amount.doubleValue());
        //支付方式
        request.setPayTypeEnum(bestPayTypeEnum);
        //发起支付(校验成功后)
        PayResponse response = bestPayService.pay(request);
        log.info("response={}", response);
        return response;
    }

    /**
     * 异步回调
     *
     * @param notifyData
     */
    @Override
    public String asyncNotify(String notifyData) {
        /**
         * 调用sdk的asyncNotify方法
         * 签名验证、支付成功后回调
         */
        PayResponse response = bestPayService.asyncNotify(notifyData);
        log.info("response={}", response);

        /**
         * 金额校验（丛数据库擦寻订单）
         */
        PayInfo payInfo = payInfoMapper.selectByOrderNo(Long.parseLong(response.getOrderId()));
        if (payInfo == null) {
            throw new RuntimeException("通过orderNo查询到的结果是null");
        }
        if (!payInfo.getPlatformStatus().equals(OrderStatusEnum.SUCCESS.name())) {
            //compareTo -1(小于) 0（等于） 1（大于）
            if (payInfo.getPayAmount().compareTo(BigDecimal.valueOf(response.getOrderAmount()))!=0) {
                throw new RuntimeException("异步通知中的金额和数据库中的金额不一致，orderAmount={}"+response.getOrderId());
            }

            /**
             * 修改订单支付状态
             */
            payInfo.setPlatformStatus(OrderStatusEnum.SUCCESS.name());
            payInfoMapper.updateByPrimaryKeySelective(payInfo);
        }

        /**
         * 不要重复请求
         */
        if (response.getPayPlatformEnum() == BestPayPlatformEnum.WX) {
            return "<xml>\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                    "</xml>";
        } else if (response.getPayPlatformEnum() == BestPayPlatformEnum.ALIPAY) {
            return "success";
        }
        throw new RuntimeException("异步通知错误的平台");
    }
}
