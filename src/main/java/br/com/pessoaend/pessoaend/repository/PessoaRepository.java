package br.com.pessoaend.pessoaend.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pessoaend.pessoaend.model.Pessoa;

public interface PessoaRepository extends CrudRepository<Pessoa, String>{
	Pessoa findByCodigo(long codigo);
}
