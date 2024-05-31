package desafio;

import java.util.Random;
import java.util.Scanner;

public class AlgoritmoPalpile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1;
        int tentativa = 0;
        int palpite = 0;

        System.out.println("Tente adivinhar o número entre 1 e 100");

        while (palpite != numeroSecreto){
            System.out.print("Digite seu palpite: ");
            palpite = scanner.nextInt();
        }

    }
}
