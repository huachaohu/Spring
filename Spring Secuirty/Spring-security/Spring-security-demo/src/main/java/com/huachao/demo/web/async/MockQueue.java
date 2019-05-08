package com.huachao.demo.web.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockQueue {
    private String placeOrder;

    private String completeOrder;

    public void setPlaceOrder(String placeOrder) {
        new Thread(() -> {
            log.info("接到下单请求, " + placeOrder);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕," + placeOrder);

        }).start();
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }

    public String getPlaceOrder() {
        return placeOrder;
    }
    public String getCompleteOrder() {
        return completeOrder;
    }
}
