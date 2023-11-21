package com.example.bibliotecaCatalogo;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

public class CustomInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("java", System.getProperty("java.version"));
        builder.withDetail("os", System.getProperty("os.name"));
    }
}
