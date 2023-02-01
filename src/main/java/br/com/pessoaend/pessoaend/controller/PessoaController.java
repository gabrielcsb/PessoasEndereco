package br.com.pessoaend.pessoaend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pessoaend.pessoaend.model.Endereco;
import br.com.pessoaend.pessoaend.model.Pessoa;
import br.com.pessoaend.pessoaend.repository.EnderecoRepository;
import br.com.pessoaend.pessoaend.repository.PessoaRepository;
import jakarta.validation.Valid;

@Controller
public class PessoaController {

    @Autowired
    private PessoaRepository pesRepository;

    @Autowired
	private EnderecoRepository enderecoRepository;

    @RequestMapping(value = "/cadastrarPessoa", method = RequestMethod.GET)
    public String form() {
        return "pessoa/formPessoa";
    }
    // CADASTRAR PESSOAS
    @RequestMapping(value = "/cadastrarPessoa", method = RequestMethod.POST)
    public String form(@Valid Pessoa pessoa, BindingResult result, RedirectAttributes attributes) {
        if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarPessoa";
		}
        pesRepository.save(pessoa);
        attributes.addFlashAttribute("mensagem", "Pessoa cadastrado com sucesso!");
        return "redirect:/cadastrarPessoa";
    }

    // BUSCAR PESSOAS
    @RequestMapping("/pessoas")
    public ModelAndView listaPessoas(){
        ModelAndView mv = new ModelAndView("listaPessoas");
        Iterable<Pessoa> pessoas = pesRepository.findAll();
        mv.addObject("pessoas", pessoas);
        return mv;
    }

	// BUSCAR POR CODIGO
    @RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesPessoa(@PathVariable("codigo") long codigo){
		Pessoa pessoa = pesRepository.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("pessoa/detalhesPessoa");
		mv.addObject("pessoa", pessoa);
        Iterable<Endereco> enderecos = enderecoRepository.findByPessoaCodigo(pessoa.getCodigo());
		mv.addObject("enderecos", enderecos);
		
		return mv;
	}

    @RequestMapping("/deletarPessoa")
	public String deletarPessoa(long codigo){
		Pessoa pessoa = pesRepository.findByCodigo(codigo);
		pesRepository.delete(pessoa);
		return "redirect:/pessoa";
	}

	//CADASTRAR ENDERECO
    @RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesPessoaPost(@PathVariable("codigo") long codigo, @Valid Endereco endereco,  BindingResult result, RedirectAttributes attributes){
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Pessoa pessoa = pesRepository.findByCodigo(codigo);
		endereco.setPessoa(pessoa);
		enderecoRepository.save(endereco);
		attributes.addFlashAttribute("mensagem", "Endereço adicionado com sucesso!");
		return "redirect:/{codigo}";
	}

	//DELETAR ENDERECO
    @RequestMapping("/deletarEndereco")
	public String deletarEndereco(long id){
		Endereco endereco = enderecoRepository.findById(id);
		enderecoRepository.delete(endereco);
		
		Pessoa pessoa = endereco.getPessoa();
		long codigoLong = pessoa.getCodigo();
		String codigo = "" + codigoLong;
		return "redirect:/" + codigo;
	}
    
}
