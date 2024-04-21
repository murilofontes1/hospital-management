package src.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncUtils {
    public static Scanner input = new Scanner(System.in);

    public static void fechaScanner() {
        input.close();
    }

    public static String readLogin() {
        String login;
        do {
            login = input.nextLine().trim(); // Remove espaços em branco no início e no final
            if (login.contains(" ")) {
                System.out.println("O login não pode conter espaços em branco.");
            }
        } while (login.contains(" "));
        return login;
    }

    public static String readPassword() {
        String pass;
        do {
            pass = input.nextLine().trim(); // Remover espaços em branco no início e no final
            if (pass.length() < 6) {
                System.out.println("A senha deve ter pelo menos 6 caracteres.");
            } else if (pass.contains(" ")) {
                System.out.println("A senha não pode conter espaços em branco.");
            }
        } while (pass.length() < 6 || pass.contains(" "));
        return pass;
    }

    public static void clearScreen() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                // Unix-like OS (Linux/Unix)
                try {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (os.contains("mac")) {
                // Mac OS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else {
                // OS not supported
                throw new RuntimeException("Sistema operacional não suportado.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String dateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        // Convertendo a data em uma string formatada
        String strDate = formatter.format(date);
        return strDate;
    }

    public static int readInt() {
        while (true) {
            try {
                String inputStr = input.nextLine().trim();
                return Integer.parseInt(inputStr);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número inteiro válido.");
            }
        }
    }

    public static String readOnlyLettersAndSpaces() {
        while (true) {
            String entrada = input.nextLine().trim(); // Remover espaços em branco no início e no final

            // Verificar se a entrada é vazia
            if (entrada.isEmpty()) {
                System.out.println("Por favor, digite algo.");
                continue;
            }

            // Verificar se a entrada contém apenas letras e espaços
            if (entrada.matches("[a-zA-Z ]+")) {
                return entrada;
            } else {
                System.out.println("Por favor, digite apenas letras e espaços.");
            }
        }
    }

    public static String readCPF() {
        while (true) {
            System.out.print("Digite um CPF (apenas números): ");
            String cpf = input.nextLine().trim();

            // Remover caracteres de pontuação do CPF, se houver
            cpf = cpf.replaceAll("[^0-9]", "");

            // Verificar se o CPF tem 11 dígitos
            if (cpf.length() != 11) {
                System.out.println("CPF inválido. Por favor, digite exatamente 11 números.");
                continue;
            }

            // Verificar se o CPF contém apenas dígitos
            if (cpf.matches("[0-9]+")) {
                return cpf;
            } else {
                System.out.println("CPF inválido. Por favor, digite apenas números.");
            }
        }
    }

    public static String readPhoneNumber() {
        while (true) {
            System.out.print("Digite um número de telefone (apenas números): ");
            String phoneNumber = input.nextLine().trim();

            // Remover caracteres que não sejam números do telefone
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "");

            // Verificar se o número de telefone tem pelo menos 10 dígitos (formato
            // brasileiro)
            if (phoneNumber.length() < 10) {
                System.out.println("Número de telefone inválido. Por favor, digite pelo menos 10 números.");
                continue;
            }

            // Verificar se o número de telefone contém apenas dígitos
            if (phoneNumber.matches("[0-9]+")) {
                return phoneNumber;
            } else {
                System.out.println("Número de telefone inválido. Por favor, digite apenas números.");
            }
        }
    }

    public static Date readDate() {
        while (true) {
            System.out.print("Digite uma data no formato YYYY-MM-DD: ");
            String dateInput = input.nextLine().trim();

            // Verifica se a entrada tem o formato correto
            if (dateInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                // Convertendo a string para java.sql.Date
                Date sqlDate = Date.valueOf(dateInput);
                return sqlDate;
            } else {
                System.out.println("Formato de data inválido. Por favor, digite no formato YYYY-MM-DD.");
            }
        }
    }

    public static boolean readSex() {
        while (true) {
            System.out.print("Digite o sexo (M/F): ");
            String sexo = input.nextLine().trim().toUpperCase();

            if (sexo.equals("M"))
                return true;
            else if (sexo.equals("F")) {
                return false;
            } else {
                System.out.println("Sexo inválido. Por favor, digite M para masculino ou F para feminino.");
            }
        }
    }

    public static boolean readHospitalized() {
        while (true) {
            System.out.print("O paciente está internado? (S/N): ");
            String internado = input.nextLine().trim().toUpperCase();

            if (internado.equals("S"))
                return true;
            else if (internado.equals("N")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Por favor, digite S para sim ou N para não.");
            }
        }
    }

    public static boolean readNeedToHospitalize() {
        while (true) {
            System.out.print("O paciente precisa ser internado? (S/N): ");
            String resposta = input.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                return true;
            } else if (resposta.equals("N")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Por favor, digite S para sim ou N para não.");
            }
        }
    }

    public static boolean readHealthPlan() {
        while (true) {
            System.out.print("O paciente possui plano de saúde? (S/N): ");
            String planoDeSaude = input.nextLine().trim().toUpperCase();

            if (planoDeSaude.equals("S"))
                return true;
            else if (planoDeSaude.equals("N")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Por favor, digite S para sim ou N para não.");
            }
        }
    }

    public static String readCod() {
        while (true) {
            String cod = input.nextLine().trim();

            if (cod.matches("[0-9]+")) {
                return cod;
            } else {
                System.out.println("Código inválido. Por favor, digite apenas números.");
            }
        }
    }

    public static String readCrm() {
        while (true) {
            System.out.print("Digite o CRM (no formato 'CRM/UF 123456'): ");
            String crm = input.nextLine().trim();

            // Verificar se o CRM está no formato correto
            if (crm.matches("CRM/[A-Z]{2} \\d{6}")) {
                return crm;
            } else {
                System.out.println("Formato de CRM inválido. Por favor, digite no formato 'CRM/UF 123456'.");
            }
        }
    }

    public static String readCoren() {
        while (true) {
            System.out.print("Digite o Coren (no formato 'Coren-UF 123.456'): ");
            String coren = input.nextLine().trim();

            // Verificar se o Coren está no formato correto
            if (coren.matches("Coren-[A-Z]{2} \\d{3}.\\d{3}")) {
                System.out.println("Digite a categoria");
                System.out.println("[1] - Enfermeiro(a)");
                System.out.println("[2] - Obstetro(iz)");
                System.out.println("[3] - Tecnico(a)");
                System.out.println("[4] - Auxiliar");
                System.out.println("[5] - Parteiro(a)");
                System.out.print("Digite a opção: ");
                int op = readInt();
                switch (op) {
                    case 1:
                        coren = coren + "-ENF";
                        break;
                    case 2:
                        coren = coren + "-OBST";
                        break;
                    case 3:
                        coren = coren + "-TE";
                        break;
                    case 4:
                        coren = coren + "-AE";
                        break;
                    case 5:
                        coren = coren + "-PAR";
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                return coren;
            } else {
                System.out.println("Formato de CRM inválido. Por favor, digite no formato 'Coren-UF 123.456'.");
            }
        }
    }

    public static double readSalary() {
        while (true) {
            try {
                System.out.print("Digite o salário: ");
                double salary = Double.parseDouble(input.nextLine().trim());
                if (salary >= 0) {
                    return salary;
                } else {
                    System.out.println("O salário não pode ser negativo. Por favor, digite um valor válido.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um valor numérico para o salário.");
            }
        }
    }

    public static Time readTime() {
        while (true) {
            try {
                System.out.print("Digite o horário (no formato HH:MM:SS): ");
                String timeInput = input.nextLine().trim();

                // Verificar se o horário está no formato correto
                if (timeInput.matches("\\d{2}:\\d{2}:\\d{2}")) {
                    // Convertendo a string para java.sql.Time
                    Time sqlTime = Time.valueOf(timeInput);
                    return sqlTime;
                } else {
                    System.out.println("Formato de horário inválido. Por favor, digite no formato HH:MM:SS.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Horário inválido. Por favor, digite um horário válido.");
            }
        }
    }

    public static boolean readShift() {
        while (true) {
            System.out.print("O médico está de plantão? (S/N): ");
            String resposta = input.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                return true;
            } else if (resposta.equals("N")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Por favor, digite S para sim ou N para não.");
            }
        }
    }

    public static String encryptMD5(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte hash[] = algorithm.digest(senha.getBytes("UTF-8"));

        StringBuilder texto = new StringBuilder();
        for (byte b : hash) {
            texto.append(String.format("%02X", 0xFF & b));
        }
        return texto.toString();
    }

    public static ArrayList<String> readSymptoms() {
        ArrayList<String> sintomas = new ArrayList<String>();
        while (true) {
            System.out.print("Digite um sintoma (ou 'sair' para encerrar): ");
            String sintoma = readOnlyLettersAndSpaces();

            if (sintoma.equalsIgnoreCase("sair")) {
                break;
            } else {
                sintomas.add(sintoma);
            }
        }
        return sintomas;
    }

    public static String readPosology() {
        System.out.println("Digite a posologia do medicamento\n");

        System.out.print("Digite a frequência (por exemplo, '8 horas'): ");
        String frequencia = input.nextLine();

        System.out.print("Digite a duração (por exemplo, '7 dias'): ");
        String duracao = input.nextLine();

        String posologia = String.format("Frequência: %s, Duração: %s", frequencia, duracao);
        return posologia;
    }

    public static String readDosage() {
        System.out.println("Digite a dosagem do medicamento\n");

        System.out.print("Digite a quantidade (por exemplo, '1 comprimido'): ");
        String quantidade = input.nextLine();

        System.out.print("Digite a dose (por exemplo, '500 mg'): ");
        String dose = input.nextLine();
        String dosagem = String.format("Quantidade: %s, Dose: %s", quantidade, dose);

        return dosagem;
    }

    public static String spacesGenerator(int n) {
        return " ".repeat(n);
    }

    public static boolean readYesOrNo(String string) {
        while (true) {
            System.out.print(string);
            String resposta = input.nextLine().trim().toUpperCase();

            if (resposta.equals("S")) {
                return true;
            } else if (resposta.equals("N")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Por favor, digite S para sim ou N para não.");
            }
        }
    }
}
