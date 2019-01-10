/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "telefone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Telefone.findAll", query = "SELECT t FROM Telefone t")})
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@SequenceGenerator(name = "telefone_sequence", sequenceName = "telefone_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "telefone_sequence")
    @Basic(optional = false)
    @Column(name = "idtelefone")
    private Long idtelefone;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "ddd")
    private String ddd;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "operadora")
    private String operadora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telefone", fetch = FetchType.LAZY)
    private Set<UsuarioTelefone> usuarioTelefones;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telefone", fetch = FetchType.LAZY)
    private Set<ResponsavelTelefone> responsavelTelefones;

    public Telefone() {
    }

    public Telefone(Long idtelefone) {
        this.idtelefone = idtelefone;
    }

    public Long getIdtelefone() {
        return idtelefone;
    }

    public void setIdtelefone(Long idtelefone) {
        this.idtelefone = idtelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

    @XmlTransient
    public Set<UsuarioTelefone> getUsuarioTelefones() {
    	if(usuarioTelefones == null) {
    		usuarioTelefones = new HashSet<>();
    	}
        return usuarioTelefones;
    }

    public void setUsuarioTelefones(Set<UsuarioTelefone> usuarioTelefones) {
    	if(usuarioTelefones == null) {
    		usuarioTelefones = new HashSet<>();
    	}
        this.usuarioTelefones = usuarioTelefones;
    }

    @XmlTransient
    public Set<ResponsavelTelefone> getResponsavelTelefones() {
        if(responsavelTelefones == null) {
        	responsavelTelefones = new HashSet<>();
        }
    	return responsavelTelefones;
    }

    public void setResponsavelTelefones(Set<ResponsavelTelefone> responsavelTelefones) {
        this.responsavelTelefones = responsavelTelefones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtelefone != null ? idtelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Telefone)) {
            return false;
        }
        Telefone other = (Telefone) object;
        if ((this.idtelefone == null && other.idtelefone != null) || (this.idtelefone != null && !this.idtelefone.equals(other.idtelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Telefone[ idtelefone=" + idtelefone + " ]";
    }
    
}
