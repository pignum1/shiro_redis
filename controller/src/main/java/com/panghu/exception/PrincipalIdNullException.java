package com.panghu.exception;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.global
 * @ClassName: PrincipalIdNullException
 * @Author: wxy
 * @Description:
 * @Date: 2020/5/18 23:35
 * @Version: 1.0
 */
public class PrincipalIdNullException extends RuntimeException {
    private static final String MESSAGE = "Principal Id shouldn't be null!";

    public PrincipalIdNullException(Class clazz, String idMethodName) {
        super(clazz + " id field: " + idMethodName + ", value is null\n" + MESSAGE);
    }
}
