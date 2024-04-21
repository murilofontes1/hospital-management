package src.views;

import java.sql.SQLException;
import java.util.ArrayList;

import src.controllers.EnfermariaDao;
import src.controllers.EnfermeiroDao;
import src.data.Banco;
import src.models.Enfermaria;
import src.models.Enfermeiro;
import src.utils.FuncUtils;

public class EnfermariaView {
    public static void wardMenu(Banco db) throws SQLException {
        int option = 0, leitos = 0, leitosDisponiveis = 0;
        String codEnfermaria;
        Enfermaria enfermaria;
        ArrayList<Enfermaria> enfermarias;
        ArrayList<Enfermeiro> enfermeiros;
        while (option != 6) {
            displayMenu();
            option = FuncUtils.readInt();
            FuncUtils.clearScreen();
            switch (option) {
                case 1:
                    enfermeiros = EnfermeiroDao.listarEnfermeirosSemEnfermaria(db);
                    if (enfermeiros.isEmpty()) {
                        System.out.println("Nenhum enfermeiro disponível para adicionar a enfermaria.");
                        break;
                    }
                    System.out.print("Insira a quantidade de leitos da enfermaria: ");
                    leitos = FuncUtils.readInt();

                    EnfermariaDao.cadastrarEnfermaria(new Enfermaria(leitos), db);
                    do {
                        System.out.println("Enfermeiros disponíveis para a enfermaria: ");
                        EnfermeiroView.listNurses(enfermeiros);
                        System.out.print("Insira o Coren do enfermeiro que deseja adicionar a enfermaria, ");
                        String coren = FuncUtils.readCoren();
                        Enfermeiro enfermeiro = EnfermeiroDao.buscaEnfermeiro(coren, db);
                        enfermaria = EnfermariaDao.ultimaEnfermariaCadastrada(db);
                        if (enfermeiro != null) {
                            enfermeiro.setCodEnfermaria(enfermaria.getCodEnfermaria());
                            EnfermeiroDao.editarEnfermeiro(enfermeiro, db);
                        } else {
                            System.out.println("Enfermeiro não encontrado.");
                        }
                        if (enfermeiros.size() > 1) {
                            enfermeiros = EnfermeiroDao.listarEnfermeirosSemEnfermaria(db);
                        } else {
                            System.out.println("Nenhum enfermeiro disponível para adicionar a enfermaria.");
                            break;
                        }
                    } while (FuncUtils.readYesOrNo("Deseja adicionar mais enfermeiros a enfermaria? (s/n) "));

                    break;
                case 2:
                    System.out.print("Insira o codigo da enfermaria que deseja buscar: ");
                    String cod = FuncUtils.readCod();
                    enfermaria = EnfermariaDao.buscaEnfermaria(cod, db);
                    if (enfermaria != null) {
                        System.out.println(enfermaria);
                    } else {
                        System.out.println("Enfermaria não encontrada.");
                    }
                    break;
                case 3:
                    System.out.print("Insira o codigo da enfermaria que deseja editar: ");
                    codEnfermaria = FuncUtils.readCod();
                    enfermaria = EnfermariaDao.buscaEnfermaria(codEnfermaria, db);
                    if (enfermaria != null) {
                        System.out.print("Insira a nova quantidade de leitos da enfermaria: ");
                        leitos = FuncUtils.readInt();
                        leitosDisponiveis = (enfermaria.getLeitosDisponiveis() - enfermaria.getQtdeLeitos()) + leitos;
                        enfermaria.setQtdeLeitos(leitos);
                        enfermaria.setLeitosDisponiveis(leitosDisponiveis);
                        EnfermariaDao.editaEnfermaria(enfermaria, db);
                    } else {
                        System.out.println("Enfermaria não encontrada.");
                    }
                    break;
                case 4:
                    System.out.print("Insira o codigo da enfermaria que deseja excluir: ");
                    codEnfermaria = FuncUtils.readCod();
                    enfermaria = EnfermariaDao.buscaEnfermaria(codEnfermaria, db);
                    if (enfermaria != null) {
                        EnfermariaDao.excluirEnfermaria(enfermaria, db);
                    } else {
                        System.out.println("Enfermaria não encontrada.");
                    }
                    break;
                case 5:
                    enfermarias = EnfermariaDao.listarEnfermarias(db);
                    if (enfermarias.isEmpty()) {
                        System.out.println("Nenhuma enfermaria cadastrada.");
                        break;
                    }
                    System.out.printf("|Cod%s|Leitos Totais|Leitos disponiveís\n", FuncUtils.spacesGenerator(4));
                    for (Enfermaria e : enfermarias) {
                        System.out.printf("|%-7s|%-13d|%-18d\n", e.getCodEnfermaria(), e.getQtdeLeitos(),
                                e.getLeitosDisponiveis());
                    }
                    System.out.println();
                    break;
                case 6:
                    enfermarias = EnfermariaDao.listarEnfermarias(db);
                    if (enfermarias.isEmpty()) {
                        System.out.println("Nenhuma enfermaria cadastrada.");
                        break;
                    }
                    System.out.print("Insira o codigo da enfermaria que deseja vincular enfermeiros: ");
                    codEnfermaria = FuncUtils.readCod();
                    enfermaria = EnfermariaDao.buscaEnfermaria(codEnfermaria, db);
                    if (enfermaria != null) {
                        enfermeiros = EnfermeiroDao.listarEnfermeirosSemEnfermaria(db);
                        if (enfermeiros.isEmpty()) {
                            System.out.println("Nenhum enfermeiro disponível para adicionar a enfermaria.");
                            break;
                        }
                        do {
                            System.out.println("Enfermeiros disponíveis para a enfermaria: ");
                            EnfermeiroView.listNurses(enfermeiros);
                            System.out.print("Insira o Coren do enfermeiro que deseja adicionar a enfermaria, ");
                            String coren = FuncUtils.readCoren();
                            Enfermeiro enfermeiro = EnfermeiroDao.buscaEnfermeiro(coren, db);
                            if (enfermeiro != null) {
                                enfermeiro.setCodEnfermaria(enfermaria.getCodEnfermaria());
                                EnfermeiroDao.editarEnfermeiro(enfermeiro, db);
                            } else {
                                System.out.println("Enfermeiro não encontrado.");
                            }
                            if (enfermeiros.size() > 1) {
                                enfermeiros = EnfermeiroDao.listarEnfermeirosSemEnfermaria(db);
                            } else {
                                System.out.println("Nenhum enfermeiro disponível para adicionar a enfermaria.");
                                break;
                            }
                        } while (FuncUtils.readYesOrNo("Deseja adicionar mais enfermeiros a enfermaria? (s/n) "));
                    } else {
                        System.out.println("Enfermaria não encontrada.");
                    }
                    break;
                case 7:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void listWards(ArrayList<Enfermaria> enfermarias) {
        System.out.printf("|Cod%s|Leitos Totais|Leitos disponiveís\n", FuncUtils.spacesGenerator(4));
        for (Enfermaria e : enfermarias) {
            System.out.printf("|%-7s|%-13d|%-18d\n", e.getCodEnfermaria(), e.getQtdeLeitos(),
                    e.getLeitosDisponiveis());
        }
    }

    public static void displayMenu() {
        System.out.println("----------- MENU ENFERMARIA -----------");
        System.out.println("[1] - Cadastrar enfermaria");
        System.out.println("[2] - Buscar enfermaria");
        System.out.println("[3] - Editar enfermaria");
        System.out.println("[4] - Excluir enfermaria");
        System.out.println("[5] - Listar enfermarias");
        System.out.println("[6] - Vincular enfermeiros a enfermaria");
        System.out.println("[7] - Sair");
        System.out.print("Digite sua opção: ");
    }
}
