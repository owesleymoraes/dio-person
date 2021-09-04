package one.digitalinnovation.personapi.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String LastName;

	@Column(nullable = false , unique = true)
	private String cpf;

	private LocalDate birthDate;

	@OneToMany(fetch = FetchType.LAZY, cascade =
		{ CascadeType.PERSIST,
				CascadeType.MERGE,
				CascadeType.REMOVE })
	private List<Phone> phone;

}

//@OneToMany(fetch = FetchType.LAZY : estratégia de obtenção de dados o LAZY utilizado para perfomece
//o tipo de busca. Caso quisesse buscar um dado no banco de forma mesclada
// teria que se fazer uma query utilizando joins fetch.
//cascade estratégia de inserção.