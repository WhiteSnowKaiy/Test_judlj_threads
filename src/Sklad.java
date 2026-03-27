public class Sklad {
    private static Sklad sklad;
    Logger logger = Logger.getLogger();

    private int susenky = 2;

    public synchronized boolean removeSusenky(int pocet){
        if (susenky >= pocet){
            susenky--;
            return true;
        }
        logger.log("[Sklad] Pocet susenek: " + susenky);
        return false;
    }

    public synchronized void addSusenky(int pocet){
        logger.log("[Sklad] Pocet susenek: " + susenky);
        susenky += pocet;
    }

    public int getSusenky(){
        return susenky;
    }

    public static Sklad getInstance() {
        if(sklad == null){
            sklad = new Sklad();
        }
        return sklad;
    }
}
