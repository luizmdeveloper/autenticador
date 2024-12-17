package br.com.vr.autenticador.helper;

import br.com.vr.autenticador.application.core.entidade.Usuario;

public class UsuarioHelper {

    private UsuarioHelper() {}

    public static Usuario criarUsuario() {
        var usuario = new Usuario();
        usuario.setEmail("luiz.cavalcante@vr.com.br");
        usuario.setSenha("$2a$10$V9VUwUO97XEMnu.2bgNZm.17VPgb10Q7kp0W/36gHbVqjnn7TuIpm");
        return usuario;
    }
}
