package GithubTest;

import ir.bki.JSONFormatter;
import ir.bki.PipelineDto;
import ir.bki.SingletonMap;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.*;

@MessageDriven(mappedName = "jms/QueueTestz1", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
})
public class MsgFirstConsumer implements MessageListener {

    @Inject
    @JMSConnectionFactory("jms/QueueFactoryTestz1")
    private JMSContext context;

    @Resource(lookup = "jms/QueueTestz2")
    private Queue queue2;

    @Override
    public void onMessage(Message message) {
        try {
            String mapRequest = message.getBody(String.class);
            System.err.println("#mapRequest: " + mapRequest);

            PipelineDto pipelineDto = JSONFormatter.fromJSON(mapRequest, PipelineDto.class);

            pipelineDto.setPayload(pipelineDto.getPayload() + "_CHANGE1");
            context.createProducer().send(queue2, pipelineDto.toJSON());
            System.out.println("## Read PL1 Add to PL2 in test Github" + pipelineDto.toJSON());

        } catch (JMSException e) {
            System.out.println("#Exception ERROR in onMessage Method!!" + e.getMessage());
            e.printStackTrace();
        }

    }


}

