package  grupox.alugueldecarros.entity; 
import javax.annotation.processing.Generated;

import jakarta.persistence.*;

@Entity
public class Automovel {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int ano;
    private String marca;
    private String modelo;
    private String placa;

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
