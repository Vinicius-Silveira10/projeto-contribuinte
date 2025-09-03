package org.aplicacao.api_real.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

@Service
public class EncryptionService {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";

    private final Key key;

    // A chave é injetada através do construtor, essa é a forma mais segura e garantida pelo Spring
    public EncryptionService(@Value("${app.encryption.key}") String encryptionKey) {
        this.key = new SecretKeySpec(encryptionKey.getBytes(StandardCharsets.UTF_8), "AES");
    }

    public String encrypt(String data) {
        if (data == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao criptografar o atributo", e);
        }
    }

    public String decrypt(String encryptedData) {
        if (encryptedData == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IllegalStateException("Erro ao descriptografar o dado do banco", e);
        }
    }
}
