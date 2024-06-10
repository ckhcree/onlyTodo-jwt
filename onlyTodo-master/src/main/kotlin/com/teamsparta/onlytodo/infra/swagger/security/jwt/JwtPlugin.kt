package com.teamsparta.onlytodo.infra.swagger.security.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Jws
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class JwtPlugin {

    companion object {
        const val ISSUER = "team.sparta.com"
        const val SECRET = "asdfasdfqwertyuio1234hgkuhijyf87"
        const val ACCESS_TOKEN_EXPIRATION_HOUR: Long = 168
    }

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt)
        }

        fun generateAccessToken(subject: String, email: String, role: String): String {
            val claims: Claims = Jwts.claims()
                .add(mapOf("role" to role, "email" to email))
                .build()
            val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
            val now = Instant.now()
            val expirationPeriod = Duration.ofHours(168)
            return Jwts.builder()
                .subject(subject)
                .issuer(ISSUER)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expirationPeriod)))
                .claims(claims)
                .signWith(key)
                .compact()
        }
    }
}