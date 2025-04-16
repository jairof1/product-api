package com.dislicores.api.b2c.app.product.config;

import com.dislicores.api.b2c.app.product.config.elastic.ElasticConverterB2CNew;
import com.dislicores.api.b2c.app.product.config.elastic.ElasticConverterB2C;
import com.dislicores.api.b2c.app.product.util.UtilProperties;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails.Node.Protocol;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.GZIPInputStream;

@Configuration
@RequiredArgsConstructor
public class ElasticConfig {
    private final UtilProperties properties;

    @Bean
    public RestClient getRestClient() {
        UtilProperties.ElasticSearch prop = this.properties.getElasticSearch();
        String url = prop.getUrl();
        String user = prop.getUser();
        String password = prop.getPassword();
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
        BasicHeader contentTypeHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpHost host = HttpHost.create("%s://%s".formatted(Protocol.HTTPS, url));
        HttpClientConfigCallback callback = client -> client
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultHeaders(List.of(contentTypeHeader))
                .addInterceptorLast(elasticResponseInterceptor())
                .addInterceptorLast(elasticRequestInterceptor());
        return RestClient.builder(host).setCompressionEnabled(true).setHttpClientConfigCallback(callback).build();
    }

    @Bean
    public ElasticsearchCustomConversions customConversions() {
        return new ElasticsearchCustomConversions(List.of(
                new ElasticConverterB2CNew(),
                new ElasticConverterB2C()
        ));
    }

    private static HttpResponseInterceptor elasticResponseInterceptor() {
        return (response, context) -> {
            response.addHeader("X-Elastic-Product", "Elasticsearch");
            response.addHeader("Content-Type", "application/vnd.elasticsearch+json;compatible-with=7");
            response.addHeader("Accept", "application/vnd.elasticsearch+json;compatible-with=7");
        };
    }

    private static HttpRequestInterceptor elasticRequestInterceptor() {
        return (request, context) -> {
            if (!(request instanceof HttpEntityEnclosingRequest)) return;
            HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
            if (entity == null || !entity.isRepeatable()) return;
            try {
                InputStream inputStream = entity.getContent();
                String content;
                boolean isGzip = "gzip".equals(entity.getContentEncoding().getValue());
                if (!isGzip) content = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
                else try (GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream)) {
                    content = StreamUtils.copyToString(gzipInputStream, StandardCharsets.UTF_8);
                }
                System.out.println(content);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
