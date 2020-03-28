package ro.unibuc.fmi.admin;
import java.util.Objects;


import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Facultate {
    private String nume;
    private List<Admitere> admList;
    private String oras;
    private String decan;
    private Integer procentajBac;

    public Facultate(){
        nume = null;
        admList = new ArrayList<>();
        oras = null;
        decan = null;
        procentajBac = 0;
    }

    public Facultate(String nume, List<Admitere> admList, String oras, String decan, Integer procentajBac){
        this.nume = nume;
        this.admList = admList;
        this.oras = oras;
        this.decan = decan;
        this.procentajBac = procentajBac;
    }

    public List<Admitere> getAdmList(){
        return admList;
    }

    public Integer getProcentajBac(){
        return procentajBac;
    }

    public Facultate citire(){
        Facultate f = new Facultate();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti numele facultatii:");
        f.nume = sc.nextLine();
        System.out.println("Introduceti orasul in care se afla facultatea:");
        f.oras = sc.nextLine();
        System.out.println("Introduceti decanul facultatii:");
        f.decan = sc.nextLine();
        System.out.println("Cat la suta conteaza bacalaureatul in calculul notei finale ?");
        f.procentajBac = sc.nextInt();
        System.out.println("Informatii despre ramura cu frecventa:");
        Frecventa frecventa = new Frecventa();
        f.admList.add(frecventa.citire());
        System.out.println("Informatii despre ramura fara frecventa:");
        Distanta distanta = new Distanta();
        f.admList.add(distanta.citire());

        return f;
    }

    public String getNume(){

        return nume;
    }

    public String getOras(){
        return oras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facultate facultate = (Facultate) o;
        if(Objects.equals(nume, facultate.nume)) {
            return true;
        }
        if(Objects.equals(oras, facultate.oras)){
            return true;
        }
        return false;
    }

}
