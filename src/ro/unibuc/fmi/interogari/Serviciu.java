package ro.unibuc.fmi.interogari;
import ro.unibuc.fmi.admin.*;
import ro.unibuc.fmi.inscriere.Student;
import ro.unibuc.fmi.sortari.AlfStudComparator;
import ro.unibuc.fmi.sortari.IdStudComparator;
import ro.unibuc.fmi.sortari.ProcFacComparator;

import java.util.*;

public class Serviciu {
    static Integer nrStudenti = 0;
    static Integer nrFacultati = 0;
    private List<Student> arrStudenti = new ArrayList<>();
    private List<Facultate> arrFacultati = new ArrayList<>();

    public void incrementStud(){
        nrStudenti++;
    }
    public void incrementFac(){
        nrFacultati++;
    }

    public static Integer getNrStud() {
        return nrStudenti;
    }

    public static Integer getNrFac() {
        return nrFacultati;
    }

    public List<Facultate> getArrFacultati(){
        return arrFacultati;
    }

    public Facultate getFacNume(String n) {
        for (Facultate facultate : arrFacultati) {
            if (facultate.getNume().equals(n)) {
                return facultate;
            }
        }
        return null;
    }

    public Examen getExamNume(String fac, String s) {
        Facultate facultate = getFacNume(fac);
        List<Admitere> listAdm = facultate.getAdmList();
        Frecventa fr = (Frecventa) listAdm.get(0);
        for (Examen examen : fr.getExamList()) {
            if (examen.getMaterie().equals(s)) {
                return examen;
            }
        }
        return null;
    }


    public void addStud(){
        if(nrFacultati == 0) {
            System.out.println("Pana acum nicio facultate nu si-a deschis inscrierile. Va multumim!");
        }
        else {
            Student student = new Student().citire();
            Scanner sc = new Scanner(System.in);
            System.out.println("Facultati la care va puteti inscrie:");
            for(Facultate facultate : arrFacultati){
                System.out.println(facultate.getNume());
            }
            System.out.println("Introduceti numele facultatii:");
            student.setFacultate(getFacNume(sc.nextLine()));
            arrStudenti.add(student);
            incrementStud();
            student.setIdLegit();
        }
    }


    public void addFac(){
        Facultate facultate = new Facultate().citire();
        arrFacultati.add(facultate);
        incrementFac();
    }

    public void printAlfStud(){
        if(nrStudenti == 0)
            System.out.println("Nu sunt inca studenti inscrisi.");
        else {
            AlfStudComparator alfStudComparator = new AlfStudComparator();
            Collections.sort(arrStudenti, alfStudComparator);
            for(Student student : arrStudenti){
                System.out.println(student.getNume());
            }
        }
    }

    public void printProcFac(){
        if(nrFacultati == 0){
            System.out.println("Pana acum nicio facultate nu si-a publicat informatiile.");
        }
        else{
            ProcFacComparator procFacComparator = new ProcFacComparator();
            Collections.sort(arrFacultati, procFacComparator);
            for(Facultate facultate: arrFacultati){
                System.out.println(facultate.getNume());
                System.out.println(facultate.getProcentajBac());
                System.out.println();
            }
        }
    }

    public void printIdStud(){
        if(nrStudenti == 0)
            System.out.println("Nu sunt inca studenti inscrisi.");
        else {
            IdStudComparator idStudComparator = new IdStudComparator();
            Collections.sort(arrStudenti, idStudComparator);
            for(Student student : arrStudenti){
                System.out.println(student.getNume());
            }
        }
    }

    public void printOrasFac(String oras){
        if(nrFacultati != 0){
            for(Facultate facultate : arrFacultati){
                if(facultate.getOras().equals(oras))
                    System.out.println(facultate.getNume());
            }
        }
    }

    public void printIdByNume(String n) {
        boolean ok = false;
        for (Student student : arrStudenti) {
            if (student.getNume().equals(n)) {
                ok = true;
                System.out.println(student.getId());
            }
        }
        if(!ok)
            System.out.println("Nu am gasit un student cu acest nume.");

    }

    public Integer studPerFacFrecv(String n){
        int nr = 0;
        for(Student student : arrStudenti){
            if(student.getFacultate().getNume().equals(n))
                if(student.getFrecv() == 1)
                    nr++;
        }

        return nr;
    }

    public Integer studPerFacDist(String n){
        int nr = 0;
        for(Student student : arrStudenti){
            if(student.getFacultate().getNume().equals(n))
                if(student.getFrecv() == 2)
                    nr++;
        }

        return nr;
    }

    public void studPerLoc(String n){
        float nrF = studPerFacFrecv(n);
        float nrD = studPerFacDist(n);
        Facultate f = getFacNume(n);
        List<Admitere> adm = f.getAdmList();
        float locF = nrF/adm.get(0).getLocuri();
        float locD = nrD/adm.get(1).getLocuri();
        System.out.println("Frecventa: " + locF + " student/loc.");
        System.out.println("Distanta: " + locD + " student/loc.");
    }

    public void printDetalii(String s){
        Facultate facultate = getFacNume(s);
        List<Admitere> listAdm = facultate.getAdmList();
        Frecventa fr = (Frecventa) listAdm.get(0);
        Distanta dist = (Distanta)  listAdm.get(1);
        System.out.println("Detalii despre ramura cu frecventa:");
        System.out.print("Locatia examenelor: ");
        System.out.println(facultate.getOras() + ", " + listAdm.get(0).getLocatie());
        Set<Examen> exam = fr.getExamList();
        for(Examen examen : exam){
            System.out.println("Materie: " + examen.getMaterie());
            System.out.println("Luna: " + examen.getLuna());
            System.out.println("Zi: " + examen.getZi());
        }
        System.out.println("Detalii despre ramura fara frecventa:");
        System.out.print("Locatia interviului: ");
        System.out.println(facultate.getOras() + ", " + listAdm.get(1).getLocatie());
        System.out.println("Luna: " + dist.getLuna());
        System.out.println("Zi: " + dist.getZi());
    }

    public void schimbaDataExamen(String fac, Integer luna, Integer zi, String mat){
        Examen examen = getExamNume(fac, mat);
        examen.setLuna(luna);
        examen.setZi(zi);
        System.out.println("Schimbare inregistrata:");
        System.out.println("Luna->" + examen.getLuna() + " zi->" + examen.getZi());
    }

    public void schimbaDataInterviu(String fac,Integer luna,Integer zi){
        Facultate facultate = getFacNume(fac);
        List<Admitere> listAdm = facultate.getAdmList();
        Distanta dist = (Distanta)  listAdm.get(1);
        dist.setLuna(luna);
        dist.setZi(zi);
        System.out.println("Schimbare inregistrata:");
        System.out.println("Luna->" + dist.getLuna() + " zi->" + dist.getZi());
    }


}
