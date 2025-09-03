package org.aplicacao.api_real.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.aplicacao.api_real.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Converter
@Component // Mantemos o @Component para que o Spring possa injetar o serviço
public class CpfCriptoConverter implements AttributeConverter<String, String> {

    private static EncryptionService encryptionService;

    // O Spring vai chamar este método e nos dar acesso estático ao serviço
    @Autowired
    public void setEncryptionService(EncryptionService service) {
        CpfCriptoConverter.encryptionService = service;
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        // transfere a criptografia para o serviço
        return encryptionService.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // transfere a descriptografia para o serviço
        return encryptionService.decrypt(dbData);
    }
}