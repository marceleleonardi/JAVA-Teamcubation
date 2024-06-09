package com.example.transacaoAPI.controller;

import com.example.transacaoAPI.model.Transacao;
import com.example.transacaoAPI.model.TransacaoEstatistica;
import com.example.transacaoAPI.service.TransacaoServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoServico transacaoServico;

    @PostMapping
    public ResponseEntity<Void> addTransacao(@RequestBody Transacao transacao) {
        if (transacao.getTimestamp().isBefore(LocalDateTime.now().minusSeconds(60))) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        transacaoServico.addTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/estatistica")
    public ResponseEntity<TransacaoEstatistica> getEstatistica() {
        TransacaoEstatistica stats = transacaoServico.getEstatistica();
        return ResponseEntity.ok(stats);
    }
}
