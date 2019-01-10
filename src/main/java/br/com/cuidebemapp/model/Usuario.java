/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cuidebemapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "login")
	private String login;
	@Basic(optional = false)
	@Column(name = "email")
	private String email;
	@Column(name = "nome")
	private String nome;
	@Column(name = "apelido")
	private String apelido;
	@Basic(optional = false)
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "genero")
	private Boolean genero;
	@Column(name = "datanascimento")
	@Temporal(TemporalType.DATE)
	private Date datanascimento;
	@Column(name = "tipousuario")
	private String tipousuario;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY ,orphanRemoval = true)
    @JsonIgnoreProperties(value = "usuario", allowSetters = true)
	private UsuarioPhoto usuarioPhoto;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnoreProperties(value = "usuario", allowSetters = true)
	private Set<Evento> eventos;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", fetch = FetchType.LAZY,orphanRemoval = true)
    @JsonIgnoreProperties(value = "usuario", allowSetters = true)
	private Set<UsuarioTelefone> usuarioTelefones;

	public Usuario() {
	}

	public Usuario(String login) {
		this.login = login;
	}

	public Usuario(String login, String email, boolean enabled) {
		this.login = login;
		this.email = email;
		this.enabled = enabled;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getGenero() {
		return genero;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getTipousuario() {
		return tipousuario;
	}

	public void setTipousuario(String tipousuario) {
		this.tipousuario = tipousuario;
	}

	public UsuarioPhoto getUsuarioPhoto() {
		return usuarioPhoto;
	}

	public void setUsuarioPhoto(UsuarioPhoto usuarioPhoto) {
		this.usuarioPhoto = usuarioPhoto;
	}

	@XmlTransient
	public Set<Evento> getEventos() {
		if(eventos.isEmpty()) {
			eventos = new HashSet<>();
		}
		return eventos;
	}

	public void setEventos(Set<Evento> eventos) {
		this.eventos = eventos;
	}

	@XmlTransient
	public Set<UsuarioTelefone> getUsuarioTelefones() {
		if(usuarioTelefones.isEmpty()) {
			usuarioTelefones = new HashSet<>();
		}
		return usuarioTelefones;
	}

	public void setUsuarioTelefones(Set<UsuarioTelefone> usuarioTelefones) {
		this.usuarioTelefones = usuarioTelefones;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (login != null ? login.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) object;
		if ((this.login == null && other.login != null)
				|| (this.login != null && !this.login.equals(other.login))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.cuidebemapp.model.Usuario[ login=" + login + " ]";
	}

}
