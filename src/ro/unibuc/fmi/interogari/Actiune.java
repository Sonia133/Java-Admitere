package ro.unibuc.fmi.interogari;

public enum Actiune {
    ADD_STUDENT {
        public String get() {
            return "adauga_student";
        }
    },
    ADD_FACULTATE {
        public String get() {
            return "adauga_facultate";
        }
    },
    SCHIMBA_EXAMEN {
        public String get() {
            return "schimba_examen";
        }
    },
    SCHIMBA_INTERVIU {
        public String get() {
            return "schimba_interviu";
        }
    };

    public abstract String get();
}
