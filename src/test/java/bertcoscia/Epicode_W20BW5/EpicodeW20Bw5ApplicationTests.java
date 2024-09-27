package bertcoscia.Epicode_W20BW5;

import bertcoscia.Epicode_W20BW5.payloads.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EpicodeW20Bw5ApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testConAutenticazione() throws Exception {
        String token = "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3Mjc0MjIzNzQsImV4cCI6MTcyODAyNzE3NCwic3ViIjoiMGZhOWYwZjEtZGEwNi00ZWM0LWJiYmUtZTk0NWIzYTczMzhhIn0.XZX_06B-2Qe2r9ig45-2HqafefpE8jH7JCS7zJLc6a1wr_aDhml_xv9-uoZHUqnhW9AWXEY7N3jKvJIOxSEf_g";
        mockMvc.perform(MockMvcRequestBuilders.get("/clienti")
                        .header("Authorization", token))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllRuoli() throws Exception {
        String token = "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3Mjc0MjIzNzQsImV4cCI6MTcyODAyNzE3NCwic3ViIjoiMGZhOWYwZjEtZGEwNi00ZWM0LWJiYmUtZTk0NWIzYTczMzhhIn0.XZX_06B-2Qe2r9ig45-2HqafefpE8jH7JCS7zJLc6a1wr_aDhml_xv9-uoZHUqnhW9AWXEY7N3jKvJIOxSEf_g";
        mockMvc.perform(get("/ruoli")
                        .header("Authorization", token))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("ADMIN"))
                .andExpect(jsonPath("$[1].nome").value("USER"));
    }

    @Test
    void testUpdateProfile() throws Exception {
        String token = "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJpYXQiOjE3Mjc0MjIzNzQsImV4cCI6MTcyODAyNzE3NCwic3ViIjoiMGZhOWYwZjEtZGEwNi00ZWM0LWJiYmUtZTk0NWIzYTczMzhhIn0.XZX_06B-2Qe2r9ig45-2HqafefpE8jH7JCS7zJLc6a1wr_aDhml_xv9-uoZHUqnhW9AWXEY7N3jKvJIOxSEf_g";
        UserDTO userDTO = new UserDTO("teamFortissimo", "topTeam@gmail.com", "1234", "team", "fortissimo");
        mockMvc.perform(put("/users/me")
                        .header("Authorization", token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("topTeam@gmail.com"))
                .andExpect(jsonPath("$.nome").value("team"))
                .andExpect(jsonPath("$.cognome").value("fortissimo"));
    }
}
