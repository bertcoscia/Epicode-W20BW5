package bertcoscia.Epicode_W20BW5.runners;

import bertcoscia.Epicode_W20BW5.services.RuoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RuoliRunner implements CommandLineRunner {
    @Autowired
    private RuoloService ruoloService;

    @Override
    public void run(String... args) throws Exception {
        /*RuoloDTO ruoloDTO = new RuoloDTO("ADMIN");
        RuoloDTO ruoloDTO1 = new RuoloDTO("USER");
        ruoloService.save(ruoloDTO);
        ruoloService.save(ruoloDTO1);*/
    }
}
