package src.views;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;

import src.controllers.EnfermariaDao;
import src.controllers.InternacaoDao;
import src.controllers.PacienteDao;
import src.data.Banco;
import src.models.Enfermaria;
import src.models.Internacao;
import src.models.Paciente;
import src.utils.FuncUtils;

public class InternacaoView {
    public static void main(String[] args) throws SQLException {
        Banco db = new Banco();
        hospitalizationMenu(db);
    }

    public static void hospitalizationMenu(Banco db) throws SQLException {
        int opcao = 0, opcao2 = 0;
        Date dataInternacao;
        Date dataAlta;
        String idPaciente, codEnfemaria, codInternacao;
        Internacao internacao = new Internacao();
        ArrayList<Enfermaria> enfermarias;
        ArrayList<Paciente> pacientes;
        ArrayList<Internacao> internacoes;
        Enfermaria enfermaria;
        Paciente paciente;

        while (opcao != 6) {
            displayMenu();
            opcao = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (opcao) {
                case 1:
                    System.out.print("Insira a data da internação: ");
                    dataInternacao = FuncUtils.readDate();

                    System.out.println();
                    pacientes = PacienteDao.listarPacientes(db);
                    if (pacientes.isEmpty()) {
                        System.out.println("Não há pacientes cadastrados.");
                        System.out.println("Cadastro de internação cancelado.");
                        break;
                    }
                    PacienteView.listPatients(pacientes);

                    System.out.print("Insira o código do paciente: ");
                    idPaciente = FuncUtils.readCod();
                    if (PacienteDao.buscaPaciente(idPaciente, db) == null
                            || PacienteDao.buscaPaciente(idPaciente, db).isInternado() == true) {
                        System.out.println("Código não está nos pacientes cadastrados ou já se encontra Internado.");
                        System.out.println("Cadastro de internação cancelado.");
                        break;
                    }
                    System.out.println();

                    enfermarias = EnfermariaDao.listarEnfermariasDisponiveis(db);
                    if (enfermarias.isEmpty()) {
                        System.out.println("Não há enfemarias disponíveis para no horário da internação.");
                        System.out.println("Cadastro de internação cancelado.");
                        break;
                    }
                    EnfermariaView.listWards(enfermarias);

                    System.out.print("Insira o codigo da enfemaria da Internação: ");
                    codEnfemaria = FuncUtils.readCod();
                    if (EnfermariaDao.buscaEnfermaria(codEnfemaria, db) == null
                            || EnfermariaDao.buscaEnfermaria(codEnfemaria, db).getLeitosDisponiveis() <= 0) {
                        System.out.println("Enfemaria não existe no sistema ou está lotada!.");
                        System.out.println("Cadastro de internação cancelado.");
                        break;
                    }
                    System.out.println();
                    internacao.setDataInternacao(dataInternacao);
                    internacao.setDataAlta(null);
                    internacao.setIdEnfermaria(codEnfemaria);
                    internacao.setIdPaciente(idPaciente);
                    InternacaoDao.cadastrarInternacao(internacao, db);

                    enfermaria = EnfermariaDao.buscaEnfermaria(codEnfemaria, db);
                    enfermaria.setLeitosDisponiveis(enfermaria.getLeitosDisponiveis() - 1);
                    EnfermariaDao.editaEnfermaria(enfermaria, db);

                    paciente = PacienteDao.buscaPaciente(idPaciente, db);
                    paciente.setInternado(true);
                    PacienteDao.editarPaciente(paciente, db);

                    break;
                case 2:
                    System.out.print("Insira o código da Internação: ");
                    codInternacao = FuncUtils.readCod();
                    internacao = InternacaoDao.buscaInternacao(codInternacao, db);
                    if (internacao == null) {
                        System.out.println("Internação não encontrada.");
                        break;
                    }
                    System.out.println(internacao);
                    System.out.println("Deseja realmente excluir a Internação? [1] - Sim [2] - Não");
                    opcao2 = FuncUtils.readInt();
                    if (opcao2 == 1) {
                        InternacaoDao.excluirInternacao(internacao, db);

                        if (internacao.getDataAlta() == null) {
                            enfermaria = EnfermariaDao.buscaEnfermaria(internacao.getIdEnfermaria(), db);
                            enfermaria.setLeitosDisponiveis(enfermaria.getLeitosDisponiveis() + 1);
                            EnfermariaDao.editaEnfermaria(enfermaria, db);
                        }

                        paciente = PacienteDao.buscaPaciente(internacao.getIdPaciente(), db);
                        paciente.setInternado(false);
                        PacienteDao.editarPaciente(paciente, db);

                        System.out.println("Internação excluída com sucesso.");
                    }
                    break;
                case 3:
                    internacoes = InternacaoDao.listarInternacoes(db);
                    if (internacoes.isEmpty()) {
                        System.out.println("Não há internações cadastradas.");
                        break;
                    }
                    listHospitalizations(internacoes, db);
                    break;
                case 4:
                    System.out.print("Insira o código da internação: ");
                    codInternacao = FuncUtils.readCod();
                    internacao = InternacaoDao.buscaInternacao(codInternacao, db);
                    if (internacao == null) {
                        System.out.println("Internação não encontrada.");
                        break;
                    }
                    System.out.println(internacao);
                    break;
                case 5:
                    internacoes = InternacaoDao.listarInternacoesAtivas(db);
                    if (internacoes.isEmpty()) {
                        System.out.println("Não há internações ativas.");
                        break;
                    }
                    listHospitalizations(internacoes, db);
                    System.out.print("Digite qual internação deseja dar alta: ");
                    codInternacao = FuncUtils.readCod();
                    internacao = InternacaoDao.buscaInternacao(codInternacao, db);
                    if (internacao == null) {
                        System.out.println("Internação não encontrada.");
                        break;
                    }
                    System.out.print("Digite a data da alta, ");
                    dataAlta = FuncUtils.readDate();
                    internacao.setDataAlta(dataAlta);
                    InternacaoDao.editarInternacao(internacao, db);

                    enfermaria = EnfermariaDao.buscaEnfermaria(internacao.getIdEnfermaria(), db);
                    enfermaria.setLeitosDisponiveis(enfermaria.getLeitosDisponiveis() + 1);
                    EnfermariaDao.editaEnfermaria(enfermaria, db);

                    paciente = PacienteDao.buscaPaciente(internacao.getIdPaciente(), db);
                    paciente.setInternado(false);
                    PacienteDao.editarPaciente(paciente, db);

                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, digite uma opção válida.");
                    break;
            }
        }
    }

    public static boolean appointmentHospitalization(Date dataInternação, String idPaciente, Banco db)
            throws SQLException {
        ArrayList<Enfermaria> enfermarias;

        String codEnfemaria;

        Internacao internacao = new Internacao();
        internacao.setDataInternacao(dataInternação);
        internacao.setIdPaciente(idPaciente);

        enfermarias = EnfermariaDao.listarEnfermariasDisponiveis(db);
        if (enfermarias.isEmpty()) {
            System.out.println("Não há enfemarias disponíveis para no horário da internação.");
            System.out.println("Cadastro de internação cancelado.");
            return false;
        }
        EnfermariaView.listWards(enfermarias);

        System.out.print("Insira o codigo da enfemaria da Internação: ");
        codEnfemaria = FuncUtils.readCod();
        if (EnfermariaDao.buscaEnfermaria(codEnfemaria, db) == null
                || EnfermariaDao.buscaEnfermaria(codEnfemaria, db).getLeitosDisponiveis() <= 0) {
            System.out.println("Enfemaria não existe no sistema ou está lotada!.");
            System.out.println("Cadastro de internação cancelado.");
            return false;
        }
        internacao.setIdEnfermaria(codEnfemaria);
        InternacaoDao.cadastrarInternacao(internacao, db);

        Paciente paciente = PacienteDao.buscaPaciente(idPaciente, db);
        paciente.setInternado(true);
        PacienteDao.editarPaciente(paciente, db);

        Enfermaria enfermaria = EnfermariaDao.buscaEnfermaria(codEnfemaria, db);
        enfermaria.setLeitosDisponiveis(enfermaria.getLeitosDisponiveis() - 1);
        EnfermariaDao.editaEnfermaria(enfermaria, db);
        return true;

    }

    public static void listHospitalizations(ArrayList<Internacao> internacoes, Banco db) throws SQLException {
        Enfermaria enfermaria;
        Paciente paciente;
        System.out.printf("|Cod%s|Data da Internação|Data da alta |Paciente%s|Cod Enfermaria\n",
                FuncUtils.spacesGenerator(4),
                FuncUtils.spacesGenerator(22));
        for (Internacao i : internacoes) {
            enfermaria = EnfermariaDao.buscaEnfermaria(i.getIdEnfermaria(), db);
            paciente = PacienteDao.buscaPaciente(i.getIdPaciente(), db);
            System.out.printf("|%-7s|%-18s|%-13s|%-30s|%-30s\n", i.getCodInternacao(), i.getDataInternacao(),
                    i.getDataAlta() == null ? "Internado(a)" : i.getDataAlta(),
                    paciente.getNome(), enfermaria.getCodEnfermaria());
        }
        System.out.println();
    }

    public static void displayMenu() {
        System.out.println("----------- MENU INTERNAÇÃO -----------");
        System.out.println("[1] - Cadastrar Internação");
        System.out.println("[2] - Excluir Internação");
        System.out.println("[3] - Listar Internações");
        System.out.println("[4] - Buscar Internação");
        System.out.println("[5] - Dar Alta em algum paciente");
        System.out.println("[6] - Sair");
        System.out.print("Digite sua opcao: ");
    }
}
