package src.views;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import src.controllers.PacienteDao;
import src.models.Paciente;
import src.data.Banco;
import src.utils.FuncUtils;

public class PacienteView {
    public static void patientMenu(Banco db) throws SQLException {
        int opcao = 0, opcao2 = 0;
        String nome, cpf, telefone, cod;
        Date data;
        boolean sexo, planoSaude, edit = false;
        Paciente paciente;
        ArrayList<Paciente> pacientes;
        while (opcao != 6) {
            displayMenu();
            opcao = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do paciente: ");
                    nome = FuncUtils.readOnlyLettersAndSpaces();
                    cpf = FuncUtils.readCPF();
                    telefone = FuncUtils.readPhoneNumber();
                    System.out.print("Digite a data de nascimento, ");
                    data = FuncUtils.readDate();
                    sexo = FuncUtils.readSex();
                    planoSaude = FuncUtils.readHealthPlan();

                    PacienteDao.cadastrarPaciente(new Paciente(nome, cpf, telefone, data, sexo, false, planoSaude), db);
                    break;
                case 2:
                    System.out.print("Digite o código do paciente: ");
                    cod = FuncUtils.readCod();
                    paciente = PacienteDao.buscaPaciente(cod, db);
                    System.out.println(paciente);
                    if (paciente != null) {
                        edit = true;
                        System.out.println(paciente);
                        System.out.println("O que deseja editar?");
                        System.out.println("[1] - Nome");
                        System.out.println("[2] - CPF");
                        System.out.println("[3] - Telefone");
                        System.out.println("[4] - Data de nascimento");
                        System.out.println("[5] - Sexo");
                        System.out.println("[6] - Plano de saúde");
                        System.out.println("[7] - Cancelar");
                        opcao2 = FuncUtils.readInt();
                        switch (opcao2) {
                            case 1:
                                System.out.print("Digite o novo nome: ");
                                paciente.setNome(FuncUtils.readOnlyLettersAndSpaces());
                                break;
                            case 2:
                                paciente.setCpf(FuncUtils.readCPF());
                                break;
                            case 3:
                                paciente.setTelefone(FuncUtils.readPhoneNumber());
                                break;
                            case 4:
                                paciente.setDataNasc(FuncUtils.readDate());
                                break;
                            case 5:
                                paciente.setSexo(FuncUtils.readSex());
                                break;
                            case 6:
                                paciente.setPlanoDeSaude(FuncUtils.readHealthPlan());
                                break;
                            case 7:
                                System.out.println("Operação cancelada");
                                edit = false;
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                        if (edit) {
                            PacienteDao.editarPaciente(paciente, db);
                        }
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o codigo do paciente que deseja excluir: ");
                    cod = FuncUtils.readCod();
                    paciente = PacienteDao.buscaPaciente(cod, db);
                    if (paciente != null) {
                        PacienteDao.excluirPaciente(paciente, db);
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o código do paciente que deseja buscar: ");
                    cod = FuncUtils.readCod();
                    paciente = PacienteDao.buscaPaciente(cod, db);
                    if (paciente != null) {
                        System.out.println(paciente);
                    } else {
                        System.out.println("Paciente não encontrado.");
                    }
                    break;
                case 5:
                    pacientes = PacienteDao.listarPacientes(db);
                    listPatients(pacientes);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    public static void listPatients(ArrayList<Paciente> pacientes) {
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        System.out.printf("|Cod%s|Nome%s|CPF%s|Telefone%s|Nascimento%s|Sexo%s|Internado%s|Plano de Saúde\n",
                FuncUtils.spacesGenerator(4), FuncUtils.spacesGenerator(26),
                FuncUtils.spacesGenerator(9),
                FuncUtils.spacesGenerator(4),
                FuncUtils.spacesGenerator(2), FuncUtils.spacesGenerator(8),
                FuncUtils.spacesGenerator(1));
        for (Paciente p : pacientes) {
            System.out.printf("|%-7s|%-30s|%-12s|%-12s|%-12s|%-12s|%-10s|%s\n",
                    p.getCodPaciente(),
                    p.getNome(),
                    p.getCpf(),
                    p.getTelefone(),
                    p.getDataNasc(),
                    p.getSexo(),
                    p.getInternado(),
                    p.getPlanoDeSaude());
        }
        System.out.println();
    }

    public static void displayMenu() {
        System.out.println("----------- MENU PACIENTE -----------");
        System.out.println("[1] Cadastrar Paciente");
        System.out.println("[2] Editar Paciente");
        System.out.println("[3] Excluir Paciente");
        System.out.println("[4] Buscar Paciente");
        System.out.println("[5] Listar Pacientes");
        System.out.println("[6] Sair");
        System.out.print("Digite sua opção: ");
    }
}
