package domain;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jurgen
 */
public class TimeFormat
{
    public TimeFormat()
    {
    }

    public String stringDifferenceFromNow(Date fromDate)
    {
        Calendar calFrom = Calendar.getInstance();
        calFrom.setTime(fromDate);
        Calendar calNow = Calendar.getInstance();
        long milliseconds1 = calFrom.getTimeInMillis();
        long milliseconds2 = calNow.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        long diffSeconds = diff / 1000;
        long diffMinutes = diff / (60 * 1000);
        long diffHours = diff / (60 * 60 * 1000);
        long diffDays = diff / (24 * 60 * 60 * 1000);

        //minder dan een minuut => druk uit in seconden
        if(diffSeconds < 60)
        {
            if (diffSeconds < 2)
            {
                return(diffSeconds + " second ago.");
            }
            else
            {
                return(diffSeconds + " seconds ago.");
            }
        }
        //minder dan een uur => druk uit in minuten
        else if(diffMinutes < 60)
        {
            if(diffMinutes < 2)
            {
                return(diffMinutes + " minute ago.");
            }
            else
            {
                return(diffMinutes + " minutes ago.");
            }
        }
        //minder dan een dag => druk uit in uren
        else if(diffHours < 24)
        {
            if(diffHours < 2)
            {
                return(diffHours + " hour ago.");
            }
            else
            {
                return(diffHours + " hours ago.");
            }
        }
        //ouder dan een dag => druk uit in dagen
        else
        {
            return(diffDays + " days ago.");
        }
    }
}
