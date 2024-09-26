package edu.zafu.user_center_backend.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 配置
 *
 * @author ColaBlack
 */
@Configuration
public class MybatisConfigurationCustomizer {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        //下划线转驼峰
        return configuration -> configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
    }
}