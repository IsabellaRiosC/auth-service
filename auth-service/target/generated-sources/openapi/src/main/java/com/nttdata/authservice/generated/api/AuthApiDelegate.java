package com.nttdata.authservice.generated.api;

import com.nttdata.authservice.generated.model.LoginRequest;
import com.nttdata.authservice.generated.model.LoginResponse;
import com.nttdata.authservice.generated.model.LogoutRequest;
import com.nttdata.authservice.generated.model.TokenRefreshRequest;
import com.nttdata.authservice.generated.model.TokenRefreshResponse;
import com.nttdata.authservice.generated.model.UserProfileResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.codec.multipart.Part;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

/**
 * A delegate to be called by the {@link AuthApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-23T20:37:52.618318600-05:00[America/Lima]", comments = "Generator version: 7.5.0")
public interface AuthApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /api/auth/login : Authenticate user and issue tokens
     *
     * @param loginRequest  (required)
     * @return Authenticated successfully (status code 200)
     *         or Invalid credentials (status code 401)
     * @see AuthApi#login
     */
    default Mono<ResponseEntity<LoginResponse>> login(Mono<LoginRequest> loginRequest,
        ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"expiresIn\" : 0, \"accessToken\" : \"accessToken\", \"tokenType\" : \"Bearer\", \"refreshToken\" : \"refreshToken\" }";
                result = ApiUtil.getExampleResponse(exchange, MediaType.valueOf("application/json"), exampleString);
                break;
            }
        }
        return result.then(loginRequest).then(Mono.empty());

    }

    /**
     * POST /api/auth/logout : Logout current user
     *
     * @param logoutRequest  (required)
     * @return Logout completed (status code 204)
     * @see AuthApi#logout
     */
    default Mono<ResponseEntity<Void>> logout(Mono<LogoutRequest> logoutRequest,
        ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        return result.then(logoutRequest).then(Mono.empty());

    }

    /**
     * GET /api/auth/me : Get current authenticated user
     *
     * @return Current user profile (status code 200)
     * @see AuthApi#me
     */
    default Mono<ResponseEntity<UserProfileResponse>> me(ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"roles\" : [ \"roles\", \"roles\" ], \"userId\" : \"userId\", \"email\" : \"email\", \"username\" : \"username\" }";
                result = ApiUtil.getExampleResponse(exchange, MediaType.valueOf("application/json"), exampleString);
                break;
            }
        }
        return result.then(Mono.empty());

    }

    /**
     * POST /api/auth/refresh : Refresh access token
     *
     * @param tokenRefreshRequest  (required)
     * @return Token refreshed successfully (status code 200)
     *         or Invalid refresh token (status code 401)
     * @see AuthApi#refreshToken
     */
    default Mono<ResponseEntity<TokenRefreshResponse>> refreshToken(Mono<TokenRefreshRequest> tokenRefreshRequest,
        ServerWebExchange exchange) {
        Mono<Void> result = Mono.empty();
        exchange.getResponse().setStatusCode(HttpStatus.NOT_IMPLEMENTED);
        for (MediaType mediaType : exchange.getRequest().getHeaders().getAccept()) {
            if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                String exampleString = "{ \"expiresIn\" : 0, \"accessToken\" : \"accessToken\", \"tokenType\" : \"Bearer\" }";
                result = ApiUtil.getExampleResponse(exchange, MediaType.valueOf("application/json"), exampleString);
                break;
            }
        }
        return result.then(tokenRefreshRequest).then(Mono.empty());

    }

}
