package handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomDateFormatter {

    private final HashMap<String, String> values = new HashMap<>();
    private final StringBuilder stringBuilder = new StringBuilder();

    public static CustomDateFormatter newInstance() {
        return new CustomDateFormatter();
    }

    private static Integer get(List<String> list, String string) {
        for (int i = 0; i > list.size(); i++) {
            if (list.get(i).equals(string)) {
                return i;
            }
        }
        return 0;
    }

    public String getCurrentDate() {
        OffsetDateTime dateTime = OffsetDateTime.now();
        values.put("day", String.valueOf(dateTime.getDayOfMonth()));
        values.put("month", String.valueOf(dateTime.getMonthValue()));
        values.put("year", String.valueOf(dateTime.getYear()));
        values.put("hour", String.valueOf(dateTime.getHour()));
        values.put("minute", String.valueOf(dateTime.getMinute()));
        for (Map.Entry<String, String> ens : values.entrySet()) {
            if (Integer.valueOf(ens.getValue()) < 10) {
                values.put(ens.getKey(), "0" + ens.getValue());
            }
        }
        stringBuilder.append(values.get("day") + "." + values.get("month") + "." + values.get("year") + " " + values.get("hour") + ":" + values.get("minute"));
        values.clear();
        return stringBuilder.toString();
    }

    public String format(OffsetDateTime dateTime) {
        values.put("day", String.valueOf(dateTime.getDayOfMonth()));
        values.put("month", String.valueOf(dateTime.getMonthValue()));
        values.put("year", String.valueOf(dateTime.getYear()));
        values.put("hour", String.valueOf(dateTime.getHour()));
        values.put("minute", String.valueOf(dateTime.getMinute()));
        for (Map.Entry<String, String> ens : values.entrySet()) {
            if (Integer.valueOf(ens.getValue()) < 10) {
                values.put(ens.getKey(), "0" + ens.getValue());
            }
        }
        stringBuilder.append(values.get("day") + "." + values.get("month") + "." + values.get("year") + " " + values.get("hour") + ":" + values.get("minute"));
        values.clear();
        return stringBuilder.toString();
    }

    public String fullDate() {
        return stringBuilder.toString();
    }

    public String dateOnly() {
        return stringBuilder.toString().split(" ")[0];
    }
}
