package com.iot.iot_back.mqtt;

public record MedicionMqtt(
    Integer co2,
    Float temperatura,
    Double humedad,
    String calidad
) {}