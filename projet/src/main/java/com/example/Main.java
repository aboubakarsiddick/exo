package com.example;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        calcul(scanner);
    }
    public static void calcul(Scanner scanner){
        int nbre1;
        int nbre2;
        int choix;
        int recommencer = 1;
        while (recommencer ==1) { 
            System.out.println("entrer le premier nombre \n");
            nbre1 = scanner.nextInt();
            System.out.println("veillez choisir l'operation a effectuer \n 1: Addition \n 2: Soustraction \n 3: Multiplication \n 4:Division");
            choix = scanner.nextInt();
            System.out.println("entrer le deuxieme nombre \n");
            nbre2 = scanner.nextInt();
    
            switch (choix) {
                case 1:
                        System.out.println("la somme donne: \n" + (nbre1 + nbre2) );
                    break;
                case  2:
                        System.out.println("la difference donne: \n" + (nbre1 - nbre2) );
                    break;
                case 3:
                        System.out.println("le produit donne: \n" + (nbre1 * nbre2) );
                    break;
                case 4:
                    if (nbre2 == 0) {
                        System.out.println("Math Error! \n" );
                    }else{
                        System.out.println("le rapport donne \n:" + (nbre1 / nbre2) );
                    }
                    break;
    
                default:
                System.out.println("Entrer invalide!");
                    // throw new AssertionError();
            }
        }
    }
}