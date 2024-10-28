package org.projeto.infra.repository.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Query;
import org.projeto.api.filter.EventoFilter;
import org.projeto.infra.entity.evento.EventoEntity;
import org.projeto.infra.repository.EventRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EventRepositoryImpl implements PanacheRepositoryBase<EventoEntity, Integer>, EventRepository {

    @Override
    public void salvarEvento(EventoEntity eventoEntity) {
        persist(eventoEntity);
    }


    @Override
    public void ativarEventos() {
        String query = "update eventos SET ativo = ?1 where ?2 > data_inicial and ?3 <= data_final and ativo = ?4";
        changeEventStatus(query, true);
    }

    @Override
    public void desativarEventos() {
        String query = "update eventos SET ativo = ?1 where (?2 < data_inicial or ?3 > data_final) and ativo = ?4";
        changeEventStatus(query, false);
    }

    private void changeEventStatus(String query, boolean statusAtualizado) {
        Query nativeQuery = getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, statusAtualizado);
        nativeQuery.setParameter(2, LocalDate.now());
        nativeQuery.setParameter(3, LocalDate.now());
        nativeQuery.setParameter(4, !statusAtualizado);
        nativeQuery.executeUpdate();
    }

    public Optional<List<EventoEntity>> buscarEventos(EventoFilter eventoFilter) {
        HashMap<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder("1 = 1");
        if (eventoFilter.getNome() != null) {
            query.append(" AND nome = :nome");
            params.put("nome", eventoFilter.getNome());
        }
        if (eventoFilter.getDataInicial() != null) {
            query.append(" AND dataInicial = :dataInicial");
            params.put("dataInicial", eventoFilter.getDataInicial());
        }
        if (eventoFilter.getDataFinal() != null) {
            query.append(" AND dataFinal = :dataFinal");
            params.put("dataFinal", eventoFilter.getDataFinal());
        }
        if (eventoFilter.getInstituicaoId() != null) {
            query.append(" AND instituicao.id = :instituicaoId");
            params.put("instituicaoId", eventoFilter.getInstituicaoId());
        }
        if (eventoFilter.getAtivo() != null) {
            query.append(" AND ativo = :ativo");
            params.put("ativo", eventoFilter.getAtivo());
        }

        return Optional.of(find(query
                .toString(), params).page(eventoFilter.getPage(), eventoFilter.getSize()).list());
    }

    public Optional<EventoEntity> buscarEventoPorId(Integer id) {
        return findByIdOptional(id);
    }

    public void atualizarEvento(EventoEntity eventoEntity) {
        String query = "update eventos SET nome = ?1, data_inicial = ?2, data_final = ?3, ativo = ?4, instituicao_id = ?5 where evento_id = ?6";
        Query nativeQuery = getEntityManager().createNativeQuery(query);
        nativeQuery.setParameter(1, eventoEntity.getNome());
        nativeQuery.setParameter(2, eventoEntity.getDataInicial());
        nativeQuery.setParameter(3, eventoEntity.getDataFinal());
        nativeQuery.setParameter(4, eventoEntity.isAtivo());
        nativeQuery.setParameter(5, eventoEntity.getInstituicao().getInstituicaoId());
        nativeQuery.setParameter(6, eventoEntity.getEventoId());
        nativeQuery.executeUpdate();
    }


    public void deletarEvento(Integer id) {
        delete("id = ?1", id);
    }
}
