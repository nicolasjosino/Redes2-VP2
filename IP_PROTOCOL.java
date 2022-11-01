import java.util.LinkedList;

public class IP_PROTOCOL {

    // 1° Questão
    public static void ipRange(String networkAddress) {
        LinkedList<String> listIp;

        String stringInitIp;
        String stringFinalIp;

        long longInitIp;
        long longFinalIp;

        String mask;
        int quantHosts;

        // Split endereçoRede e seta atributos
        stringInitIp = networkAddress.split("/")[0];
        mask = networkAddress.split("/")[1];


        // Transforma a String IP em uma LinkedList de String contendo os bits de cada numero
        listIp = linkedListIp(stringInitIp);

        // Transforma a string de binarios no long correspondente
        longInitIp = binaryToLong(listIp);


        // Pega a quantidade de Hosts da mascara de subrede
        quantHosts = getNumberOfHostsAllowed(mask);
        System.out.println("Quantidade de Hosts: " + quantHosts);


        // Soma o long do IP à quantHosts para saber o longFinal, que é o range do IP
        longFinalIp = longInitIp + quantHosts;


        //Agr basta transformar o numero longFinal(long) em Ip (String)
        stringFinalIp = longToIpAddressString(longFinalIp);

        System.out.println("Range: " + stringInitIp + " ~~~ " + stringFinalIp + "\n");
    }

    // 2° Questão
    public static void getSubnetNeeded(String enderecoRede, int n) {
        LinkedList<String> listIp;
        LinkedList<String> subnetList = new LinkedList<>();

        listIp = linkedListIp(enderecoRede.split("/")[0]);
//        System.out.println("listIp " + listIp);

        String mask = enderecoRede.split("/")[1];

        int result = (int) Math.ceil(Math.log(n) / Math.log(2));
        int quantHosts = getNumberOfHostsAllowed(mask);

        long longInitIp = binaryToLong(listIp);
//        System.out.println("longInitIp: " + longInitIp);

        System.out.println("Bits necessarios para " + quantHosts + " hosts: " + result);


        // Mascara de subrede + resultado
        int newSubnet = Integer.parseInt(mask) + result;

        int numberNewHosts = getNumberOfHostsAllowed(Integer.toString(newSubnet));

        if (n * numberNewHosts <= quantHosts) {
//            System.out.println("Quantidade de Hosts (" + quantHosts + ") maior/igual que quantidade de novos hosts(" + (n * numberNewHosts) + ")");

            System.out.println("Novas Subredes: ");

            for (int i = 0; i < n; i++) {
                subnetList.add(longToIpAddressString(longInitIp + (i * 32L)) + "/" + newSubnet);
                System.out.println(subnetList.get(i));
            }
        } else {
//            System.out.println("Quantidade de Hosts (" + quantHosts + ") menor que quantidade de novos hosts(" + (n * numberNewHosts) + ")");
            System.out.println("Não há como gerar subredes");
        }
    }

    // 3° Questão
    public static boolean check_if_ip_belongs_to_network(String netAddress, String ipAddress) {
        LinkedList<String> listIpAddress;
        long longIpAddress;

        LinkedList<String> listNetAddress;
        long longInitNetAddress;
        long longFinalNetAddress;
        String ipNetAddress = netAddress.split("/")[0];
        String maskNetAddress = netAddress.split("/")[1];
        int range;
//        int nHosts;


        // Retorna o long do ipAddress
        listIpAddress = linkedListIp(ipAddress);
        longIpAddress = binaryToLong(listIpAddress);

        // Retorna o long do netAddress
        listNetAddress = linkedListIp(ipNetAddress);
        longInitNetAddress = binaryToLong(listNetAddress);
        range = getNumberOfHostsAllowed(maskNetAddress);
        longFinalNetAddress = longInitNetAddress + range;

        if ((longIpAddress >= longInitNetAddress) && (longIpAddress <= longFinalNetAddress)) {
            System.out.println("O IP " + ipAddress + " pertence à rede " + netAddress + ". \n");
            return true;
        } else {
            System.out.println("O IP " + ipAddress + " NAO pertence à rede " + netAddress + ". \n");
            return false;
        }
    }

    // 4° Questão
    public static void checks_if_ip_belongs_to_subnet(LinkedList<String> subnets, String ip) {
        String subnetIp;
        String ip32bits;
        String longestPrefix = "";
        int countLongestPrefix = 0;

        ip32bits = return32BitString(ip);

        for (String s : subnets) {

            // Se IP pertencer a Subrede entao executa o codigo
            if (check_if_ip_belongs_to_network(s, ip)) {

                // Retorna uma String no formato de IP ( Extrai somente o IP do endereço de rede )
                subnetIp = s.split("/")[0];

                // Passa uma String no formato IP e retorna um String no formato 32bits
                subnetIp = return32BitString(subnetIp);

                // Checa o maior prefixo e mascara
                int sizeIp32bits = ip32bits.length();
                int count = 0;

                // Compara os 32 bits do IP e compara se os bits são iguais
                // Para cada bit igual adiciona no contador
                for (int i = 0; i < sizeIp32bits; i++) {

                    char bitIp32bits = ip32bits.charAt(i);
                    char bitSubnetIp = subnetIp.charAt(i);

                    if (bitIp32bits == bitSubnetIp) {
                        count += 1;
                    }
                }


                if (count > countLongestPrefix) {
                    countLongestPrefix = count;
                    longestPrefix = s;
                }
            }

        }

        System.out.println("Longest Prefix: " + longestPrefix);

    }


    // Recebe um IP em String -> Faz sua formatação -> Transforma cada elemento em binario ->
    // Adiciona o elemento na linkedList ->
    // Chama a função completeLeftZeros para completar a LinkedList em 8 bits completando os 0 a esquerda faltando
    private static LinkedList<String> linkedListIp(String x) {
        String[] octs = x.split("\\.");
        LinkedList<String> list = new LinkedList<>();

        for (String oct : octs) {
            String l;

            l = toBinaryString(oct);
            list.add(l);
        }

        // completa os zeros a esquerda faltando nos elementos da LinkedList e retorna-o
        return completeLeftZeroes(list);
    }

    private static LinkedList<String> completeLeftZeroes(LinkedList<String> x) {
        for (String s : x) {
            String newString = s;

            if (newString.length() < 8) {
                int tamanho = newString.length();

                while (tamanho < 8) {
                    newString = "0" + newString;
                    tamanho++;
                }

                x.set(x.indexOf(s), newString);
            }
        }

        return x;
    }


    private static String toBinaryString(String x) {
        long l = Long.parseLong(x);
        return Long.toBinaryString(l);
    }


    // Recede uma LinkedList de String e retorna o valor Long correspondente
    private static Long binaryToLong(LinkedList<String> x) {
        long l = 0;

        // Retorna uma String de Binarios de 32Bits
        String longString = getBinaryStringIp(x);


        int size = longString.length();

        for (int i = 0; i < size; i++) {
            int position = size - i - 1;

            char bit = longString.charAt(i);

            if (bit == '1') {
                l += Math.pow(2, position);
            }
        }

        return l;
    }


    // Recebe uma LinkedList de String Contendo os 4Bytes completos
    // Transforma e retorna em uma String de binarios de 32bits
    private static String getBinaryStringIp(LinkedList<String> x) {
        StringBuilder binaryString = new StringBuilder();
        for (String s : x) {
            binaryString.append(s);
        }

        return binaryString.toString();
    }


    private static Integer getNumberOfHostsAllowed(String mask) {
        int quant = 0;

        // Seta a String de mascara de subrede
        for (int i = 0; i < 32; i++) {
            if (i >= Long.parseLong(mask)) {
                quant += 1;
            }
        }

        quant = (int) Math.pow(2, quant);

        // System.out.println("Subnet Masks: " + netMask);

        return quant;
    }


    private static String longToIpAddressString(Long longIp) {
        String ip = "";
        ip += ((longIp & 0b11111111000000000000000000000000) >> 24) + ".";
        ip += ((longIp & 0b111111110000000000000000) >> 16) + ".";
        ip += ((longIp & 0b1111111100000000) >> 8) + ".";
        ip += (longIp & 0b11111111);

        return ip;
    }


    // Retorna a String de 32bits
    private static String return32BitString(String s) {
        String bit32String;

        // Cria uma LinkedList para pegar os 4Bytes da string de IP
        LinkedList<String> ipLinkedList;

        ipLinkedList = linkedListIp(s);

        // Retorna uma String de binario em 32bits
        bit32String = getBinaryStringIp(ipLinkedList);


        return bit32String;
    }


}