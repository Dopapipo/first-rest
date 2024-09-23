package fr.pantheonsorbonne.miage.service;

public class InsufficientQuotaException extends Exception {
    public InsufficientQuotaException() {
        super("Not enough tickets available");
    }
}
