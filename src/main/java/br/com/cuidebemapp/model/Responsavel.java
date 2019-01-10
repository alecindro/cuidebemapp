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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "responsavel")
@XmlRootElement
public class Responsavel implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name = "responsavel_sequence", sequenceName = "responsavel_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "responsavel_sequence")
	@Basic(optional = false)
	@Column(name = "idresponsavel")
	private Long idresponsavel;
	@Column(name = "nome")
	private String nome;
	@Column(name = "apelido")
	private String apelido;
	@Column(name = "email")
	private String email;
	@Basic(optional = false)
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "datanascimento")
	@Temporal(TemporalType.DATE)
	private Date datanascimento;
	@Column(name = "vinculo")
	private String vinculo;
	@Column(name = "tpresponsavel")
	private Boolean tpresponsavel;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "rg")
	private String rg;
	@Column(name = "endereco")
	private String endereco;
	@Column(name = "cidade")
	private String cidade;
	@Column(name = "estado")
	private String estado;
	@Column(name = "genero")
	private Boolean genero;
	@Column(name = "cep")
	private String cep;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responsavel", fetch = FetchType.LAZY)
	private Set<ResponsavelPaciente> responsavelPacientes;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "responsavel", fetch = FetchType.LAZY)
	private ResponsavelPhoto responsavelPhoto;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responsavel", fetch = FetchType.LAZY)
	private Set<ResponsavelTelefone> responsavelTelefones;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "responsavel", fetch = FetchType.LAZY)
	private Set<Schedulemail> schedulemails;

	public Responsavel() {
	}

	public Responsavel(Long idresponsavel) {
		this.idresponsavel = idresponsavel;
	}

	public Responsavel(Long idresponsavel, boolean enabled) {
		this.idresponsavel = idresponsavel;
		this.enabled = enabled;
	}

	public Long getIdresponsavel() {
		return idresponsavel;
	}

	public void setIdresponsavel(Long idresponsavel) {
		this.idresponsavel = idresponsavel;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getVinculo() {
		return vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public Boolean getTpresponsavel() {
		return tpresponsavel;
	}

	public void setTpresponsavel(Boolean tpresponsavel) {
		this.tpresponsavel = tpresponsavel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getGenero() {
		return genero;
	}

	public void setGenero(Boolean genero) {
		this.genero = genero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@XmlTransient
	public Set<ResponsavelPaciente> getResponsavelPacientes() {
		if(responsavelPacientes == null) {
			responsavelPacientes = new HashSet<>();
		}
		return responsavelPacientes;
	}

	public void setResponsavelPacientes(Set<ResponsavelPaciente> responsavelPacientes) {
		this.responsavelPacientes = responsavelPacientes;
	}

	public ResponsavelPhoto getResponsavelPhoto() {
		return responsavelPhoto;
	}

	public void setResponsavelPhoto(ResponsavelPhoto responsavelPhoto) {
		this.responsavelPhoto = responsavelPhoto;
	}

	@XmlTransient
	public Set<ResponsavelTelefone> getResponsavelTelefones() {
		if(responsavelTelefones == null) {
			responsavelTelefones = new HashSet<>();
		}
		return responsavelTelefones;
	}

	public void setResponsavelTelefones(Set<ResponsavelTelefone> responsavelTelefones) {
		this.responsavelTelefones = responsavelTelefones;
	}

	@XmlTransient
	public Set<Schedulemail> getSchedulemails() {
		if(schedulemails == null) {
			schedulemails = new HashSet<>();
		}
		return schedulemails;
	}

	public void setSchedulemails(Set<Schedulemail> schedulemails) {
		this.schedulemails = schedulemails;
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
		if (!(object instanceof Responsavel)) {
			return false;
		}
		Responsavel other = (Responsavel) object;
		if ((this.idresponsavel == null && other.idresponsavel != null)
				|| (this.idresponsavel != null && !this.idresponsavel.equals(other.idresponsavel))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "br.com.cuidebemapp.model.Responsavel[ idresponsavel=" + idresponsavel + " ]";
	}

}
