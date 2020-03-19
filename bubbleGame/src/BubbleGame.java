/*

O jogo Bolhas funciona da seguinte maneira:

A partir de uma sequência aleatória de números inteiros de tamanho N, dois ou mais jogadores se revezam nas jogadas.
Cada jogador faz um movimento, e a jogada passa para o outro jogador.
Supondo que Fulano e Ciclano estão jogando, Fulano começa sempre a jogar.
Um movimento de um jogador consiste na escolha do primeiro par de elementos consecutivos da seqüência que estejam fora
de ordem crescente e em inverter a ordem dos dois elementos. Por exemplo, dada a seqüência 1, 5, 3, 4, 2, o jogador pode
inverter as posições de 5 e 3 ou de 4 e 2, mas não pode inverter as posições de 3 e 4, nem de 5 e 2. Continuando com o
exemplo, se o jogador decide inverter as posições de 5 e 3 então a nova seqüência será 1, 3, 5, 4, 2.
Mais cedo ou mais tarde, a seqüência ficará ordenada. Perde o jogador impossibilitado de fazer um movimento.
Faça um programa que simule o funcionamento deste jogo e encontre o nome do vencedor do jogo: Fulano ou Ciclano.

Os dados de entrada para o programa devem ser os seguintes:
Em uma primeira linha deve-se ler o tamanho N da sequência de números a ser digitada, onde o tamanho máximo é N=100.
Na próxima linha deve-se ler a sequência, P = (X1, X2, ...,XN), de inteiros separados por um espaço em branco entre cada par.
Cada inteiro da sequência segue aos limites: 0 <= Pi < 1000.
O dado de saída consiste em uma única linha contendo o nome do vencedor do jogo: Fulano ou Ciclano.
Para a escolha da resposta correta abaixo considere o seguintes dados de entrada:

13
6 6 5 4 10 2 3 2 1 9 20 30 15

20
6 1 50 900 7 8 6 5 4 10 2 3 2 1 9 21 35 15 16 11

14
3 4 8 100 25 60 61 45 32 8 10 9 1 80
 */
import java.util.Scanner;
import java.util.Arrays;

public class BubbleGame {
    static void bubbleSort(int[] arr) {
        int winner = 0;
        int n = arr.length;
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr[j-1] > arr[j]){
//                    swap(arr, j);
                    //swap elements
                    temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    winner++;
                }

            }
        }
        if(winner == 0)
            System.out.println("Fulano");
        else if ((winner % 2) == 0 ) {
            System.out.println("Ciclano");
        } else {
            System.out.println("Fulano");
        }

    }

    static void swap (int[] arr, int j) {
        int temp= 0;
        temp = arr[j-1];
        arr[j-1] = arr[j];
        arr[j] = temp;
    }

    public static int [] readNumsFromCommandLine() {

        Scanner s = new Scanner(System.in);

        int count = s.nextInt();
        s.nextLine(); // throw away the newline.

        int [] numbers = new int[count];
        Scanner numScanner = new Scanner(s.nextLine());
        for (int i = 0; i < count; i++) {
            if (numScanner.hasNextInt()) {
                numbers[i] = numScanner.nextInt();
            } else {
                System.out.println("You didn't provide enough numbers");
                break;
            }
        }

        return numbers;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner( System.in );

        int[] arr = readNumsFromCommandLine();

//        System.out.println("Array Before Bubble Sort");
//        for(int i=0; i < arr.length; i++) {
//            System.out.println(arr[i] + " ");
//        }
//        System.out.println();

        bubbleSort(arr);

//        System.out.println("Array After Bubble Sort");
//        for(int i=0; i < arr.length; i++) {
//            System.out.print(arr[i] + " ");
//        }
    }
}
