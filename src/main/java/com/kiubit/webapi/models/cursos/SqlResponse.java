package com.kiubit.webapi.models.cursos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlResponse {
    private String mensaje;
    private boolean exito;
}
