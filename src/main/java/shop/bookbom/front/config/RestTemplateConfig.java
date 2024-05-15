package shop.bookbom.front.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import shop.bookbom.front.common.exception.SecureManagerException;
import shop.bookbom.front.security.adapter.SecurityAdapter;
import shop.bookbom.front.security.config.JwtConfig;
import shop.bookbom.front.security.interceptor.AddJwtInterceptor;

@Configuration
public class RestTemplateConfig {
    @Value("${keystore}")
    private Resource keyStore;

    @Value("${keystore.password}")
    private String keyStorePassword;


    @Bean
    public RestTemplate restTemplateWithTLS() {
        try {
            KeyStore clientStore = KeyStore.getInstance("PKCS12");
            clientStore.load(keyStore.getInputStream(), keyStorePassword.toCharArray());
            SSLContext sslContext = SSLContextBuilder.create()
                    .setProtocol("TLS")
                    .loadKeyMaterial(clientStore, keyStorePassword.toCharArray())
                    .loadTrustMaterial(new TrustSelfSignedStrategy())
                    .build();
            SSLConnectionSocketFactory sslConnectionSocketFactory =
                    new SSLConnectionSocketFactory(sslContext);
            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .build();
            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory(httpClient);
            return new RestTemplate(requestFactory);
        } catch (KeyStoreException | KeyManagementException | NoSuchAlgorithmException | IOException |
                 CertificateException | UnrecoverableKeyException e) {
            throw new SecureManagerException();
        }
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(5L))
                .setReadTimeout(Duration.ofSeconds(5L))
                .build();
        restTemplate.setInterceptors(Collections.singletonList(addJwtInterceptor()));
        return restTemplate;
    }

    @Bean
    public RestTemplate multipartRestTemplate() {
        HttpMessageConverter<Object> jackson = new MappingJackson2HttpMessageConverter();
        HttpMessageConverter<Resource> resource = new ResourceHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        formHttpMessageConverter.addPartConverter(jackson);
        formHttpMessageConverter.addPartConverter(resource);

        return new RestTemplate(Arrays.asList(jackson, resource, formHttpMessageConverter));
    }

    @Bean
    public SecurityAdapter securityAdapter() {
        return new SecurityAdapter(new RestTemplate());
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public AddJwtInterceptor addJwtInterceptor() {
        return new AddJwtInterceptor(securityAdapter(), jwtConfig());
    }
}
