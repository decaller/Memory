import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class Menu {
    private JPanel panelMenu;
    private JButton startGameButton;
    private JLabel highScoreLabel;
    private JLabel waktuLabel;
    private JLabel levelLabel;
    private JProgressBar timeProgressBar;
    private JProgressBar levelProgressBar;
    private JLabel highLevelLabel;
    private JPanel gamePanel;
    private JButton buttonStart;

    private LevelMaker makerSoal = new LevelMaker();
    private Position soal;

    public Menu() {
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameStart();
            }
        });
    }

    private void gameStart() {
        //TODO atur bar progress
        gamePanel.removeAll();
        gamePanel.repaint();

        for (int lvl = 1; lvl <= 3; lvl++){ //3 Level difficulty
            for (int sublvl = 1; sublvl <= 5-lvl; sublvl++){ //subelevel lebih sedikit seiring level meningkat
                soal = makerSoal.makeLevel(lvl,sublvl);
                startSubLevel(soal);
            }
        }
    }

    private void startSubLevel(Position soal){
        System.out.println("startSubLevel");
        gamePanel.removeAll();

        ArrayList<String> isiKartu2 = soal.getPos();

        int x = 3;
        int y =2;
        switch (isiKartu2.size()){
            case 12 : {
                x = 4;
                y = 3;
                break;
            }
            case 20 : {
                x = 5;
                y = 4;
                break;
            }
        }
        gamePanel.setLayout(new GridLayout(x,y,5,5)); // 5,5 margin


        for (String isiKartu : isiKartu2){ //buat kartu...
            System.out.println(isiKartu);
            Card kartu = new Card(isiKartu);
            gamePanel.add(kartu);
        }

        gamePanel.revalidate();
        gamePanel.repaint();

        //TODO logic game


    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu");
        frame.setContentPane(new Menu().panelMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
