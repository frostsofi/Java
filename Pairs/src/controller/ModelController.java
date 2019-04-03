package controller;

import model.GameModel;
import view.GameView;

public class ModelController
{
    private GameModel coreGame;
    private int pairCount;
    private int one;
    private int two;

    public ModelController(GameView curView)
    {
        coreGame = new GameModel(curView);
        pairCount = 1;
    }

    public void infoCard(int numbCard)
    {
        if (pairCount == 1) {
            one = numbCard;
            pairCount++;
        }
        else
            { two = numbCard;
                pairCount = 1;
              coreGame.checkSimilarCards(one, two);
            }
    }

    public void infoNewGame(String name)
    {
         coreGame.prepairForNewGame(name);
         coreGame.play();
    }


  public void infoScore()
    {
           coreGame.giveScoreTable();
    }
}
