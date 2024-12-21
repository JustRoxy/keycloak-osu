package org.keycloak.social.osu;

import org.keycloak.broker.provider.AbstractIdentityProviderFactory;
import org.keycloak.broker.social.SocialIdentityProviderFactory;
import org.keycloak.models.IdentityProviderModel;
import org.keycloak.models.KeycloakSession;

public class OsuIdentityProviderFactory extends AbstractIdentityProviderFactory<OsuIdentityProvider>
        implements SocialIdentityProviderFactory<OsuIdentityProvider> {
    public static final String PROVIDER_ID = "osu";

    @Override
    public String getName() {
        return "Osu!";
    }

    @Override
    public OsuIdentityProvider create(KeycloakSession session, IdentityProviderModel model) {
        return new OsuIdentityProvider(session, new OsuIdentityProviderConfig(model));
    }

    @Override
    public IdentityProviderModel createConfig() {
        return new OsuIdentityProviderConfig();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
