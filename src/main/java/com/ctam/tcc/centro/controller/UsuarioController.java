package com.ctam.tcc.centro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ctam.tcc.centro.UsuarioRepository;
import com.ctam.tcc.centro.model.Usuario;

@Controller
@RequestMapping("/centro/usuario")
public class UsuarioController {
	
	List<Usuario>  listaDeUsuarios = new ArrayList<Usuario>();
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	@GetMapping("/listar")
	public String listarUsuario(Model model) {
		model.addAttribute("listaDeUsuarios", usuarioRepository.findAll());
		return "Administradores";
		
	}


	// Carrega o formulário de Cadastro
    @GetMapping("/CadastroADM")
public String novoUsuario(Usuario usuario, Model model) {
	
	model.addAttribute("usuario", usuario);
	
	return "CadastroADM";
	
}
    @GetMapping("/paginainicial")
public String showPaginaInicial(Usuario usuario, Model model) {
	
	model.addAttribute("usuario", usuario);
	
	return "TelaInicialPL";
	
}
    @GetMapping("/cadastroMembro")
public String showCadMembro(Usuario usuario, Model model) {
	
	model.addAttribute("usuario", usuario);
	
	return "CadastroMembro";
	
}
    
    @GetMapping("/cadastroTurma")
    public String showCadTurma(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "CadastroTurma";
    	
    }
    
    @GetMapping("/registrarPagamento")
    public String showRegisPay(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "RegistrarPagamento";
    	
    }
    
    @GetMapping("/login")
    public String showLogin(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "login";
    	
    }
    
    @GetMapping("/RecuperarSenha")
    public String showRecuperar(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "RecuperarSenha";
    	
    }
    
    @GetMapping("/listarTurmas")
    public String showTurmaList(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "Turmas";
    	
    }
    
    @GetMapping("/listarMembros")
    public String showMembrosList(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "Membros";
    	
    }
    
    @GetMapping("/listarMensalidade")
    public String showMensalidadeList(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "Mensalidade";
    	
    }
    
    @GetMapping("/myaccount")
    public String showprofile(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "perfil";
    	
    }
    
    
    @GetMapping("/home")
    public String showHome(Usuario usuario, Model model) {
    	
    	model.addAttribute("usuario", usuario);
    	
    	return "index";
    	
    }
    
    @PostMapping("/logon")
    public String logar(Usuario usuario,BindingResult result, Model model) {
    	if(result.hasErrors()) {
    		return "login";
    	}
    	
    	return "redirect:/centro/usuario/paginainicial";
    }
    
// Inserir usuário no banco de dados
@PostMapping("/add-user")
public String addUsuario(Usuario usuario,BindingResult result, Model model) {
	if(result.hasErrors()) {
		return "CadastroADM";
	}
	
	usuario.setStatusUsuario(true);
	Usuario usuarioDb = usuarioRepository.save(usuario);
	
	return "redirect:/centro/usuario/listar";
}


    // Carregar página após cadastro 

    @GetMapping("/todo")
    public List<Usuario>  listaDeUsuarios() {
    	return usuarioRepository.findAll();
    	
    }
    
    @GetMapping("/editar/{id}")
    public String getEditPage(@PathVariable("id") long id, ModelMap model) {
    	
    	Usuario user = usuarioRepository.findById(id)
    			.orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
    	model.addAttribute("user", user);
    	return "edit";
    }
    
    @PostMapping("/atualizar/{id}")
    public String updateUser(
    		@PathVariable("id") long id,
    		@ModelAttribute("usuario") Usuario user, BindingResult result) {
    	if (result.hasErrors()) {
			user.setId(id);
			return "edit";
		}
    	usuarioRepository.save(user);
    	return "redirect:/centro/usuario/listar";
    }
}
