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

    public Distanta(String locatie, Integer locuri, Integer index, Integer luna, Integer zi, String numeHr){
        super(locatie, locuri, index);
        this.luna = luna;
        this.zi = zi;
        this.numeHr = numeHr;
    }

    @Override
    public void setIndex(Integer index) {
        super.setIndex(index);
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
    @Override
    public Integer getIndex() {
        return super.getIndex();
    }
    @Override
    public String getLocatie() {
        return super.getLocatie();
    }
    @Override
    public Integer getLocuri() {
        return super.getLocuri();
    }
    public String getNumeHr() {
        return numeHr;
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
