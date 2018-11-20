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
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "emailcontent")
@XmlRootElement
public class Emailcontent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idemailenviado")
    private Long idemailenviado;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private byte[] content;
    @JoinColumn(name = "idemailenviado", referencedColumnName = "idemailenviado", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Emailenviado emailenviado;

    public Emailcontent() {
    }

    public Emailcontent(Long idemailenviado) {
        this.idemailenviado = idemailenviado;
    }

    public Emailcontent(Long idemailenviado, byte[] content) {
        this.idemailenviado = idemailenviado;
        this.content = content;
    }

    public Long getIdemailenviado() {
        return idemailenviado;
    }

    public void setIdemailenviado(Long idemailenviado) {
        this.idemailenviado = idemailenviado;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Emailenviado getEmailenviado() {
        return emailenviado;
    }

    public void setEmailenviado(Emailenviado emailenviado) {
        this.emailenviado = emailenviado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemailenviado != null ? idemailenviado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emailcontent)) {
            return false;
        }
        Emailcontent other = (Emailcontent) object;
        if ((this.idemailenviado == null && other.idemailenviado != null) || (this.idemailenviado != null && !this.idemailenviado.equals(other.idemailenviado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Emailcontent[ idemailenviado=" + idemailenviado + " ]";
    }
    
}
