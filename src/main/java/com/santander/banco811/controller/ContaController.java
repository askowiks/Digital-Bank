package com.santander.banco811.controller;


import com.santander.banco811.dto.ContaRequest;
import com.santander.banco811.dto.ContaResponse;
import com.santander.banco811.model.Conta;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    ContaService contaService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<ContaResponse> getAll(@RequestParam(required = false) Integer id) {
        return contaService.getAll(id);
    }

    @PostMapping("/{idUsuario}")
    public ContaResponse create(
            @PathVariable Integer idUsuario,
            @RequestBody ContaRequest contaRequest) {

        var usuario =  usuarioService.getById(idUsuario);

        return contaService.create(usuario, contaRequest);
    }

    @PutMapping("/{id}")
    public Conta update(@PathVariable Integer id, @RequestBody ContaRequest contaUpdate) {
        return contaService.update(contaUpdate, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        contaService.delete(id);
    }

}
