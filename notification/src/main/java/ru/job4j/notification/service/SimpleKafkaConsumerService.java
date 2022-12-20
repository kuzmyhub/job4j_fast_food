package ru.job4j.notification.service;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.domain.model.Notification;
import ru.job4j.domain.model.Order;

@Service
@AllArgsConstructor
public class SimpleKafkaConsumerService implements KafkaConsumerService {

    private NotificationService simpleNotificationService;

    @KafkaListener(topics = {"notification"})
    public void onApiCall(ConsumerRecord<Integer, Order> input) {
        Notification notification = simpleNotificationService
                .createNotification(input.value());
        simpleNotificationService.save(notification);
    }
}
