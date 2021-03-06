package fr.uge.reddit.config;

import fr.uge.reddit.entity.TopicSortEnum;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import  org.springframework.security.crypto.password.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;
import java.beans.PropertyEditorSupport;

@Configuration
public class AppConfig {

    @Bean
    DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.p6spy.engine.spy.P6SpyDriver");
        dataSourceBuilder.url("jdbc:p6spy:h2:tcp://localhost/~/h2DB");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
