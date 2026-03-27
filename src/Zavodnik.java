import java.util.Random;

public class Zavodnik extends Thread {
    private String jmeno;
    private int pocetKol = 3;
    private int cisloKola = 0;
    private int hodKostkou;
    private int celkovyPocetKol = 0;

    public boolean maSeZavodUkoncit = false;


    private Random rand = new Random();

    Sklad sklad = Sklad.getInstance();
    Logger logger = Logger.getLogger();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            hodKostkou = rand.nextInt(1, 7);
            vypisStatus("["+jmeno+"] Zacatek kola cislo: " +  cisloKola);
            vypisStatus("["+jmeno+"] Na kostce spadlo cislo: " +  hodKostkou);
            switch(hodKostkou) {
                case 1 -> {
                    try {
                        snezSusenku();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 2 -> {
                    cisloKola--;
                    break;
                }
                case 3 -> {
                    vypisStatus("["+jmeno+"] Spinkam na 5 sekund");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        logger.log("Interrupted");
                    }
                    break;
                }
                case 4 -> {
                    vypisStatus("["+jmeno+"] Spinkam na 2 sekundy");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        logger.log("Interrupted");
                    }
                    break;
                }
                case 5 -> {
                    pridejSusenku();
                    break;
                }
                case 6 -> {
                    vypisStatus("["+jmeno+"] Nic se nedeje");
                    break;
                }
            }
            vypisStatus("["+jmeno+"] Konec kola cislo: " +  cisloKola);
            cisloKola++;
            celkovyPocetKol++;

            zkontrolujPocetKol();
        }
    }

    public void vypisStatus(String msg) {
        logger.log(msg);
    }

    public int getCelkovyPocetKol(){
        return celkovyPocetKol;
    }

    // Private methods
    private void zkontrolujPocetKol(){
        if (cisloKola == pocetKol) {
            vypisStatus("["+jmeno+"] Splnil jsem pocet kol. Koncim");
            Thread.currentThread().interrupt();
        }
    }

    private void snezSusenku() throws InterruptedException {
        if (!sklad.removeSusenky(1)){
            vypisStatus("["+jmeno+"] Nejsme na zavode jedliku, bezte behat do lesa.");
            maSeZavodUkoncit = true;
            return;
        }
        sleep(3000);
        vypisStatus("["+jmeno+"] Snedl susenku");

    }

    private void pridejSusenku(){
        sklad.addSusenky(1);
        vypisStatus("["+jmeno+"] Pridal susenku do skladu");
    }

    Zavodnik(String jmeno) {
        this.jmeno = jmeno;
    }
}
