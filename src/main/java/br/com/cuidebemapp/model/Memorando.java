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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "memorando")
@XmlRootElement
public class Memorando implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmemorando")
    private Long idmemorando;
    @Basic(optional = false)
    @Column(name = "dataregistro")
    
    private java.time.OffsetDateTime dataregistro;
    @Column(name = "dataalteracao")
    
    private java.time.OffsetDateTime dataalteracao;
    @Basic(optional = false)
    
    @Column(name = "descricao")
    private String descricao;
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"pacientePhotoSet","responsavelPacienteSet","agendaSet","memorandoSet","eventoSet","patologiaPacienteSet","agendadefSet","schedulemailSet"}, allowSetters = true)
    private Paciente paciente;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"eventoSet","usuarioTelefoneSet","usuarioPhoto"}, allowSetters = true)
    private Usuario usuario;

    public Memorando() {
    }

    public Memorando(Long idmemorando) {
        this.idmemorando = idmemorando;
    }

    public Memorando(Long idmemorando, java.time.OffsetDateTime dataregistro, String descricao) {
        this.idmemorando = idmemorando;
        this.dataregistro = dataregistro;
        this.descricao = descricao;
    }

    public Long getIdmemorando() {
        return idmemorando;
    }

    public void setIdmemorando(Long idmemorando) {
        this.idmemorando = idmemorando;
    }

    public java.time.OffsetDateTime getDataregistro() {
        return dataregistro;
    }

    public void setDataregistro(java.time.OffsetDateTime dataregistro) {
        this.dataregistro = dataregistro;
    }

    public java.time.OffsetDateTime getDataalteracao() {
        return dataalteracao;
    }

    public void setDataalteracao(java.time.OffsetDateTime dataalteracao) {
        this.dataalteracao = dataalteracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idmemorando != null ? idmemorando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memorando)) {
            return false;
        }
        Memorando other = (Memorando) object;
        if ((this.idmemorando == null && other.idmemorando != null) || (this.idmemorando != null && !this.idmemorando.equals(other.idmemorando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Memorando[ idmemorando=" + idmemorando + " ]";
    }
    
}
