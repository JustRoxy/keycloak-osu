package org.keycloak.social.osu;

import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;

public class OsuUserAttributeMapper extends AbstractJsonUserAttributeMapper {
    private static final String[] compatibleProviders = new String[]{OsuIdentityProviderFactory.PROVIDER_ID};
    @Override
    public String[] getCompatibleProviders() {
        return compatibleProviders;
    }

    @Override
    public String getId() {
        return "osu-user-attribute-mapper";
    }
}
