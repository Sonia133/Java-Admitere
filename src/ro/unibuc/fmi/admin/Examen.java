package ro.unibuc.fmi.admin;
import ro.unibuc.fmi.inscriere.Student;

import java.util.Objects;
import java.util.Scanner;

public class Examen {
    private String materie;
    private Integer luna;
    private Integer zi;
    private Boolean tipExercitii;

    public Examen(){
        materie = null;
        luna = 0;
        zi = 0;
        tipExercitii = false;
    }

    public Examen(String materie, Integer luna, Integer zi, Boolean tipExercitii) {
        this.materie = materie;
        this.luna = luna;
        this.zi = zi;
        this.tipExercitii = tipExercitii;
    }

    public String getMaterie(){
        return materie;
    }

    public Integer getLuna(){
        return luna;
    }

    public Integer getZi(){
        return zi;
    }

    public void setLuna(Integer l){
        this.luna = l;
    }

    public void setZi(Integer zi) {
        this.zi = zi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examen examen = (Examen) o;
        return Objects.equals(materie, examen.materie);
    }

    public Examen citire(){
        Examen e = new Examen();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti materia examenului:");
        e.materie = sc.nextLine();
        System.out.println("Introduceti luna in care se va tine examenul(format numeric ex: 7, 12...):");
        e.luna = sc.nextInt();
        System.out.println("Introduceti ziua in care se va tine examenul(format numeric ex: 7, 28...):");
        e.zi = sc.nextInt();
        Integer tip;
        System.out.println("Introduceti tipul exercitiilor(0 - grila | 1 - exercitii/intrebari):");
        tip = sc.nextInt();
        if(tip == 0){
            e.tipExercitii = false;
        }
        else e.tipExercitii = true;

        return e;
    }
}
