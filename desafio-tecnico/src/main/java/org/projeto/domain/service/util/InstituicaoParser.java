package org.projeto.domain.service.util;

import org.projeto.api.dto.InstituicaoTO;
import org.projeto.infra.entity.instituicao.InstituicaoEntity;


public class InstituicaoParser {

    public static InstituicaoEntity toEntity(InstituicaoTO instituicaoTO) {
        return InstituicaoEntity.builder()
                .id(instituicaoTO.getId())
                .nome(instituicaoTO.getNome())
                .tipo(instituicaoTO.getTipo())
                .build();
    }

    public static InstituicaoTO toTO(InstituicaoEntity instituicaoEntity) {
        return InstituicaoTO.builder()
                .id(instituicaoEntity.getInstituicaoId())
                .nome(instituicaoEntity.getNome())
                .tipo(instituicaoEntity.getTipo())
                .eventos(instituicaoEntity.getEventos()
                        .stream()
                        .map(EventoParser::toTOSemInstituicao)
                        .toList())
                .build();
    }

    public static InstituicaoTO toTOSemEventos(InstituicaoEntity instituicaoEntity) {
        return InstituicaoTO.builder()
                .id(instituicaoEntity.getInstituicaoId())
                .nome(instituicaoEntity.getNome())
                .tipo(instituicaoEntity.getTipo())
                .build();
    }
}
