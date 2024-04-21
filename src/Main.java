package src;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import src.data.Banco;
import src.utils.FuncUtils;
import src.views.TelaInicialView;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Banco db = new Banco();
        TelaInicialView.homeScreen(db);
        db.disconnect();
        FuncUtils.fechaScanner();
    }
}