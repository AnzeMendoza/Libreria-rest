package edu.sucho.libreriaweb.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

@Component
public class EncryptPassword {
    private Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public String encriptarCadena(String cadena) {
        return argon2.hash(1, 1024, 1, cadena);
    }

    public boolean verificarIgualdadDeCadenas(String hashString, String notHashString) {
        return argon2.verify(hashString, notHashString);
    }
}
