package br.com.cuidebemapp.web.rest.dto;

import java.time.OffsetDateTime;
import java.util.Comparator;

public class Timeline implements Comparator<Timeline> {

	private OffsetDateTime dataEvento;
	private String tipo;
	private Object timelineData;
	public static final String TIPO_EVENTO = "evento";
	public static final String TIPO_MEMO = "memo";
	public static final String TIPO_PHOTO = "photo";

	public OffsetDateTime getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(OffsetDateTime dataEvento) {
		this.dataEvento = dataEvento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Object getTimelineData() {
		return timelineData;
	}

	public void setTimelineData(Object timelineData, String tipo) {
		this.timelineData = timelineData;
		this.tipo = tipo;
	}

	@Override
	public int compare(Timeline o1, Timeline o2) {
		return o2.getDataEvento().compareTo(o1.getDataEvento());
	}

	@Override
	public String toString() {
		
		return "Timeline [dataEvento=" + dataEvento + ", tipo=" + tipo + ", timeline=" + timelineData.toString() + "]";
	}

}
