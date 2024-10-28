package org.projeto.infra.repository;

import org.projeto.infra.entity.instituicao.InstituicaoEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface InstituicaoRepository {
    Optional<InstituicaoEntity> findInstituicaoById(Integer id);

    Optional<InstituicaoEntity> findInstituicaoEntityById(Integer id);

    void update(InstituicaoEntity instituicao);

    Optional<List<InstituicaoEntity>> findInstituicoes(Integer page, Integer size);
}
