package com.giri.validity.customer;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.apache.commons.codec.language.Metaphone;

import javax.inject.Singleton;

/**
 * Algorithm factory that makes third-party library provided classes as beans.
 *
 * @see "https://docs.micronaut.io/latest/guide/index.html#factories"
 *
 * @author gpottepalem
 * Created on Mar 29, 2019
 */
@Factory
public class AlgorithmFactory {

    final static int MAX_CODEC_LENGTH = 4; // TODO: externalize

    /**
     * Creates an instance of {@link Metaphone} and makes it available as a bean for DI
     * The class this bean needs to be DI can simply define a private property of type Metaphone and annotate it with
     * @Inject. The name of the property doesn't matter.
     *
     * The name of the property doesn't matter as DI by type is used
     *
     * @return an instance of {@link Metaphone}
     */
    @Bean
    @Singleton
    Metaphone metaphone() {
        Metaphone metaphone = new Metaphone();
        metaphone.setMaxCodeLen(MAX_CODEC_LENGTH);
        return metaphone;
    }
}
