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
@Table(name = "responsavel_telefone")
@XmlRootElement
public class ResponsavelTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "responsavel_telefone_sequence", sequenceName = "responsavel_telefone_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_telefone_sequence")
    @Basic(optional = false)
    @Column(name = "idresponsavel_telefone")
    private Long idresponsavelTelefone;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Responsavel responsavel;
    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Telefone telefone;

    public ResponsavelTelefone() {
    }

    public ResponsavelTelefone(Long idresponsavelTelefone) {
        this.idresponsavelTelefone = idresponsavelTelefone;
    }

    public Long getIdresponsavelTelefone() {
        return idresponsavelTelefone;
    }

    public void setIdresponsavelTelefone(Long idresponsavelTelefone) {
        this.idresponsavelTelefone = idresponsavelTelefone;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresponsavelTelefone != null ? idresponsavelTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelTelefone)) {
            return false;
        }
        ResponsavelTelefone other = (ResponsavelTelefone) object;
        if ((this.idresponsavelTelefone == null && other.idresponsavelTelefone != null) || (this.idresponsavelTelefone != null && !this.idresponsavelTelefone.equals(other.idresponsavelTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.ResponsavelTelefone[ idresponsavelTelefone=" + idresponsavelTelefone + " ]";
    }
    
}
