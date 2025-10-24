import java.util.Random;

public class CampoMinato {
    // ATTRIBUTI
    private boolean[][] campo;
    private boolean[][] scoperte;
    private int fiori; // celle sicure
    private int maxEsplosioni;

    // COSTRUTTORI
    public CampoMinato(int x, int y, int maxEsplosioni, float livello){
        if(x > 1 && y > 1 && maxEsplosioni > 0 && livello > 0){
            campo = new boolean[x][y];
            scoperte = new boolean[x][y];
            this.maxEsplosioni = maxEsplosioni;
            reset(livello);
        }
    }

    public CampoMinato(){
        this(10, 10, 5, 0.5F);
    }

    // RESET DEL CAMPO
    public void reset(float livello){
        Random random = new Random();
        int righe = campo.length;
        int colonne = campo[0].length;

        int numeroMine = (int)((righe * colonne) * livello);
        fiori = (righe * colonne) - numeroMine;

        // inizializza tutto a false
        for (int i = 0; i < righe; i++) {
            for (int j = 0; j < colonne; j++) {
                campo[i][j] = false;
                scoperte[i][j] = false;
            }
        }

        int piazzate = 0;
        while(piazzate < numeroMine){
            int x = random.nextInt(righe);
            int y = random.nextInt(colonne);
            if(!campo[x][y]){
                campo[x][y] = true;
                piazzate++;
            }
        }
    }

    // MOSSA DEL GIOCATORE
    public void mossa(int x, int y){
        int righe = campo.length;
        int colonne = campo[0].length;
        if (x >= 0 && x < righe && y >= 0 && y < colonne && !scoperte[x][y]) {
            scoperte[x][y] = true;
            if (campo[x][y]) {
                maxEsplosioni--;
            } else {
                fiori--;
            }
        }
    }

    // CONTROLLO STATO DEL GIOCO
    public int checkWin() {
        int r = 0;
        if (maxEsplosioni < 0) {
            r = 2; // Sconfitta
        } else if (fiori == 0) {
            r = 1; // Vittoria
        }
        return r;
    }

    // STAMPA DEL CAMPO
    public String toString(){
        int righe = campo.length;
        int colonne = campo[0].length;
        String r = "";
        for(int i = 0; i < righe; i++){
            for(int j = 0; j < colonne; j++){
                if(scoperte[i][j]){
                    r += campo[i][j] ? "B" : "F";
                } else {
                    r += "H";
                }
            }
            r += "\n";
        }
        return r;
    }
}