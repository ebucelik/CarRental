package ac.at.fhcampuswien.carrental.entity.service;

import ac.at.fhcampuswien.carrental.expections.CustomerNotFoundException;
import ac.at.fhcampuswien.carrental.rest.models.LoginDTO;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    //256-bit encryption for key
    public static final String SECRET = "58703273357638792F423F4528472B4B6250655368566D597133743677397A24";

    @NotNull

    CustomerEntityService customerEntityService;

    public String extractUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Boolean isTokenExpired(String token) {
        if (extractExpiration(token).before(new Date()))
            return true;
        else {
            return false;
        }
    }

/*    public Boolean validateToken(String token) throws CustomerNotFoundException {
        final String userEmail = extractUserEmail(token);
        if (customerEntityService.checkCustomerExistance(userEmail))
            return true;
        throw new CustomerNotFoundException("This customers token does not exist.");
    }*/

    public String generateToken(String userEmail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userEmail);
    }

    private String createToken(Map<String, Object> claims, String userEmail) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userEmail)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5)) //token valid for 30 Minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}


