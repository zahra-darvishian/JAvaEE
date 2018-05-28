package GithubTest;

import ir.bki.JSONFormatter;
import ir.bki.PipelineDto;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/QueueTestz2", activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")
})
public class MsgSecConsumer implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            String receivedTxt = message.getBody(String.class);
            PipelineDto pipelineDto = JSONFormatter.fromJSON(receivedTxt, PipelineDto.class);
            System.out.println("##Read From PL2 in github" + pipelineDto.toJSON());


        } catch (JMSException e) {
            System.out.println("#Exception ERROR in onMessage Method!!" + e.getMessage());
            e.printStackTrace();
        }
    }

}
