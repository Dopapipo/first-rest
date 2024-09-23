package fr.pantheonsorbonne.miage.resources;

import fr.pantheonsorbonne.miage.dto.Quota;
import fr.pantheonsorbonne.miage.service.InsufficientQuotaException;
import fr.pantheonsorbonne.miage.service.NoSuchQuotaException;
import fr.pantheonsorbonne.miage.service.QuotaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import java.util.List;
import org.jboss.logging.annotations.Param;


@Path("/vendor/{vendorId}/quota")
public class QuotaResource {
    @Inject
    QuotaService quotaService;
    @GET
    @Path("/{concertId}")
    public Quota getQuotaForVendorAndConcert(@PathParam("vendorId") int vendorId, @PathParam("concertId") int concertId) {
        return this.quotaService.getQuota(vendorId, concertId);
    }
    @GET
    public List<Quota> getQuotaForVendor(@PathParam("vendorId") int vendorId) {
        return this.quotaService.getQuotas(vendorId);
    }
    @PUT
    @Path("/{concertId}")
    public void bookConcert(@PathParam("vendorId") int vendorId, @PathParam("concertId") int concertId, BookingRequest bookingRequest) {
        try {
            this.quotaService.bookTickets(vendorId, concertId, bookingRequest.seated(), bookingRequest.standing());
        }
        catch (InsufficientQuotaException e) {
            throw new jakarta.ws.rs.BadRequestException("Insufficient Quota");
        }
        catch (NoSuchQuotaException e) {
            throw new jakarta.ws.rs.NotFoundException("No such quota");
        }
    }
}
