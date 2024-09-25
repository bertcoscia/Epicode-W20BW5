package bertcoscia.Epicode_W20BW5.DTO;

import java.util.UUID;
import java.util.Date;

public class FatturaDTO {
    private UUID id;
    private Date data;
    private double importo;
    private long numeroFattura;
    private String statoFattura;
    private String cliente;

    public FatturaDTO(UUID id, Date data, double importo, long numeroFattura, String statoFattura, String cliente) {
        this.id = id;
        this.data = data;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
        this.statoFattura = statoFattura;
        this.cliente = cliente;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public long getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(long numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public String getStatoFattura() {
        return statoFattura;
    }

    public void setStatoFattura(String statoFattura) {
        this.statoFattura = statoFattura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}