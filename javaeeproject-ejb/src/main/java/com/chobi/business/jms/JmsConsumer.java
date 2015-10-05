package com.chobi.business.jms;

import com.chobi.business.service.JMSService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by Chobii on 17/09/15.
 */
@MessageDriven(
        activationConfig = @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:jboss/exported/remote"
        )
)
public class JmsConsumer implements MessageListener {

    @Inject
    private JmsProducer producer;
    @Inject
    private JMSService service;

    @Override
    public void onMessage(Message msg) {
        try {
            String message = msg.getBody(String.class);
            producer.sendMessage(service.retreiveAbscentAndPresent(message));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
