package com.panghu.config;

import com.panghu.global.MyByteSource;
import com.panghu.global.SerializeUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ProjectName: nacos_base4j
 * @Package: com.panghu.config
 * @ClassName: RedisConfig
 * @Author: wxy
 * @Description: redis配置类
 * @Date: 2020/5/18 10:35
 * @Version: 1.0
 */

@Configuration
@EnableCaching
@Data
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * spring管理自动注入  redisTemplate <String ,Object>
     *
     * @param factory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        // 1.创建 redisTemplate 模版
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 2.关联 redisConnectionFactory
        template.setConnectionFactory(factory);
        // 3.创建 自定义序列化类

//        FastJsonRedisSerializer myRedisSerializer = new FastJsonRedisSerializer(Object.class);
        // 7.设置 value 的转化格式和 key 的转化格式 默认使用的是JdkSerializationRedisSerializer
//        template.setValueSerializer(myRedisSerializer);
//        template.setHashValueSerializer(myRedisSerializer);
//        template.setDefaultSerializer(myRedisSerializer);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(new SerializeUtils());
        template.setHashValueSerializer(new SerializeUtils());
        // 设置键（key）的序列化采用StringRedisSerializer。
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }

    /**
     * spring管理自动注入  redisTemplate <String ,String>
     *
     * @param factory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public RedisTemplate<String, String> stringRedisTemplate(RedisConnectionFactory factory) {
        return new StringRedisTemplate(factory);
    }

// ################################jedis连接池配置###################################
    /**
     * redis地址
     */
    @Value("${spring.redis.host}")
    private String host;
    /**
     * redis端口号
     */
    @Value("${spring.redis.port}")
    private Integer port;
//    /**
//     * redis密码
//     */
//    @Value("${spring.redis.password}")
//    private String password;
//

    /**
     * JedisPoolConfig 连接池
     *
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(300);
        //连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(1000);
        //最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(1000);
        //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(10);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
        //是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(true);
        //在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }

//    /**
//     * 配置工厂
//     *
//     * @param jedisPoolConfig
//     * @return
//     */
//    @Bean
//    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        //连接池
//        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
//        //IP地址
//        jedisConnectionFactory.setHostName(host);
//        //端口号
//        jedisConnectionFactory.setPort(port);
//        //如果Redis设置有密码
////        jedisConnectionFactory.setPassword(password);
//        //客户端超时时间单位是毫秒
//        jedisConnectionFactory.setTimeout(5000);
//        return jedisConnectionFactory;
//    }
}
