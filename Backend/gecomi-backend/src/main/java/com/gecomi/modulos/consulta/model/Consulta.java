package com.gecomi.modulos.consulta.model;

import com.gecomi.modulos.especialidad.model.Especialidad;
import com.gecomi.modulos.medico.model.Medico;
import com.gecomi.modulos.paciente.model.Paciente;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
@Where(clause = "deleted = false")
@SQLDelete(sql="update consulta set deleted=true where id_consulta = ?")
@Audited
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsulta;

	@ManyToOne // FK
	@JoinColumn(name = "id_paciente", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_paciente"))
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name = "id_medico", nullable = false, foreignKey = @ForeignKey(name = "FK_consulta_medico"))
	private Medico medico;

	@ManyToOne
	@JoinColumn(name = "id_especialidad", nullable = false, foreignKey = @ForeignKey(name = "fk_consulta_especialidad"))
	private Especialidad especialidad;

	@Column(name = "num_consultorio", length = 3, nullable = true)
	private String numConsultorio;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

	@Column(name = "deleted", nullable = true, columnDefinition = "boolean default false")
	private Boolean deleted = false;
	
	//private boolean isDeleted;

	// A nivel lógico
	@OneToMany(mappedBy = "consulta", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DetalleConsulta> detalleConsulta;

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public String getNumConsultorio() {
		return numConsultorio;
	}

	public void setNumConsultorio(String numConsultorio) {
		this.numConsultorio = numConsultorio;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public List<DetalleConsulta> getDetalleConsulta() {
		return detalleConsulta;
	}

	public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
		this.detalleConsulta = detalleConsulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConsulta == null) ? 0 : idConsulta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (idConsulta == null) {
			if (other.idConsulta != null)
				return false;
		} else if (!idConsulta.equals(other.idConsulta))
			return false;
		return true;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	// SQL
	// SELECT c.* FROM Consulta c INNER JOIN Paciente p ON c.idPaciente =
	// p.idPaciente WHERE id.cedula = xyz

	// JPQL
	// FROM Consulta c WHERE c.paciente.cedula = xyz

}
