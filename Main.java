// ======= MAIN ==============================================================

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

//Crie um sistema, em qualquer linguagem de programação, que seja capaz de fazer as seguintes operações:
//    1 - Dado um endereço de rede, calcular o primeiro e o último endereço de IP.
//    2 - Dado um endereço de rede e um valor N, indicar todas as faixas de IP para todas as subredes necessárias para endereçar N subredes.
//    3 - Dado um endereço IP e um endereço de rede, indicar se o endereço pertence à rede indicada.
//    4 - Considerando uma lista de endereços de rede e dado um endereço IP,
//        indicar qual endereço de rede contido na lista é 'dono' do endereço IP recebido. A resposta deve ser a subrede mais específica.

public class Main {
    public static void main(String[] args) {
        // Variaveis para a 1° questao
        String enderecoRede = "192.168.0.0/24";

        // Variaveis para a 2° questao
        String givenIpAddress = "192.168.0.78";

        IP_PROTOCOL ip_range = new IP_PROTOCOL(enderecoRede);
        ip_range.printIpRange();
        ip_range.check_if_ip_belongs_to_network(givenIpAddress);
    }
}