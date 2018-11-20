/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "agenda")
@XmlRootElement
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagenda")
    private Long idagenda;
    @Basic(optional = false)
    @Column(name = "data")
  
    private java.time.OffsetDateTime data;
    @Column(name = "dataregistro")
    
    private java.time.OffsetDateTime dataregistro;
    @Basic(optional = false)
    @Column(name = "grupoevento")
    private String grupoEvento;
    @Column(name = "subgrupoevento")
    private String subGrupoEvento;
    @Column(name = "observacao")
    private String observacao;
    @Column(name = "idevento")
    private Long idevento;
    @JoinColumn(name = "idagendadef", referencedColumnName = "idagendadef")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Agendadef agendadef;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente paciente;

    public Agenda() {
    }

    public Agenda(Long idagenda) {
        this.idagenda = idagenda;
    }

    public Agenda(Long idagenda, java.time.OffsetDateTime data, String grupoEvento) {
        this.idagenda = idagenda;
        this.data = data;
        this.grupoEvento = grupoEvento;
    }

    public Long getIdagenda() {
        return idagenda;
    }

    public void setIdagenda(Long idagenda) {
        this.idagenda = idagenda;
    }

    public java.time.OffsetDateTime getData() {
        return data;
    }

    public void setData(java.time.OffsetDateTime data) {
        this.data = data;
    }

    public java.time.OffsetDateTime getDataregistro() {
        return dataregistro;
    }

    public void setDataregistro(java.time.OffsetDateTime dataregistro) {
        this.dataregistro = dataregistro;
    }

    public String getGrupoEvento() {
        return grupoEvento;
    }

    public void setGrupoEvento(String grupoEvento) {
        this.grupoEvento = grupoEvento;
    }

    public String getSubGrupoEvento() {
        return subGrupoEvento;
    }

    public void setSubGrupoEvento(String subGrupoEvento) {
        this.subGrupoEvento = subGrupoEvento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public Agendadef getAgendadef() {
        return agendadef;
    }

    public void setAgendadef(Agendadef agendadef) {
        this.agendadef = agendadef;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagenda != null ? idagenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agenda)) {
            return false;
        }
        Agenda other = (Agenda) object;
        if ((this.idagenda == null && other.idagenda != null) || (this.idagenda != null && !this.idagenda.equals(other.idagenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Agenda[ idagenda=" + idagenda + " ]";
    }
    
}
