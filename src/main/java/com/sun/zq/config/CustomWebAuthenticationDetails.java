package com.sun.zq.config;

import lombok.Data;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author：sunzheng
 * @date 2020/3/23 23:42
 *
 */
@Data
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 1314L;

    private final String verifyCode;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        verifyCode = request.getParameter("verifyCode");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("; verifyCode: ").append(this.getVerifyCode());
        return sb.toString();
    }
}
