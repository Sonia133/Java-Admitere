package ro.unibuc.fmi.admin;
import ro.unibuc.fmi.interogari.Serviciu;

import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Frecventa extends Admitere {
    private Set<Examen> examList;
    private Integer nrExamene;

    public Frecventa() {
        super();
        examList = new HashSet<>();
        nrExamene = 0;
    }

    public Frecventa(String locatie, Integer locuri, Integer index, Set<Examen> examList, Integer nrExamene) {
        super(locatie, locuri, index);
        this.examList = examList;
        this.nrExamene = nrExamene;
    }

    @Override
    public Integer getIndex() {
        return super.getIndex();
    }
    @Override
    public Integer getLocuri() {
        return super.getLocuri();
    }
    @Override
    public String getLocatie() {
        return super.getLocatie();
    }
    public Integer getNrExamene() {
        return nrExamene;
    }
    public Set<Examen> getExamList(){
        return examList;
    }

    @Override
    public void setIndex(Integer index) {
        super.setIndex(index);
    }
    public void setExamList(Set<Examen> examList) {
        this.examList = examList;
    }
    public void setNrExamene(Integer nrExamene) {
        this.nrExamene = nrExamene;
    }
    @Override
    public void setLocatie(String locatie) {
        super.setLocatie(locatie);
    }
    @Override
    public void setLocuri(Integer locuri) {
        super.setLocuri(locuri);
    }

    public Frecventa citire() {
        Admitere adm = super.citire();
        Frecventa fr = new Frecventa();
        fr.locatie = adm.locatie;
        fr.locuri = adm.locuri;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti numarul de examene:");
        fr.nrExamene = sc.nextInt();
        for(Integer i = 0; i < fr.nrExamene; i++) {
            Integer ordine = i + 1;
            System.out.println("Examenul " + ordine +":");
            Examen e = new Examen().citire();
            fr.examList.add(e);
        }

        return fr;
    }
}
