package com.cursospringrest.cursoSpringRest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cursospringrest.cursoSpringRest.models.Usuario;
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario saveAndFlush(Usuario usuario);
	
	//query para buscar por nome 
	@Query(value = "select u from Usuario u where upper(trim(u.nome)) like %?1%")
	List<Usuario> buscarPorNome(String name);

}
