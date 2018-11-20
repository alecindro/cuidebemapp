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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Set<ResidenciaTelefone> residenciaTelefoneSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telefone", fetch = FetchType.LAZY)
    private Set<UsuarioTelefone> usuarioTelefoneSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "telefone", fetch = FetchType.LAZY)
    private Set<ResponsavelTelefone> responsavelTelefoneSet;

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
    public Set<ResidenciaTelefone> getResidenciaTelefoneSet() {
        return residenciaTelefoneSet;
    }

    public void setResidenciaTelefoneSet(Set<ResidenciaTelefone> residenciaTelefoneSet) {
        this.residenciaTelefoneSet = residenciaTelefoneSet;
    }

    @XmlTransient
    public Set<UsuarioTelefone> getUsuarioTelefoneSet() {
        return usuarioTelefoneSet;
    }

    public void setUsuarioTelefoneSet(Set<UsuarioTelefone> usuarioTelefoneSet) {
        this.usuarioTelefoneSet = usuarioTelefoneSet;
    }

    @XmlTransient
    public Set<ResponsavelTelefone> getResponsavelTelefoneSet() {
        return responsavelTelefoneSet;
    }

    public void setResponsavelTelefoneSet(Set<ResponsavelTelefone> responsavelTelefoneSet) {
        this.responsavelTelefoneSet = responsavelTelefoneSet;
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
