package com.example.imse_g2_m2.exceptions;


public class MemberNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 2134684649545134079L;

	public MemberNotFoundException(String message) {
        super(message);
    }
}

