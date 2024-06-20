package com.CabbageAndGarlic.service;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final VonageClient vonageClient;

    public SmsService(@Value("${vonage.apiKey}") String apiKey, @Value("${vonage.apiSecret}") String apiSecret) {
        this.vonageClient = VonageClient.builder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    }

    public void sendSms(String to, String from, String text) {
        TextMessage message = new TextMessage(from, to, text);

        SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }
}
