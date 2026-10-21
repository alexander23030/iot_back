package com.iot.iot_back.mqtt;

import tools.jackson.databind.json.JsonMapper;
import datosService.datosServices;
import lombok.RequiredArgsConstructor;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

@Component
@RequiredArgsConstructor
public class MqttMedicionesSubscriber {

    private final datosServices service;
private final JsonMapper jsonMapper;
    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.topic}")
    private String topic;

    private MqttClient client;

    @EventListener(ApplicationReadyEvent.class)
    public void conectar() {
        try {
            client = new MqttClient(
                broker,
                MqttClient.generateClientId(),
                new MemoryPersistence()
            );

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);

            client.connect(options);
            System.out.println("Conectado a MQTT. Escuchando: " + topic);

            client.subscribe(topic, 1, (temaRecibido, mensaje) -> {
                try {
                    MedicionMqtt medicion =  jsonMapper.readValue(
                        mensaje.getPayload(),
                        MedicionMqtt.class
                    );

                    service.guardar(
                        medicion.co2(),
                        medicion.temperatura(),
                        medicion.humedad()
                    );

                    System.out.println("Medición MQTT guardada en MySQL.");
                } catch (Exception e) {
                    System.err.println(
                        "Error al procesar la medición MQTT: "
                            + e.getMessage()
                    );
                }
            });
        } catch (MqttException e) {
            System.err.println(
                "No se pudo conectar al broker MQTT: " + e.getMessage()
            );
        }
    }
}