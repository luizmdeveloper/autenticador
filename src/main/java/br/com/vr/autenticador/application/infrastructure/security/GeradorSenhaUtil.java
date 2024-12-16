package br.com.vr.autenticador.application.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenhaUtil {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("@dministrador"));
    }
}
