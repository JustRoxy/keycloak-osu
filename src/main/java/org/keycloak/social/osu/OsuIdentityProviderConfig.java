package org.keycloak.social.osu;

import org.keycloak.broker.oidc.OAuth2IdentityProviderConfig;
import org.keycloak.models.IdentityProviderModel;

public class OsuIdentityProviderConfig extends OAuth2IdentityProviderConfig {
    public OsuIdentityProviderConfig(IdentityProviderModel model) {
        super(model);
    }

    public OsuIdentityProviderConfig() {
    }
}
