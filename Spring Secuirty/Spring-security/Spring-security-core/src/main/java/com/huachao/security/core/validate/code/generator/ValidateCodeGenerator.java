package com.huachao.security.core.validate.code.generator;

import com.huachao.security.core.validate.code.entities.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
