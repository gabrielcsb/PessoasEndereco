package br.com.pessoaend.pessoaend.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.pessoaend.pessoaend.model.Endereco;



public interface EnderecoRepository extends CrudRepository<Endereco, String>{
	Iterable<Endereco> findByPessoaCodigo(long codigo);
	Endereco findById(long id);
}
