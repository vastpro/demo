package models;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;

public class Patient extends User {

    private final String PASSWORD;
    private int consultationId;

    public int getConsultationId() {
        return consultationId;
    }

    public Patient(String fullName, String phone, String password, String dateOfBirth) {
        super(fullName, phone, dateOfBirth);
        this.PASSWORD = password;
        patientRegistration();
    }

    @DisplayName("Регистрация пациента")
    private void patientRegistration() {
        String postJsonBody = "{\n" +
                "\t\"full_name\": \"" + getFullName() + "\",\n" +
                "\t\"login\": \"" + getPhone() + "\",\n" +
                "\t\"password\": \"" + PASSWORD + "\",\n" +
                "\t\"password_confirmation\": \"" + PASSWORD + "\",\n" +
                "\t\"clinic_id\": 2,\n" +
                "\t\"date_of_birth\": \"" + getDateOfBirth() + "\"\n" +
                "}";

        String REGISTER = "/api/v3/docdoc/patient/register";
        Response response =
                given()
                        .baseUri(getBASE_URI())
                        .basePath(REGISTER)
                        .headers(
                                "Content-Type",
                                ContentType.JSON)
                        .body(postJsonBody)
                        .when().post()
                        .then().log().body().extract().response();
        setId((Integer) response.path("id"));
        setFullName(response.path("full_name").toString());
        //setAvatar(response.path("avatar").toString());
        //setEmail(response.path("email").toString());
        setPhone(response.path("phone").toString());
        setDateOfBirth(response.path("date_of_birth").toString());
        setType(response.path("type").toString());
        setApiToken(response.path("api_token").toString());
        setFingerprint((Integer) response.path("fingerprint"));
        setSendSms((Integer) response.path("send_sms"));
        setSendEmail((Integer) response.path("send_email"));
        //setLastEntered(response.path("last_entered").toString());
        setCreatedAt(response.path("created_at").toString());
        setUpdatedAt(response.path("updated_at").toString());
        setActive((Boolean) response.path("active"));
        setOnline((Boolean) response.path("online"));
    }

    @DisplayName("Обновить статус консультации")
    public void consultationStatusUpdate(String apiToken) {
        String CONSULTATION_FIND = "/api/v3/consultation/find";
        Response response =
                given()
                        .baseUri(getBASE_URI())
                        .basePath(CONSULTATION_FIND)
                        .params("specialty", "pediatrician", "product_id", 1, "marketplace_code", 99)
                        .headers(
                                "Authorization",
                                "Bearer " + apiToken,
                                "Content-Type",
                                ContentType.JSON)
                        .when().get()
                        .then().log().body().extract().response();
        consultationId = response.path("id");
    }
}