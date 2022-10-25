// ======= IP_PROTOCOL ==============================================================

import java.util.LinkedList;

public class IP_PROTOCOL {

    private LinkedList<String> listIp = new LinkedList<String>();
    private String stringInitIp;
    private String mask;
    private long longInitIp;
    private long longFinalIp;

    public IP_PROTOCOL(String networkAddress) {
        // Split endereçoRede e seta atributos
        stringInitIp = networkAddress.split("/")[0];
        mask = networkAddress.split("/")[1];
    }

    public void printIpRange() {

        // Transforma o endereço rede em uma LinkedList de String contendo os bits de
        // cada numero
        listIp = linkedListIp(stringInitIp);
        System.out.println(listIp);

        // transforma a string de binarios no long correspondente
        longInitIp = binaryToLong(listIp);

        // System.out.println("longIP: " + longInitIp);

        longFinalIp = longInitIp + getIpRange(mask); // Soma o range da mascara ao longIp para saber a
                                                     // distancia, agr basta transformar o long em Ip

        // System.out.println("newLongIP: " + longIp);
        System.out.println("Range: " + stringInitIp + " ~ " + longToIpAddressString(longFinalIp));

    }

    private LinkedList<String> linkedListIp(String x) {
        String[] octs = x.split("\\.");
        LinkedList<String> list = new LinkedList<String>();

        for (String oct : octs) {
            String l = "";

            l = toBinaryString(oct);
            list.add(l);
        }

        // completa os zeros a esquerda faltando nos elementos da LinkedList e retorna-o
        return completeLeftZeroes(list);
    }

    private LinkedList<String> completeLeftZeroes(LinkedList<String> x) {
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

    public String toBinaryString(String x) {
        long l = Long.parseLong(x);
        String result = Long.toBinaryString(l);
        return result;
    }

    public Long binaryToLong(LinkedList<String> x) {
        String longString = "";
        long l = 0;

        for (String s : x) {
            longString += s;
        }

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

    public Integer getIpRange(String mask) {
        int quantHosts = 0;

        // Seta a String de mascara de subrede
        String netMask = "";
        for (int i = 0; i < 32; i++) {
            if (i >= Long.parseLong(mask)) {
                netMask += "0";
                quantHosts += 1;
            } else {
                netMask += "1";
            }
        }

        quantHosts = (int) Math.pow(2, quantHosts);

        System.out.println("Quantidade de Hosts: " + quantHosts);
        // System.out.println("Subnet Maks: " + netMask);

        return quantHosts;
    }

    public String longToIpAddressString(Long longIp) {
        String ip = "";
        ip += ((longIp & 0b11111111000000000000000000000000) >> 24) + ".";
        ip += ((longIp & 0b111111110000000000000000) >> 16) + ".";
        ip += ((longIp & 0b1111111100000000) >> 8) + ".";
        ip += (longIp & 0b11111111);

        return ip;
    }

    public boolean check_if_ip_belongs_to_network(String ipAddress) {
        LinkedList<String> list = new LinkedList<String>();
        long longIp = 0;

        list = linkedListIp(ipAddress);
        longIp = binaryToLong(list);

        System.out.println(longInitIp);
        System.out.println(longIp);

        if ((longIp >= longInitIp) && (longIp <= longFinalIp)) {
            System.out.println("Esse endereco de IP pertence a rede.");
            return true;
        } else {
            System.out.println("Esse endereco de IP nao pertence a rede.");
            return false;

        }

    }

}