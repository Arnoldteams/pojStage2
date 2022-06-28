package com.cskaoyan.config.realm;

import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author stone
 * @date 2022/06/28 15:59
 */
@Data
public class MarketToken extends UsernamePasswordToken {
    String type;//"admin"\"wx"

    public MarketToken(String username, String password, String type) {
        super(username, password);
        this.type = type;
    }
}
