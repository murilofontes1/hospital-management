package src.views;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import src.controllers.EnfermeiroDao;
import src.data.Banco;
import src.models.Enfermeiro;
import src.utils.FuncUtils;

public class EnfermeiroView {
    public static void nurseMenu(Banco db) throws SQLException {
        int opcao = 0, opcao2 = 0;
        String nome, cpf, telefone, coren;
        Date dataNasc, dataDeAdmissao;
        Time horarioDeTrabalhoInicio, horarioDeTrabalhoFinal;
        boolean sexo;
        double salario;
        Enfermeiro enfermeiro;
        ArrayList<Enfermeiro> enfermeiros;
        while (opcao != 6) {
            displayMenu();
            opcao = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    nome = FuncUtils.readOnlyLettersAndSpaces();

                    cpf = FuncUtils.readCPF();
                    telefone = FuncUtils.readPhoneNumber();

                    System.out.print("Insira a data de nascimento, ");
                    dataNasc = FuncUtils.readDate();

                    sexo = FuncUtils.readSex();
                    coren = FuncUtils.readCoren();

                    System.out.print("Insira a data de admissão, ");
                    dataDeAdmissao = FuncUtils.readDate();

                    System.out.print("Insira o inicio do expediente, ");
                    horarioDeTrabalhoInicio = FuncUtils.readTime();

                    System.out.print("Insira o final do expediente, ");
                    horarioDeTrabalhoFinal = FuncUtils.readTime();

                    salario = FuncUtils.readSalary();

                    EnfermeiroDao.cadastrarEnfermeiro(
                            new Enfermeiro(nome, cpf, telefone, dataNasc, sexo, salario, dataDeAdmissao,
                                    horarioDeTrabalhoInicio, horarioDeTrabalhoFinal, coren),
                            db);
                    break;
                case 2:
                    coren = FuncUtils.readCoren();
                    enfermeiro = EnfermeiroDao.buscaEnfermeiro(coren, db);
                    if (enfermeiro != null) {
                        System.out.println(enfermeiro);
                        System.out.println("O que deseja editar?");
                        System.out.println("[1] - Nome");
                        System.out.println("[2] - CPF");
                        System.out.println("[3] - Telefone");
                        System.out.println("[4] - Data de nascimento");
                        System.out.println("[5] - Sexo");
                        System.out.println("[6] - Data de admissão");
                        System.out.println("[7] - Horário de trabalho (início)");
                        System.out.println("[8] - Horário de trabalho (final)");
                        System.out.println("[9] - Cancelar");
                        System.out.print("Digite sua opção: ");
                        opcao2 = FuncUtils.readInt();
                        switch (opcao2) {
                            case 1:
                                System.out.print("Digite o novo nome: ");
                                enfermeiro.setNome(FuncUtils.readOnlyLettersAndSpaces());
                                break;
                            case 2:
                                enfermeiro.setCpf(FuncUtils.readCPF());
                                break;
                            case 3:
                                enfermeiro.setTelefone(FuncUtils.readPhoneNumber());
                                break;
                            case 4:
                                enfermeiro.setDataNasc(FuncUtils.readDate());
                                break;
                            case 5:
                                System.out.println("Insira o novo sexo do enfermeiro:");
                                enfermeiro.setSexo(FuncUtils.readSex());
                                break;
                            case 6:
                                System.out.print("Insira a nova data de admissão: ");
                                enfermeiro.setDataDeAdmissao(FuncUtils.readDate());
                                break;
                            case 7:
                                System.out.print("Insira o novo horário de trabalho (início): ");
                                enfermeiro.setHorarioDeTrabalhoInicio(FuncUtils.readTime());
                                break;
                            case 8:
                                System.out.print("Insira o novo horário de trabalho (final): ");
                                enfermeiro.setHorarioDeTrabalhoFinal(FuncUtils.readTime());
                                break;
                            case 9:
                                break;
                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                        if (opcao2 != 9) {
                            EnfermeiroDao.editarEnfermeiro(enfermeiro, db);
                        }
                    } else {
                        System.out.println("Enfermeiro não encontrado.");
                    }
                    break;
                case 3:
                    coren = FuncUtils.readCoren();
                    enfermeiro = EnfermeiroDao.buscaEnfermeiro(coren, db);
                    if (enfermeiro != null) {
                        EnfermeiroDao.excluirEnfermeiro(enfermeiro, db);
                    } else {
                        System.out.println("Enfermeiro não encontrado.");
                    }
                    break;
                case 4:
                    enfermeiros = EnfermeiroDao.listarEnfermeiros(db);
                    listNurses(enfermeiros);
                    break;
                case 5:
                    coren = FuncUtils.readCoren();
                    enfermeiro = EnfermeiroDao.buscaEnfermeiro(coren, db);
                    if (enfermeiro != null) {
                        System.out.println("\n" + enfermeiro);
                    } else {
                        System.out.println("Enfermeiro não encontrado.");
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

    public static void listNurses(ArrayList<Enfermeiro> enfermeiros) {
        if (enfermeiros.isEmpty()) {
            System.out.println("Nenhum enfermeiro cadastrado.");
            return;
        }
        System.out.printf("|Nome%s|Sexo%s|Salário%s|Coren%s|Enfermaria\n",
                FuncUtils.spacesGenerator(26),
                FuncUtils.spacesGenerator(9),
                FuncUtils.spacesGenerator(3),
                FuncUtils.spacesGenerator(17));
        for (Enfermeiro e : enfermeiros) {
            System.out.printf("|%-30s|%-13s|%-10.2f|%-22s|%s\n", e.getNome(), e.getSexo(),
                    e.getSalario(), e.getCoren(),
                    (e.getCodEnfermaria() == null ? "Sem enfermaria" : "Cod: " + e.getCodEnfermaria()));
        }
        System.out.println();
    }

    public static void displayMenu() {
        System.out.println("----------- MENU ENFERMEIRO -----------");
        System.out.println("[1] - Cadastrar enfermeiro");
        System.out.println("[2] - Editar enfermeiro");
        System.out.println("[3] - Excluir enfermeiro");
        System.out.println("[4] - Listar enfermeiros");
        System.out.println("[5] - Buscar enfermeiro");
        System.out.println("[6] - Sair");
        System.out.print("Digite sua opção: ");
    }
}
