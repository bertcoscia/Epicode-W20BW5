package bertcoscia.Epicode_W20BW5.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
    public NotFoundException(UUID id) {super("Non è stato possibile trovare il record con id " + id);}
}
