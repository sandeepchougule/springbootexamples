import spock.lang.Specification

class HelloWorld extends Specification {


    def "Test world" () {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }



}
