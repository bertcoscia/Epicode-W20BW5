package bertcoscia.Epicode_W20BW5.runners;

import bertcoscia.Epicode_W20BW5.services.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CsvToPostgressRunner implements CommandLineRunner {

    @Autowired
    ProvinceService provinceService;

    @Override
    public void run(String... args) throws Exception {
        // provinceService.importData("src/main/java/bertcoscia/Epicode_W20BW5/files/province-italiane.csv");
        
    }
}
