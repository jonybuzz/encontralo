package com.jonybuzz.encontralo.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Funciones utilitarias de encriptación con algoritmo AES.
 * Basada en https://mkyong.com/java/java-aes-encryption-and-decryption/
 */
public class AesEncryptionUtil {

    private AesEncryptionUtil() {
        throw new UnsupportedOperationException("Clase utilitaria");
    }

    private static final String ENCRYPTION_ALGORITHM = "AES/GCM/NoPadding";
    private static final String SECRET_KEY_ALGORITHM = "PBKDF2WithHmacSHA256";

    private static final int TAG_BIT_LENGTH = 128;
    private static final int IV_BYTE_LENGTH = 12;
    private static final int SALT_BYTE_LENGTH = 16;
    private static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * Encripta un string usando una contraseña
     *
     * @param textoPlano texto a encriptar
     * @param password contrasenia usada para desencriptar luego
     * @return Texto encriptado con IV y salt, en base 64
     * @throws GeneralSecurityException No deberian surgir excepciones con la configuraciion actual
     */
    public static String encriptar(String textoPlano, String password) throws GeneralSecurityException {

        // 16 bytes salt
        byte[] salt = AesEncryptionUtil.getRandomNonce(SALT_BYTE_LENGTH);

        // GCM recommended 12 bytes iv
        byte[] iv = AesEncryptionUtil.getRandomNonce(IV_BYTE_LENGTH);

        // secret key from password
        SecretKey aesKeyFromPassword = AesEncryptionUtil.getAesPasswordDerivedSecretKey(password.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);

        // ASE-GCM needs GCMParameterSpec
        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_BIT_LENGTH, iv));

        byte[] cipherText = cipher.doFinal(textoPlano.getBytes(CHARSET));

        // prefix IV and Salt to cipher text
        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length)
                .put(iv)
                .put(salt)
                .put(cipherText)
                .array();

        // string representation, base64, send this string to other for decryption.
        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
    }

    /**
     * Desencripta un string en base 64 que contiene IV y salt. Necesita el mismo IV, salt
     * y password que se usaron en la encriptación.
     *
     * @param encrypted el texto a desencriptar
     * @param password misma contrasenia usada en la encriptacion
     * @return texto plano original
     * @throws GeneralSecurityException No deberian surgir excepciones con la configuraciion actual
     */
    public static String desencriptar(String encrypted, String password) throws GeneralSecurityException {

        byte[] decode = Base64.getDecoder().decode(encrypted.getBytes(CHARSET));

        // get back the iv and salt from the cipher text
        ByteBuffer byteBuffer = ByteBuffer.wrap(decode);

        byte[] iv = new byte[IV_BYTE_LENGTH];
        byteBuffer.get(iv);

        byte[] salt = new byte[SALT_BYTE_LENGTH];
        byteBuffer.get(salt);

        byte[] cipherText = new byte[byteBuffer.remaining()];
        byteBuffer.get(cipherText);

        // get back the aes key from the same password and salt
        SecretKey aesKeyFromPassword = AesEncryptionUtil.getAesPasswordDerivedSecretKey(password.toCharArray(), salt);

        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);

        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(TAG_BIT_LENGTH, iv));

        byte[] plainText = cipher.doFinal(cipherText);

        return new String(plainText, CHARSET);
    }

    private static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    private static SecretKey getAesPasswordDerivedSecretKey(char[] password, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_ALGORITHM);
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }


}
