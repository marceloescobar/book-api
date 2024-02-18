package com.mescobar.bookapi;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

public interface MyContainers {
    @Container
    @ServiceConnection
    MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.4");

    @Container
    @ServiceConnection
    GenericContainer<?> redis =
            new GenericContainer<>(DockerImageName.parse("redis:7"))
                    .withExposedPorts(6379);

}