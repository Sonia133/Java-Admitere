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
    private Integer index;

    public Facultate() {
        nume = null;
        admList = new ArrayList<>();
        oras = null;
        decan = null;
        procentajBac = 0;
    }

    public Facultate(String nume, List<Admitere> admList, String oras, String decan, Integer procentajBac, Integer index) {
        this.nume = nume;
        this.admList = admList;
        this.oras = oras;
        this.decan = decan;
        this.procentajBac = procentajBac;
        this.index = index;
    }

    public List<Admitere> getAdmList(){
        return admList;
    }
    public String getNume(){ return nume; }
    public String getOras(){
        return oras;
    }
    public String getDecan() { return decan; }
    public Integer getProcentajBac(){
        return procentajBac;
    }
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
    public void setAdmList(List<Admitere> admList) {
        this.admList = admList;
    }
    public void setDecan(String decan) {
        this.decan = decan;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public void setOras(String oras) {
        this.oras = oras;
    }
    public void setProcentajBac(Integer procentajBac) {
        this.procentajBac = procentajBac;
    }

    public Facultate citire() {
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
        System.out.println("Informatii despre ramura cu frecventa");
        Frecventa frecventa = new Frecventa().citire();
        f.admList.add(frecventa);
        System.out.println("Informatii despre ramura fara frecventa");
        Distanta distanta = new Distanta().citire();
        f.admList.add(distanta);

        return f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facultate facultate = (Facultate) o;
        if(Objects.equals(nume, facultate.nume)) {
            return true;
        }
        if(Objects.equals(oras, facultate.oras)) {
            return true;
        }
        return false;
    }

}
