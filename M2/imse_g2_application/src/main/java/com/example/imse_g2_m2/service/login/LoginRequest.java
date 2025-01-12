package com.example.imse_g2_m2.service.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequest {

	private String username;
    private String password;
}
