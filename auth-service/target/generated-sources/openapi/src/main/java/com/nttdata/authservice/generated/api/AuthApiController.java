package com.nttdata.authservice.generated.api;

import com.nttdata.authservice.generated.model.LoginRequest;
import com.nttdata.authservice.generated.model.LoginResponse;
import com.nttdata.authservice.generated.model.LogoutRequest;
import com.nttdata.authservice.generated.model.TokenRefreshRequest;
import com.nttdata.authservice.generated.model.TokenRefreshResponse;
import com.nttdata.authservice.generated.model.UserProfileResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import jakarta.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-23T02:02:44.235992200-05:00[America/Lima]", comments = "Generator version: 7.5.0")
@Controller
@RequestMapping("${openapi.authService.base-path:}")
public class AuthApiController implements AuthApi {

    private final AuthApiDelegate delegate;

    public AuthApiController(@Autowired(required = false) AuthApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new AuthApiDelegate() {});
    }

    @Override
    public AuthApiDelegate getDelegate() {
        return delegate;
    }

}
