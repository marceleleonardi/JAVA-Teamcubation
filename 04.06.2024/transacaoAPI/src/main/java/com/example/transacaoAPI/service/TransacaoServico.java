package com.example.transacaoAPI.service;

import com.example.transacaoAPI.model.Transacao;
import com.example.transacaoAPI.model.TransacaoEstatistica;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class TransacaoServico {

    private final List<Transacao> transacoes = new CopyOnWriteArrayList<>();

    public void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public TransacaoEstatistica getEstatistica() {
        LocalDateTime now = LocalDateTime.now();
        List<Transacao> lastMinuteTransactions = transacoes.stream()
                .filter(t -> t.getTimestamp().isAfter(now.minusSeconds(60)))
                .collect(Collectors.toList());

        DoubleSummaryStatistics stats = lastMinuteTransactions.stream()
                .mapToDouble(Transacao::getAmount)
                .summaryStatistics();

        return new TransacaoEstatistica(stats.getSum(), stats.getAverage(), stats.getMax(), stats.getMin(), stats.getCount());
    }

}
