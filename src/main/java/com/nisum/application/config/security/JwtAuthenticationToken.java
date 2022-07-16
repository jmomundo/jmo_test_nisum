package com.nisum.application.config.security;

import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final transient Object principal;
    private final transient Object credentials;

    /**
     * Creates a token with the supplied of authorities.
     *
     * @param principal the claims for the token
     */
    public JwtAuthenticationToken(Object principal) {
        super(List.of());
        this.principal = principal;
        this.credentials = null;
        setAuthenticated(false);
    }

    public static JwtAuthenticationToken fromContext(Object principal) {
        return new JwtAuthenticationToken(principal);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
