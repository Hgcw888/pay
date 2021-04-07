package com.hgcw.pay.service;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author hgcw
 * @date 2021/4/6 13:39 支付几口
 */
@Service
public interface IPsyService {
    /**
     * 支付
     * @param orderId
     * @param amount
     * @return
     */
    PayResponse create(String orderId, BigDecimal amount, BestPayTypeEnum bestPayTypeEnum);
    /**
     * 异步回调
     */
    String asyncNotify(String notifyData);
}
