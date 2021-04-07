package com.hgcw.pay.enums;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import lombok.Data;
import lombok.Getter;

/**
 * @author hgcw
 * @date 2021/4/7 15:05
 */
@Getter
public enum PayPlatformEnum {
    //支付宝支付
    ALIPAY(1),
    //微信支付
    WX(2),
    ;
    Integer code;

    PayPlatformEnum(Integer code) {
        this.code = code;
    }
   public static PayPlatformEnum getByBestPayTypeEnum(BestPayTypeEnum bestPayTypeEnum){
        for (PayPlatformEnum payPlatformEnum : PayPlatformEnum.values()) {
            if (bestPayTypeEnum.getPlatform().name().equals(bestPayTypeEnum.name())) {
                return payPlatformEnum;
            }
        }
        throw new RuntimeException("错误的支付平台"+bestPayTypeEnum.name());
    }
}
