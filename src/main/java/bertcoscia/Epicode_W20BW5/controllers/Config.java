package bertcoscia.Epicode_W20BW5.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Config {

    @Value("${server.port}")
    private String serverPort;

    @Value("${PG_USERNAME}")
    private String pgUsername;

    @Value("${PG_URL}")
    private String pgUrl;

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${CLOUDINARY.NAME:}")
    private String cloudinaryName;

    @Value("${CLOUDINARY.KEY:}")
    private String cloudinaryKey;

    @Value("${CLOUDINARY.SECRET:}")
    private String cloudinarySecret;
}

