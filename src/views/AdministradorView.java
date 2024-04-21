package src.views;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import src.controllers.AdministradorDao;
import src.data.Banco;
import src.models.Administrador;
import src.utils.FuncUtils;

public class AdministradorView {
    public static void adminMenu(Banco db) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        int opcao = 0, opcao2 = 0;
        String nome, cpf, telefone, login, senha;
        Date dataNasc, dataDeAdmissao;
        Time horarioDeTrabalhoInicio, horarioDeTrabalhoFinal;
        boolean sexo;
        double salario;
        Administrador administrador;
        ArrayList<Administrador> administradores;

        while (opcao != 6) {
            displayMenu();
            opcao = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o login: ");
                    login = FuncUtils.readLogin();

                    System.out.print("Digite a senha: ");
                    senha = FuncUtils.readPassword();

                    System.out.print("Digite o nome: ");
                    nome = FuncUtils.readOnlyLettersAndSpaces();

                    cpf = FuncUtils.readCPF();
                    telefone = FuncUtils.readPhoneNumber();

                    System.out.print("Insira a data de nascimento, ");
                    dataNasc = FuncUtils.readDate();

                    sexo = FuncUtils.readSex();
                    salario = FuncUtils.readSalary();

                    System.out.print("Insira a data de admissão, ");
                    dataDeAdmissao = FuncUtils.readDate();

                    System.out.print("insira o inicio do expediente, ");
                    horarioDeTrabalhoInicio = FuncUtils.readTime();

                    System.out.print("insira o final do expediente, ");
                    horarioDeTrabalhoFinal = FuncUtils.readTime();

                    AdministradorDao.cadastrarAdministrador(
                            new Administrador(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao,
                                    horarioDeTrabalhoInicio, horarioDeTrabalhoFinal, login, senha),
                            db);
                    break;
                case 2:
                    System.out.print("Digite o login do adm: ");
                    login = FuncUtils.readLogin();
                    administrador = AdministradorDao.buscaAdministrador(login, db);
                    if (administrador != null) {
                        System.out.println(administrador);
                        System.out.println("O que deseja editar?");
                        System.out.println("[1] - Nome");
                        System.out.println("[2] - CPF");
                        System.out.println("[3] - Telefone");
                        System.out.println("[4] - Data de nascimento");
                        System.out.println("[5] - Sexo");
                        System.out.println("[6] - Salário");
                        System.out.println("[7] - Data de admissão");
                        System.out.println("[8] - Horário de trabalho (início)");
                        System.out.println("[9] - Horário de trabalho (final)");
                        System.out.println("[10] - Cancelar");
                        System.out.print("Digite sua opção: ");
                        opcao2 = FuncUtils.readInt();
                        switch (opcao2) {
                            case 1:
                                System.out.print("Digite o novo nome: ");
                                administrador.setNome(FuncUtils.readOnlyLettersAndSpaces());
                                break;
                            case 2:
                                administrador.setCpf(FuncUtils.readCPF());
                                break;
                            case 3:
                                administrador.setTelefone(FuncUtils.readPhoneNumber());
                                break;
                            case 4:
                                administrador.setDataNasc(FuncUtils.readDate());
                                break;
                            case 5:
                                System.out.println("Insira o novo sexo do administrador:");
                                administrador.setSexo(FuncUtils.readSex());
                                break;
                            case 6:
                                administrador.setSalario(FuncUtils.readSalary());
                                break;
                            case 7:
                                System.out.print("Insira a nova data de admissão, ");
                                administrador.setDataDeAdmissao(FuncUtils.readDate());
                                break;
                            case 8:
                                System.out.print("Insira o novo horário de trabalho (início), ");
                                administrador.setHorarioDeTrabalhoInicio(FuncUtils.readTime());
                                break;
                            case 9:
                                System.out.print("Insira o novo horário de trabalho (final), ");
                                administrador.setHorarioDeTrabalhoFinal(FuncUtils.readTime());
                                break;
                            case 10:
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                        if (opcao2 != 10) {
                            AdministradorDao.editarAdministrador(administrador, db);
                        }
                    } else {
                        System.out.println("Administrador não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o login do adm: ");
                    login = FuncUtils.readLogin();
                    administrador = AdministradorDao.buscaAdministrador(login, db);
                    if (administrador == null) {
                        System.out.println("Administrador não encontrado.");
                        break;
                    }
                    AdministradorDao.excluirAdministrador(administrador, db);
                    break;
                case 4:
                    administradores = AdministradorDao.listarAdministradores(db);
                    if (administradores.isEmpty()) {
                        System.out.println("Nenhum administrador cadastrado.");
                        break;
                    }
                    System.out.printf("|Nome%s|Sexo%s|Salário%s|Login%s\n",
                            FuncUtils.spacesGenerator(26), FuncUtils.spacesGenerator(9),
                            FuncUtils.spacesGenerator(3),
                            FuncUtils.spacesGenerator(12), FuncUtils.spacesGenerator(8));
                    for (Administrador a : administradores) {
                        System.out.printf("|%-30s|%-13s|%-10.2f|%s\n", a.getNome(), a.getSexo(), a.getSalario(),
                                a.getLogin());
                    }
                    System.out.println();
                    break;
                case 5:
                    System.out.print("Digite o login do adm: ");
                    login = FuncUtils.readLogin();
                    administrador = AdministradorDao.buscaAdministrador(login, db);
                    if (administrador != null) {
                        System.out.println("\n" + administrador);
                    } else {
                        System.out.println("Administrador não encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void displayMenu() {
        System.out.println("----------- MENU ADM -----------");
        System.out.println("[1] - Cadastrar administrador");
        System.out.println("[2] - Editar administrador");
        System.out.println("[3] - Excluir administrador");
        System.out.println("[4] - Listar administradores");
        System.out.println("[5] - Buscar administrador");
        System.out.println("[6] - Sair");
        System.out.print("Digite sua opcao: ");
    }
}