package fr.pantheonsorbonne.miage.dto;

public record Quota(int vendorId, int concertId, int seated, int standing) {
    public Quota {
        if (seated < 0 || standing < 0) {
            throw new IllegalArgumentException("Quota cannot be negative");
        }
    }
}
