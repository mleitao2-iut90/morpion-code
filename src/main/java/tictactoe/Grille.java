package tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Grille {

    private Joueur [] grille = new Joueur [9];

    public void joue(int i, Joueur  motif) throws OccupiedCellException {
        if (grille[i]!=null) throw new OccupiedCellException();
        grille[i]=motif;
    }

    public boolean isVide() {
        for (Joueur aGrille : grille) {
            if (aGrille != null)
                return false;
        }
        return true;
    }

    public List<Integer> casesJoueur(Joueur  motif) {
        List<Integer> collect = new ArrayList<Integer>();
        for (int i = 0; i < grille.length; i++) {
            if (motif.equals(grille[i]))
                collect.add(i);
        }
        return collect;
    }

    public List<Integer> casesAdverses(Joueur  motif) {
        List<Integer> collect = new ArrayList<Integer>();
        for (int i = 0; i < grille.length; i++) {
            if (grille[i]!=null && !motif.equals(grille[i]))
                collect.add(i);
        }
        return collect;
    }

    public boolean estLibre(int i) {
        return grille[i]==null;
    }

    public boolean isGagnante() {
        return gagnant()!=null;
    }

    public Joueur gagnant() {
        for (int i = 0; i < 3; i++) {
            if (grille[i*3]!=null && grille[i*3]==grille[i*3+1] && grille[i*3]==grille[i*3+2])
                return grille[i*3];
            if (grille[i]!=null && grille[i+3]==grille[i] && grille[i+6]==grille[i])
                return grille[i];
        }
        if (grille[0]!=null && grille[0]==grille[4] && grille[0] == grille[8]) {
            return grille[4];
        }
        if (grille[2]!=null && grille[2]==grille[4] && grille[2] == grille[6]) {
            return grille[4];
        }
        return null;
    }


    public boolean isPat() {
        for (Joueur joueur : grille) {
            if (joueur==null) return false;
        }
        return !isGagnante();
    }

    public void display() {
        System.out.println(buidDisplayString());
    }

    private String buidDisplayString() {
        StringBuilder builder = new StringBuilder();
        for (int li = 0; li < 3; li++) {
            for (int co = 0; co < 3; co++) {
                builder.append(" ").append(grille[li * 3 + co] == null ? "." : grille[li * 3 + co]);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
