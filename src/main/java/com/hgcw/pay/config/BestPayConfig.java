package com.hgcw.pay.config;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.BestPayService;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author hgcw
 * @date 2021/4/6 18:03
 * 签名校验
 */
@Component
public class BestPayConfig {
    @Bean
    public BestPayService bestPayService() {
        /**
         * 微信
         */
        WxPayConfig wxPayConfig = new WxPayConfig();
        //公众账号qppid
        wxPayConfig.setAppId("wxd898fcb01713c658");
        //商户id
        wxPayConfig.setMchId("1483469312");
        //商户密钥
        wxPayConfig.setMchKey("098F6BCD4621D373CADE4E832627B4F6");
        //接受异步平台的地址
        wxPayConfig.setNotifyUrl("http://weiwei-project.natapp1.cc/pay/create/notify");
        /**
         * 支付宝
         */
        AliPayConfig aliPayConfig = new AliPayConfig();
        //应用id
        aliPayConfig.setAppId("2018062960540016");
        //应用密钥
        aliPayConfig.setPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCC/tk1oqymli5HpP9MxOkNofKkON1a6EqKg/S0++mZoKLInLasLkNuz0F8nSg/nBZGMv8XcIOXeOsK1ZT9tibxlgzun22lnBpHLueCAZlqITx33XO8SwiVULar2NIVFJwuUdvHPf/2Wj361cTd+zw+uJTPy3vIBljeOHDajzqBg9m5JrCIOgUze+4MZBT5/74gwwwpMDhRHRfR+4FTFmxqqzbTBGaFv4gCLSRB/3VpuruAP5AKCTR2ATHXgFO9veCemNwx5sI3F9Tw43+16xzAZh2T/6Xb3d31H9k31h9Iai7t2s00qY6eKsFpdi4ySK5qB2G9lbFvbpBv+3gz/5UFAgMBAAECggEANWn7Yc5yPf8w4c/atg/4w7FMgkAZqm7brj1+M6ogp58DoW+dYPoATxe9qTVf6wgXYQp8T692SPZqSOGsPgPfP1Ui8s4ZZJURdgMfLTi/uLHSuUGYZqfl2RSvV8UybJwZzx1b05NpYqeLbFNsUeOWvjB/pXdggsBrzjkPc19ByPtERzjiEFzvxGdYPDdvgNUmvbQj647X8umdyDAcNAbd8rkSQW87zBT12OQ/i/QR74BBg4VBJqTg9APk9mdVNufvxMwi9+lyGsGRFdnjmPCLHJvnT3Nh9KSHWuLrOeUjkGVEozd40iz/8LVwr+BZNeL6EjtZ3ztfphnIl92lMFNhQQKBgQDlm0t5N0REM0al/L0BygkwLsHHvAmjYhzjDJeooLgH0NyYYP/ruqqF6oPxDDaw17pciEypyOdoQ1JFaw9Uq41PfZlqL74PehSD3KWe7pPzlWIukaDq1aPit/pox92+ZO0lqwHQa80Tf1PO55xAVJQPltrhScnAmZiVIG1TD9z+sQKBgQCSDbGW2lVx5b9s1R0l8SRvoHQc83iIIyqx/+UJEEwxEMwSJ4ygAfUPcrJOYs2gOB9Ny08xqtNEQKlghwgwFqIec705AiU67Bw3TjWHnxgzvhzLPCrty/yJbWMmpXDjMm/VvwASkBL6pijjh/GUrRsJvh0McgWkDIg8OgIRu5rYlQKBgQChJ+nGb7sTn2XT9Vv4GIVwczEB7wJY0fFyj5EXA4+HtNpQfazDGOa9TchD9Q2h/BjK/8PHW8LIVJA3NxiwliR+CasXc+ET3dzuXH1G2y+vRUd/ZimrCj6YUAeLadVC4HXu/WMtlayAJdt+GuR55qNxebGxdOgNrgEBkpwJM9YqUQKBgCvFcNit/HeTDEiYohCx8WKG6uWWTiQ2reAEueZ6fOsjhpVWRv3ZOFF15Vw6njeLOk59RPG0qXZGDr0AGwMWdWW8+BOyweejxV0J0l8f3gf7zPNXx+HWhYvGPbXiVS+x+PRNNr9ZcGawD4cJQex16KmF0XzeWzRsERRDqkUYPXL1AoGBAJVzao5Irdt0wj/R8NG3uVeEMbESNLKNLZ7M9hPaZn2xacoPHXXLKg2m546RYNOngYLB5l9dbpake5BoXtuGylmtD1tZvGSxLmznfrxQpZr0vuR2iCIgsVGdPhJlIRbKyeocofsRdaXgD413O4MCi/WmJ4E1ydyOqv+2cCQQAJBB");
        //支付宝公钥
        aliPayConfig.setAliPayPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtojdtkETo4OEsQLeyyPwtWK9ZqYJANq6jjXC74vk9n/r88yW577y7VdxcK9X/F/wvR7D8of7lndYdhg6xZro0eO2skPZTU+A549J7tfzahVbIBAS+x1WPFJwPtVrfBBvkwHL8PT+YnMcxKyBxOa6wo8fzJs1NgU1+qnDCpwUFyv59GUfdzBvTPL1fY3ZzvRHFHbapevVltbO/jNV0thb8dafmcJXl8lnjQy3XlH3eTH28tlVfqickacfRl/WSD8WN3dGgF7dTDKYfSR7YB7jsHe6VzoHM3UnD9/yQbi/Z3ZrL7yOxEjq4tfrKlZIW7ZCoUpOU4QdPIRhLeC6nWyGrQIDAQAB");
        //异步回调地址
        aliPayConfig.setNotifyUrl("http://weiwei-project.natapp1.cc/pay/create/notify");
        //支付完成跳转地址
        aliPayConfig.setReturnUrl("https://open.alipay.com/platform/developerIndex.htm");

        /**
         * 签名校验
         */
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        //校验微信
        bestPayService.setWxPayConfig(wxPayConfig);
        //校验支付宝
        bestPayService.setAliPayConfig(aliPayConfig);
        return bestPayService;
    }
}
