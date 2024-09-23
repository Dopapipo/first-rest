package fr.pantheonsorbonne.miage.resources;

public record BookingRequest(int seated, int standing) {
    public BookingRequest {
        if (seated < 0 || standing < 0) {
            throw new IllegalArgumentException("Booking request cannot be negative");
        }
    }
}
