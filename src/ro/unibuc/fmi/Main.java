package ro.unibuc.fmi;

import ro.unibuc.fmi.admin.Facultate;
import ro.unibuc.fmi.interogari.Serviciu;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Serviciu S = new Serviciu();
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("Pentru modul admin apasati tasta 1.");
            System.out.println("Pentru modul vizitator apasati tasta 2.");
            System.out.println("Pentru a iesi apasati tasta 3.");
            int alegere = sc.nextInt();
            if(alegere == 1) {
                System.out.println("Pentru a adauga o facultate apasati tasta 1.");
                System.out.println("Pentru a schimba data unui examen apasati tasta 2.");
                System.out.println("Pentru a schimba data unui interviu apasati tasta 3.");
                System.out.println("Pentru a iesi din modul admin apasati tasta 4.");
                int admin = sc.nextInt();
                sc.nextLine();
                switch (admin){
                    case 1:
                        S.addFac();
                        break;
                    case 2:
                        System.out.println("Introduceti numele facultatii:");
                        String nume = sc.nextLine();
                        System.out.println("Introduceti materia:");
                        String mat = sc.nextLine();
                        System.out.println("Introduceti luna(format 7,12,..):");
                        Integer luna = sc.nextInt();
                        System.out.println("Introduceti ziua(format 7,28,..):");
                        Integer zi = sc.nextInt();
                        S.schimbaDataExamen(nume, luna, zi, mat);
                        break;
                    case 3:
                        System.out.println("Introduceti numele facultatii:");
                        String numeI = sc.nextLine();
                        System.out.println("Introduceti luna(format 7,12,..):");
                        Integer lunaI = sc.nextInt();
                        System.out.println("Introduceti ziua(format 7,28,..):");
                        Integer ziI = sc.nextInt();
                        S.schimbaDataInterviu(numeI, lunaI, ziI);
                        break;
                    case 4:
                        break;
                }
            }
            if(alegere == 2){
                System.out.println("Pentru a efectua o inscriere apasati tasta 1.");
                System.out.println("Pentru a afisa studentii in ordine alfabetica apasati tasta 2.");
                System.out.println("Pentru a afisa studentii in ordinea inscrierilor apasati tasta 3.");
                System.out.println("Pentru a afisa toate facultatile dintr-un oras apasati tasta 4.");
                System.out.println("Pentru a afisa id-ul legitimatiei dumneavoastra introduceti tasta 5 si apoi numele.");
                System.out.println("Pentru a afisa facultatile descrescator dupa procentajul bacalaureatului in nota finala apasati tasta 6.");
                System.out.println("Pentru a afisa data/locatia despre admiterea unei facultati apasati tasta 7.");
                System.out.println("Pentru a vedea cati candidati sunt pe loc la o anumita facultate apasati tasta 8.");
                System.out.println("Pentru a iesi din modul vizitator apasati tasta 9.");

                int vizitator = sc.nextInt();
                sc.nextLine();
                switch (vizitator){
                    case 1:
                        S.addStud();
                        break;
                    case 2:
                        S.printAlfStud();
                        break;
                    case 3:
                        S.printIdStud();
                        break;
                    case 4:
                        System.out.println("Introduceti un oras:");
                        S.printOrasFac(sc.nextLine());
                        break;
                    case 5:
                        System.out.println("Introduceti numele dumneavoastra:");
                        S.printIdByNume(sc.nextLine());
                        break;
                    case 6:
                        S.printProcFac();
                        break;
                    case 7:
                        System.out.println("Introduceti numele facultatii:");
                        S.printDetalii(sc.nextLine());
                        break;
                    case 8:
                        System.out.println("Alegeti facultatea:");
                        for(Facultate facultate : S.getArrFacultati()){
                            System.out.println(facultate.getNume());
                        }
                        S.studPerLoc(sc.nextLine());
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
