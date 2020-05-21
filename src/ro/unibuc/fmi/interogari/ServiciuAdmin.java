package ro.unibuc.fmi.interogari;
import ro.unibuc.fmi.admin.*;

import ro.unibuc.fmi.connection.DatabaseConnection;
import ro.unibuc.fmi.inscriere.Student;
import ro.unibuc.fmi.persistenta.Persistenta;
import ro.unibuc.fmi.sortari.AlfStudComparator;
import ro.unibuc.fmi.sortari.IdStudComparator;
import ro.unibuc.fmi.sortari.ProcFacComparator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class ServiciuAdmin {

    Persistenta persistenta = Persistenta.getInstance();

    private static final String SELECT_NUME_FACULTATE = "SELECT * FROM `facultati` WHERE `nume` = ?";
    private static final String SELECT_INDEX_EXAM = "SELECT * FROM `examen` WHERE `index` = ?";
    private static final String SELECT_INDEX_FRECV = "SELECT * FROM `frecventa` WHERE `index` = ?";
    private static final String SELECT_INDEX_DIST = "SELECT * FROM `distanta` WHERE `index` = ?";
    private static final String SELECT_INDEX_FACULTATE = "SELECT * FROM `facultati` WHERE `index` = ?";
    private static final String COUNT_STATEMENT_STUD = "SELECT * FROM `students`";
    private static final String COUNT_STATEMENT_FAC = "SELECT * FROM `facultati`";
    private static final String SELECT_NUME_FACULTATI = "SELECT * FROM `facultati`";
    private static final String UPDATE_EXAMEN = "UPDATE `examen` SET `luna` = ?, `zi` = ? WHERE `index` = ? AND `materie` = ?";
    private static final String UPDATE_INTERVIU = "UPDATE `distanta` SET `luna` = ?, `zi` = ? WHERE `index` = ?";

    public Facultate getFacNume(String nume) {
        Facultate facultate = new Facultate();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_NUME_FACULTATE)) {
            statement.setString(1, nume);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Nu am gasit o facultate cu acest nume!");
                    return facultate;
                }

                facultate.setIndex(result.getInt("index"));
                facultate.setNume(result.getString("nume"));
                facultate.setOras(result.getString("oras"));
                facultate.setDecan(result.getString("decan"));
                facultate.setProcentajBac(result.getInt("procentajBac"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return facultate;
    }

    public Set<Examen> getExamIndex(Integer index) {
        Set<Examen> examene = new HashSet<>();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_INDEX_EXAM)) {
            statement.setInt(1, index);

            try (ResultSet result = statement.executeQuery()) {
                while(result.next()) {
                    Examen examen = new Examen();
                    examen.setIndex(result.getInt("index"));
                    examen.setLuna(result.getInt("luna"));
                    examen.setZi(result.getInt("zi"));
                    examen.setMaterie(result.getString("materie"));
                    examene.add(examen);
                }

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return examene;
    }

    public Facultate getFacIndex(Integer index) {
        Facultate facultate = new Facultate();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_INDEX_FACULTATE)) {
            statement.setInt(1, index);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Nu am gasit o facultate cu acest nume!");
                    return facultate;
                }

                facultate.setIndex(result.getInt("index"));
                facultate.setNume(result.getString("nume"));
                facultate.setOras(result.getString("oras"));
                facultate.setDecan(result.getString("decan"));
                facultate.setProcentajBac(result.getInt("procentajBac"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return facultate;
    }

    public Frecventa getFrecvIndex(Integer index) {
        Frecventa frecventa = new Frecventa();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_INDEX_FRECV)) {
            statement.setInt(1, index);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Nu am gasit o facultate cu acest index!");
                    return frecventa;
                }

                frecventa.setIndex(result.getInt("index"));
                frecventa.setLocatie(result.getString("locatie"));
                frecventa.setLocuri(result.getInt("locuri"));
                frecventa.setNrExamene(result.getInt("nrExamene"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return frecventa;
    }

    public Distanta getDistIndex(Integer index) {
        Distanta distanta = new Distanta();
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_INDEX_DIST)) {
            statement.setInt(1, index);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Nu am gasit o facultate cu acest index!");
                    return distanta;
                }

                distanta.setIndex(result.getInt("index"));
                distanta.setLocatie(result.getString("locatie"));
                distanta.setLocuri(result.getInt("locuri"));
                distanta.setLuna(result.getInt("luna"));
                distanta.setZi(result.getInt("zi"));
                distanta.setNumeHr(result.getString("numeHr"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return distanta;
    }

    public int nrStudenti() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_STATEMENT_STUD)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return count;
    }

    public int nrFacultati() {
        int count = 0;

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(COUNT_STATEMENT_FAC)) {
            try (ResultSet result = statement.executeQuery()) {
                while (result.next()) {
                    count++;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    public void addFac() {
        Facultate facultate = new Facultate().citire();
        facultate.setIndex(nrFacultati());
        List<Admitere> listAdm = facultate.getAdmList();
        Frecventa fr = (Frecventa) listAdm.get(0);
        Distanta dist = (Distanta)  listAdm.get(1);
        fr.setIndex(facultate.getIndex());
        dist.setIndex(facultate.getIndex());
        Set<Examen> exam = fr.getExamList();
        for(Examen examen : exam) {
            examen.setIndex(fr.getIndex());
        }
        persistenta.saveFacultate(facultate);

        persistenta.audit(Actiune.ADD_FACULTATE.get(), new Date());

    }

    public void printMaterii(String fac) {
        Facultate facultate = getFacNume(fac);
        Set<Examen> examene = new HashSet<>(getExamIndex(facultate.getIndex()));
        for (Examen examen : examene) {
            System.out.println(examen.getMaterie());
        }
    }

    public void printNumeFac() {
        if(nrFacultati() != 0) {
            try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(SELECT_NUME_FACULTATI)) {
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

    public void printDetalii(String s) {
        Facultate facultate = getFacNume(s);
        Frecventa fr = getFrecvIndex(facultate.getIndex());
        Distanta dist = getDistIndex(facultate.getIndex());
        System.out.println("Detalii despre ramura cu frecventa:");
        System.out.print("Locatia examenelor: ");
        System.out.println(facultate.getOras() + ", " + fr.getLocatie());
        Set<Examen> exam = getExamIndex(facultate.getIndex());
        for(Examen examen : exam) {
            System.out.println("Materie: " + examen.getMaterie());
            System.out.println("Luna: " + examen.getLuna());
            System.out.println("Zi: " + examen.getZi());
        }
        System.out.println("Detalii despre ramura fara frecventa:");
        System.out.print("Locatia interviului: ");
        System.out.println(facultate.getOras() + ", " + dist.getLocatie());
        System.out.println("Luna: " + dist.getLuna());
        System.out.println("Zi: " + dist.getZi());
    }

    public void schimbaDataExamen(String fac, Integer luna, Integer zi, String mat) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_EXAMEN)) {
            statement.setInt(1, luna);
            statement.setInt(2, zi);
            statement.setInt(3, getFacNume(fac).getIndex());
            statement.setString(4, mat);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Schimbare inregistrata!");
                persistenta.audit(Actiune.SCHIMBA_EXAMEN.get(), new Date());
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
            return;
        }

        System.out.println("Nu am putut gasi aceasta materie.");
    }

    public void schimbaDataInterviu(String fac,Integer luna,Integer zi) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(UPDATE_INTERVIU)) {
            statement.setInt(1, luna);
            statement.setInt(2, zi);
            statement.setInt(3, getFacNume(fac).getIndex());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Schimbare inregistrata!");
                persistenta.audit(Actiune.SCHIMBA_INTERVIU.get(), new Date());
                return;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to update user: " + e.getMessage());
            return;
        }

        System.out.println("Aceasta facultate nu exista.");
    }

}
