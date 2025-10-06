package grupox.alugueldecarros.entity;

import jakarta.persistence.*;

@Entity
public class Aluguel {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int clienteID;
    private int  AutomovelID;
    private String statusPedido;
    private int quantidadeDias;
    private int agenteID;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getClienteID() {
        return clienteID;
    }
    public void setClienteID(int clienteID) {
        this.clienteID = clienteID;
    }
    public int getAutomovelID() {
        return AutomovelID;
    }
    public void setAutomovelID(int automovelID) {
        AutomovelID = automovelID;
    }
    public String getStatusPedido() {
        return statusPedido;
    }
    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }
    public int getQuantidadeDias() {
        return quantidadeDias;
    }
    public void setQuantidadeDias(int quantidadeDias) {
        this.quantidadeDias = quantidadeDias;
    }
    public int getAgenteID() {
        return agenteID;
    }
    public void setAgenteID(int agenteID) {
        this.agenteID = agenteID;
    }
}
