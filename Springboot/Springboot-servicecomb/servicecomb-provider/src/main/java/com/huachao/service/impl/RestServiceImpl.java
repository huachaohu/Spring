package com.huachao.service.impl;

import com.huachao.service.RestService;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "hello")
@RequestMapping("/hello")
public class RestServiceImpl implements RestService {
    //@Override
    public String sayHello(String name) {
        return "Hello World!"+name;
    }
}
