package com.tenpo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.exceptions.BadRequestException;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static String encrypt(String s) {
        return DigestUtils.sha256Hex(s);
    }

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher m = pattern.matcher(email);
        return m.matches();
    }

    public static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new BadRequestException("Invalid email or Password");
        } else {
            return true;
        }
    }

    public static String asJsonString(Object request) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(request);
    }
}
