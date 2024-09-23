package fr.pantheonsorbonne.miage.service;

public class NoSuchQuotaException extends Throwable {
    public NoSuchQuotaException() {
        super("No such quota");
    }
}
