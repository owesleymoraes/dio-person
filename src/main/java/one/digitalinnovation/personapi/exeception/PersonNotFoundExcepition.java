package one.digitalinnovation.personapi.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class PersonNotFoundExcepition extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersonNotFoundExcepition(Long id) {
		super("Pessoa não encontrada com esse: "+ id);
	}

}
