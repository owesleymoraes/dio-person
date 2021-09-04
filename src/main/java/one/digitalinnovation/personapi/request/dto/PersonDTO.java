package one.digitalinnovation.personapi.request.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

	private Long id;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String LastName;

	@NotEmpty
	@CPF
	private String cpf;

	private LocalDate birthDate;

	@Valid
	@NotEmpty
	private List<PhoneDTO> phone;

}
