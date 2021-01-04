package com.gestaoresponsabilidadetecnica.responsavelTecnico.enums;

import lombok.Getter;

@Getter
public enum ExtensaoArquivo {

    PDF("application/pdf"),
    JPG("image/jpg"),
    JPEG("image/jpeg"),
    TIF("image/tif"),
    BMP("image/bmp"),
    PNG("image/png");

    private String codigo;

    ExtensaoArquivo(String codigo) {
        this.codigo = codigo;
    }

}
