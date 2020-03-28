package ro.unibuc.fmi.admin;
import java.util.Scanner;

public class Distanta extends Admitere {
    private Integer luna;
    private Integer zi;
    private String numeHr;

    public Distanta(){
        super();
        luna = 0;
        zi = 0;
        numeHr = null;
    }

    public Distanta(String locatie, Integer locuri, Integer luna, Integer zi, String numeHr){
        super(locatie, locuri);
        this.luna = luna;
        this.zi = zi;
        this.numeHr = numeHr;
    }

    public void setLuna(Integer l){
        this.luna = l;
    }

    public void setZi(Integer zi) {
        this.zi = zi;
    }

    public Integer getLuna(){
        return luna;
    }

    public Integer getZi(){
        return zi;
    }

    public Distanta citire(){
        Admitere adm = super.citire();
        Distanta dist = new Distanta();
        dist.locatie = adm.locatie;
        dist.locuri = adm.locuri;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduceti luna in care se va tine interviul:");
        dist.luna = sc.nextInt();
        System.out.println("Introduceti ziua in care se va tine interviul:");
        dist.zi = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduceti numele intervievatorului:");
        dist.numeHr = sc.nextLine();

        return dist;
    }
}
