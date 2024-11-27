package tictactoe;

import java.util.Random;

public class Manche {

    public Joueur play(Grille grid, Joueur joueur1, Joueur joueur2)  {
        Joueur player = joueur1;
        Joueur other = joueur2;
        grid.display();
        while (!grid.isGagnante() && !grid.isPat()) {
            player.joue(grid);
            Joueur temp = player;
            player = other;
            other = temp;
            grid.display();
        }
        return grid.gagnant();
    }
}
