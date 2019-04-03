package model;

import view.GameView;
import java.util.*;

public class GameModel {

    private ArrayList<Integer> cards = new ArrayList<>();
    private int emountCards = 8;
    private Vector<Vector<String>> scoreTable = new Vector<>();
    private GameView view;
    private String namePlayer;
    private int countTurns;

    public GameModel(GameView viewAnother) {
                view = viewAnother;
    }

    public void prepairForNewGame(String name)
    {
        namePlayer = name;
        countTurns = 0;
        cards.clear();
        emountCards = 4;
    }

    public void play()
    {
        cards = new ArrayList<>();
        Random rnd = new Random(System.currentTimeMillis());
        int i = 0, count = 0, rank;

        while (i < emountCards*2)
        {
            rank = rnd.nextInt(4);
            if (!cards.contains(rank))
            {
                cards.add(i, rank);
                i++;
            }
            else {
                for (int j = 0; j < i; j++)
                    if (cards.get(j) == rank)
                        count++;

                if (count == 1)
                {
                    cards.add(i, rank);
                    i++;
                }
            }
            count = 0;
        }

        view.showNewSet(cards);
    }

    public void checkSimilarCards(int one, int two)
    {
       if (!cards.get(one).equals(cards.get(two)))
           view.turnBack(one, two);
       else {
           emountCards--;
           view.remove(one, two);
           }
       countTurns++;
       checkEndGame();
    }

    public void giveScoreTable()
    {
       view.showScoreTable(scoreTable);
    }

    private void checkEndGame()
    {
          if (emountCards == 0)
          {
              Vector<String> pair = new Vector<>();

              pair.add(0, Integer.toString(countTurns));
              pair.add(namePlayer);
              scoreTable.add(pair);
              
              view.showEnd();
          }
    }

}
