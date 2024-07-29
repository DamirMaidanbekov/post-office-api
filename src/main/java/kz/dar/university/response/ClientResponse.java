package kz.dar.university.response;

import lombok.Data;

@Data
public class ClientResponse {
    private String clientId; // Добавляем поле clientId
    private String name;
    private String surname;
    private String email;
}
