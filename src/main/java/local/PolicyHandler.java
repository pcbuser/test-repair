package local;

import local.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired
    RepairProcessingRepository repairProcessingRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAcceptPlaced_RepairStart(@Payload AcceptPlaced acceptPlaced) {
        if(acceptPlaced.isMe()){
            RepairProcessing repairProcessing = new RepairProcessing();
            repairProcessing.setStatus("STARTED");
            repairProcessing.setAcceptid(acceptPlaced.getId());

            repairProcessingRepository.save(repairProcessing);

        }
    }



}
