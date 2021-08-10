package com.cursospringrest.cursoSpringRest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursospringrest.cursoSpringRest.models.Usuario;
import com.cursospringrest.cursoSpringRest.repository.UsuarioRepository;

@RestController
public class UsuarioController {

	

	@Autowired // injeção de dependencia
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/salvarNome/{nome}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String salvarUssuario(@PathVariable String nome) {
	
		Usuario usuario = new Usuario();
		
		usuario.setNome("Luis antonio");
		
		usuarioRepository.save(usuario);
		
		return "Usuario cadastrado " + nome;

	}

	@GetMapping(value = "/listaTodos")
	@ResponseBody /* retornar dados para corpo da resposta Json */
	public ResponseEntity<List<Usuario>> listaUsuario() {
		List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);// retornar lista Jsaon

	}
	
	//MTD, Salvar
	@PostMapping(value = "/salvar")
	@ResponseBody
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		Usuario user = usuarioRepository.save(usuario);
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
		
	}
	
	//MTD, Deletar
		@DeleteMapping(value = "/delete") // passando id para deletar
		@ResponseBody
		public ResponseEntity<String> delete(@RequestParam long id ){
			
			 usuarioRepository.deleteById(id);
			 
			return new ResponseEntity<String>("usuario deletado com sucesso", HttpStatus.OK);
			
		}
		
		// passando id para Buscar Usuario
		@GetMapping(value = "/buscarPorId") // passando id para Buscar Usuario
		@ResponseBody
		public ResponseEntity<Usuario> buscarPorId(@RequestParam (name = "id") Long id ){
			
			Usuario usuario = usuarioRepository.findById(id).get();
			 
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
			
		}
		
			
		//atualizar User (update)
		@PutMapping(value = "/atualizar")
		@ResponseBody
		public ResponseEntity<?> atualizar(@RequestBody Usuario usuario){
			if (usuario.getId()== null) {
				return new ResponseEntity<String>("[ERRO !]Id do usuário não foi informado.", HttpStatus.EXPECTATION_FAILED);
			}
			Usuario user = usuarioRepository.saveAndFlush(usuario);
			return new ResponseEntity<Usuario>(user, HttpStatus.OK);
			
		}

		//MTD Biscar por Nome
		@GetMapping(value = "/buscarPorNome") // passando id para Buscar Usuario
		@ResponseBody
		public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam (name = "name") String name ){
			
			List<Usuario> usuario= usuarioRepository.buscarPorNome(name.trim().toUpperCase());
			
			return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
			
		}


}
