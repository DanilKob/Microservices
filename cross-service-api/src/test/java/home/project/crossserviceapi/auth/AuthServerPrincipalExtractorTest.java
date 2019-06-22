package home.project.crossserviceapi.auth;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AuthServerPrincipalExtractorTest {

    private
    static final String JSON_RESPONSE =
            "{\n" +
                    "    \"user\": {\n" +
                    "        \"password\": null,\n" +
                    "        \"username\": \"john\",\n" +
                    "        \"authorities\": [\n" +
                    "            {\n" +
                    "                \"authority\": \"USER\"\n" +
                    "            }\n" +
                    "        ],\n" +
                    "        \"accountNonExpired\": true,\n" +
                    "        \"accountNonLocked\": true,\n" +
                    "        \"credentialsNonExpired\": true,\n" +
                    "        \"enabled\": true,\n" +
                    "        \"externalData\": null,\n" +
                    "        \"firstName\": \"John\",\n" +
                    "        \"middleName\": \"James\",\n" +
                    "        \"lastName\": \"White\"\n" +
                    "    },\n" +
                    "    \"authorities\": [\n" +
                    "        \"USER\"\n" +
                    "    ]\n" +
                    "}";

    @org.junit.jupiter.api.Test
    void extractPrincipal() throws IOException {
        AuthServerPrincipalExtractor authServerPrincipalExtractor =
                new AuthServerPrincipalExtractor();
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Map<String, Object> responseMap = objectMapper.readValue(JSON_RESPONSE, new TypeReference<Map<String, Object>>(){});
        System.out.println(responseMap);
        Object principal = authServerPrincipalExtractor.extractPrincipal(responseMap);
        System.out.println(principal.getClass());
        System.out.println(principal);
    }
}