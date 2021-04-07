package com.hgcw.pay.service;

import com.hgcw.pay.PayApplication;
import com.hgcw.pay.PayApplicationTests;
import com.hgcw.pay.service.imp.IPsyServiceImpl;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author hgcw
 * @date 2021/4/6 14:05
 */
public class IPsyServiceTest extends PayApplicationTests {
 @Autowired
    private  IPsyServiceImpl iPsyService;

    @Test
    public void create() {
        iPsyService.create("123456789123456", BigDecimal.valueOf(0.01), BestPayTypeEnum.ALIPAY_PC);
    }
}