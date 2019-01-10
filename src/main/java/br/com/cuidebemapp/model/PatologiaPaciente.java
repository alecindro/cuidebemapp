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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "patologia_paciente")
@XmlRootElement
public class PatologiaPaciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "patologia_paciente_sequence", sequenceName = "patologia_paciente_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patologia_paciente_sequence")
    @Basic(optional = false)
    @Column(name = "idpatologiapaciente")
    private Long idpatologiapaciente;
    @Basic(optional = false)
    @Column(name = "patologia")
    private String patologia;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Paciente paciente;

    public PatologiaPaciente() {
    }

    public PatologiaPaciente(Long idpatologiapaciente) {
        this.idpatologiapaciente = idpatologiapaciente;
    }

    public PatologiaPaciente(Long idpatologiapaciente, String patologia) {
        this.idpatologiapaciente = idpatologiapaciente;
        this.patologia = patologia;
    }

    public Long getIdpatologiapaciente() {
        return idpatologiapaciente;
    }

    public void setIdpatologiapaciente(Long idpatologiapaciente) {
        this.idpatologiapaciente = idpatologiapaciente;
    }

    public String getPatologia() {
        return patologia;
    }

    public void setPatologia(String patologia) {
        this.patologia = patologia;
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
        hash += (idpatologiapaciente != null ? idpatologiapaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatologiaPaciente)) {
            return false;
        }
        PatologiaPaciente other = (PatologiaPaciente) object;
        if ((this.idpatologiapaciente == null && other.idpatologiapaciente != null) || (this.idpatologiapaciente != null && !this.idpatologiapaciente.equals(other.idpatologiapaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.PatologiaPaciente[ idpatologiapaciente=" + idpatologiapaciente + " ]";
    }
    
}
