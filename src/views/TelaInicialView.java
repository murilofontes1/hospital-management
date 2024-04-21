package src.views;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import src.data.Banco;
import src.utils.FuncUtils;

public class TelaInicialView {
    public static void homeScreen(Banco db)
            throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        FuncUtils.clearScreen();
        while (true) {
            System.out.println("----------- TELA INICIAL -----------");
            System.out.println("[1] - Logar");
            System.out.println("[2] - Sair");
            System.out.print("Digite sua opcao: ");
            int opcao = 0;
            try {
                opcao = FuncUtils.readInt();
                FuncUtils.clearScreen();
                if (opcao == 1) {
                    TelaLoginView.loginScreen(db);
                    break;
                } else if (opcao == 2) {
                    System.out.println("Saindo...");
                    return;
                } else {
                    System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Por favor, digite um número.");
            }
        }
    }
}
