package com.salah.test

import spock.lang.Specification;

class FirstSpecification extends Specification {
    void setupSpec() {
        // runs once for Specification
    }

    void setup() {
        // runs before each individual test
    }

    def "one plus one equals two"() {
        expect:
        1 + 1 == 2
    }

    def "1 + 1 = 2 part 2"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }

    def "Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

    def "Should throws Index out of bounds exception"() {
        given:
        def list = [1, 2, 3]

        when:
        list.remove(5)

        then:
        thrown(IndexOutOfBoundsException)
        list.size() == 3
    }

    void cleanupSpec() {
        // runs once for Specification
    }

    void cleanup() {
        // tear down
    }
}
