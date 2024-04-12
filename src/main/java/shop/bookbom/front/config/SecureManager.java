package shop.bookbom.front.config;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Objects;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import shop.bookbom.front.config.dto.KeystoreResponse;

@Component
public class SecureManager {

    @Value("${keymanager.url}")
    private String keyManagerUrl;

    @Value("${secure.manager.appkey}")
    private String appkey;

    @Value("${keystore}")
    private Resource keyStore;

    @Value("${keystore.password}")
    private String keyStorePassword;


    /**
     * 인증서를 통해 Secure key manager와 HTTPS 통신을 해서 값을 가져오는 메서드입니다.
     *
     * @param key Secure Key Manager에 저장한 값의 아이디
     * @return value 저장한 값
     */
    public String getValue(String key) {
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

            RestTemplate restTemplate = new RestTemplate(requestFactory);
            String url = keyManagerUrl + appkey + "/secrets/" + key;
            KeystoreResponse response = restTemplate.getForObject(url, KeystoreResponse.class);
            return Objects.requireNonNull(response).getBody().getSecret();
        } catch (KeyStoreException | UnrecoverableKeyException | CertificateException | IOException |
                 NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e); // todo 예외처리
        }
    }
}
