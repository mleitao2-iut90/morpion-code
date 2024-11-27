package tictactoe;

import java.util.Scanner;


public class Human implements Joueur {

    private Scanner scanner;

    public Human(Scanner scanner) {
        this.scanner = scanner;
    }

    public void joue(Grille grille)  {
        boolean saisieOK;
        do {
            saisieOK = true;
            System.out.print("coordonnée : ");
            String line = scanner.nextLine();
            String[] split = line.split(",");
            int ligne = Integer.valueOf(split[0]) - 1;
            int colone = Integer.valueOf(split[1]) -1;
            try {
                grille.joue(ligne*3+colone, this);
            } catch (OccupiedCellException e) {
                System.out.println("Case occupée, rejouez");
                saisieOK = false;
            }
        } while (!saisieOK);
    }

    @Override
    public String toString() {
        return "O";
    }
}
