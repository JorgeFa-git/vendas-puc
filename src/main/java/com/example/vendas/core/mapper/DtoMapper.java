package com.example.vendas.core.mapper;

import java.util.List;

public interface DtoMapper<T, D> {

    T domainToDto(D domain);

    List<T> domainToDto(List<D> domains);

    D dtoToDomain(T dto);
}
