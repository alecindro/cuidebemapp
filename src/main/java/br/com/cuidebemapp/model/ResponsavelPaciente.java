/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "responsavel_paciente")
@XmlRootElement
public class ResponsavelPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "responsavel_paciente_sequence", sequenceName = "responsavel_paciente_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_paciente_sequence")
    @Basic(optional = false)
    @Column(name = "idresponsavel_paciente")
    private Long idresponsavelPaciente;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente paciente;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
    private Responsavel responsavel;

    public ResponsavelPaciente() {
    }

    public ResponsavelPaciente(Long idresponsavelPaciente) {
        this.idresponsavelPaciente = idresponsavelPaciente;
    }

    public Long getIdresponsavelPaciente() {
        return idresponsavelPaciente;
    }

    public void setIdresponsavelPaciente(Long idresponsavelPaciente) {
        this.idresponsavelPaciente = idresponsavelPaciente;
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
        hash += (idresponsavelPaciente != null ? idresponsavelPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelPaciente)) {
            return false;
        }
        ResponsavelPaciente other = (ResponsavelPaciente) object;
        if ((this.idresponsavelPaciente == null && other.idresponsavelPaciente != null) || (this.idresponsavelPaciente != null && !this.idresponsavelPaciente.equals(other.idresponsavelPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.ResponsavelPaciente[ idresponsavelPaciente=" + idresponsavelPaciente + " ]";
    }
    
}
