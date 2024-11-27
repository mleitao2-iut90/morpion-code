package tictactoe;

import java.util.*;

public class Game {

    private Joueur joueur1;
    private Joueur joueur2;
    private MancheFactory mancheFactory;
    private List<Joueur> lesGagnants;
    private Map<Joueur, Integer> score;

    public Game(Joueur joueur1, Joueur joueur2, MancheFactory mancheFactory) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.mancheFactory = mancheFactory;
        lesGagnants = new ArrayList<Joueur>();
        score = new HashMap<Joueur, Integer>();
        score.put(joueur1, 0);
        score.put(joueur2, 0);
    }

    public Joueur play() {
        Random random = new Random();
        Joueur actifPlayer;
        Joueur otherPlayer;
        if (random.nextInt()%2 == 0) {
            actifPlayer = joueur1;
            otherPlayer = joueur2;
        } else {
            actifPlayer = joueur1;
            otherPlayer = joueur2;
        }
        while (!isWin()) {
            Manche manche = mancheFactory.createManche();
            Joueur gagnant = manche.play(new Grille(), actifPlayer, otherPlayer);
            if (gagnant!=null) {
                lesGagnants.add(gagnant);
                score.put(gagnant, score.get(gagnant)+1);
                System.out.println("manche gagné par "+gagnant);
            }
            Joueur tempo = actifPlayer;
            actifPlayer = otherPlayer;
            otherPlayer = tempo;
        }
        return winner();
    }

    public boolean isWin() {
        return (score.get(joueur1)>=2 || score.get(joueur2)>=2);
    }

    public Joueur winner() {
        if (score.get(joueur1)>=score.get(joueur2))
            return joueur1;
        return joueur2;
    }
}
