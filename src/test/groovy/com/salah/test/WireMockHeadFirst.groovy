package com.salah.test

import com.github.tomakehurst.wiremock.WireMockServer
import spock.lang.Specification


class WireMockHeadFirst extends Specification {
    public static WireMockServer wireMock = new WireMockServer()

    void setupSpec() {
        wireMock.start()
    }


    void cleanupSpec() {
        wireMock.stop()
    }

    void cleanup() {
        wireMock.resetAll()
    }
}