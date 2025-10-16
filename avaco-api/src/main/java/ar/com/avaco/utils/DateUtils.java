package ar.com.avaco.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * dd/MM/yyyy
	 */
	public final static String dd_MM_yyyy = "dd/MM/yyyy";

	/**
	 * yyyyMMdd
	 */
	public final static String yyyyMMdd = "yyyyMMdd";

	/**
	 * dd/MM/yy HH:mm
	 */
	public final static String dd_MM_yy_HH_mm = "dd/MM/yy HH:mm";

	/**
	 * dd/MM/yyyy HH:mm:ss
	 */
	public final static String dd_MM_yyyy_HH_mm_ss = "dd/MM/yyyy HH:mm:ss";

	public static Date toDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDate toLocalDate(String fecha, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return LocalDate.parse(fecha, formatter);
	}

	public static String toString(LocalDate ld, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return ld.format(formatter);
	}

	public static Date toDate(String dia, String pattern) {
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(pattern);
		try {
			return dateFormat.parse(dia);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date toDate(String dia) throws ParseException {
		return toDate(dia, yyyyMMdd);
	}

	/**
	 * Convierte fecha a string con pattern dd/MM/yyyy
	 * 
	 * @param fecha
	 * @return
	 */
	public static String toStringFecha(Date fecha) {
		return toString(fecha, dd_MM_yyyy);
	}

	/**
	 * Convierte fecha a string con pattern dd/MM/yy HH:mm
	 * 
	 * @param fecha
	 * @return
	 */
	public static String toStringFechaHora(Date fecha) {
		return toString(fecha, dd_MM_yy_HH_mm);
	}

	public static String toStringFechaHoraSinSegundos(Date fecha) {
		return toString(fecha, dd_MM_yy_HH_mm);
	}

	public static String toString(Date fecha, String pattern) {
		if (fecha != null) {
			SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
			dateFormat.applyPattern(pattern);
			return dateFormat.format(fecha);
		}
		return "";
	}

	public static String toString(LocalDate fecha) {
		SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(yyyyMMdd);
		return dateFormat.format(fecha);
	}

	public static Date getFechaYHoraActual() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

}
