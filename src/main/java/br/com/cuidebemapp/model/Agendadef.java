/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "agendadef")
@XmlRootElement
public class Agendadef implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "agendadef_sequence", sequenceName = "agendadef_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agendadef_sequence")
	@Basic(optional = false)
	@Column(name = "idagendadef")
	private Long idagendadef;
	@Basic(optional = false)
	@Column(name = "datainicio")
	private java.time.OffsetDateTime datainicio;
	@Basic(optional = false)
	@Column(name = "datafim")
	private java.time.OffsetDateTime datafim;
	@Basic(optional = false)
	@Column(name = "dataregistro")
	private java.time.OffsetDateTime dataRegistro;
	@Basic(optional = false)
	@Column(name = "horario")
	private String horario;
	@Column(name = "repetirhoras")
	private Integer repetirHoras;
	@Column(name = "diasemana")
	private String diasemana;
	@Basic(optional = false)
	@Column(name = "grupoevento")
	private String grupoevento;
	@Basic(optional = false)
	@Column(name = "subgrupoevento")
	private String subgrupoevento;
	@Column(name = "observacao")
	private String observacao;
	@Basic(optional = false)
	@Column(name = "diaspersonalizado")
	private boolean diaspersonalizado;
	@Basic(optional = false)
	@Column(name = "enabled")
	private boolean enabled;
	@OneToMany(mappedBy = "agendadef", fetch = FetchType.LAZY)
	private Set<Agenda> agendaSet;
	@JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Paciente paciente;
	  @javax.persistence.Transient
	    private Integer[] dias;

	public Agendadef() {
	}

	public Agendadef(Long idagendadef) {
		this.idagendadef = idagendadef;
	}

	public Agendadef(Long idagendadef, java.time.OffsetDateTime datainicio, java.time.OffsetDateTime datafim,
			java.time.OffsetDateTime dataRegistro, String horario, String grupoevento, String subgrupoevento,
			boolean diaspersonalizado, boolean enabled) {
		this.idagendadef = idagendadef;
		this.datainicio = datainicio;
		this.datafim = datafim;
		this.dataRegistro = dataRegistro;
		this.horario = horario;
		this.grupoevento = grupoevento;
		this.subgrupoevento = subgrupoevento;
		this.diaspersonalizado = diaspersonalizado;
		this.enabled = enabled;
	}

	public Long getIdagendadef() {
		return idagendadef;
	}

	public void setIdagendadef(Long idagendadef) {
		this.idagendadef = idagendadef;
	}

	public java.time.OffsetDateTime getDatainicio() {
		return datainicio;
	}

	public void setDatainicio(java.time.OffsetDateTime datainicio) {
		this.datainicio = datainicio;
	}

	public java.time.OffsetDateTime getDatafim() {
		return datafim;
	}

	public void setDatafim(java.time.OffsetDateTime datafim) {
		this.datafim = datafim;
	}

	public java.time.OffsetDateTime getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(java.time.OffsetDateTime dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getRepetirHoras() {
		return repetirHoras;
	}

	public void setRepetirHoras(Integer repetirHoras) {
		this.repetirHoras = repetirHoras;
	}

	public String getDiasemana() {
		return diasemana;
	}

	public void setDiasemana(String diasemana) {
		this.diasemana = diasemana;
	}

	public String getGrupoevento() {
		return grupoevento;
	}

	public void setGrupoevento(String grupoevento) {
		this.grupoevento = grupoevento;
	}

	public String getSubgrupoevento() {
		return subgrupoevento;
	}

	public void setSubgrupoevento(String subgrupoevento) {
		this.subgrupoevento = subgrupoevento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean getDiaspersonalizado() {
		return diaspersonalizado;
	}

	public void setDiaspersonalizado(boolean diaspersonalizado) {
		this.diaspersonalizado = diaspersonalizado;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@XmlTransient
	public Set<Agenda> getAgendaSet() {
		return agendaSet;
	}

	public void setAgendaSet(Set<Agenda> agendaSet) {
		this.agendaSet = agendaSet;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public Integer[] getDias() {
		if (diasemana != null && !diasemana.isEmpty()) {
			String[] _dias = diasemana.split(",");
			int _diaslenght = _dias.length;
			dias = new Integer[_diaslenght];
			for(int i = 0 ;i<_diaslenght;i++){
				dias[i] = Integer.valueOf(_dias[i]);
			}
		}
		return dias;
	}

	public void setDias(Integer[] dias) {
		this.dias = dias;
		if (dias != null && dias.length > 0) {
			diasemana = "";
			boolean first = true;
			for(Integer _dia : dias){
				if(first){
					diasemana = String.valueOf(_dia);
				}else{
				diasemana = diasemana.concat(",").concat(String.valueOf(_dia));
				}
				first = false;
			}
		}
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idagendadef != null ? idagendadef.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Agendadef)) {
			return false;
		}
		Agendadef other = (Agendadef) object;
		if ((this.idagendadef == null && other.idagendadef != null)
				|| (this.idagendadef != null && !this.idagendadef.equals(other.idagendadef))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.cuidebemapp.model.Agendadef[ idagendadef=" + idagendadef + " ]";
	}

}
