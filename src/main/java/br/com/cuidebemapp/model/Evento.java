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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author alecindro
 */
@Entity
@Table(name = "evento")
@XmlRootElement
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idevento")
    private Long idevento;
    @Column(name = "descevento")
    private String descevento;
    @Basic(optional = false)
    @Column(name = "dataevento")
    
    private java.time.OffsetDateTime dataevento;
    @Column(name = "dataregistro")
    
    private java.time.OffsetDateTime dataregistro;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled = true;
    @Column(name = "obsevento")
    private String obsevento;
    @Column(name = "grupoevento")
    private String grupoevento;
    @Column(name = "subgrupoevento")
    private String subgrupoevento;
    @Column(name = "respeventos")
    private String respeventos;
    @Column(name = "peso")
    private Integer peso;
    @Column(name = "pressaoinicial")
    private Integer pressaoinicial;
    @Column(name = "pressaofinal")
    private Integer pressaofinal;
    @Column(name = "value")
    private Integer value;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "aspecto")
    private String aspecto;
    @Column(name = "quantidade")
    private String quantidade;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"eventoSet","usuarioTelefoneSet","usuarioPhoto"}, allowSetters = true)
    private Usuario usuario;
	@JoinColumn(name = "idpaciente", referencedColumnName = "idpaciente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = {"eventoSet","pacientePhotoSet","responsavelPacienteSet","agendaSet","memorandoSet","eventoSet","patologiaPacienteSet","agendadefSet","usuarioTelefoneSet"}, allowSetters = true)
    private Paciente paciente;

    public Evento() {
    }

    public Evento(Long idevento) {
        this.idevento = idevento;
    }

    public Evento(Long idevento, java.time.OffsetDateTime dataevento, boolean enabled) {
        this.idevento = idevento;
        this.dataevento = dataevento;
        this.enabled = enabled;
    }

    public Long getIdevento() {
        return idevento;
    }

    public void setIdevento(Long idevento) {
        this.idevento = idevento;
    }

    public String getDescevento() {
        return descevento;
    }

    public void setDescevento(String descevento) {
        this.descevento = descevento;
    }

    public java.time.OffsetDateTime getDataevento() {
        return dataevento;
    }

    public void setDataevento(java.time.OffsetDateTime dataevento) {
        this.dataevento = dataevento;
    }

    public java.time.OffsetDateTime getDataregistro() {
        return dataregistro;
    }

    public void setDataregistro(java.time.OffsetDateTime dataregistro) {
        this.dataregistro = dataregistro;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getObsevento() {
        return obsevento;
    }

    public void setObsevento(String obsevento) {
        this.obsevento = obsevento;
    }

    public String getGrupoevento() {
        return grupoevento;
    }

    public void setGrupoevento(String grupoevento) {
        this.grupoevento = grupoevento;
    }

    public String getSubgrupoevento() {
        return subgrupoevento;
    }

    public void setSubgrupoevento(String subgrupoevento) {
        this.subgrupoevento = subgrupoevento;
    }

    public String getRespeventos() {
        return respeventos;
    }

    public void setRespeventos(String respeventos) {
        this.respeventos = respeventos;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Integer getPressaoinicial() {
        return pressaoinicial;
    }

    public void setPressaoinicial(Integer pressaoinicial) {
        this.pressaoinicial = pressaoinicial;
    }

    public Integer getPressaofinal() {
        return pressaofinal;
    }

    public void setPressaofinal(Integer pressaofinal) {
        this.pressaofinal = pressaofinal;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAspecto() {
        return aspecto;
    }

    public void setAspecto(String aspecto) {
        this.aspecto = aspecto;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idevento != null ? idevento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idevento == null && other.idevento != null) || (this.idevento != null && !this.idevento.equals(other.idevento))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Evento [idevento=" + idevento + ", descevento=" + descevento + ", dataevento=" + dataevento
				+ ", dataregistro=" + dataregistro + ", enabled=" + enabled + ", obsevento=" + obsevento
				+ ", grupoevento=" + grupoevento + ", subgrupoevento=" + subgrupoevento + ", respeventos=" + respeventos
				+ ", peso=" + peso + ", pressaoinicial=" + pressaoinicial + ", pressaofinal=" + pressaofinal
				+ ", value=" + value + ", descricao=" + descricao + ", aspecto=" + aspecto + ", quantidade="
				+ quantidade + ", usuario=" + usuario.toString() + ", paciente=" + paciente.toString() + "]";
	}

    
    
}
