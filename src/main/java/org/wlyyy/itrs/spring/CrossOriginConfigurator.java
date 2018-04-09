package org.wlyyy.itrs.spring;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域配置类
 */
@Configuration
public class CrossOriginConfigurator extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(CrossOriginConfigurator.class);

    @Autowired
    private Environment env;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        final String domains = env.getProperty("web.crossorigin.domains");

        if (StringUtils.isNotEmpty(domains)) {
            LOG.info("Read web.crossorigin.domains [{}]", domains);
            final CorsRegistration corsRegistration = registry.addMapping("/**")
                    .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowCredentials(false).maxAge(3600);
            final String[] split = domains.split(",");
            corsRegistration.allowedOrigins(split);

        } else {
            LOG.info("Empty web.crossorigin.domains, no CORS configured.");
        }
    }
}