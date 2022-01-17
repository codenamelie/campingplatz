package objects;

import java.util.HashMap;
import java.util.TreeMap;

public class LoveImageMessageHandler {

    private static TreeMap<Integer,String> messages = new TreeMap<>();

    static {
        messages.put(15, "Nicht einmal Freunde wie es scheint");
        messages.put(30, "Freundschaft+");
        messages.put(45, "Drückt euch näher aneinander");
        messages.put(60, "Ihr solltet euch näher kennenlernen!");
        messages.put(75, "Es knistert");
        messages.put(90, "Ihr könntet einander heiraten");
    }

    public static String get(int i) {
        double minDiff = Double.MAX_VALUE;
        Integer nearest = null;
        for (Integer key : messages.keySet()) {
            double diff = Math.abs(i - key);
            if (diff < minDiff) {
                nearest = key;
                minDiff = diff;
            }
            if(i == 100) {
                return "Perfekte Liebe!";
            }else if(i == 69) {
                return ":eyes:";
            }else if(i == 0) {
                return "Ohje";
            }
        }
        return messages.get(nearest);
    }

}
