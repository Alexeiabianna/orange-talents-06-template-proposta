package com.alexei.proposta.controllers.client.documento;

import org.springframework.security.crypto.encrypt.Encryptors;

public class CriptografaDados {
    private static final String CHAVE = "3afcecf8d447a6289bdd4804286c9c86";
    private static final String PASSWORD = "a410173d274cb7da04a66099d19a69cc";
    
    public static String encryptaDocumento(String documento) {
        return Encryptors.text(PASSWORD, CHAVE).encrypt(documento);
    }

    public static String decryptDocumento(String documento) {
        return Encryptors.text(PASSWORD, CHAVE).decrypt(documento);
    }
    
}
