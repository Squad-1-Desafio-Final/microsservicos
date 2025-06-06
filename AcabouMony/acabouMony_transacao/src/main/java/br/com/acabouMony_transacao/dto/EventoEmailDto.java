package br.com.acabouMony_transacao.dto;

import java.io.Serializable;
import java.util.UUID;

public class EventoEmailDto implements Serializable {
    private UUID pedidoId;
    private UUID usuarioId;

    public EventoEmailDto() {}

    public EventoEmailDto(UUID pedidoId, UUID usuarioId) {
        this.pedidoId = pedidoId;
        this.usuarioId = usuarioId;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}
