public class Usuario {
// Classe responsável pelos dados e pela verificação do usuário

    // Dados de acesso do usuário
    String login;
    String senha;

    // Armazena o login e a senha informados
    public Usuario(String usuario, String senhaUsuario) {
        login = usuario;
        senha = senhaUsuario;
    }

    // Verifica se o login e a senha estão corretos
    public boolean verificarLogin(String usuarioDigitado, String senhaDigitada) {
        if (login.equals(usuarioDigitado) && senha.equals(senhaDigitada)) {
            return true;
        } else {
            return false;
        }
    }
}
//commit nao vai, sos
//tentando fazer commit