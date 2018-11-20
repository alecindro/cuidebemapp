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
@Table(name = "schedulemail")
@XmlRootElement
public class Schedulemail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idschedulemail")
    private Long idschedulemail;
    @Basic(optional = false)
    @Column(name = "diasemana")
    private String diasemana;
    @Basic(optional = false)
    @Column(name = "hora")
    private String hora;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente paciente;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Responsavel responsavel;

    public Schedulemail() {
    }

    public Schedulemail(Long idschedulemail) {
        this.idschedulemail = idschedulemail;
    }

    public Schedulemail(Long idschedulemail, String diasemana, String hora, boolean enabled) {
        this.idschedulemail = idschedulemail;
        this.diasemana = diasemana;
        this.hora = hora;
        this.enabled = enabled;
    }

    public Long getIdschedulemail() {
        return idschedulemail;
    }

    public void setIdschedulemail(Long idschedulemail) {
        this.idschedulemail = idschedulemail;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idschedulemail != null ? idschedulemail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Schedulemail)) {
            return false;
        }
        Schedulemail other = (Schedulemail) object;
        if ((this.idschedulemail == null && other.idschedulemail != null) || (this.idschedulemail != null && !this.idschedulemail.equals(other.idschedulemail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Schedulemail[ idschedulemail=" + idschedulemail + " ]";
    }
    
}
