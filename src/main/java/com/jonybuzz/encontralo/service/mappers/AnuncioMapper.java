package com.jonybuzz.encontralo.service.mappers;

import com.jonybuzz.encontralo.dto.NuevoAnuncioDto;
import com.jonybuzz.encontralo.model.Anuncio;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AnuncioMapper {

  @Mapping(source = "nombreMascota", target = "nombreMascota", qualifiedByName = "normalizarEspacio")
  @Mapping(source = "nombreMascota", target = "nombreMascotaNormalizado", qualifiedByName = "normalizarNombre")
  @Mapping(source = "comentario", target = "comentario", qualifiedByName = "normalizarEspacio")
  @Mapping(source = "fotos", target = "fotos", ignore = true)
  Anuncio NuevoAnuncioDtoToAnuncio(NuevoAnuncioDto nuevoAnuncioDto);

  @Named("normalizarNombre")
  static String normalizarNombre(String str) {
    return str == null ? null :
        StringUtils.normalizeSpace(
            StringUtils.stripAccents(str)
                .toUpperCase().replaceAll("[^A-Z\\s]", "")
        );
  }

  @Named("normalizarEspacio")
  static String normalizarEspacio(String str) {
    return StringUtils.normalizeSpace(str);
  }

}
