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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "responsavel_photo")
@XmlRootElement
public class ResponsavelPhoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idresponsavel")
    private Long idresponsavel;
    @Column(name = "photo")
    private String photo;
    @JoinColumn(name = "idresponsavel", referencedColumnName = "idresponsavel", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnore
    private Responsavel responsavel;

    public ResponsavelPhoto() {
    }

    public ResponsavelPhoto(Long idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    public Long getIdresponsavel() {
        return idresponsavel;
    }

    public void setIdresponsavel(Long idresponsavel) {
        this.idresponsavel = idresponsavel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
        hash += (idresponsavel != null ? idresponsavel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResponsavelPhoto)) {
            return false;
        }
        ResponsavelPhoto other = (ResponsavelPhoto) object;
        if ((this.idresponsavel == null && other.idresponsavel != null) || (this.idresponsavel != null && !this.idresponsavel.equals(other.idresponsavel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.ResponsavelPhoto[ idresponsavel=" + idresponsavel + " ]";
    }
    
}
