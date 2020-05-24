package ro.unibuc.fmi.persistenta;

import ro.unibuc.fmi.admin.*;
import ro.unibuc.fmi.connection.DatabaseConnection;
import ro.unibuc.fmi.inscriere.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Persistenta {
    private static Persistenta instance = null;

    private Persistenta() {}

    public static Persistenta getInstance() {
        if (instance == null)
            instance = new Persistenta();

        return instance;
    }

    private static final String INSERT_STATEMENT_STUDENT = "INSERT INTO `students` (`index`, `frecventa`, `nume`, `cnp`, `notaBac`, `oras`, `idLegit`)" +
            " VALUES (?, ?, ? ,? ,? , ?, ?)";
    private static final String INSERT_STATEMENT_FACULTATE = "INSERT INTO `facultati` (`index`, `nume`, `oras`, `decan`, `procentajBac`)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_STATEMENT_FRECVENTA = "INSERT INTO `frecventa` (`index`, `locatie`, `locuri`, `nrExamene`)" +
            "VALUES (?, ?, ?, ?)";
    private static final String INSERT_STATEMENT_DISTANTA = "INSERT INTO `distanta` (`index`, `locatie`, `locuri`, `luna`, `zi`, `numeHr`)" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_STATEMENT_EXAMEN = "INSERT INTO `examen` (`index`, `materie`, `luna`, `zi`, `tipExercitii`)" +
            "VALUES (?, ?, ?, ?, ?)";


    public Student saveStudent(Student student) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT_STUDENT)) {
            statement.setInt(1, student.getIndex());
            statement.setInt(2, student.getFrecv());
            statement.setString(3, student.getNume());
            statement.setDouble(4, student.getCnp());
            statement.setFloat(5, student.getNotaBac());
            statement.setString(6, student.getOras());
            statement.setInt(7, student.getIdLegit());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student adaugat cu succes!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Student();
        }
        return student;
    }

    public Facultate saveFacultate(Facultate facultate) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT_FACULTATE)) {
            statement.setInt(1, facultate.getIndex());
            statement.setString(2, facultate.getNume());
            statement.setString(3, facultate.getOras());
            statement.setString(4, facultate.getDecan());
            statement.setInt(5, facultate.getProcentajBac());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("O noua facultate a fost inserata!");
                List<Admitere> listAdm = facultate.getAdmList();
                Distanta dist = (Distanta) listAdm.get(1);
                Frecventa frecv = (Frecventa) listAdm.get(0);
                saveDistanta(dist);
                saveFrecventa(frecv);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Facultate();
        }
        return facultate;
    }

    public Distanta saveDistanta(Distanta distanta) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT_DISTANTA)) {
            statement.setInt(1, distanta.getIndex());
            statement.setString(2, distanta.getLocatie());
            statement.setInt(3, distanta.getLocuri());
            statement.setInt(4, distanta.getLuna());
            statement.setInt(5, distanta.getZi());
            statement.setString(6, distanta.getNumeHr());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("O noua ramura 'distanta' a fost adaugata!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Distanta();
        }
        return distanta;
    }

    public Frecventa saveFrecventa(Frecventa frecventa) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT_FRECVENTA)) {
            statement.setInt(1, frecventa.getIndex());
            statement.setString(2, frecventa.getLocatie());
            statement.setInt(3, frecventa.getLocuri());
            statement.setInt(4, frecventa.getNrExamene());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("O noua ramura 'frecventa' a fost adaugata!");
                Set<Examen> ex = new HashSet<>(frecventa.getExamList());
                for(Examen examen : ex) {
                    saveExamen(examen);
                }
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Frecventa();
        }
        return frecventa;
    }

    public Examen saveExamen(Examen examen) {
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(INSERT_STATEMENT_EXAMEN)) {
            statement.setInt(1, examen.getIndex());
            statement.setString(2, examen.getMaterie());
            statement.setInt(3, examen.getLuna());
            statement.setInt(4, examen.getZi());
            int myInt = examen.getTipExercitii() ? 1 : 0;
            statement.setInt(5, myInt);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("O noua serie de examene a fost inserata!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return new Examen();
        }
        return examen;
    }

    public void audit(String nume, Date data) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("audit.csv", true))) {
            bufferedWriter.write(nume + ',' + data + '\n');
        } catch (IOException e) {
            System.out.println("Nu s-a putut scrie in fisier: " + e.getMessage());
        }
    }
}
