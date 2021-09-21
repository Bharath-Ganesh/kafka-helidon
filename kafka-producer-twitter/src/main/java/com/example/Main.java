package com.example;


import com.example.kakfa.Producer;
import io.helidon.microprofile.server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {


    private Main() {
    }

    public static void main(final String[] args) throws IOException {
        Server server = startServer();
        System.out.println("http://localhost:" + server.port() + "/greet");

    }

    static Server startServer() {

        return Server.builder()
                .build()
                .start();
    }
}
