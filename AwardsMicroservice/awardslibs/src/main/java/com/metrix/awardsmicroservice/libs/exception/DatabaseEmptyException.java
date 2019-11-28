/**
 * Database Empty Exception Class extending Exception Class
 */

package com.metrix.awardsmicroservice.libs.exception;

public class DatabaseEmptyException extends Exception {

    /**
     *
     *
     * Initialising message to return whenever an exception occurs
     */
    String message;

    /**
     *
     *
     * Parametrized Constructor for Database Empty Exception Class
     */
    public DatabaseEmptyException(String message) {
        super(message);
        this.message = message;
    }

}