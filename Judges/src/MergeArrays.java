/******************************************************************************
 *  Author:  Matheus Gomes de Paula
 *  RA:      122.043
 *  UNIFESP - Universidade Federal de São Paulo
 *  AED2    - Algoritmo e estrutura de dados 2
 *
 *  Task: Mesclar vetores ordenados
 *
 *  Instructions:
 *      1- E/S: tanto a entrada quanto a saı́da de dados devem ser “secas”, ou seja,
 *          não devem apresentar frases explicativas. Siga o modelo fornecido e apenas
 *          complete as partes informadas (veja o exemplo abaixo).
 *
 *      2- Identificadores de variáveis: escolha nomes apropriados.
 *      3- Documentação: inclua cabeçalho, comentários e indentação no programa.
 *      4- Submeta o programa no sistema judge: http://judge.sjc.unifesp.br/ (acesso
 *          remoto por VPN).
 *      5- O programa pode ser escrito em C, C++ ou Java.
 *
 *  Description: Implemente um algoritmo que mescle dois vetores de números inteiros já
 *               ordenados e crie um terceiro vetor, igualmente ordenado de números inteiros.
 *
 *  Input:  A entrada consiste de 4 linhas onde a primeira define o tamanho do primeiro
 *          vetor (array VETA), limitado a 100 elementos do tipo inteiro. Na segunda linha
 *          deve-se informar cada elementos de VETA (sempre números inteiros positivos,
 *          negativos ou zero). Na terceira e quarta linha o procedimento se repete mas para o
 *          segundo array, ou seja, deve-se informar o tamanho de VETB e os elementos do
 *          próprio array VETB.
 *
 *  Output: Imprima o vetor mesclado resultante com os números em ordem crescente.
 *
 *  Example:
 *      input:  4
 *              1 10 23 45
 *              5
 *              2 3 4 92 98
 *
 *      output: 1 2 3 4 10 23 45 92 98
 *
 *      input:  3
 *              10 20 30
 *              6
 *              1 15 35 40 70 80
 *
 *      output: 1 10 15 20 30 35 40 70 80
 *
 ******************************************************************************/
import java.util.*;

public class MergeArrays {

    public static void merge2SortedArrays(int []a1, int []a2, int a1Size, int a2Size, int []a3) {
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a1Size && j < a2Size){
            if(a1[i] < a2[j])
                a3[k++] = a1[i++];
            else
                a3[k++] = a2[j++];
        }

        while (i < a1Size)
            a3[k++] = a1[i++];

        while (i < a2Size)
            a3[k++] = a2[j++];
    }

    public static void printArray(int a1Size, int a2Size, int []a3) {
        for(int i = 0; i < a1Size + a2Size; i++) {
            System.out.println(a3[i] + " ");
        }
    }

    public static int[] readValuesFromCommandLine() {

        Scanner sInt = new Scanner(System.in);
        int arraySize = sInt.nextInt();
        int []array = new int[arraySize];
        for(int i = 0; i < arraySize; i ++) {
            array[i] = sInt.nextInt();
        }
//        sInt.close();
        return array;
    }

    public static void main(String[] args) {
        System.out.println("primeiro");
        int []array1 = readValuesFromCommandLine();
        int array1Length = array1.length;
        System.out.println("segundo");
        int []array2 = readValuesFromCommandLine();
        int array2Length = array2.length;
        int []array3 = new int[array1Length + array2Length];

        merge2SortedArrays(array1, array2, array1Length, array2Length, array3);
        printArray(array1Length, array2Length, array3);
    }
}
