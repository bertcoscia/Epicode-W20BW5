package bertcoscia.Epicode_W20BW5.DTO;

import java.util.Date;
import java.util.UUID;

public class FatturaDTO {
    private UUID id;
    private Date data;
    private double importo;
    private long numeroFattura;
    private String statoFattura;
    private String cliente;

    public FatturaDTO(Date data, double importo, long numeroFattura, String statoFattura, String cliente) {
        this.data = data;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
        this.statoFattura = statoFattura;
        this.cliente = cliente;
    }
}