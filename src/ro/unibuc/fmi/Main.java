package ro.unibuc.fmi;

import ro.unibuc.fmi.interogari.ServiciuAdmin;
import ro.unibuc.fmi.interogari.ServiciuStudent;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        ServiciuAdmin serviciuAdmin = new ServiciuAdmin();
        ServiciuStudent serviciuStudent = new ServiciuStudent();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Pentru modul admin apasati tasta 1.");
            System.out.println("Pentru modul vizitator apasati tasta 2.");
            System.out.println("Pentru a iesi apasati tasta 3.");
            int alegere = scanner.nextInt();
            if(alegere == 1) {
                System.out.println("Pentru a adauga o facultate apasati tasta 1.");
                System.out.println("Pentru a schimba data unui examen apasati tasta 2.");
                System.out.println("Pentru a schimba data unui interviu apasati tasta 3.");
                System.out.println("Pentru a iesi din modul admin apasati tasta 4.");
                int admin = scanner.nextInt();
                scanner.nextLine();
                switch (admin) {
                    case 1:
                        serviciuAdmin.addFac();
                        break;
                    case 2:
                        serviciuAdmin.printNumeFac();

                        System.out.println("Introduceti numele facultatii:");
                        String nume = scanner.nextLine();
                        serviciuAdmin.printMaterii(nume);
                        System.out.println("Introduceti materia:");
                        String mat = scanner.nextLine();
                        System.out.println("Introduceti luna(format 7,12,..):");
                        Integer luna = scanner.nextInt();
                        System.out.println("Introduceti ziua(format 7,28,..):");
                        Integer zi = scanner.nextInt();
                        serviciuAdmin.schimbaDataExamen(nume, luna, zi, mat);
                        break;
                    case 3:
                        serviciuAdmin.printNumeFac();

                        System.out.println("Introduceti numele facultatii:");
                        String numeI = scanner.nextLine();
                        System.out.println("Introduceti luna(format 7,12,..):");
                        Integer lunaI = scanner.nextInt();
                        System.out.println("Introduceti ziua(format 7,28,..):");
                        Integer ziI = scanner.nextInt();
                        serviciuAdmin.schimbaDataInterviu(numeI, lunaI, ziI);
                        break;
                    case 4:
                        break;
                }
            }
            if(alegere == 2) {
                System.out.println("Pentru a efectua o inscriere apasati tasta 1.");
                System.out.println("Pentru a afisa studentii in ordine alfabetica apasati tasta 2.");
                System.out.println("Pentru a afisa studentii in ordinea inscrierilor apasati tasta 3.");
                System.out.println("Pentru a afisa toate facultatile dintr-un oras apasati tasta 4.");
                System.out.println("Pentru a afisa id-ul legitimatiei dumneavoastra introduceti tasta 5 si apoi numele.");
                System.out.println("Pentru a afisa facultatile crescator dupa procentajul bacalaureatului in nota finala apasati tasta 6.");
                System.out.println("Pentru a afisa data/locatia despre admiterea unei facultati apasati tasta 7.");
                System.out.println("Pentru a vedea cati candidati sunt pe loc la o anumita facultate apasati tasta 8.");
                System.out.println("Pentru a iesi din modul vizitator apasati tasta 9.");

                int vizitator = scanner.nextInt();
                scanner.nextLine();
                switch (vizitator) {
                    case 1:
                        serviciuStudent.addStud();
                        break;
                    case 2:
                        serviciuStudent.printAlfStud();
                        break;
                    case 3:
                        serviciuStudent.printIdStud();
                        break;
                    case 4:
                        System.out.println("Introduceti un oras:");
                        serviciuStudent.printOrasFac(scanner.nextLine());
                        break;
                    case 5:
                        System.out.println("Introduceti numele dumneavoastra:");
                        serviciuStudent.printIdByNume(scanner.nextLine());
                        break;
                    case 6:
                        serviciuStudent.printProcFac();
                        break;
                    case 7:
                        serviciuAdmin.printNumeFac();

                        System.out.println("Introduceti numele facultatii:");
                        serviciuAdmin.printDetalii(scanner.nextLine());
                        break;
                    case 8:
                        serviciuAdmin.printNumeFac();
                        System.out.println("Alegeti facultatea:");

                        serviciuStudent.studPerLoc(scanner.nextLine());
                        break;
                    case 9:
                        break;
                }
            }
            if(alegere == 3) {
                break;
            }
        }
    }
}
