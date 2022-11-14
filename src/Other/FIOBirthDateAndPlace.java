package Other;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FIOBirthDateAndPlace {
    public static boolean isCorrect(String str)
    {
        String regex = "^[А-ЯA-Z][а-яa-z]* [А-ЯA-Z][а-яa-z]* ([А-ЯA-Z][а-яa-z]* )?((0?[1-9]|[1-2][0-9]|3[01])[./](0?[1-9]|1[0-2])[./]([1-9]\\d*)) [А-ЯA-Z][а-яa-z]*([ -]([А-ЯA-Z][а-яa-z]*|[а-яa-z]+))*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches())
        {
            String date = matcher.group(2); //Get date group

            try{
                var splitDate = date.split("[./]");

                int day = Integer.parseInt(splitDate[0]);
                int month = Integer.parseInt(splitDate[1]);
                int year = Integer.parseInt(splitDate[2]);

                // Try to convert
                LocalDate ld = LocalDate.of(year, month, day);

                return true;
            }
            catch (DateTimeException ignored) {}
        }

        return false;
    }
}
