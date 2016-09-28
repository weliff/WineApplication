package com.algaworks.wine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.wine.dto.FotoDTO;
import com.algaworks.wine.model.TipoVinho;
import com.algaworks.wine.model.Vinho;
import com.algaworks.wine.repository.Vinhos;
import com.algaworks.wine.service.CadastroVinhoService;
import com.algaworks.wine.storage.FotoStorage;

@Controller
@RequestMapping("/vinhos")
public class VinhosController {
	
	@Autowired
	private Vinhos vinhos;
	
	@Autowired
	private CadastroVinhoService cadastroVinhoService;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@RequestMapping
	public ModelAndView pesquisa() {
		ModelAndView mv = new ModelAndView("vinho/ListagemVinhos");
		mv.addObject("vinhos", vinhos.findAll());
		return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(Vinho vinho) {
		ModelAndView mv = new ModelAndView("vinho/CadastroVinho");
		mv.addObject("tipos", TipoVinho.values());
		return mv;
	}
	
	@RequestMapping(value="/novo", method=RequestMethod.POST)
	public ModelAndView salvar(@Validated Vinho vinho, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(vinho);
		}
		cadastroVinhoService.salvar(vinho);
		attributes.addFlashAttribute("mensagem", "Vinho salvo com sucesso!");
		ModelAndView mv = new ModelAndView("redirect:/vinhos/novo");
		return mv;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView visualizar(@PathVariable("codigo") Vinho vinho) {
		if (vinho.hasFoto()){
			vinho.setFotoDTO(new FotoDTO(fotoStorage.getURL(vinho.getFoto())));
		}
		ModelAndView mv = new ModelAndView("vinho/VisualizacaoVinho");
		mv.addObject("vinho", vinho);
		return mv;
	}
	
}
