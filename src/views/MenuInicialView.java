package src.views;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import src.data.Banco;
import src.utils.FuncUtils;

public class MenuInicialView {
    public static void initialMenu(Banco db)
            throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        FuncUtils.clearScreen();
        System.out.println("----------- MENU PRINCIPAL -----------");
        System.out.println("[1] - Menu Administrador");
        System.out.println("[2] - Menu Paciente");
        System.out.println("[3] - Menu Médico");
        System.out.println("[4] - Menu Enfermeiro");
        System.out.println("[5] - Menu Medicamentos");
        System.out.println("[6] - Menu Enfermaria");
        System.out.println("[7] - Menu Internação");
        System.out.println("[8] - Menu Consulta");
        System.out.println("[9] - Logout");
        System.out.print("Digite sua opção: ");
        int opcao = 0;
        try {
            opcao = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (opcao) {
                case 1:
                    AdministradorView.adminMenu(db);
                    initialMenu(db);
                    break;
                case 2:
                    PacienteView.patientMenu(db);
                    initialMenu(db);
                    break;
                case 3:
                    MedicoView.doctorMenu(db);
                    initialMenu(db);
                    break;
                case 4:
                    EnfermeiroView.nurseMenu(db);
                    initialMenu(db);
                    break;
                case 5:
                    MedicamentoView.medicamentMenu(db);
                    initialMenu(db);
                    break;
                case 6:
                    EnfermariaView.wardMenu(db);
                    initialMenu(db);
                    break;
                case 7:
                    InternacaoView.hospitalizationMenu(db);
                    initialMenu(db);
                    break;
                case 8:
                    ConsultaView.appointmentMenu(db);
                    initialMenu(db);
                    break;
                case 9:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Por favor, digite um número.");
        }
    }
}
