package ro.unibuc.fmi.admin;
import java.util.Scanner;


public class Admitere {
    protected String locatie;
    protected Integer locuri;

    public Admitere(){
        locatie = null;
        locuri = 0;
    }

    public Admitere(String locatie, Integer locuri){
        this.locatie = locatie;
        this.locuri = locuri;
    }

    public Integer getLocuri(){
        return locuri;
    }

    public String getLocatie(){
        return locatie;
    }

    public Admitere citire(){
        Admitere adm = new Admitere();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti locatia in care se va sustine admiterea:");
        adm.locatie = sc.nextLine();
        System.out.println("Introduceti numarul de locuri disponibile:");
        adm.locuri = sc.nextInt();

        return adm;
    }
}
