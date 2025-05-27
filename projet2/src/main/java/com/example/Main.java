// application de gestion des stocks utilisant le crud
/*
on aura la possiblite:
    d'ajouter un pruduit
    supprimer un projet
    mettre a jour un produit
    supprimer un produit
*/

package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class Main {

    private static String Nom;
    private static int Prix;
    private static int Quantite;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int choix = 1;
        while (choix > 0 && choix < 6) { 
            System.out.println("1: Ajouter un produit \n");
            System.out.println("2: Lister les produits \n");
            System.out.println("3: Mettre a jour un prodduit \n");
            System.out.println("4: Supprimer un produit \n");
            System.out.println("5: Passer une commande de produit \n");
            System.out.println("6: Quitter \n");

            System.out.println("veillez faire votre choix \n");
            choix = scanner.nextInt();scanner.nextLine();

            switch (choix) {
                case 1:
                    Produits(scanner);
                    break;
                    case 2:
                    // lire les produits dans le fichier file 
                    ReadFile(scanner);
                    break;
                    case 3:
                        try{
                            Update(scanner);
                            System.out.println("Mise a jour reussi !");
                        }catch(Exception e){
                            System.out.println("Erreur de mise a jour :" + e.getMessage());
                        }
                            
                    //         break;
                    //     default:
                    //         System.out.println("Entrer invalid !!");
                    // }
                    // Upadte();
                    break;
                    case 4:
                    // supprimeer les produits dans le fichier file
                    Delete(scanner);
                    break;
                    case 5:
                    Commande(scanner);
                    break;
                    case 6:
                        // sortir de l'application
                        break;
                default:System.out.println("Erreur lors du choix :\n" );
            }
        }

    }

    public static void Produits(Scanner scanner) throws Exception{
     System.out.println("entrer le nom du produit \n");
      Nom = scanner.next();
      scanner.nextLine();

     System.out.println("entrer le prx du produit \n");
      Prix = scanner.nextInt();
      scanner.nextLine();

     System.out.println("entrer la quantite du produit \n");
      Quantite = scanner.nextInt();  
     scanner.nextLine();

     writeFile();
    }

    public static void writeFile() throws Exception {
        
        String Writes = "file.txt";
        // String contenue = "bonjour faha";
        try (BufferedReader read = new BufferedReader(new FileReader(Writes))){
            String line;

            // while ((line = read.readLine()) != null) { 
            //     if (Nom.equals(Nom)) {
            //         System.err.println("le produit existe deja dans le fichier ! \n");
            //         return;

            //     }
            // }
        }


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(Writes, true))) {
            writer.write(Nom + " " + Prix + " " + Quantite + "\n");          
                System.out.println("contenue ecrit avec succes dans le fichier \n");
            
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ecriture dans le fichier :" + e.getMessage());
        }
    }
    public static void ReadFile(Scanner scanner){
        String file = "file.txt";
        try (BufferedReader read = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = read.readLine()) != null) { 
                System.out.println(line);
                if (!line.isEmpty()) {
                    String[] parts = line.split(" ");
                    if (parts.length != 3) throw new Exception("Infos incorrectes");
                    Nom = parts[0];
                    Prix = Integer.parseInt(parts[1]);
                    Quantite = Integer.parseInt(parts[2]);

                    System.out.println("Nom :" + Nom);
                    System.out.println("Prix :" + Prix);
                    System.out.println("Quantite :" + Quantite);
                    System.out.println();
                    System.out.println("************************************************ \n");

                }
            }
        } catch (Exception e) {
            System.out.println("erreur lors de la lecture du fichier \n");
        }
    }
    public static void Update(Scanner scanner) throws Exception{
        String files = "file.txt";
        String filetemporaire = "file1.txt";
        String[] newOptions = {"le nouveau nom", "le nouveau prix", "la nouvelle qte"};

        int option;
        System.out.println("Que voulez mettre a jour ? \n 1 : Le nom \n 2: Le prix \n 3 : La quantite ?\n");
        option = scanner.nextInt();

        System.out.print("Entrer le nom du produit a modifier: \n");
        String oldNom = scanner.next();
        scanner.nextLine();
        System.out.print("Entrer " + newOptions[option-1] + ": \n");
        String newData = scanner.next();scanner.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader(files));
                BufferedWriter writer = new BufferedWriter(new FileWriter(filetemporaire, true))){

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                Object[] parts = ligne.split(" ");
                if (parts.length != 3) throw new Exception("Infos incorrectes");

                String nom = parts[0].toString();
                if (nom.equals(oldNom)) {
                    List<Object> list = Arrays.asList(parts);
                    list.set(option-1, newData);
                    Object[] parts2 = list.toArray();
                    writer.write(parts2[0] + " " + parts2[1] + " " + parts2[2] + "\n");
                    continue;
                }
                writer.write(ligne);
                writer.write("\n");
            }
        }catch(IOException e){
            System.out.println("Erreur lors de la mise a jour du fichier :" + e.getMessage());
        }
        java.io.File fichierOriginale = new java.io.File(files);
        java.io.File fichiertmp = new java.io.File(filetemporaire);
        fichierOriginale.delete();
        fichiertmp.renameTo(fichierOriginale);
    }

    public static void Delete(Scanner scanner) {
        String file = "file.txt";
        String filetemporaire = "file1.txt";

        System.out.println("Entrer le nom du produit a supprimer \n");
        String delete = scanner.next();
        String ligne;

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filetemporaire, true)))  {

            while ((ligne = reader.readLine()) != null) {
                Object[] parts = ligne.split(" ");
                List<Object> list = Arrays.asList(parts);
                Object[] parts2 = list.toArray();
                String nom = parts[0].toString();
                if (nom.equals(delete)) {
                  System.out.println(nom + " " + " supprimer avec succes \n");
                    continue;
                }
                writer.write(parts2[0] + " " + parts2[1] + " " + parts2[2] + "\n");
                writer.write(ligne);
                writer.write("\n");
            }
        } catch(IOException exception){
            System.out.println("Erreur lors de la suppresion \n");
        }
        java.io.File fichierOriginale = new java.io.File(file);
        java.io.File fichiertmp = new java.io.File(filetemporaire);
        fichierOriginale.delete();
        fichiertmp.renameTo(fichierOriginale);
    }
    public static void Commande(Scanner scanner) throws Exception{
        String file = "file.txt";
        String fileCommade = "commande.txt";
        String filetemporaires = "tempo.txt";
        // String nomCommande;
        // int prixCommande;
        // int quantiteCommande;
        // String commande;
        // System.out.println("Que desirer vous commander ? \n");
        // commande = scanner.next();

        // System.err.println("entrer le nom le l'article : \n");
        // nomCommande = scanner.next();
        // System.err.println("entrer la quantite : \n");
        // quantiteCommande = scanner.nextInt();

        // String ligne;

        // try (BufferedReader reader = new BufferedReader(new FileReader(file));
        // BufferedWriter writer = new BufferedWriter(new FileWriter(fileCommade, true)))  {

        // while ((ligne = reader.readLine()) != null) {
        //     Object[] parts = ligne.split(" ");
        //     List<Object> list = Arrays.asList(parts);
        //     Object[] parts2 = list.toArray();
        //     String nom = parts[0].toString();
        //     String Prix = parts2[1].toString();
        //     String Quantite = parts2[2].toString();
        //     int qtes = 0;
        //    int q = Integer.parseInt(Quantite);

        //         if (nom.equals(commande)  && q >= qtes) {
                    
        //             System.out.println(nom + " " + " commander avec succes \n");
        //               continue;
        //           }
                  
            
        //     writer.write(parts2[0] + " " + parts2[1] + " " + parts2[2] + "\n");
        //     writer.write(ligne);
        //     writer.write("\n");
        // }}catch(IOException e){

        // }
        String[] newOptions = {"le nom de la commande", "le prix de la commande", "la qte commande "};

        int option;
        System.out.println("Que voulez commander ? \n");
        option = scanner.nextInt();

        System.out.print("Entrer le nom du produit a commander: \n");
        String oldNom = scanner.next();
        scanner.nextLine();
        System.out.print("Entrer " + newOptions[option-1] + ": \n");
        String newData = scanner.next();
        scanner.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileCommade, true))){

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                Object[] parts = ligne.split(" ");
                if (parts.length != 3) throw new Exception("Infos incorrectes");

                String nom = parts[0].toString();
                int prix = Integer.parseInt(parts[1].toString());
                int quantite = Integer.parseInt(parts[2].toString());

                if (nom.equals(oldNom)) {
                    List<Object> list = Arrays.asList(parts);
                    list.set(option-1, newData);
                    // Object[] parts2 = list.toArray();
                   System.err.println("produit vendue avec succes ! \n");
                    continue;
                }
                writer.write(ligne);
                writer.write("\n");
            }
        }catch(IOException e){
            System.out.println("Erreur lors de la commande  du produit :" + e.getMessage());
        }
        java.io.File fichierOriginale = new java.io.File(file);
        java.io.File fichiertmp = new java.io.File(fileCommade);
        fichierOriginale.delete();
        fichiertmp.renameTo(fichierOriginale);
    }
}