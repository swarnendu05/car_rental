package com.realProject.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.phone.number}")
    private String fromPhoneNumber;

    public String sendSms(String to, String messageBody) {
        Message message = Message.creator(
                new PhoneNumber(to),       // Receiver's phone number
                new PhoneNumber(fromPhoneNumber), // Twilio phone number
                messageBody
        ).create();

        return "Message sent with SID: " + message.getSid();
    }
}
