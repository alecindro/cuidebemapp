/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "paciente_sequence", sequenceName = "paciente_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paciente_sequence")
	@Basic(optional = false)
    @Column(name = "idpaciente")
    private Long idpaciente;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @Column(name = "apelido")
    private String apelido;
    @Basic(optional = false)
    @Column(name = "genero")
    private boolean genero;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date datanascimento;
    @Column(name = "status")
    private Boolean status;
    @Basic(optional = false)
    @Column(name = "tpestadia")
    private boolean tpestadia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<PacientePhoto> pacientePhotoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<ResponsavelPaciente> responsavelPacienteSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Agenda> agendaSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Memorando> memorandoSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = "paciente", allowSetters = true)
    private Set<Evento> eventoSet;
    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<PatologiaPaciente> patologiaPacienteSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Agendadef> agendadefSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente", fetch = FetchType.LAZY)
    private Set<Schedulemail> schedulemailSet;

    public Paciente() {
    }

    public Paciente(Long idpaciente) {
        this.idpaciente = idpaciente;
    }

    public Paciente(Long idpaciente, String nome, boolean genero, boolean enabled, boolean tpestadia) {
        this.idpaciente = idpaciente;
        this.nome = nome;
        this.genero = genero;
        this.enabled = enabled;
        this.tpestadia = tpestadia;
    }

    public Long getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Long idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public boolean getGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public boolean getTpestadia() {
        return tpestadia;
    }

    public void setTpestadia(boolean tpestadia) {
        this.tpestadia = tpestadia;
    }

    @Transient
    public Set<PacientePhoto> getPacientePhotoSet() {
    	if(pacientePhotoSet == null) {
    		pacientePhotoSet = new HashSet<>();
    	}
        return pacientePhotoSet;
    }

    public void setPacientePhotoSet(Set<PacientePhoto> pacientePhotoSet) {
        this.pacientePhotoSet = pacientePhotoSet;
    }

    @XmlTransient
    public Set<ResponsavelPaciente> getResponsavelPacienteSet() {
    	if(responsavelPacienteSet == null) {
    		responsavelPacienteSet = new HashSet<ResponsavelPaciente>();
    	}
        return responsavelPacienteSet;
    }

    public void setResponsavelPacienteSet(Set<ResponsavelPaciente> responsavelPacienteSet) {
        this.responsavelPacienteSet = responsavelPacienteSet;
    }

    @XmlTransient
    public Set<Agenda> getAgendaSet() {
    	if(agendaSet == null) {
    		agendaSet = new HashSet<Agenda>();
    	}
        return agendaSet;
    }

    public void setAgendaSet(Set<Agenda> agendaSet) {
        this.agendaSet = agendaSet;
    }

    @XmlTransient
    public Set<Memorando> getMemorandoSet() {
    	if(memorandoSet == null) {
    		memorandoSet = new HashSet<Memorando>();
    	}
        return memorandoSet;
    }

    public void setMemorandoSet(Set<Memorando> memorandoSet) {
        this.memorandoSet = memorandoSet;
    }

    @XmlTransient
    public Set<Evento> getEventoSet() {
    	if(eventoSet == null) {
    		eventoSet = new HashSet<Evento>();
    	}
        return eventoSet;
    }

    public void setEventoSet(Set<Evento> eventoSet) {
        this.eventoSet = eventoSet;
    }

    @Transient
    public Set<PatologiaPaciente> getPatologiaPacienteSet() {
    	if(patologiaPacienteSet == null) {
    		patologiaPacienteSet = new HashSet<PatologiaPaciente>();
    	}
        return patologiaPacienteSet;
    }

    public void setPatologiaPacienteSet(Set<PatologiaPaciente> patologiaPacienteSet) {
        this.patologiaPacienteSet = patologiaPacienteSet;
    }

    
    @XmlTransient
    public Set<Agendadef> getAgendadefSet() {
    	if(agendadefSet == null) {
    		agendadefSet = new HashSet<Agendadef>();
    	}
        return agendadefSet;
    }

    public void setAgendadefSet(Set<Agendadef> agendadefSet) {
        this.agendadefSet = agendadefSet;
    }

    @XmlTransient
    public Set<Schedulemail> getSchedulemailSet() {
    	if(schedulemailSet == null) {
    		schedulemailSet = new HashSet<Schedulemail>();
    	}
        return schedulemailSet;
    }

    public void setSchedulemailSet(Set<Schedulemail> schedulemailSet) {
        this.schedulemailSet = schedulemailSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaciente != null ? idpaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.idpaciente == null && other.idpaciente != null) || (this.idpaciente != null && !this.idpaciente.equals(other.idpaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Paciente[ idpaciente=" + idpaciente + " ]";
    }
    
}
