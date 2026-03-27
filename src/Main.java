void main() {
    Sklad s = Sklad.getInstance();

    boolean jeZavodAktivni = true;

    Zavodnik z1 = new Zavodnik("Petr");
    Zavodnik z2 = new Zavodnik("Karel");
    Zavodnik z3 = new Zavodnik("Jirka");
    Zavodnik z4 = new Zavodnik("Marek");

    z1.start();
    z2.start();
    z3.start();
    z4.start();

    while (jeZavodAktivni) {
        if (z1.isInterrupted() && z2.isInterrupted() && z3.isInterrupted() && z4.isInterrupted()) {
            // Doufám že nevadí sout v mainu když jsou thready už ukončený
            System.out.println("Beh je u konce. Zde jsou statistiky: ");
            System.out.println("Zbylo " + s.getSusenky() + " susenek");
            System.out.println("zavodnik 1 ubehl " + z1.getCelkovyPocetKol() + " kol");
            System.out.println("zavodnik 2 ubehl " + z2.getCelkovyPocetKol() + " kol");
            System.out.println("zavodnik 3 ubehl " + z3.getCelkovyPocetKol() + " kol");
            System.out.println("zavodnik 4 ubehl " + z4.getCelkovyPocetKol() + " kol");
            jeZavodAktivni = false;
        }

        if (z1.maSeZavodUkoncit || z2.maSeZavodUkoncit ||  z3.maSeZavodUkoncit ||  z4.maSeZavodUkoncit) {
            System.out.println("lodfjgdifgdhfghdfoighdoifghoidfhgoidfhgohdfogihdfoigofdihg");
            try {
                z1.interrupt();
                z2.interrupt();
                z3.interrupt();
                z4.interrupt();
            } catch (Exception e) {
                System.out.println("Beh je u konce.");
            }
        }
    }

}
