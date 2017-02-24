# Arquillian Rest Extension With Embedded Glassfish and JUnit Example 
A Rest API with JAX-RS with Integration Tests implemented on [Arquillian](https://github.com/arquillian) and executed against an embedded instance of Glassfish [WildFly](https://embedded-glassfish.java.net/).

## Test Content
In the [Arquillian XML](https://github.com/Lemmy4555/arquillian-rest-extension-embedded-server-example/blob/master/src/test/resources/arquillian.xml) the container's configuration reside.

Here we find 2 different test approach:
- Using WebTarget injection with Arquillian
- By creating an instance of the WebTarget starting from the base url returned by Arquillian

In every test case i'll call an API "api/getAResponse" which would respond with a string like this: 

    {"response":"This is a rest response: Without WebTarget Injection"}

## How to run tests

Using maven:

    mvn test

## Documentation

* [Arquillian Guides](http://arquillian.org/guides/)
