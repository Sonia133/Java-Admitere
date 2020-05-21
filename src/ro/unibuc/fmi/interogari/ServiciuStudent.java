package ro.unibuc.fmi.interogari;

import ro.unibuc.fmi.admin.Distanta;
import ro.unibuc.fmi.admin.Facultate;
import ro.unibuc.fmi.admin.Frecventa;
import ro.unibuc.fmi.connection.DatabaseConnection;
import ro.unibuc.fmi.inscriere.Student;
import ro.unibuc.fmi.persistenta.Persistenta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class ServiciuStudent {

    ServiciuAdmin serviciuAdmin = new ServiciuAdmin();
    Persistenta persistenta = Persistenta.getInstance();

    private static final String SELECT_STUD_ALF = "SELECT * FROM `students` ORDER BY `nume`";
    private static final String SELECT_STUDENT_CRESC_ID = "SELECT * FROM `students` ORDER BY `idLegit`";
    private static final String SELECT_ORAS_FACULTATE = "SELECT * FROM `facultati` WHERE `oras` = ?";
    private static final String SELECT_STUDENT_ID_BY_NAME = "SELECT * FROM `students` WHERE `nume` = ?";
    private static final String SELECT_FAC_CRESC_BAC = "SELECT * FROM `facultati` ORDER BY `procentajBac`";
    private static final String COUNT_STATEMENT_STUD = "SELECT * FROM `students`";

    public void addStud() {
        if (serviciuAdmin.nrFacultati() == 0) {
            System.out.println("Inscrierile nu s-au deschis inca.");
        }
        else {
            Student student = new Student().citire();
            Scanner sc = new Scanner(System.in);
            System.out.println("Facultati la care va puteti inscrie:");

            serviciuAdmin.printNumeFac();

            System.out.println("Introduceti numele facultatii:");
            student.setFacultate(serviciuAdmin.getFacNume(sc.nextLine()));
            student.setIdLegit(serviciuAdmin.nrStudenti());
            student.setIndex(student.getFacultate().getIndex());
            persistenta.saveStudent(student);

            persistenta.audit(Actiune.ADD_STUDENT.get(), new Date());
        }
    }

    public void printIdStud() {
        if(serviciuAdmin.nrStudenti() == 0)
            System.out.println("Nu sunt inca studenti inscrisi.");
        else {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STUDENT_CRESC_ID)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("nume"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void printAlfStud() {
        if(serviciuAdmin.nrStudenti() == 0)
            System.out.println("Nu sunt inca studenti inscrisi.");
        else {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STUD_ALF)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("nume"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void printOrasFac(String oras) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_ORAS_FACULTATE)) {
            statement.setString(1, oras);

            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    System.out.println(result.getString("nume"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
    }

    public void printIdByNume(String nume) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_STUDENT_ID_BY_NAME)) {
            statement.setString(1, nume);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Nu am gasit un student cu acest nume!");
                    return;
                }
                System.out.println(result.getInt("idLegit"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
    }

    public void printProcFac() {
        if(serviciuAdmin.nrFacultati() != 0) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_FAC_CRESC_BAC)) {
                try (ResultSet result = statement.executeQuery()) {
                    while (result.next()) {
                        System.out.println(result.getString("nume"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else {
            System.out.println("Nici o facultate nu si-a deschis inca inscrierile!");
        }
    }

    public Integer studPerFacFrecv(String nume) {
        int nr = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_STATEMENT_STUD)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int index = result.getInt("index");
                    int frecv = result.getInt("frecventa");
                    Facultate facultate = serviciuAdmin.getFacIndex(index);
                    if(facultate.getNume().equals(nume)) {
                        if(frecv == 1) {
                            nr++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return nr;
    }

    public Integer studPerFacDist(String nume) {
        int nr = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_STATEMENT_STUD)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    int index = result.getInt("index");
                    int frecv = result.getInt("frecventa");
                    Facultate facultate = serviciuAdmin.getFacIndex(index);
                    if(facultate.getNume().equals(nume)) {
                        if(frecv == 2) {
                            nr++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return nr;
    }

    public void studPerLoc(String n) {
        float nrF = studPerFacFrecv(n);
        float nrD = studPerFacDist(n);
        Facultate facultate = serviciuAdmin.getFacNume(n);

        Frecventa fr = serviciuAdmin.getFrecvIndex(facultate.getIndex());
        Distanta dist = serviciuAdmin.getDistIndex(facultate.getIndex());

        float locF = nrF/fr.getLocuri();
        float locD = nrD/dist.getLocuri();

        System.out.println("Frecventa: " + locF + " student/loc.");
        System.out.println("Distanta: " + locD + " student/loc.");
    }

}
