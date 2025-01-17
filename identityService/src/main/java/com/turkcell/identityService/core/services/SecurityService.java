package com.turkcell.identityService.core.services;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
    HttpSecurity configureSecurity(HttpSecurity http) throws Exception;
}
