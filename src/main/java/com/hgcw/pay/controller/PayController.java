package com.hgcw.pay.controller;

import com.hgcw.pay.service.imp.IPsyServiceImpl;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hgcw
 * @date 2021/4/6 14:57
 */
@Slf4j
@RestController
@RequestMapping("/pay")
public class PayController {
    @Autowired
    private IPsyServiceImpl iPsyService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("amount") BigDecimal amount,
                               @RequestParam("payType") BestPayTypeEnum bestPayTypeEnum) {
        PayResponse response = iPsyService.create(orderId, amount, bestPayTypeEnum);
        //支付方式不同，渲染就不同，微信使用codeUrl，支付宝使用body
        Map map = new HashMap<>();
        if (bestPayTypeEnum == BestPayTypeEnum.WXPAY_NATIVE) {
            map.put("codeUrl", response.getCodeUrl());
            return new ModelAndView("createForWxNative", map);
        } else if (bestPayTypeEnum == BestPayTypeEnum.ALIPAY_PC) {
            map.put("body", response.getBody());
            return new ModelAndView("createForAlipaypc", map);
        }
        throw new RuntimeException("暂不支持的支付方式");

    }

    @PostMapping("/notify")
    @ResponseBody
    public void notify(@RequestBody String notifyData) {
        iPsyService.asyncNotify(notifyData);

    }
}
