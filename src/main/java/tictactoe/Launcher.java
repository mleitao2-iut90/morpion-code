package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) throws OccupiedCellException {
        Random random = new Random();
        Joueur human = new Human(new Scanner(System.in));
       // Joueur human = new IA("O");
        Joueur ia =new IA("X");
        Game game = new Game(human, ia, new MancheFactory());
        game.play();
        System.out.println("Match gagné par :"+game.winner());
    }
}
