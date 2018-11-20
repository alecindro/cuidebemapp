package br.com.cuidebemapp.util;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class DateUtil {

	public static LocalDateTime dataInicio() {
	return DateUtil.dataNow().minusHours(24L);
	}
	
	public static LocalDateTime dataNow() { 
	LocalDateTime end = LocalDateTime.now();
	return end;
	}
	
	public static java.time.OffsetDateTime dataAntes24Hs(){
		return java.time.OffsetDateTime.of(DateUtil.dataInicio(), OffsetDateTime.now().getOffset());
	}
	
	public static java.time.OffsetDateTime dataAgora(){
		return java.time.OffsetDateTime.of(DateUtil.dataNow(), OffsetDateTime.now().getOffset());
	}
	
	public static java.time.OffsetDateTime fromData(LocalDateTime localdatetime){
		return java.time.OffsetDateTime.of(localdatetime, OffsetDateTime.now().getOffset());
	}
	
}
