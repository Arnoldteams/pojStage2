package com.cskaoyan.mall.commons.result;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {

    private static final long serialVersionUID = 1717442845820713651L;
    // 参数校验
    public abstract void requestCheck();

    @Override
    public String toString() {
        return "AbstractRequest{}";
    }
}
