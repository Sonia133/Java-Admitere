package ro.unibuc.fmi.inscriere;
import ro.unibuc.fmi.admin.Facultate;
import ro.unibuc.fmi.interogari.Serviciu;

import java.util.List;
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
    private Integer index;

    public Student() {
        frecv = -1;
        nume = null;
        cnp = 0d;
        notaBac = 0f;
        oras = null;
        idLegit = -1;
        facultate = null;
    }

    public Student(Integer frecv, String nume, Double cnp, Float notaBac, String oras, Integer idLegit, Facultate facultate, Integer index){
        this.nume = nume;
        this.cnp = cnp;
        this.notaBac = notaBac;
        this.oras = oras;
        this.idLegit = idLegit;
        this.facultate = facultate;
        this.frecv = frecv;
        this.index = index;
    }

    public void setIdLegit(Integer size) {
        this.idLegit = size;
    }
    public void setFacultate(Facultate f){

        this.facultate = f;
    }
    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
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
    public Double getCnp() {
        return cnp;
    }
    public Float getNotaBac() {
        return notaBac;
    }
    public Integer getIdLegit() {
        return idLegit;
    }
    public String getOras() {
        return oras;
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
        System.out.println("Introduceti nota din BAC:");
        student.notaBac = sc.nextFloat();
        System.out.println("1-Frecventa, 2-Distanta:");
        student.frecv = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduceti orasul in care locuiti:");
        student.oras = sc.nextLine();

        return student;
    }
}
