package com.anmol420.notebook_api.service;

import com.anmol420.notebook_api.domain.dtos.user.LoginRequest;
import com.anmol420.notebook_api.domain.dtos.user.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
    LoginRequest login(LoginRequest request);

}
