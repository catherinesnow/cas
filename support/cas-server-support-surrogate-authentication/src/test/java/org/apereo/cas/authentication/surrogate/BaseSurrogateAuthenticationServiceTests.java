package org.apereo.cas.authentication.surrogate;

import org.apereo.cas.authentication.CoreAuthenticationTestUtils;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.util.junit.ConditionalIgnoreRule;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import static org.junit.Assert.*;

/**
 * This is {@link BaseSurrogateAuthenticationServiceTests}.
 *
 * @author Timur Duehr
 * @since 6.0.0
 */
@Slf4j
public abstract class BaseSurrogateAuthenticationServiceTests {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final ConditionalIgnoreRule conditionalIgnoreRule = new ConditionalIgnoreRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Mock
    protected ServicesManager servicesManager;

    public abstract SurrogateAuthenticationService getService();

    @Test
    public void verifyList() {
        val list = getService().getEligibleAccountsForSurrogateToProxy("casuser");
        LOGGER.debug("verifyList [{}]", list);
        assertFalse(list.isEmpty());
    }

    @Test
    public void verifyProxying() {
        assertTrue(getService().canAuthenticateAs("banderson", CoreAuthenticationTestUtils.getPrincipal("casuser"),
            CoreAuthenticationTestUtils.getService()));
    }

}
