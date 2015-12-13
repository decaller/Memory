import java.util.Random;

/**
 * Created by HarridiIlman on 11/12/2015.
 */
public class LevelMaker {

    private int sumCard;

    //pembuat level
    public Position makeLevel(int level, int sublevel) {
        switch (level){
            case 1 : {
                sumCard = 6;
                break;
            }
            case 2 : {
                sumCard = 12;
                break;
            }
            case 3 : {
                sumCard = 20;
                break;
            }
        }

        Random rand = new Random();
        Position soal = new Position();

        for (int i = 1; i <= sumCard/2; i++){
            switch (sublevel){
                case 1 : { //number
                    int number = rand.nextInt(100)+1;
                    soal.setCard(Integer.toString(number));
                    break;
                }
                case 2 : { //character
                    int number = rand.nextInt(47);
                    char symbol = (char) (48 + number);
                    soal.setCard(Character.toString(symbol));
                    break;
                }
                case 3 : { //big number
                    int number = rand.nextInt(100000)+1;
                    soal.setCard(Integer.toString(number));
                    break;
                }
                case 4 : { //symbol
                    int number = rand.nextInt(100);
                    char symbol = (char) (number);
                    soal.setCard(Character.toString(symbol));
                    break;
                }
            }

        }

        soal.randomize(); //randomize position

        return soal;
    }
}
