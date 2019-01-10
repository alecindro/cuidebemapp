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
@Table(name = "residencia")
@XmlRootElement
public class Residencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "residencia_sequence", sequenceName = "residencia_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "residencia_sequence")
    @Basic(optional = false)
    @Column(name = "idresidencia")
    private Long idresidencia;
    @Basic(optional = false)
    @Column(name = "razao")
    private String razao;
    @Column(name = "fantasia")
    private String fantasia;
    @Column(name = "endereco")
    private String endereco;
    @Column(name = "cep")
    private String cep;
    @Column(name = "cnpj")
    private String cnpj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "residencia", fetch = FetchType.LAZY)
    private Set<ResidenciaTelefone> residenciaTelefoneSet;

    public Residencia() {
    }

    public Residencia(Long idresidencia) {
        this.idresidencia = idresidencia;
    }

    public Residencia(Long idresidencia, String razao) {
        this.idresidencia = idresidencia;
        this.razao = razao;
    }

    public Long getIdresidencia() {
        return idresidencia;
    }

    public void setIdresidencia(Long idresidencia) {
        this.idresidencia = idresidencia;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    @XmlTransient
    public Set<ResidenciaTelefone> getResidenciaTelefoneSet() {
        return residenciaTelefoneSet;
    }

    public void setResidenciaTelefoneSet(Set<ResidenciaTelefone> residenciaTelefoneSet) {
        this.residenciaTelefoneSet = residenciaTelefoneSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresidencia != null ? idresidencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Residencia)) {
            return false;
        }
        Residencia other = (Residencia) object;
        if ((this.idresidencia == null && other.idresidencia != null) || (this.idresidencia != null && !this.idresidencia.equals(other.idresidencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Residencia[ idresidencia=" + idresidencia + " ]";
    }
    
}
