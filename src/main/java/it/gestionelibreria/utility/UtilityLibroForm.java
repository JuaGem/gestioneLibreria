package it.gestionelibreria.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class UtilityLibroForm {

	public static boolean validateInput(String titoloInputParam, String genereInputParam, String pagineInputStringParam,
			String dataPubblicazioneStringParam) {

		if (StringUtils.isBlank(titoloInputParam) || StringUtils.isBlank(genereInputParam)
				|| !NumberUtils.isCreatable(pagineInputStringParam)
				|| StringUtils.isBlank(dataPubblicazioneStringParam) || !checkIntegerPositive(pagineInputStringParam))
			return false;

		return true;
	}

	public static boolean checkIntegerPositive(String intero) {
		if(!checkInteger(intero))
			return false;
		
		if(Integer.parseInt(intero) < 0)
			return false;
		
	  return true;
	}
	
	public static boolean checkInteger(String intero) {
		if(!NumberUtils.isCreatable(intero) || NumberUtils.toInt(intero, -1) == -1)
			return false;
		
	 return true;
	}
	
	public static Date parseDatePubblicazioneFromString(String dataPubblicazioneStringParam) {
		
		if (StringUtils.isBlank(dataPubblicazioneStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataPubblicazioneStringParam);
		} catch (ParseException e) {
			return null;
		}
		
	}

}
