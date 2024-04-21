package src.views;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import src.controllers.AdministradorDao;
import src.data.Banco;
import src.models.Administrador;
import src.utils.FuncUtils;

public class TelaLoginView {

    public static void loginScreen(Banco db)
            throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        while (true) {
            System.out.println("----------- TELA DE LOGIN -----------");
            System.out.print("Digite seu login: ");
            String login = FuncUtils.readLogin();
            System.out.print("Digite sua senha: ");
            String senha = FuncUtils.readPassword();
            FuncUtils.clearScreen();
            // encriptando para fazer a comparação
            senha = FuncUtils.encryptMD5(senha);

            Administrador administrador = AdministradorDao.buscaAdministrador(login, db);
            if (administrador == null) {
                FuncUtils.clearScreen();
                System.out.println("Login ou senha inválidos. Tente novamente.");
                loginScreen(db);
            } else if (login.equals(administrador.getLogin()) && senha.equals(administrador.getSenha())) {
                MenuInicialView.initialMenu(db);
                return;
            } else {
                FuncUtils.clearScreen();
                System.out.println("Login ou senha inválidos. Tente novamente.");
                loginScreen(db);
            }
        }
    }
}
