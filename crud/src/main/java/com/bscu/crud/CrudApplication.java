package com.bscu.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bscu.crud.UsersBD.Controller.UserController;
import com.bscu.crud.UsersBD.Entity.User;
import com.bscu.crud.UsersBD.Repository.UserRepository;

@SpringBootApplication
public class CrudApplication implements ApplicationRunner {

    private UserRepository userRepository;
    UserController controlador;

    @Autowired
    public CrudApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        cargarInformacionInicial();
    }

    private void cargarInformacionInicial() {
        String csvFilePath = "src/main/resources/data.csv";
        List<User> users = (List<User>) userRepository.findAll();

        try {
            if (users.isEmpty()) {  
                List<Map<String, String>> jsonData = readCsv(csvFilePath);
                for (Map<String, String> data : jsonData) {
                    User user = new User();
                    user.setName(data.get("name"));
                    user.setLastName(data.get("lastName"));
                    user.setAge(data.get("age"));
                    user.setEmail(data.get("email"));
                    user.setNumberPhone(data.get("numberPhone"));
                    User usuarioNuevo = userRepository.save(user);
                    System.out.println(usuarioNuevo);
                }
            } else {
                System.out.println("La lista de usuarios tiene contenido");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Map<String, String>> readCsv(String filePath) throws IOException {
        List<Map<String, String>> jsonData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String[] headers = br.readLine().split(";");

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");

                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i], fields[i]);
                }

                jsonData.add(row);
            }
        }

        return jsonData;
    }
}
