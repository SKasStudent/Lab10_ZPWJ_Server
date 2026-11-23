package org.example.controllers;

import org.example.models.Wood;
import org.example.readers.WoodReader;
import org.example.vigenre.VigenereEncryption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
public class WoodController {

    private final WoodReader WoodReader;
    private final ObjectMapper objectMapper;

    @Value("${vigenere.key}")
    private String encryptionKey;

    public WoodController(WoodReader WoodReader, ObjectMapper objectMapper) {
        this.WoodReader = WoodReader;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/api/woods", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getEncryptedWoods(){
        List<Wood> woods = WoodReader.readWood();
        String json = objectMapper.writeValueAsString(woods);
        return VigenereEncryption.encrypt(json, encryptionKey);
    }
}