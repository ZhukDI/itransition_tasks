import java.util.ArrayList;

/**
 * Created by Pavel on 27.06.2017.
 */
public class Compare {
    static ArrayList<Integer> winningElements = new ArrayList<>();

    //Who is winner
    public static void selectWinner(int botChoice, int userChoice, int numberOfElements) {
//        System.out.println("bot: " + botChoice);
//        System.out.println("user: " + userChoice);
//        System.out.println("number: " + numberOfElements);
        for (int i = 2; i <= userChoice; i+=2) {
//            System.out.println("elem " + (userChoice - i));
            winningElements.add(userChoice - i);
        }

        for (int i = 0; i < numberOfElements-userChoice; i+=2) {
            winningElements.add((userChoice+1) + i);
        }

        boolean flag = false;
        for (Integer el: winningElements) {
            if (el == botChoice) {
                flag = true;
                break;
            }
        }

        if(flag)
            System.out.println("You win");
        else if (userChoice == botChoice)
            System.out.println("Draw");
        else
            System.out.println("You lose");
    }

}
