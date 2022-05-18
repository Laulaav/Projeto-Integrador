package com.redetec.redetec.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.redetec.redetec.model.Categoria;
import com.redetec.redetec.repository.CategoriaRepository;


@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping 
	public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria>getById(@PathVariable Long id){ 
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta)) 
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); 
	}
	
	@GetMapping("/tema/{tema}") 
	public ResponseEntity<List<Categoria>> GetByTema(@PathVariable String tema){ 
		return ResponseEntity.ok(categoriaRepository .findAllByTemaContainingIgnoreCase(tema)); 
	}
	
	@PostMapping 
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria){ 
		return ResponseEntity.status(HttpStatus.CREATED) 
				.body(categoriaRepository.save(categoria)); 
	}
	
	@PutMapping 
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria){ 
		return categoriaRepository.findById(categoria.getId()) 
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED) 
						.body(categoriaRepository.save(categoria))) 
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	@DeleteMapping("/{id}") 
	public void delete(@PathVariable Long id){ 
		Optional<Categoria> categoria = categoriaRepository.findById(id); 
		
		if(categoria.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND); 
		
		categoriaRepository.deleteById(id); 
		
	}

}
