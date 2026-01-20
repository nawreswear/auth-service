module auth.service {
    requires jakarta.persistence;
    requires jjwt.api;
    requires spring.aspects;
    requires spring.beans;
    requires spring.boot.autoconfigure;
    requires spring.boot.test.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.security.config;
    requires spring.security.core;
    requires spring.security.crypto;
    requires spring.security.web;
    requires spring.web;
}