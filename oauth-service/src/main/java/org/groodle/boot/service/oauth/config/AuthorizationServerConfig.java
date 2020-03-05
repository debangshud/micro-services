package org.groodle.boot.service.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;


@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients
                .inMemory()
                .withClient("client-with-registered-redirect")
                .authorizedGrantTypes("authorization_code")
//                .authorities("ROLE_CLIENT")
                .scopes("read", "trust")
//                .resourceIds(RESOURCE_ID)
                .redirectUris("http://anywhere?key=value")
                .secret("secret123")
                .and()
                .withClient("guide_portal")
                .secret("guide_portal")
                .scopes(AuthorizationScope.READ, AuthorizationScope.TRUST)
                .authorizedGrantTypes("authorization_code")
                .authorities("ROLE_CLIENT")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600)
                .and()
                .withClient("salesforce")
                .secret("salesforce")
                .scopes(
                        AuthorizationScope.READ,
                        AuthorizationScope.WRITE)
                .authorizedGrantTypes(
                        "authorization_code",
                        "password",
                        "refresh_token",
                        "implicit",
                        "client_credential")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(3600);
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}
