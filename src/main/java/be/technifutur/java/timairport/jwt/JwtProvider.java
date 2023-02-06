package be.technifutur.java.timairport.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
public class JwtProvider {
    private final UserDetailsService userDetailsService;

    public JwtProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(Authentication auth) {
        Date issuesAt = new Date();
        Date expiresAt = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000));

        try {
            return JWT.create()
                    .withSubject(auth.getName())
                    .withExpiresAt(expiresAt)
                    .withIssuedAt(issuesAt)
                    .sign(Algorithm.HMAC512("s3cr3t"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("internal error", e);
        }
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512("s3cr3t"))
                    .acceptExpiresAt(24 * 60 * 60 * 1000)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

//            String subject = decodedJWT.getSubject();
//            Claim claim = decodedJWT.getClaim("Claim_name");
//            return subject.length() > 50 && claim.asBoolean();

            return true;
        } catch (JWTVerificationException e) {
            return false;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Authentication generateAuth(String token) {
        DecodedJWT jwt = JWT.decode(token);
        String username = jwt.getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails.getPassword(),
                null,
                userDetails.getAuthorities());

        return auth;
    }
}
