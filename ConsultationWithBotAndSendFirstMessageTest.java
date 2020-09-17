package tests.patient.positive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.base.BaseTest;

public class ConsultationWithBotAndSendFirstMessageTest extends BaseTest {


    @Test
    @DisplayName("Тест бота, начало консультации")
    public void testConsultationStart() {

        doctor1.doctorAuth();

        openIframe(patient1.getApiToken());

        consultationPage.checkChatLabel();

        consultationPage.checkSettingBtn();

        consultationPage.checkFirstSystemMessage();

        consultationPage.checkFirstDoctorMessage();

        consultationPage.checkBotName();

        consultationPage.sendMessage(consultationPage.FIRST_PATIENT_MESSAGE);

        consultationPage.checkLastDoctorMessage(consultationPage.SECOND_DOCTOR_MESSAGE);

        consultationPage.checkConsultationStart();

        consultationPage.checkDoctorName(doctor1.getFullName());

        patient1.consultationStatusUpdate(patient1.getApiToken());

        doctor1.doctorEnd(patient1.getConsultationId());

        consultationPage.checkLastSystemMessage(consultationPage.CONSULTATION_FINISH_MESSAGE);
    }

}