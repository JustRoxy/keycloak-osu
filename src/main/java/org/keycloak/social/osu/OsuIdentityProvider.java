package org.keycloak.social.osu;


import com.fasterxml.jackson.databind.JsonNode;
import org.jboss.logging.Logger;
import org.keycloak.broker.oidc.AbstractOAuth2IdentityProvider;
import org.keycloak.broker.oidc.mappers.AbstractJsonUserAttributeMapper;
import org.keycloak.broker.provider.BrokeredIdentityContext;
import org.keycloak.broker.provider.IdentityBrokerException;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.broker.social.SocialIdentityProvider;
import org.keycloak.events.EventBuilder;
import org.keycloak.models.KeycloakSession;

public class OsuIdentityProvider extends AbstractOAuth2IdentityProvider<OsuIdentityProviderConfig>
        implements SocialIdentityProvider<OsuIdentityProviderConfig> {

    private static final Logger log = Logger.getLogger(OsuIdentityProvider.class);

    public static final String AUTH_URL = "https://osu.ppy.sh/oauth/authorize";
    public static final String TOKEN_URL = "https://osu.ppy.sh/oauth/token";
    public static final String PROFILE_URL = "https://osu.ppy.sh/api/v2/me";
    public static final String DEFAULT_SCOPE = "identify";

    public OsuIdentityProvider(KeycloakSession session, OsuIdentityProviderConfig config) {
        super(session, config);
        config.setAuthorizationUrl(AUTH_URL);
        config.setTokenUrl(TOKEN_URL);
        config.setUserInfoUrl(PROFILE_URL);
    }

    @Override
    protected boolean supportsExternalExchange() {
        return true;
    }

    @Override
    protected String getProfileEndpointForValidation(EventBuilder event) {
        return PROFILE_URL;
    }

    @Override
    protected BrokeredIdentityContext extractIdentityFromProfile(EventBuilder event, JsonNode profile) {
        String userId = getJsonProperty(profile, "id");
        String username = getJsonProperty(profile, "username");

        log.info("Id: " + userId);
        log.info("Username: " + username);
        BrokeredIdentityContext user = new BrokeredIdentityContext(userId, getConfig());

        user.setId(userId);
        user.setUsername(username);
        user.setIdp(this);

        AbstractJsonUserAttributeMapper.storeUserProfileForMapper(user, profile, getConfig().getAlias());

        return user;
    }

    @Override
    protected BrokeredIdentityContext doGetFederatedIdentity(String accessToken) {
        log.debug("doGetFederatedIdentity()");
        log.info("Access Token" + accessToken);
        JsonNode profile;

        try {
            var response = SimpleHttp.doGet(PROFILE_URL, session).header("Authorization", "Bearer " + accessToken);
            log.info(response.asString());
            profile = response.asJson();
        } catch (Exception e) {
            throw new IdentityBrokerException("Could not obtain user profile from osu!", e);
        }

        log.info("Profile: " + profile.asText());
        return extractIdentityFromProfile(null, profile);
    }

    @Override
    protected String getDefaultScopes() {
        return DEFAULT_SCOPE;
    }
}