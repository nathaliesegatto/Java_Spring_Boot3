package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.medico.DadoaAtualizacaoMedico;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;

@RestController
@RequestMapping("medicos") /*
							 * nome da url = medicos / usado pelo string para diferenciar as controllers
							 * quando requisitados
							 */
public class MedicoController {

	@Autowired /*
				 * indica para o Spring que ele vai instanciar e passar o atributo repository
				 * dentro da classe controller
				 */
	private MedicoRepository repository;

	@PostMapping /*
					 * se chegar uma requisição do tipo "post", para a url "/medicos", String vai
					 * chamar o método "cadastrar" da classe "MedicoController"
					 */
	@Transactional
	public void cadastrar(
			@RequestBody @Valid DadosCadastroMedico dados) { /*
																 * recebe os parâmetros passados no corpo (ResquestBody)
																 * do arquivo no formato json que está no Insomnia
																 */
		repository.save(new Medico(dados)); /* a anotação @Valid pede para o Spring se integrar com o Bean Validation e executar as validações em cima desse DTO (DadosCadastroMedico)*/

	}

	@GetMapping
	public Page<DadosListagemMedico> Listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosListagemMedico::new);

	}

	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadoaAtualizacaoMedico dados) {

		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) { /*a anotação avisa o Spring que a variável Long recebida é a mesma do caminho (da url), a mesma que está no DeleteMapping e*/
		var medico = repository.getReferenceById(id);
		medico.excluir();
	}
	
	

}














