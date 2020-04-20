package ro.unibuc.fmi;

import ro.unibuc.fmi.admin.Distanta;
import ro.unibuc.fmi.admin.Examen;
import ro.unibuc.fmi.admin.Facultate;
import ro.unibuc.fmi.admin.Frecventa;
import ro.unibuc.fmi.inscriere.Student;
import ro.unibuc.fmi.interogari.Serviciu;
import ro.unibuc.fmi.persistenta.Persistenta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Serviciu serviciu = new Serviciu();
        Scanner scanner = new Scanner(System.in);

        Facultate f = new Facultate();
        Student s = new Student();
        Examen ex = new Examen();
        Frecventa fr = new Frecventa();
        Distanta dist = new Distanta();


        serviciu.setArrFacultati(Persistenta.INSTANCE.citire(f, serviciu));
        serviciu.setArrStudenti(Persistenta.INSTANCE.citire(s, serviciu));


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
                        serviciu.addFac();
                        break;
                    case 2:
                        for(Facultate facultate : serviciu.getArrFacultati()) {
                            System.out.println(facultate.getNume());
                        }
                        System.out.println("Introduceti numele facultatii:");
                        String nume = scanner.nextLine();
                        serviciu.printMaterii(nume);
                        System.out.println("Introduceti materia:");
                        String mat = scanner.nextLine();
                        System.out.println("Introduceti luna(format 7,12,..):");
                        Integer luna = scanner.nextInt();
                        System.out.println("Introduceti ziua(format 7,28,..):");
                        Integer zi = scanner.nextInt();
                        serviciu.schimbaDataExamen(nume, luna, zi, mat);
                        break;
                    case 3:
                        for(Facultate facultate : serviciu.getArrFacultati()) {
                            System.out.println(facultate.getNume());
                        }
                        System.out.println("Introduceti numele facultatii:");
                        String numeI = scanner.nextLine();
                        System.out.println("Introduceti luna(format 7,12,..):");
                        Integer lunaI = scanner.nextInt();
                        System.out.println("Introduceti ziua(format 7,28,..):");
                        Integer ziI = scanner.nextInt();
                        serviciu.schimbaDataInterviu(numeI, lunaI, ziI);
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
                System.out.println("Pentru a afisa facultatile descrescator dupa procentajul bacalaureatului in nota finala apasati tasta 6.");
                System.out.println("Pentru a afisa data/locatia despre admiterea unei facultati apasati tasta 7.");
                System.out.println("Pentru a vedea cati candidati sunt pe loc la o anumita facultate apasati tasta 8.");
                System.out.println("Pentru a iesi din modul vizitator apasati tasta 9.");

                int vizitator = scanner.nextInt();
                scanner.nextLine();
                switch (vizitator) {
                    case 1:
                        serviciu.addStud();
                        break;
                    case 2:
                        serviciu.printAlfStud();
                        break;
                    case 3:
                        serviciu.printIdStud();
                        break;
                    case 4:
                        System.out.println("Introduceti un oras:");
                        serviciu.printOrasFac(scanner.nextLine());
                        break;
                    case 5:
                        System.out.println("Introduceti numele dumneavoastra:");
                        serviciu.printIdByNume(scanner.nextLine());
                        break;
                    case 6:
                        serviciu.printProcFac();
                        break;
                    case 7:
                        for(Facultate facultate : serviciu.getArrFacultati()) {
                            System.out.println(facultate.getNume());
                        }
                        System.out.println("Introduceti numele facultatii:");
                        serviciu.printDetalii(scanner.nextLine());
                        break;
                    case 8:
                        System.out.println("Alegeti facultatea:");
                        for(Facultate facultate : serviciu.getArrFacultati()) {
                            System.out.println(facultate.getNume());
                        }
                        serviciu.studPerLoc(scanner.nextLine());
                        break;
                    case 9:
                        break;
                }
            }
            if(alegere == 3) {
                Persistenta.INSTANCE.scriere(s, serviciu);
                Persistenta.INSTANCE.scriere(f, serviciu);
                Persistenta.INSTANCE.scriere(fr, serviciu);
                Persistenta.INSTANCE.scriere(dist, serviciu);
                Persistenta.INSTANCE.scriere(ex, serviciu);
                break;
            }
        }
    }
}
