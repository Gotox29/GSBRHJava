package bzh.gsbrh.modeles;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class formDate {

	public static String formatDate(String date){
		int jours = 0;
		int mois = 0;
		int annees = 0;
		NumberFormat formatterMJ = new DecimalFormat("00");
		NumberFormat formatterAN = new DecimalFormat("0000");
		if(date.equals("") || date.equals("--")){
			return date;
		}else{
			String[]dates = date.split("-");
			if(!dates[0].isEmpty())
				annees = Integer.parseInt(dates[0]);
			if(!dates[1].isEmpty())
				mois = Integer.parseInt(dates[1]);
			if(!dates[2].isEmpty())
				jours = Integer.parseInt(dates[2]);

			date = formatterAN.format(annees) +"-"+ formatterMJ.format(mois) +"-"+ formatterMJ.format(jours);
		}
		return date;
	}
	public static String dateAffichable(String date){
		int jours = 0;
		int mois = 0;
		int annees = 0;
		NumberFormat formatterMJ = new DecimalFormat("00");
		NumberFormat formatterAN = new DecimalFormat("0000");
		if(!(date.equals(""))){
			String[]dates = date.split("-");
			if(!dates[0].isEmpty())
				annees = Integer.parseInt(dates[0]);
			if(!dates[1].isEmpty())
				mois = Integer.parseInt(dates[1]);
			if(!dates[2].isEmpty())
				jours = Integer.parseInt(dates[2]);
			date = formatterMJ.format(jours) + "-" + formatterMJ.format(mois) + "-" + formatterAN.format(annees);
		}
		return date;
		
	}
	
	public static boolean dateDepasse(String date){
		if(date.isEmpty())
			return false;
		int jours = 0;
		int mois = 0;
		int annees = 0;
		LocalDate compar = LocalDate.now();
		String[]dates = date.split("-");
		if(!dates[0].isEmpty())
			annees = Integer.parseInt(dates[0]);
		if(!dates[1].isEmpty())
			mois = Integer.parseInt(dates[1]);
		if(!dates[2].isEmpty())
			jours = Integer.parseInt(dates[2]);
		LocalDate temp = LocalDate.now();
		try{
			temp = LocalDate.of(annees, mois, jours);
		}catch(Exception e){
			 
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		int dateTemp = Integer.parseInt(temp.format(formatter));
		int dateCompar = Integer.parseInt(compar.format(formatter));
		
		if(dateTemp < dateCompar)
			return true;
		
		
		return false;
	}
	
}
