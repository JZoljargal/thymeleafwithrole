package com.zolmn.demo.provider;

public interface SecurityService {
    boolean isAuthenticated();

    void autoLogin(String username, String password);
}