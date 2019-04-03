package view;

import controller.ModelController;

import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.util.*;

public class GameView
{
    private ModelController curController = new ModelController(this);
    private JFrame gameFrame = new JFrame();
    private defaultPanel backGround = new defaultPanel();
    private fieldPanel gameField = new fieldPanel();
    private HashMap<fieldPanel.CardButton, Map.Entry<Integer, String>> cardAttributes = new HashMap<>();
    private Menu gameMenu = new Menu();

    class defaultPanel extends JPanel
    {
        private JToggleButton openMenu = new JToggleButton("MENU");
        private GridBagConstraints constraints = new GridBagConstraints();

        public void paintComponent(Graphics g)
        {
            Image imageBack = new ImageIcon("C:/Users/frost/IdeaProjects/Pairs/src/view/background.jpg").getImage();
            g.drawImage(imageBack, 0, 0, gameFrame.getWidth(), gameFrame.getHeight(),  this);

            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.insets = new Insets(50, 100,  50, 200);

            if (openMenu.getActionListeners().length == 0)
            {
                openMenu.setSize(200,100);
                defaultPanel.this.add(openMenu, constraints);
                openMenu.addItemListener(new startGameListener());
                openMenu.updateUI();
            }

        }

        class startGameListener implements ItemListener
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
                    gameMenu.setVisible(true);
                else
                    gameMenu.setVisible(false);
            }
        }

    }

    class fieldPanel extends JPanel
    {
        private int x = 0, y = 0;
        private GridBagConstraints constraints = new GridBagConstraints();

        class CardButton extends JButton {

            CardButton(ImageIcon imageIcon)
            {
                this.setIcon(imageIcon);
                this.setSize(150, 150);
                this.addActionListener(new CardListener());
            }

            class CardListener implements ActionListener
            {
                public void actionPerformed(ActionEvent event)
                {
                    CardButton.this.setIcon(new ImageIcon(cardAttributes.get(CardButton.this).getValue()));
                    curController.infoCard(cardAttributes.get(CardButton.this).getKey());
                }
            }
        }

        CardButton getButton(int size)
        {
            CardButton cur = new CardButton(new ImageIcon("C:/Users/frost/IdeaProjects/Pairs/src/view/standart.jpg"));

            constraints.gridwidth = 1;
            constraints.gridheight = 1;
            constraints.insets = new Insets(30, 40,  10, 40);

            if (x == size/2 ) {
                constraints.gridy = 1;
                x = 0;
                y++;
            }

            constraints.gridx = x;
              x++;

            fieldPanel.this.add(cur, constraints);
            if (y == 1 && x == size/2)
            {
                x = 0;
                y = 0;
                constraints.gridy = 0;
            }
            return cur;
        }

        public void paintComponent(Graphics g)
        {
            Image imageBack = new ImageIcon("C:/Users/frost/IdeaProjects/Pairs/src/view/background.jpg").getImage();
            g.drawImage(imageBack, 0, 0, gameFrame.getWidth(), gameFrame.getHeight(), this);
            this.setVisible(true);
        }
    }

    class Menu extends JPopupMenu
    {
        private JPopupMenu popup = new JPopupMenu();
        private JMenuItem newGame = new JMenuItem("NEW GAME");
        private JMenuItem exit = new JMenuItem("EXIT");
        private JMenuItem about = new JMenuItem("ABOUT");
        private JMenuItem highScore = new JMenuItem("HIGH SCORE");

        private class newGameListener implements MouseListener
        {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e)
            {
                TextArea name = new TextArea();
                JOptionPane.showConfirmDialog(Menu.this, name, "Enter name",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (!name.getText().isEmpty())
                  curController.infoNewGame(name.getText());
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) {
                popup.setVisible(false);
            }
        }

        class exitListener implements MouseListener
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                popup.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        }

        class aboutListener implements MouseListener
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                JTextArea area = new JTextArea();

                try {
                    area.setText(new String(new FileInputStream("C:/Users/frost/IdeaProjects/Pairs/src/view/about.txt").readAllBytes()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JPanel pane = new JPanel();

                class closeButtonListener implements ActionListener
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        pane.setVisible(false);
                        backGround.openMenu.setVisible(true);
                    }
                }

                pane.setBackground(Color.lightGray);
                JButton close = new JButton("CLOSE");
                close.addActionListener(new closeButtonListener());
                close.setSize(30,50);

                pane.add(area);
                pane.add(close);

                backGround.openMenu.setVisible(false);
                backGround.add(pane);
                backGround.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }

        }

        class highScoreListener implements MouseListener
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                curController.infoScore();
            }
            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e)
            { }
            @Override
            public void mouseExited(MouseEvent e) { }

        }

        class popupListener implements PopupMenuListener
        {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
              popup.setVisible(true);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e)
            { }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e)
            {
                popup.setVisible(false);
            }
        }

        public  void downloadOptions()
        {
            newGame.addMouseListener(new newGameListener());
            exit.addMouseListener(new exitListener());
            about.addMouseListener(new aboutListener());
            highScore.addMouseListener(new highScoreListener());

            popup.setPopupSize(300, 300);
            popup.add(newGame);
            popup.add(exit);
            popup.add(about);
            popup.add(highScore);
            this.addPopupMenuListener(new popupListener());
        }

    }

    public void showNewSet(ArrayList<Integer> cards)
    {
        gameField.removeAll();
        fieldPanel.CardButton newButton;

       for (int i = 0; i < cards.size(); i++)
        {
            newButton = gameField.getButton(cards.size());
            cardAttributes.put(newButton, Map.entry(i, "C:/Users/frost/IdeaProjects/Pairs/src/view/image"+cards.get(i)+".jpg"));
        }

        gameFrame.remove(backGround);
        gameFrame.add(gameField);
        gameFrame.setVisible(true);
        gameField.repaint();
    }

    public void start()
    {
         backGround.setSize(gameFrame.getWidth(), gameFrame.getHeight());
         backGround.setLayout(new GridBagLayout());

         gameFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
         gameFrame.add(backGround);

         gameFrame.setVisible(true);

         gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         gameField.setSize(gameFrame.getWidth(), gameFrame.getHeight());
         gameField.setLayout(new GridBagLayout());

         gameMenu.downloadOptions();
     }

    public void showEnd()
    {
        gameFrame.remove(gameField);
        gameFrame.add(backGround).repaint();
        cardAttributes.clear();
    }

    public void showScoreTable(Vector<Vector<String>> scoreTable)
    {
        Vector <String> columnNames = new Vector<>();
        columnNames.add("Name");
        columnNames.add("Score");

        JTable table = new JTable(scoreTable, columnNames);
        JPanel pane = new JPanel();

        class closeButtonListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                pane.setVisible(false);
                backGround.openMenu.setVisible(true);
            }
        }

        pane.setBackground(Color.lightGray);

        JButton close = new JButton("CLOSE");
        close.addActionListener(new closeButtonListener());
        close.setSize(30,50);

        pane.add(table);
        pane.add(close);

        backGround.openMenu.setVisible(false);
        backGround.add(pane);
        backGround.setVisible(true);

    }

    public void remove(int one, int two)
    {
        cardAttributes.forEach((key, value) -> {
            if (value.getKey() == two){
                for (ActionListener listener : key.getActionListeners())
                    listener = null;
                key = null;
            }
            });

        cardAttributes.forEach((key, value) -> {
            if (value.getKey() == two){
                for (ActionListener listener : key.getActionListeners())
                    listener = null;
                key = null;
            }
        });
    }
    public void turnBack(int one, int two)
    {
        cardAttributes.forEach((key, value) -> {
                   if (value.getKey() == one){
                       key.setIcon(new ImageIcon("C:/Users/frost/IdeaProjects/Pairs/src/view/standart.jpg"));
                   }
        });

        cardAttributes.forEach((key, value) -> {
            if (value.getKey() == two){
                key.setIcon(new ImageIcon("C:/Users/frost/IdeaProjects/Pairs/src/view/standart.jpg"));
            }
        });
    }

}
