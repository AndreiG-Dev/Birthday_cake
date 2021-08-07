import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Cake {

    private Candle[] candles;
    private int numberOfCandles;

    private final static String NEW_LINE = System.getProperty("line.separator");
    private final static String GET_NUMBER = "Provide the number of candles (min 1, max 1000):";
    private final static String NOT_INT = "Input is not a valid integer.";
    private final static String NOT_IN_RANGE = "Input doesn't comply with constraint - min 1, max 1000.";
    private final static String GET_CANDLES_HEIGHT = "Provide height (min 1, max 1000) for %s candles: %s";
    private final static String WRONG_NUMBER = "Input doesn't correspond to the number of candles or is not integer.";
    private final static String WISH_WILL_COME_TRUE = " Congratulations, your wish will come true!";
    private final static String WISH_WILL_NOT_COME_TRUE = " Your wish will not come true. " +
                                                          "Next time buy candles of similar height.";
    private final static String BLOWN_OUT = "You have blown out %s out of %d candles.";


    public int getNumberOfCandles() {
        return numberOfCandles;
    }

    public void setNumberOfCandles(int numberOfCandles) {
        this.numberOfCandles = numberOfCandles;
        this.candles = new Candle[numberOfCandles];
    }

    public Candle[] getCandles() {
        return candles;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println(GET_NUMBER);
        String inputNumberOfCandles = input.nextLine();

        //Checking if provided input complies to constraints
        while (true){
            if (!isInteger(inputNumberOfCandles)){
                System.out.println(NOT_INT);
                System.out.println(GET_NUMBER);
                inputNumberOfCandles = input.nextLine();

            }
            else {
                if (!(Integer.parseInt(inputNumberOfCandles) >= 1 && Integer.parseInt(inputNumberOfCandles) <= 1000)){
                    System.out.println(NOT_IN_RANGE);
                    System.out.println(GET_NUMBER);
                    inputNumberOfCandles = input.nextLine();
                }
                else{
                    break;
                }
            }
        }

        Cake cake = new Cake();
        cake.setNumberOfCandles(Integer.parseInt(inputNumberOfCandles));

        System.out.format(GET_CANDLES_HEIGHT, inputNumberOfCandles, NEW_LINE);
        String[] inputCandles = input.nextLine().split(" ");

        //Checking if provided input complies to constraints
        while (true){
            if (inputCandles.length != cake.getNumberOfCandles()){
                System.out.println(WRONG_NUMBER);
                System.out.format(GET_CANDLES_HEIGHT, inputNumberOfCandles, NEW_LINE);
                System.out.println();
                inputCandles = input.nextLine().split(" ");
            }
            else {
                if (!Arrays.stream(inputCandles).allMatch(c -> isInteger(c))) {
                    System.out.println(NOT_INT);
                    System.out.format(GET_CANDLES_HEIGHT, inputNumberOfCandles, NEW_LINE);
                    System.out.println();
                    inputCandles = input.nextLine().split(" ");
                }
                else {
                    break;
                }
            }
        }

        for (int i=0; i<inputCandles.length; i++){
            cake.getCandles()[i] = new Candle(Integer.parseInt(inputCandles[i]));
        }

        int candlesBlownOut = cake.birthdayCakeCandles(cake.getCandles());
        if (candlesBlownOut == cake.getNumberOfCandles()){
            System.out.format(BLOWN_OUT, candlesBlownOut, cake.getNumberOfCandles());
            System.out.println(WISH_WILL_COME_TRUE);
        }
        else {
            System.out.format(BLOWN_OUT, candlesBlownOut, cake.getNumberOfCandles());
            System.out.println(WISH_WILL_NOT_COME_TRUE);
        }
    }

    public int birthdayCakeCandles(Candle[] candles){
        return (int) Arrays.stream(candles).filter(candle -> candle.getHeight() == Arrays.stream(candles).
                max(Comparator.comparingInt(Candle::getHeight)).get().getHeight()).count();

    }

    public static boolean isInteger(String input){
        try{
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

class Candle {

    private int height;

    public Candle(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}

