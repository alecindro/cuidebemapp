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
@Table(name = "usuario_telefone")
@XmlRootElement
public class UsuarioTelefone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
	@SequenceGenerator(name = "usuario_telefone_sequence", sequenceName = "usuario_telefone_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_telefone_sequence")
    @Basic(optional = false)
    @Column(name = "idusuario_telefone")
    private Long idusuarioTelefone;
    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Telefone telefone;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario usuario;

    public UsuarioTelefone() {
    }

    public UsuarioTelefone(Long idusuarioTelefone) {
        this.idusuarioTelefone = idusuarioTelefone;
    }

    public Long getIdusuarioTelefone() {
        return idusuarioTelefone;
    }

    public void setIdusuarioTelefone(Long idusuarioTelefone) {
        this.idusuarioTelefone = idusuarioTelefone;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
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
        hash += (idusuarioTelefone != null ? idusuarioTelefone.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioTelefone)) {
            return false;
        }
        UsuarioTelefone other = (UsuarioTelefone) object;
        if ((this.idusuarioTelefone == null && other.idusuarioTelefone != null) || (this.idusuarioTelefone != null && !this.idusuarioTelefone.equals(other.idusuarioTelefone))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cuidebemapp.model.UsuarioTelefone[ idusuarioTelefone=" + idusuarioTelefone + " ]";
    }
    
}
