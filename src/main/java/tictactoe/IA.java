package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class IA implements Joueur {

    private String motif;

    public IA(String motif) {
        this.motif = motif;
    }

    public void joue(Grille grille) {
        if (grille.isVide())
            safePlay(grille, 4);
        else {
            List<Integer> mesCases = grille.casesJoueur(this);
            // Win conditions
            if (mesCases.size() >= 2)
                if (coupGagnant(grille, mesCases)) return;
            List<Integer> sesCases = grille.casesAdverses(this);
            if (mesCases.size() + sesCases.size() == 8) {
                joueDerniereCase(grille);
                return;
            }
            if (sesCases.size() == 1) {
                if (centreLibre(mesCases, sesCases)) safePlay(grille, 4);
                else if (sesCases.contains(4))
                    if (mesCases.isEmpty()) joueEnCoin(grille);
                    else safePlay(grille, 6);
                else if (sesCases.get(0) % 2 == 0) safePlay(grille, 8 - sesCases.get(0));
                else if (sesCases.get(0) < 4) safePlay(grille, 0);
                else safePlay(grille, 8);
            } else if (sesCases.size() >= 2)
                if (coupGagnant(grille, sesCases)) return;
                else if (mesCases.contains(4))
                    joueOffensif(grille, mesCases, sesCases);
                else joueDefensif(grille, mesCases, sesCases);
        }

    }

    private void joueDerniereCase(Grille grille) {
        for (int i = 0; i < 9; i++) {
            if (grille.estLibre(i)) {
                if (safePlay(grille, i)) return;
            }
        }
    }

    private boolean joueEnCoin(Grille grille) {
        int[] coins = new int[]{0, 2, 6, 8};
        List<Integer> coinsJouables = new ArrayList<Integer>();
        for (int i = 0; i < coins.length; i++) {
            if (grille.estLibre(coins[i]))
                coinsJouables.add(coins[i]);
        }
        if (coinsJouables.isEmpty()) return false;
        Random random = new Random();
        return safePlay(grille, coinsJouables.get(random.nextInt(coinsJouables.size())));
    }

    private void joueDefensif(Grille grille, List<Integer> mesCases, List<Integer> sesCases)  {
        if (joueEnCoin(grille)) return;
        throw new RuntimeException("defensif not yet implemented");
    }

    private void joueOffensif(Grille grille, List<Integer> mesCases, List<Integer> sesCases) {
        if (decisionOffensive(grille, mesCases, 8, Arrays.asList(2, 5, 6))) return;
        if (decisionOffensive(grille, mesCases, 8, Arrays.asList(6, 7, 2))) return;
        if (decisionOffensive(grille, mesCases, 6, Arrays.asList(0, 3, 2))) return;
        if (decisionOffensive(grille, mesCases, 6, Arrays.asList(8, 7, 0))) return;
        if (decisionOffensive(grille, mesCases, 2, Arrays.asList(0, 1, 8))) return;
        if (decisionOffensive(grille, mesCases, 2, Arrays.asList(8, 5, 0))) return;
        if (decisionOffensive(grille, mesCases, 0, Arrays.asList(2, 1, 6))) return;
        if (decisionOffensive(grille, mesCases, 0, Arrays.asList(6, 3, 2))) return;

        if (decisionOffensive(grille, mesCases, 4, Arrays.asList(1, 7))) return;
        if (decisionOffensive(grille, mesCases, 4, Arrays.asList(3, 5))) return;

        throw new RuntimeException("offensif not yet implemented");
    }


    private boolean decisionOffensive(Grille grille, List<Integer> mesCases, int casePrise, List<Integer> caseLibres) {
        if (mesCases.contains(casePrise)) {
            for (Integer caseLibre : caseLibres) {
                if (!grille.estLibre(caseLibre)) return false;
            }
            return safePlay(grille, caseLibres.get(0));
        }
        return false;
    }

    private boolean coupGagnant(Grille grille, List<Integer> mesCases) {
        for (int i = 0; i < 3; i++) {
            if (mesCases.contains(3 * i) && mesCases.contains(3 * i + 1) && grille.estLibre(3 * i + 2)) {
                return safePlay(grille, 3 * i + 2);
            }
            if (grille.estLibre(3 * i) && mesCases.contains(3 * i + 1) && mesCases.contains(3 * i + 2)) {
                return safePlay(grille, 3 * i);
            }
            if (mesCases.contains(3 * i) && grille.estLibre(3 * i + 1) && mesCases.contains(3 * i + 2)) {
                return safePlay(grille, 3 * i + 1);
            }
            if (mesCases.contains(i) && mesCases.contains(i + 3) && grille.estLibre(i + 6)) {
                return safePlay(grille, i + 6);
            }
            if (grille.estLibre(i) && mesCases.contains(i + 3) && mesCases.contains(i + 6)) {
                return safePlay(grille, i);
            }
            if (mesCases.contains(i) && grille.estLibre(i + 3) && mesCases.contains(i + 6)) {
                return safePlay(grille, i + 3);
            }
        }
        if (mesCases.contains(0) && mesCases.contains(4) && grille.estLibre(8)) {
            return safePlay(grille, 8);
        }
        if (grille.estLibre(0) && mesCases.contains(4) && mesCases.contains(8)) {
            return safePlay(grille, 0);
        }
        if (mesCases.contains(0) && grille.estLibre(4) && mesCases.contains(8)) {
            return safePlay(grille, 4);
        }
        if (mesCases.contains(2) && mesCases.contains(4) && grille.estLibre(6)) {
            return safePlay(grille, 6);
        }
        if (grille.estLibre(2) && mesCases.contains(4) && mesCases.contains(6)) {
            return safePlay(grille, 2);
        }
        if (mesCases.contains(2) && grille.estLibre(4) && mesCases.contains(6)) {
            return safePlay(grille, 4);
        }
        return false;
    }

    private boolean safePlay(Grille grille, int i) {
        try {
            grille.joue(i, this);
        } catch (OccupiedCellException e) {
            throw new RuntimeException("it would'nt be append");
        }
        return true;
    }

    private boolean centreLibre(List<Integer> mesCases, List<Integer> sesCases) {
        return !(mesCases.contains(4) || sesCases.contains(4));
    }

    @Override
    public String toString() {
        return motif;
    }
}
