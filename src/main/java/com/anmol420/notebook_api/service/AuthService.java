package com.anmol420.notebook_api.service;

import com.anmol420.notebook_api.domain.dtos.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

}
