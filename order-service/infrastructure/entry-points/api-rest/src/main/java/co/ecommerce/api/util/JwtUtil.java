package co.ecommerce.api.util;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class JwtUtil {

    private JwtUtil() {
    }

    @SuppressWarnings("unchecked")
    public static List<String> getRoles(Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");

        if (realmAccess == null) {
            return Collections.emptyList();
        }

        return (List<String>) realmAccess.getOrDefault(
                "roles",
                Collections.emptyList()
        );
    }

    public static boolean hasRole(Jwt jwt, String role) {
        return getRoles(jwt)
                .stream()
                .anyMatch(r -> r.equalsIgnoreCase(role));
    }

    public static String getUserId(Jwt jwt) {
        return jwt.getSubject();
    }

    public static String getUsername(Jwt jwt) {
        return jwt.getClaimAsString("preferred_username");
    }
}
