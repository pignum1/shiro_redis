package com.panghu.global;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.reids
 * @ClassName: FastJsonRedisSerializer
 * @Author: wxy
 * @Description: fastjson序列化
 * @Date: 2020/5/18 14:29
 * @Version: 1.0
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final static ParserConfig defaultRedisConfig=new ParserConfig();

    static{
        //打开autotype功能,需要强转的类一次添加其后
        ParserConfig.getGlobalInstance()
                .addAccept("org.apache.shiro.authc");
        ParserConfig.getGlobalInstance()
                .addAccept("com.panghu.entity");
        ParserConfig.getGlobalInstance()
                .addAccept("com.panghu.redis");
        ParserConfig.getGlobalInstance()
                .addAccept("com.panghu.shiro");
    }

    private Class<T> clazz;

    static {
        defaultRedisConfig.setAutoTypeSupport(true);
        defaultRedisConfig.addAccept("org.apache.shiro.authc.SimpleAuthenticationInfo");
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Nullable
    @Override
    public byte[] serialize(@Nullable T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }

        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Nullable
    @Override
    public T deserialize(@Nullable byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz);
    }
}
