import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner user = new Scanner(System.in);

        System.out.println("0 - Sair");
        System.out.println("1 - 1ª Questão");
        System.out.println("2 - 2ª Questão");
        System.out.println("3 - 3ª Questão");
        System.out.println("4 - 4ª Questão");

        while (true) {
            System.out.println();
            System.out.print("Digite qual questão deseja testar: ");
            String Q = user.nextLine();

            switch (Q) {
                case "0":
                    System.out.println("Até mais!");
                    System.exit(1);
                case "1":
                    questao1(user);
                    break;
                case "2":
                    questao2(user);
                    break;
                case "3":
                    questao3(user);
                    break;
                case "4":
                    questao4(user);
                    break;
                default:
                    System.out.println("Digite um valor válido!");
                    break;
            }
        }
    }

    public static void questao1(Scanner user) {
        System.out.println("1ª Questão");

        System.out.print("Digite o endereço de rede: ");
        String rede = user.nextLine();
        IP_PROTOCOL.ipRange(rede);
    }

    public static void questao2(Scanner user) {
        System.out.println("2ª Questão");

        System.out.print("Digite o endereço de rede: ");
        String rede = user.nextLine();

        System.out.print("Digite o valor de N: ");
        int n = user.nextInt();
        user.nextLine();

        System.out.println();
        IP_PROTOCOL.getSubnetNeeded(rede, n);
    }

    public static void questao3(Scanner user) {
        System.out.println("3ª Questão");

        System.out.print("Digite o endereço de rede: ");
        String rede = user.nextLine();
        System.out.print("Digite o endereço IP: ");
        String ip = user.nextLine();

        IP_PROTOCOL.check_if_ip_belongs_to_network(rede, ip);
    }

    public static void questao4(Scanner user) {
        System.out.println("4ª Questão");

        LinkedList<String> redes = new LinkedList<>();

        System.out.print("Digite a quantidade de endereços de rede: ");
        int n = user.nextInt();
        user.nextLine();

        System.out.println("Digite os " + n + " endereços de rede: ");
        for (int i = 0; i < n; i++) {
            String rede = user.nextLine();
            redes.add(rede);
        }
        System.out.print("Digite o endereço IP: ");
        String ip = user.nextLine();

        IP_PROTOCOL.checks_if_ip_belongs_to_subnet(redes, ip);
    }
}