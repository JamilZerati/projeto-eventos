package org.projeto.domain.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.projeto.api.dto.InstituicaoTO;
import org.projeto.api.exception.InstituicaoNotFoundException;
import org.projeto.domain.service.util.InstituicaoParser;
import org.projeto.infra.entity.instituicao.InstituicaoEntity;
import org.projeto.infra.repository.InstituicaoRepository;

import java.util.List;

@ApplicationScoped
public class InstituicaoService {

    private final InstituicaoRepository instituicaoRepository;

    public InstituicaoService(InstituicaoRepository instituicaoRepository) {
        this.instituicaoRepository = instituicaoRepository;
    }

    protected InstituicaoEntity findInstituicaoEntityById(Integer id) {
        return instituicaoRepository.findInstituicaoEntityById(id).orElseThrow();
    }

    public List<InstituicaoTO> getInstituicoes(Integer page, Integer size) {
        return instituicaoRepository.findInstituicoes(page, size).map(eventos ->
                eventos.stream().map(InstituicaoParser::toTO).toList()).orElseThrow(InstituicaoNotFoundException::new);
    }

    public InstituicaoTO getById(Integer id) {
        return instituicaoRepository.findInstituicaoById(id).map(InstituicaoParser::toTO).orElseThrow(InstituicaoNotFoundException::new);
    }
}
