package com.jonybuzz.encontralo.security;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AESUtilTests {

    @Test
    @SneakyThrows
    void encriptar_textoYPasswordOk_retornaTextoEncriptadoConIvYSaltEnBase64() {
        String outputFormat = "%-22s: %s%n";
        String password = "contraseña";
        String textoPlano = "Mi texto privado!";

        String encryptedTextBase64 = AesEncryptionUtil.encriptar(textoPlano, password);

        System.out.println("------ AES GCM Password-based Encryption ------");
        System.out.printf(outputFormat, "Input (plain text)", textoPlano);
        System.out.printf(outputFormat, "Encrypted (base64) ", encryptedTextBase64);

        System.out.println("------ AES GCM Password-based Decryption ------");
        System.out.printf(outputFormat, "Input (base64)", encryptedTextBase64);

        String decryptedText = AesEncryptionUtil.desencriptar(encryptedTextBase64, password);
        System.out.printf(outputFormat, "Decrypted (plain text)", decryptedText);

        Assertions.assertEquals(decryptedText, textoPlano);
    }

    @Test
    @SneakyThrows
    void desencriptar_TextoEncriptadoConIvYSaltEnBase64_retornaTextoPlanoOriginal() {
        String password = "otra_pass";
        String textoPlano = "Otro textito";
        String textoEncriptadoBase64 = "6glg9O5UyAeSCqutw29yiaG7P+q1Y57Tw7erj0JsEksSTlG0sem4twAf3EfKr/QTC7QFrf/HbP8=";
        String otroTextoEncriptadoBase64 = "y5Gb3mZuMhrbc2YYCbq1vsX5uw+562QtDsmfJHYmZuKzPxvBmttzekU6WlXYc+HZ6RDNCQWl/mw=";
        Assertions.assertEquals(textoPlano, AesEncryptionUtil.desencriptar(textoEncriptadoBase64, password));
        Assertions.assertEquals(textoPlano, AesEncryptionUtil.desencriptar(otroTextoEncriptadoBase64, password));
    }

}
