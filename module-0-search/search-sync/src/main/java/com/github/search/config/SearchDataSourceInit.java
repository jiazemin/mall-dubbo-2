package com.github.search.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class SearchDataSourceInit {

    @Value("${search.ip-port:127.0.0.1:9200}")
    private String ipAndPort;

    @Bean
    @ConfigurationProperties(prefix = "database")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    public RestHighLevelClient search() {
        String[] ipPortArray = ipAndPort.split(",");
        int length = ipPortArray.length;
        List<HttpHost> hostList = Lists.newArrayListWithCapacity(length);
        for (String ipAndPort : ipPortArray) {
            hostList.add(HttpHost.create(ipAndPort));
        }
        return new RestHighLevelClient(RestClient.builder(hostList.toArray(new HttpHost[length])));
    }
}
