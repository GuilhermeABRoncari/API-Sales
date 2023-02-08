import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        //fazendo algumas alterações para testar os commits
        Scanner teclado = new Scanner(System.in);
        System.out.println("Enter a number: ");
        int n1 = teclado.nextInt();
        System.out.println("Enter another number");
        int n2 = teclado.nextInt();
        int soma = n1 + n2;
        System.out.println("sum of " + n1 + " and " + n2 + " = " + soma);
    }
}