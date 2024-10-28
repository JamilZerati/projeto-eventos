package org.projeto.infra.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.projeto.infra.entity.instituicao.InstituicaoEntity;
import org.projeto.infra.repository.InstituicaoRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InstituicaoRepositoryImpl implements PanacheRepositoryBase<InstituicaoEntity, Integer>, InstituicaoRepository {
    @Override
    public Optional<InstituicaoEntity> findInstituicaoById(Integer id) {
        return findByIdOptional(id);
    }

    @Override
    public Optional<InstituicaoEntity> findInstituicaoEntityById(Integer id) {
        return findByIdOptional(id);
    }

    @Override
    public void update(InstituicaoEntity instituicao) {
        persist(instituicao);
    }

    @Override
    public Optional<List<InstituicaoEntity>> findInstituicoes(Integer page, Integer size) {
        return Optional.of(findAll()
                .page(page, size).stream().toList());
    }
}
