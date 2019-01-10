/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "emailenviado")
@XmlRootElement
public class Emailenviado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "emailenviado_sequence", sequenceName = "emailenviado_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emailenviado_sequence")
    @Basic(optional = false)
    @Column(name = "idemailenviado")
    private Long idemailenviado;
    @Basic(optional = false)
    @Column(name = "to_email")
    private String toEmail;
    @Basic(optional = false)
    @Column(name = "subject")
    private String subject;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @Column(name = "dataenvio")
    
    private java.time.OffsetDateTime dataenvio;
    @Basic(optional = false)
    @Column(name = "datarelatorio")
    
    private java.time.OffsetDateTime datarelatorio;
    @Basic(optional = false)
    @Column(name = "error")
    private boolean error;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Basic(optional = false)
    @Column(name = "automatic")
    private boolean automatic;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "emailenviado", fetch = FetchType.LAZY)
    private Emailcontent emailcontent;

    public Emailenviado() {
    }

    public Emailenviado(Long idemailenviado) {
        this.idemailenviado = idemailenviado;
    }

    public Emailenviado(Long idemailenviado, String toEmail, String subject, java.time.OffsetDateTime dataenvio, java.time.OffsetDateTime datarelatorio, boolean error, boolean enabled, boolean automatic) {
        this.idemailenviado = idemailenviado;
        this.toEmail = toEmail;
        this.subject = subject;
        this.dataenvio = dataenvio;
        this.datarelatorio = datarelatorio;
        this.error = error;
        this.enabled = enabled;
        this.automatic = automatic;
    }

    public Long getIdemailenviado() {
        return idemailenviado;
    }

    public void setIdemailenviado(Long idemailenviado) {
        this.idemailenviado = idemailenviado;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public java.time.OffsetDateTime getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(java.time.OffsetDateTime dataenvio) {
        this.dataenvio = dataenvio;
    }

    public java.time.OffsetDateTime getDatarelatorio() {
        return datarelatorio;
    }

    public void setDatarelatorio(java.time.OffsetDateTime datarelatorio) {
        this.datarelatorio = datarelatorio;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getAutomatic() {
        return automatic;
    }

    public void setAutomatic(boolean automatic) {
        this.automatic = automatic;
    }

    public Emailcontent getEmailcontent() {
        return emailcontent;
    }

    public void setEmailcontent(Emailcontent emailcontent) {
        this.emailcontent = emailcontent;
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
        if (!(object instanceof Emailenviado)) {
            return false;
        }
        Emailenviado other = (Emailenviado) object;
        if ((this.idemailenviado == null && other.idemailenviado != null) || (this.idemailenviado != null && !this.idemailenviado.equals(other.idemailenviado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.Emailenviado[ idemailenviado=" + idemailenviado + " ]";
    }
    
}
