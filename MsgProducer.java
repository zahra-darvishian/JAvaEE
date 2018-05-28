package GithubTest;

import ir.bki.test.java.org.apache.oltu.oauth2.integration.dto.PipelineDto;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("producers")
public class MsgProducer {
        /**
     * Created by z-darvishian  on 2018/04/07.
     */

//    public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
//    public final static String JMS_FACTORY="QCF";
//    public final static String QUEUE="TestQ";

        @Inject
        @JMSConnectionFactory("jms/QueueFactoryTestz1")
        private JMSContext context;

        @Resource(lookup = "jms/QueueTestz1")
        private Queue queue;

        private static long tagger;

        @POST
        @Path("myTest")
        @Produces(MediaType.TEXT_PLAIN)
        @Consumes(MediaType.APPLICATION_JSON)
        public String sendMessagePostSync( PipelineDto pipelineDto) {

            tagger++;
            String tag= tagger+"";
            pipelineDto.setTag(tag);
            pipelineDto.setPayload(System.nanoTime()+"");
            System.out.println("## will be sent to consumer in test Github");
            context.createProducer().send(queue, pipelineDto.toJSON());
            System.out.println("Come back in test Github?!");
            return "###has been sent to consumer in test Github";
        }
    }

