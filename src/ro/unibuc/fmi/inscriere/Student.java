package ro.unibuc.fmi.inscriere;
import ro.unibuc.fmi.admin.Facultate;
import ro.unibuc.fmi.interogari.Serviciu;

import java.util.Objects;
import java.util.Scanner;

public class Student {
    private Integer frecv;
    private String nume;
    private Double cnp;
    private Float notaBac;
    private String oras;
    private Integer idLegit;
    private Facultate facultate;

    public Student() {
        frecv = -1;
        nume = null;
        cnp = 0d;
        notaBac = 0f;
        oras = null;
        idLegit = -1;
        facultate = null;
    }

    public Student(Integer frecv, String nume, Double cnp, Float notaBac, Integer idLegit, Facultate facultate){
        this.nume = nume;
        this.cnp = cnp;
        this.notaBac = notaBac;
        this.idLegit = idLegit;
        this.facultate = facultate;
        this.frecv = frecv;
    }

    public void setIdLegit() {
        this.idLegit = Serviciu.getNrStud();
    }

    public void setFacultate(Facultate f){

        this.facultate = f;
    }

    public String getNume() {
        return nume;
    }

    public Integer getId(){
        return idLegit;
    }

    public Facultate getFacultate(){
        return facultate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(nume, student.nume);
    }

    public Integer getFrecv(){
        return frecv;
    }

    public Student citire(){
        Student student = new Student();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti numele candidatului:");
        student.nume = sc.nextLine();
        System.out.println("Introduceti CNP-ul:");
        student.cnp = sc.nextDouble();
        System.out.println("1-Frecventa, 2-Distanta:");
        student.frecv = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduceti orasul in care locuiti:");
        student.oras = sc.nextLine();

        return student;
    }
}
