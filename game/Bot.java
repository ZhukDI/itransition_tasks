import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.SecureRandom;
import java.util.Arrays;

public class Bot {
    private int choiceBot;

    public int choice(int size) {
        //Generation random bot choice
        SecureRandom random = new SecureRandom();
//        choiceBot = random.nextInt(size);
        byte bytes[] = new byte[10];
//        System.out.println(Arrays.toString(bytes));
        random.nextBytes(bytes);
//        System.out.println(Arrays.toString(bytes));

        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.order(ByteOrder.LITTLE_ENDIAN);  // if you want little-endian
        int result = Math.abs(buffer.getShort());
//        System.out.println(result);
        choiceBot = result % size;
//        System.out.println(choiceBot);

        return choiceBot;
    }

    public int getChoiceBot() {
        return choiceBot;
    }
}
