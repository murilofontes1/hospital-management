package src.views;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import src.controllers.ConsultaDao;
import src.controllers.MedicamentoDao;
import src.controllers.MedicoDao;
import src.controllers.PacienteDao;
import src.controllers.PrescricaoDao;
import src.data.Banco;
import src.models.Consulta;
import src.models.Medicamento;
import src.models.Medico;
import src.models.Paciente;
import src.models.Prescricao;
import src.utils.FuncUtils;

public class ConsultaView {
    public static void main(String[] args) throws SQLException {
        Banco db = new Banco();
        appointmentMenu(db);
    }

    public static void appointmentMenu(Banco db) throws SQLException {
        int opcao = 0, opcao2 = 0;
        Date dataConsulta;
        Time horarioConsulta;
        String diagnostico, codConsulta, crmMedico, idPaciente, dosagem, posologia;
        ArrayList<String> sintomas;
        boolean precisaInternar;
        Prescricao prescricao;
        Consulta consulta;
        ArrayList<Consulta> consultas;
        ArrayList<Medico> medicos;
        ArrayList<Paciente> pacientes;
        ArrayList<Medicamento> medicamentos;
        ArrayList<Prescricao> prescricoes;
        Medico medico;
        Paciente paciente;

        while (opcao != 6) {
            displayMenu();
            opcao = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (opcao) {
                case 1:
                    System.out.print("Insira a data da consulta, ");
                    dataConsulta = FuncUtils.readDate();
                    System.out.print("Insira o horário da consulta, ");
                    horarioConsulta = FuncUtils.readTime();

                    System.out.print("Insira o diagnóstico: ");
                    diagnostico = FuncUtils.readOnlyLettersAndSpaces();
                    sintomas = FuncUtils.readSymptoms();
                    precisaInternar = FuncUtils.readNeedToHospitalize();

                    System.out.println();
                    pacientes = PacienteDao.listarPacientes(db);
                    if (pacientes.isEmpty()) {
                        System.out.println("Não há pacientes cadastrados.");
                        System.out.println("Cadastro de consulta cancelado.");
                        break;
                    }
                    PacienteView.listPatients(pacientes);

                    System.out.print("Insira o código do paciente da consulta: ");
                    idPaciente = FuncUtils.readCod();
                    if (PacienteDao.buscaPaciente(idPaciente, db) == null) {
                        System.out.println("Código não está nos pacientes cadastrados.");
                        System.out.println("Cadastro de consulta cancelado.");
                        break;
                    }
                    System.out.println();

                    medicos = MedicoDao.verificarMedicosDisponiveisEmAlgumHorario(horarioConsulta, db);
                    if (medicos.isEmpty()) {
                        System.out.println("Não há médicos disponíveis para no horário da consulta.");
                        System.out.println("Cadastro de consulta cancelado.");
                        break;
                    }
                    MedicoView.listDoctors(medicos);

                    System.out.print("Insira o crm do médico da consulta, ");
                    crmMedico = FuncUtils.readCrm();
                    if (MedicoDao.buscaMedico(crmMedico, db) == null) {
                        System.out.println("CRM não está nos médicos disponíveis.");
                        System.out.println("Cadastro de consulta cancelado.");
                        break;
                    }
                    System.out.println();

                    consulta = ConsultaDao.buscaConsulta(dataConsulta, horarioConsulta, crmMedico, db);
                    if (consulta != null) {
                        System.out.println("Já existe uma consulta com este médico nesta data e horário.");
                        System.out.println("Cadastro de consulta cancelado.");
                        break;
                    }

                    if (precisaInternar) {
                        if (!(InternacaoView.appointmentHospitalization(dataConsulta, idPaciente, db))) {
                            System.out.println(
                                    "Cadastro de consulta cancelado, pois não foi possível realizar a internação.");
                            break;
                        }
                    }

                    medicamentos = MedicamentoDao.listarMedicamentos(db);
                    ConsultaDao.cadastrarConsulta(new Consulta(dataConsulta, horarioConsulta, diagnostico, sintomas,
                            precisaInternar, crmMedico, idPaciente), db);
                    consulta = ConsultaDao.buscaConsulta(dataConsulta, horarioConsulta, crmMedico, db);

                    if (FuncUtils.readYesOrNo("Deseja prescrever medicamentos? [S/N]: ")) {
                        if (medicamentos.isEmpty()) {
                            System.out.println("Não há medicamentos cadastrados.");
                            break;
                        }
                        MedicamentoView.listMedicines(medicamentos);
                        do {
                            System.out.print("Insira o código do medicamento: ");
                            String codMedicamento = FuncUtils.readCod();
                            Medicamento medicamento = MedicamentoDao.buscaMedicamento(codMedicamento, db);
                            if (medicamento == null) {
                                System.out.println("Medicamento não encontrado.");
                                continue;
                            }
                            dosagem = FuncUtils.readDosage();
                            posologia = FuncUtils.readPosology();
                            prescricao = new Prescricao(medicamento, consulta.getCodConsulta(), dosagem, posologia);
                            PrescricaoDao.cadastrarPrescricao(prescricao, db);
                        } while (FuncUtils.readYesOrNo("Deseja prescrever mais medicamentos? [S/N]: "));
                    }

                    break;
                case 2:
                    System.out.print("Insira o código da consulta: ");
                    codConsulta = FuncUtils.readCod();
                    consulta = ConsultaDao.buscaConsulta(codConsulta, db);
                    if (consulta == null) {
                        System.out.println("Consulta não encontrada.");
                        break;
                    }
                    System.out.println(consulta);
                    System.out.println("Deseja realmente excluir a consulta? [1] - Sim [2] - Não");
                    opcao2 = FuncUtils.readInt();
                    if (opcao2 == 1) {
                        ConsultaDao.excluirConsulta(consulta, db);
                        System.out.println("Consulta excluída com sucesso.");
                    }
                    break;
                case 3:
                    consultas = ConsultaDao.listarConsultas(db);
                    if (consultas.isEmpty()) {
                        System.out.println("Não há consultas cadastradas.");
                        break;
                    }
                    System.out.printf("|Cod%s|Data%s|Hora%s|Medico%s|Paciente\n", FuncUtils.spacesGenerator(4),
                            FuncUtils.spacesGenerator(7), FuncUtils.spacesGenerator(5), FuncUtils.spacesGenerator(24));
                    for (Consulta c : consultas) {
                        medico = MedicoDao.buscaMedico(c.getIdMedico(), db);
                        paciente = PacienteDao.buscaPaciente(c.getIdPaciente(), db);
                        System.out.printf("|%-7s|%-11s|%-9s|%-30s|%-30s\n", c.getCodConsulta(), c.getDataConsulta(),
                                c.getHorarioConsulta(), medico.getNome(), paciente.getNome());
                    }
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Insira o código da consulta: ");
                    codConsulta = FuncUtils.readCod();
                    consulta = ConsultaDao.buscaConsulta(codConsulta, db);
                    if (consulta == null) {
                        System.out.println("Consulta não encontrada.");
                        break;
                    }
                    System.out.println(consulta);
                    break;
                case 5:
                    System.out.print("Insira o código da consulta: ");
                    codConsulta = FuncUtils.readCod();
                    consulta = ConsultaDao.buscaConsulta(codConsulta, db);
                    if (consulta == null) {
                        System.out.println("Consulta não encontrada.");
                        break;
                    }
                    prescricoes = consulta.getPrescricoes();
                    if (prescricoes.isEmpty()) {
                        System.out.println("Não há prescrições para essa consulta.");
                        break;
                    }
                    System.out.printf("|Cod%s|Medicamento%s|Dosagem%s|Posologia\n", FuncUtils.spacesGenerator(4),
                            FuncUtils.spacesGenerator(19), FuncUtils.spacesGenerator(40));
                    for (Prescricao p : prescricoes) {
                        System.out.printf("|%-7s|%-30s|%-47s|%-10s\n", p.getCodPrescricao(),
                                p.getMedicamento().getNome(),
                                p.getDosagem(), p.getPosologia());
                    }
                    System.out.println();
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

    public static void displayMenu() {
        System.out.println("----------- MENU CONSULTA -----------");
        System.out.println("[1] - Cadastrar Consulta");
        System.out.println("[2] - Excluir Consulta");
        System.out.println("[3] - Listar Consultas");
        System.out.println("[4] - Buscar Consulta");
        System.out.println("[5] - Ver prescricoes de uma consulta");
        System.out.println("[6] - Sair");
        System.out.print("Digite sua opcao: ");
    }
}
