package ro.unibuc.fmi.persistenta;

import ro.unibuc.fmi.admin.*;
import ro.unibuc.fmi.inscriere.Student;
import ro.unibuc.fmi.interogari.Serviciu;

import java.io.*;
import java.util.ArrayList;
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

    public List<Distanta> citireDistanta(Serviciu serviciu) {
        List<Distanta> distante = new ArrayList<>();

        try(BufferedReader bufferedReaderDist = new BufferedReader(new FileReader("distanta.csv"))) {
            String currentLineDist;
            while((currentLineDist = bufferedReaderDist.readLine()) != null) {
                String[] dataFieldsDist = currentLineDist.split(",");
                Distanta dist = new Distanta(dataFieldsDist[1], Integer.parseInt(dataFieldsDist[2]), Integer.parseInt(dataFieldsDist[0]), Integer.parseInt(dataFieldsDist[3]), Integer.parseInt(dataFieldsDist[4]), dataFieldsDist[5]);
                distante.add(dist);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return null;
        }

        return distante;
    }

    public List<Examen> citireExamen(Serviciu serviciu) {
        List<Examen> examene = new ArrayList<>();
        try(BufferedReader bufferedReaderEx = new BufferedReader(new FileReader("examene.csv"))) {
            String currentLineEx;
            while((currentLineEx = bufferedReaderEx.readLine()) != null) {
                String[] dataFieldsEx = currentLineEx.split(",");
                Examen ex = new Examen(dataFieldsEx[1], Integer.parseInt(dataFieldsEx[2]), Integer.parseInt(dataFieldsEx[3]), Boolean.parseBoolean(dataFieldsEx[4]), Integer.parseInt(dataFieldsEx[0]));
                examene.add(ex);

            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return null;
        }
        return examene;
    }

    public List<Frecventa> citireFrecventa(Serviciu serviciu) {
        List<Frecventa> frecvente = new ArrayList<>();

        List<Examen> examene = citireExamen(serviciu);

        try(BufferedReader bufferedReaderFr = new BufferedReader(new FileReader("frecventa.csv"))) {
            String currentLineFr;
            while((currentLineFr = bufferedReaderFr.readLine()) != null) {
                String[] dataFieldsFr = currentLineFr.split(",");
                Set<Examen> exam = new HashSet<>();
                for(Examen examen: examene) {
                    if(Integer.parseInt(dataFieldsFr[0]) == examen.getIndex()) {
                        exam.add(examen);
                    }
                }

                Frecventa fr = new Frecventa(dataFieldsFr[1], Integer.parseInt(dataFieldsFr[2]), Integer.parseInt(dataFieldsFr[0]), exam, Integer.parseInt(dataFieldsFr[3]));
                frecvente.add(fr);
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return null;
        }

        return frecvente;
    }

    public List<Facultate> citireFacultate(Serviciu serviciu) {
        List<Facultate> facultati = new ArrayList<>();

        List<Distanta> distante = citireDistanta(serviciu);
        List<Frecventa> frecvente = citireFrecventa(serviciu);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("facultati.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                List<Admitere> adm = new ArrayList<>();
                String[] dataFields = currentLine.split(",");

                for(Frecventa frecventa : frecvente) {
                    if(Integer.parseInt(dataFields[0]) == frecventa.getIndex()) {
                        adm.add(frecventa);
                        break;
                    }
                }

                for(Distanta distanta : distante) {
                    if(Integer.parseInt(dataFields[0]) == distanta.getIndex()) {
                        adm.add(distanta);
                        break;
                    }
                }

                Facultate facultate = new Facultate(dataFields[1], adm, dataFields[2], dataFields[3], Integer.parseInt(dataFields[4]), Integer.parseInt(dataFields[0]));
                facultati.add(facultate);
            }

        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return null;
        }

        return facultati;
    }

    public List<Student> citireStudent(Serviciu serviciu) {
        List<Student> studenti = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("studenti.csv"))) {
            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] dataFieldsStud = currentLine.split(",");
                for(Facultate facultate : serviciu.getArrFacultati()) {
                    if(Integer.parseInt(dataFieldsStud[0]) == facultate.getIndex()) {
                        Student student = new Student(Integer.parseInt(dataFieldsStud[1]), dataFieldsStud[2], Double.parseDouble(dataFieldsStud[3]), Float.parseFloat(dataFieldsStud[4]), dataFieldsStud[5], Integer.parseInt(dataFieldsStud[6]), facultate, Integer.parseInt(dataFieldsStud[0]));
                        studenti.add(student);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read data from file: " + e.getMessage());
            return null;
        }

        return studenti;
    }

    public void scriereFacultate(Serviciu serviciu) {
        List<Facultate> facultati = new ArrayList<>(serviciu.getArrFacultati());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("facultati.csv"))) {
            for (Facultate facultate : facultati) {
                bufferedWriter.write(facultate.getIndex() + "," + facultate.getNume() + ","  + facultate.getOras() + "," + facultate.getDecan() + "," + facultate.getProcentajBac());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }

    }

    public void scriereDistanta(Serviciu serviciu) {
        List<Facultate> facultati = new ArrayList<>(serviciu.getArrFacultati());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("distanta.csv"))) {
            for (Facultate facultate: facultati) {
                List<Admitere> listAdm = facultate.getAdmList();
                Distanta dist = (Distanta) listAdm.get(1);
                bufferedWriter.write(dist.getIndex() + "," + dist.getLocatie() + "," + dist.getLocuri() + "," + dist.getLuna() + "," + dist.getZi() + "," + dist.getNumeHr());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }

    public void scriereFrecventa(Serviciu serviciu) {
        List<Facultate> facultati = new ArrayList<>(serviciu.getArrFacultati());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("frecventa.csv"))) {
            for (Facultate facultate: facultati) {
                List<Admitere> listAdm = facultate.getAdmList();
                Frecventa fr = (Frecventa) listAdm.get(0);
                bufferedWriter.write(fr.getIndex() + "," + fr.getLocatie() + "," + fr.getLocuri() +  "," + fr.getNrExamene());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }

    public void scriereExamen(Serviciu serviciu) {
        List<Facultate> facultati = new ArrayList<>(serviciu.getArrFacultati());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("examene.csv"))) {
            for (Facultate facultate: facultati) {
                List<Admitere> listAdm = facultate.getAdmList();
                Frecventa fr = (Frecventa) listAdm.get(0);
                Set<Examen> ex = new HashSet<>(fr.getExamList());
                for(Examen examen : ex) {
                    bufferedWriter.write(examen.getIndex() + "," + examen.getMaterie() + "," + examen.getLuna() + "," + examen.getZi() + "," + examen.getTipExercitii());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }

    public void scriereStudent(Serviciu serviciu) {
        List<Facultate> facultati = new ArrayList<>(serviciu.getArrFacultati());

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("studenti.csv"))) {
            List<Student> stud = new ArrayList<>(serviciu.getArrStudenti());
            for(Student student : stud) {
                for(Facultate facultate: facultati) {
                    if(student.getIndex() == facultate.getIndex()) {
                        bufferedWriter.write(student.getIndex() + "," + student.getFrecv() + "," + student.getNume() + "," + student.getCnp() + "," + student.getNotaBac() + "," + student.getOras() + "," + student.getIdLegit());
                        bufferedWriter.newLine();
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not write data to file: " + e.getMessage());
        }
    }

}
