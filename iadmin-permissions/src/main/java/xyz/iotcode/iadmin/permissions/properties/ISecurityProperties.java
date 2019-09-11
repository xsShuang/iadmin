package xyz.iotcode.iadmin.permissions.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@SpringBootConfiguration
@ConfigurationProperties(prefix = "permission")
public class ISecurityProperties {

    /**
     * 免认证访问路径
     */
    private String noCheckUrl;
}