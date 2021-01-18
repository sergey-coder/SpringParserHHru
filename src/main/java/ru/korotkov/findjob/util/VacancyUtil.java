package ru.korotkov.findjob.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.korotkov.findjob.model.Vacancy;
import ru.korotkov.findjob.services.VacancyService;

import java.net.URI;
import java.util.Objects;

@Component
public class VacancyUtil {

    public void saveVacancies(VacancyService vacancyService, String incomingJson) {

        try {

            JSONObject jsonObjectMain = new JSONObject(incomingJson);
            JSONArray jsonArray = jsonObjectMain.getJSONArray("items");

            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    String namevacancy="";
                    String salary="";
                    String employer="";
                    String published_at="";
                    String numbervacancy="";
                    String reference_hhru="https://novosibirsk.hh.ru/vacancy/";

                    JSONObject vacanseFromJson = jsonArray.getJSONObject(i);

                    if (checNullObject(vacanseFromJson.get("name"))) {
                        namevacancy = vacanseFromJson.getString("name");
                    } else {
                        namevacancy = "не указано";
                    }

                    StringBuilder fillSalary = new StringBuilder();
                    if (checNullObject(vacanseFromJson.get("salary")) && checNullObject(vacanseFromJson.getJSONObject("salary").get("from"))) {
                        fillSalary.append("от " + vacanseFromJson.getJSONObject("salary").getInt("from"));
                    }

                    if (checNullObject(vacanseFromJson.get("salary")) && checNullObject(vacanseFromJson.getJSONObject("salary").get("to"))) {
                        fillSalary.append(" до " + vacanseFromJson.getJSONObject("salary").getInt("to"));
                    }

                    if (checNullObject(vacanseFromJson.get("salary"))) {
                        fillSalary.append(" " + vacanseFromJson.getJSONObject("salary").getString("currency"));
                    }
                    salary = fillSalary.toString().equals("")? "не указано": fillSalary.toString();

                    if (checNullObject(vacanseFromJson.getJSONObject("employer").get("name"))) {
                        employer= vacanseFromJson.getJSONObject("employer").getString("name");
                    } else {
                        employer ="не указано";
                    }
                    published_at = vacanseFromJson.getString("published_at");
                    numbervacancy = vacanseFromJson.getString("id");
                    reference_hhru = reference_hhru + numbervacancy;
                    vacancyService.addVacancy(new Vacancy(namevacancy,salary,employer,published_at,numbervacancy,reference_hhru));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String sendHTTPSrequest(String specialization, String nameCity) {
        WebClient.RequestHeadersSpec<?> requestSpec = WebClient
                .create("http://localhost:8080")
                .get()
                .uri(URI.create("https://api.hh.ru/vacancies?area=" + nameCity + "&specialization=" + specialization + "&page=1&per_page=50"));

        String response = Objects.requireNonNull(requestSpec.exchange()
                .block())
                .bodyToMono(String.class)
                .block();

        return response;
    }

    private boolean checNullObject(Object jsonObject) {
        if (jsonObject.getClass().getName().equals("org.json.JSONObject$Null")) {
            return false;
        } else return true;
    }
}
