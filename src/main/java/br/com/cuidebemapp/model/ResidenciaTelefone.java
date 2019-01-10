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
@Table(name = "residencia_telefone")
@XmlRootElement
public class ResidenciaTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "residencia_telefone_sequence", sequenceName = "residencia_telefone_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "residencia_telefone_sequence")
  @Basic(optional = false)
    @Column(name = "idresidencia_telefone")
    private Long idresidenciaTelefone;
    @JoinColumn(name = "idresidencia", referencedColumnName = "idresidencia")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Residencia residencia;
    @Column(name = "telefone")
    private String telefone;
    @Column(name = "ddd")
    private String ddd;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "operadora")
    private String operadora;

	public ResidenciaTelefone() {
    }

    public ResidenciaTelefone(Long idresidenciaTelefone) {
        this.idresidenciaTelefone = idresidenciaTelefone;
    }

    public Long getIdresidenciaTelefone() {
        return idresidenciaTelefone;
    }

    public void setIdresidenciaTelefone(Long idresidenciaTelefone) {
        this.idresidenciaTelefone = idresidenciaTelefone;
    }

    public Residencia getResidencia() {
        return residencia;
    }

    public void setResidencia(Residencia residencia) {
        this.residencia = residencia;
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

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idresidenciaTelefone != null ? idresidenciaTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResidenciaTelefone)) {
            return false;
        }
        ResidenciaTelefone other = (ResidenciaTelefone) object;
        if ((this.idresidenciaTelefone == null && other.idresidenciaTelefone != null) || (this.idresidenciaTelefone != null && !this.idresidenciaTelefone.equals(other.idresidenciaTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.ResidenciaTelefone[ idresidenciaTelefone=" + idresidenciaTelefone + " ]";
    }
    
}
