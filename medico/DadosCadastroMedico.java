package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
		@NotBlank /*Informa que o campo não pode estar vazio nem ser nulo (válido somente para strings) */
		String nome, 
		
		@NotBlank
		@Email /*Define formato de e-mail*/
		String email, 
		
		@NotBlank
		String telefone,
		
		@NotBlank
		@Pattern(regexp = "\\d{4,6}") /* "regex" = empressão regular, "//d{4,6}" = dígito com 4 a 6 caracteres */
		String crm, 
		
		@NotNull /* O próprio texto vai validar se o texto que está chegando é uma constante do enum */
		Especialidade especialidade, 
		
		@NotNull
		@Valid
		DadosEndereco endereco) {

	
	
}
