package br.com.gmauricio.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.gmauricio.dto.ErroFormDTO;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDTO> handle(MethodArgumentNotValidException exception) {
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		List<ErroFormDTO> list = new ArrayList<>();
		fieldErrors.forEach(a -> {
			list.add(new ErroFormDTO(a.getField(), a.getDefaultMessage()));
		});
		return list;
	}
	
}
