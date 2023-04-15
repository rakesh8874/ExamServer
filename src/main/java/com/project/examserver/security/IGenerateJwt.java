package com.project.examserver.security;

import com.project.examserver.domain.User;

import java.util.Map;

public interface IGenerateJwt {

    Map<String, String> getJwtToken(User user);

}
