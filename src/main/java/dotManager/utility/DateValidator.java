package dotManager.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateValidator {

    public static int determineHowManyDaysItsBeen (Date date) {
            Calendar query = Calendar.getInstance();
            boolean isOk = false;
            int howLong = 0;
            Date inject = new Date();
			inject.setDate((int) Date.UTC(2001, 1, 1, 0, 0, 1));
            while(!isOk) {
            	
            	if(date.before(inject))
            	{
            		howLong = -1;
            		isOk = true;
            		break;
            	}
            	if(!date.before(query.getTime())&&!date.after(query.getTime())) {
            		isOk = true;
            		break;
            	}
            	query.add(Calendar.DAY_OF_YEAR, 1);
            	howLong++;
            }
            return howLong;         
    }

}