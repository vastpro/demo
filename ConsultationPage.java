package pages.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.MyFiles;
import org.junit.jupiter.api.DisplayName;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;

public class ConsultationPage {

    /**
     * Page constants
     */
    public final String CHAT_LABEL = "Доктор на связи, консультация началась";
    public final String SETTING_BTN_NAME = "Настройки";
    public final String FIRST_SYSTEM_MESSAGE = "Дежурный врач занимается вашей консультацией";
    public final String FIRST_DOCTOR_MESSAGE = "Здравствуйте! Подскажите, в каком формате вам комфортно общаться: аудио, видео, чат?";
    public final String SECOND_DOCTOR_MESSAGE = "Отлично! Ваш врач подключится в ближайшее время.";
    public final String FIRST_PATIENT_MESSAGE = "видео";
    public final String CONSULTATION_START_MESSAGE = "Начало консультации";
    public final String CONSULTATION_FINISH_MESSAGE = "Конец консультации";
    public final String BOT_NAME = "Дежурный врач";

    //Заголовок чата "Доктор на связи, консультация началась"
    private final SelenideElement chatLabel =
            $x("(//div[@data-test-id='chat']//span)[1]");

    //Кнопка "Настройки"
    private final SelenideElement settingBtn =
            $x("//button[@data-test-id='setting-btn']");

    //Первое системное сообщение
    private final SelenideElement firstSystemMessage =
            $x("//div[@data-test-id='system-message'][1]");

    //Последнее системное сообщение
    private final SelenideElement lastSystemMessage =
            $x("//div[@data-test-id='system-message'][last()]");

    //Первое сообщение от доктора
    private final SelenideElement firstDoctorMessage =
            $x("//div[@data-test-id='incoming-message'][1]//span");

    //Последнее сообщение от доктора
    private final SelenideElement lastDoctorMessage =
            $x("//div[@data-test-id='incoming-message'][last()]//span");

    //Первое сообщение от пациента
    private final SelenideElement firstPatientMessage =
            $x("//div[@data-test-id='outgoing-message'][1]//span");

    //Последнее сообщение от пациента
    private final SelenideElement lastPatientMessage =
            $x("//div[@data-test-id='outgoing-message'][last()]//span");

    //Кнопка добавления файла
    private final SelenideElement fileInputBtn =
            $x("//div[@data-test-id='file-input-btn']");

    //Поле ввода сообщений
    private final SelenideElement messageInput =
            $x("//textarea[@data-test-id='message-input']");

    //Кнопка отправки сообщений
    private final SelenideElement sendBtn =
            $x("//div[@data-test-id='send-btn']");

    //ФИО врача
    private final SelenideElement doctorName =
            $x("(//div[@data-test-id='doctor-profile']//span)[1]");

    //Последнее картинка в чате
    private final SelenideElement lastPhoto =
            $x("(//div[@data-test-id='messages']//img)[last()]");

    @DisplayName("Проверить заголовок окна")
    public void checkChatLabel() {
        chatLabel.shouldBe(Condition.visible);
        chatLabel.shouldHave(Condition.text(CHAT_LABEL));
    }

    @DisplayName("Проверить название кнопки")
    public void checkSettingBtn() {
        settingBtn.shouldHave(Condition.text(SETTING_BTN_NAME));
    }

    @DisplayName("Проверить текст первого системного сообщения")
    public void checkFirstSystemMessage() {
        firstSystemMessage.shouldHave(Condition.text(FIRST_SYSTEM_MESSAGE));
    }

    @DisplayName("Проверить текст последнего системного сообщения")
    public void checkLastSystemMessage(String locator) {
        lastSystemMessage.shouldHave(Condition.text(locator));
    }

    @DisplayName("Проверить текст первого сообщения от доктора")
    public void checkFirstDoctorMessage() {
        firstDoctorMessage.shouldHave(Condition.text(FIRST_DOCTOR_MESSAGE));
    }

    @DisplayName("Проверить текст последнего сообщение от доктора")
    public void checkLastDoctorMessage(String locator) {
        lastDoctorMessage.shouldHave(Condition.text(locator));
    }

    public void checkFirstPatientMessage() {
        firstPatientMessage.shouldHave(Condition.text(FIRST_PATIENT_MESSAGE));
    }

    public void checkLastPatientMessage(String locator) {
        lastPatientMessage.shouldHave(Condition.text(locator));
    }

    @DisplayName("Проверить что консультация началась")
    public void checkConsultationStart() {
        lastSystemMessage.shouldHave(Condition.text(CONSULTATION_START_MESSAGE));
    }

    @DisplayName("Проверить имя бота")
    public void checkBotName() {
        doctorName.shouldHave(Condition.text(BOT_NAME));
    }

    @DisplayName("Проверить имя доктора")
    public void checkDoctorName(String name) {
        doctorName.shouldHave(Condition.text(name));
    }

    public void clickSettingBtn() {
        settingBtn.shouldBe(Condition.visible).click();
    }

    @DisplayName("Отправить текстовое сообщение")
    public void sendMessage(String text) {
        messageInput.shouldBe(Condition.visible).setValue(text);
        sendBtn.click();
    }

    @DisplayName("Отправить файл")
    public void sendFile(MyFiles text) {
        File file = new File("src/test/resources/file." + text.toString().toLowerCase());
        $x("//input").shouldBe().uploadFile(file);
        sendBtn.shouldBe(Condition.enabled).click();
    }
}