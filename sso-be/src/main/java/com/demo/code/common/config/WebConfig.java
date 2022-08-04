package com.demo.code.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  @Value("${swagger.show}")
  private Boolean swaggerShow;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    //允许全部请求跨域
    registry.addMapping("/**").allowCredentials(true).allowedHeaders("*").allowedOriginPatterns("*")
            .allowedMethods("*");
  }

  /**
   * 配置静态资源
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (swaggerShow != null && swaggerShow) {
      /*放行swagger*/
      registry.addResourceHandler("swagger-ui.html")
              .addResourceLocations("classpath:/META-INF/resources/");
      registry.addResourceHandler("/webjars/**")
              .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    super.addResourceHandlers(registry);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    List<String> excludePathPatterns = new LinkedList<>();
    if (swaggerShow != null && swaggerShow) {
      excludePathPatterns.addAll(Arrays.asList("/swagger-resources/**", "/webjars/**", "/v2/**",
              "/swagger-ui.html/**"));
    }
    super.addInterceptors(registry);
  }

  //解决中文乱码问题
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(
            converters);
    converters.add(new ByteArrayHttpMessageConverter());
    converters.add(new ResourceHttpMessageConverter());
    converters.add(new AllEncompassingFormHttpMessageConverter());
    //解决中文乱码
    converters.add(responseBodyConverter());
    //解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错 500：no convertter for return value of type
    converters.add(messageConverter());
  }

  @Bean
  public HttpMessageConverter<String> responseBodyConverter() {
    StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
    return converter;
  }

  @Bean
  public MappingJackson2HttpMessageConverter messageConverter() {
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
    ObjectMapper mapper = getObjectMapper();
    converter.setObjectMapper(mapper);
    return converter;
  }

  @Bean
  public ObjectMapper getObjectMapper() {
    return new ObjectMapper();
  }

}
