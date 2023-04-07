package com.integration.mercans.configurations.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class AbstractDynamicConfigurations {
    protected Reader getFileHandle(String fileName) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
        return new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
    }
}
