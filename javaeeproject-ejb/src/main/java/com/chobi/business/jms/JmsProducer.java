package com.chobi.business.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import java.util.Map;

/**
 * Created by Chobii on 17/09/15.
 */

@Stateless
public class JmsProducer {

    @Resource(mappedName = "java:jboss/exported/remoteBack")
    private Queue queue;

    @Inject
    JMSContext context;

    public void sendMessage(Map<String, Object> objects) {
        try {
            context.createProducer().send(queue, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
