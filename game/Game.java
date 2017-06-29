import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public void run () throws NoSuchAlgorithmException {
        //Input elements

        System.out. print("Введите элементы: ");
        ArrayList<String> elements = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String stringElements = scanner.nextLine();
        String[] s = stringElements.split(" ");
        for (int i = 0; i < s.length; i++) {
            elements.add(s[i]);
        }

        Bot bot = new Bot();

//        GenerateHash.generate("5");
        User user = new User();
//        Compare compare = new Compare(bot.choice(elements.size()), user.choice(elements), elements.size());

        System.out.println(Generator.generateHash(Generator.generateKey(), "камень") + "   key: " + Generator.generateKey());
        Compare.selectWinner(bot.choice(elements.size()), user.choice(elements), elements.size());

        System.out.println("Компьютер выбрал " + elements.get(bot.getChoiceBot()));
    }
}
