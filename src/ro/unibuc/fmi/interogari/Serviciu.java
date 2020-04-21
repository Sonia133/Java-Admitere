package ro.unibuc.fmi.interogari;
import ro.unibuc.fmi.admin.*;

import ro.unibuc.fmi.inscriere.Student;
import ro.unibuc.fmi.sortari.AlfStudComparator;
import ro.unibuc.fmi.sortari.IdStudComparator;
import ro.unibuc.fmi.sortari.ProcFacComparator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Serviciu {
    private List<Student> arrStudenti = new ArrayList<>();
    private List<Facultate> arrFacultati = new ArrayList<>();

    public List<Facultate> getArrFacultati(){
        return arrFacultati;
    }
    public List<Student> getArrStudenti(){
        return arrStudenti;
    }

    public void setArrFacultati(List<Facultate> arrFacultati) {
        this.arrFacultati = arrFacultati;
    }

    public void setArrStudenti(List<Student> arrStudenti) {
        this.arrStudenti = arrStudenti;
    }

    public Facultate getFacNume(String n) {
        for (Facultate facultate : arrFacultati) {
            if (facultate.getNume().equals(n)) {
                return facultate;
            }
        }
        System.out.println("Nu am gasit o facultate cu numele " + n);
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

    public void printMaterii(String fac) {
        Facultate f = getFacNume(fac);
        List<Admitere> listAdm = f.getAdmList();
        Frecventa fr = (Frecventa) listAdm.get(0);
        for (Examen examen : fr.getExamList()) {
            System.out.println(examen.getMaterie());
        }
    }


    public void addStud() {
        if(arrFacultati.size() == 0) {
            System.out.println("Pana acum nicio facultate nu si-a deschis inscrierile. Va multumim!");
        }
        else {
            Student student = new Student().citire();
            Scanner sc = new Scanner(System.in);
            System.out.println("Facultati la care va puteti inscrie:");
            for(Facultate facultate : arrFacultati) {
                System.out.println(facultate.getNume());
            }
            System.out.println("Introduceti numele facultatii:");
            student.setFacultate(getFacNume(sc.nextLine()));
            student.setIdLegit(arrStudenti.size());
            student.setIndex(student.getFacultate().getIndex());
            arrStudenti.add(student);

            audit(Actiune.ADD_STUDENT.get(), new Date());
        }
    }


    public void addFac() {
        Facultate facultate = new Facultate().citire();
        facultate.setIndex(arrFacultati.size());
        List<Admitere> listAdm = facultate.getAdmList();
        Frecventa fr = (Frecventa) listAdm.get(0);
        Distanta dist = (Distanta)  listAdm.get(1);
        fr.setIndex(facultate.getIndex());
        dist.setIndex(facultate.getIndex());
        Set<Examen> exam = fr.getExamList();
        for(Examen examen : exam) {
            examen.setIndex(fr.getIndex());
        }
        arrFacultati.add(facultate);

        audit(Actiune.ADD_FACULTATE.get(), new Date());

    }

    public void printAlfStud() {
        if(arrStudenti.size() == 0)
            System.out.println("Nu sunt inca studenti inscrisi.");
        else {
            AlfStudComparator alfStudComparator = new AlfStudComparator();
            Collections.sort(arrStudenti, alfStudComparator);
            for(Student student : arrStudenti) {
                System.out.println(student.getNume());
            }
        }
    }

    public void printProcFac() {
        if(arrFacultati.size() == 0) {
            System.out.println("Pana acum nicio facultate nu si-a publicat informatiile.");
        }
        else {
            ProcFacComparator procFacComparator = new ProcFacComparator();
            Collections.sort(arrFacultati, procFacComparator);
            for(Facultate facultate: arrFacultati){
                System.out.println(facultate.getNume());
                System.out.println(facultate.getProcentajBac());
                System.out.println();
            }
        }
    }

    public void printIdStud() {
        if(arrStudenti.size() == 0)
            System.out.println("Nu sunt inca studenti inscrisi.");
        else {
            IdStudComparator idStudComparator = new IdStudComparator();
            Collections.sort(arrStudenti, idStudComparator);
            for(Student student : arrStudenti) {
                System.out.println(student.getNume());
            }
        }
    }

    public void printOrasFac(String oras) {
        if(arrFacultati.size() != 0) {
            for(Facultate facultate : arrFacultati) {
                if(facultate.getOras().equals(oras))
                    System.out.println(facultate.getNume());
            }
        }
    }

    public void printIdByNume(String n) {
        for (Student student : arrStudenti) {
            if (student.getNume().equals(n)) {

                System.out.println(student.getId());
                return;
            }
        }

        System.out.println("Nu am gasit un candidat cu numele " + n);
    }

    public Integer studPerFacFrecv(String n) {
        int nr = 0;
        for(Student student : arrStudenti) {
            if(student.getFacultate().getNume().equals(n))
                if(student.getFrecv() == 1)
                    nr++;
        }

        return nr;
    }

    public Integer studPerFacDist(String n) {
        int nr = 0;
        for(Student student : arrStudenti) {
            if(student.getFacultate().getNume().equals(n))
                if(student.getFrecv() == 2)
                    nr++;
        }

        return nr;
    }

    public void studPerLoc(String n) {
        float nrF = studPerFacFrecv(n);
        float nrD = studPerFacDist(n);
        Facultate f = getFacNume(n);
        List<Admitere> adm = f.getAdmList();
        float locF = nrF/adm.get(0).getLocuri();
        float locD = nrD/adm.get(1).getLocuri();
        System.out.println("Frecventa: " + locF + " student/loc.");
        System.out.println("Distanta: " + locD + " student/loc.");
    }

    public void printDetalii(String s) {
        Facultate facultate = getFacNume(s);
        List<Admitere> listAdm = facultate.getAdmList();
        Frecventa fr = (Frecventa) listAdm.get(0);
        Distanta dist = (Distanta)  listAdm.get(1);
        System.out.println("Detalii despre ramura cu frecventa:");
        System.out.print("Locatia examenelor: ");
        System.out.println(facultate.getOras() + ", " + listAdm.get(0).getLocatie());
        Set<Examen> exam = fr.getExamList();
        for(Examen examen : exam) {
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

    public void schimbaDataExamen(String fac, Integer luna, Integer zi, String mat) {
        for(Facultate facultate : arrFacultati) {
            System.out.println(facultate.getNume());
        }
        Examen examen = getExamNume(fac, mat);
        examen.setLuna(luna);
        examen.setZi(zi);
        System.out.println("Schimbare inregistrata:");
        System.out.println("Luna->" + examen.getLuna() + " zi->" + examen.getZi());

        audit(Actiune.SCHIMBA_EXAMEN.get(), new Date());

    }

    public void schimbaDataInterviu(String fac,Integer luna,Integer zi) {
        Facultate facultate = getFacNume(fac);
        List<Admitere> listAdm = facultate.getAdmList();
        Distanta dist = (Distanta)listAdm.get(1);
        dist.setLuna(luna);
        dist.setZi(zi);
        System.out.println("Schimbare inregistrata:");
        System.out.println("Luna->" + dist.getLuna() + " zi->" + dist.getZi());

        audit(Actiune.SCHIMBA_INTERVIU.get(), new Date());
    }

    public void audit(String nume, Date data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }
}
