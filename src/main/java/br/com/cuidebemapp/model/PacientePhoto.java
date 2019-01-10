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
@Table(name = "paciente_photo")
@XmlRootElement
public class PacientePhoto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name="paciente_photo_sequence",
                       sequenceName="paciente_photo_sequence",
                       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="paciente_photo_sequence")
    @Basic(optional = false)
    @Column(name = "idpacientephoto")
    private Long idpacientephoto;
    @Column(name = "photo")
    private String photo;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "principal")
    private Boolean principal;
    @Column(name = "type")
    private String type;
    @Column(name = "dataregistro")
    private java.time.OffsetDateTime dataregistro;
    @Column(name = "login")
    private String login;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    private Paciente paciente;

    public PacientePhoto() {
    }

    public PacientePhoto(Long idpacientephoto) {
        this.idpacientephoto = idpacientephoto;
    }

    public Long getIdpacientephoto() {
        return idpacientephoto;
    }

    public void setIdpacientephoto(Long idpacientephoto) {
        this.idpacientephoto = idpacientephoto;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.time.OffsetDateTime getDataregistro() {
        return dataregistro;
    }

    public void setDataregistro(java.time.OffsetDateTime dataregistro) {
        this.dataregistro = dataregistro;
    }

    public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Paciente getPaciente() {
		if(paciente == null) {
			paciente = new Paciente();
		}
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpacientephoto != null ? idpacientephoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PacientePhoto)) {
            return false;
        }
        PacientePhoto other = (PacientePhoto) object;
        if ((this.idpacientephoto == null && other.idpacientephoto != null) || (this.idpacientephoto != null && !this.idpacientephoto.equals(other.idpacientephoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.PacientePhoto[ idpacientephoto=" + idpacientephoto + " ]";
    }
    
}
