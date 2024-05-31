package desafio;

import java.util.Scanner;

public class AlgoritmoCalculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();


        System.out.print("Escolha a operação: ");
        char operacao = scanner.next().charAt(0);

        System.out.println("Resultado: ");

        double resultado;
    }
}
