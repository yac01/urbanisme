package com.ybn.urban.rest.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.util.Arrays;

@Configuration
public class ExceptionConfigurer {
    @Autowired
    @Qualifier("exceptionRes")
    private String[] resources;

    @Qualifier("dictionary")
    @Bean
    public Dictionary exposeDictionaryToSpring() {
        Dictionary globalDico = new Dictionary();
        Arrays.stream(this.resources).forEach(res -> {
            Constructor constructor = new Constructor(Dictionary.class);
            Yaml yml = new Yaml(constructor);
            try {
                Dictionary dico = yml.load(ExceptionConfigurer.class.getClassLoader().getResourceAsStream(res));
                globalDico.getEntries().addAll(dico.getEntries());
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("Unable to build dictionary from resource %1s - see stack trace for more details", res));
            }
        });
        return globalDico;
    }
}
