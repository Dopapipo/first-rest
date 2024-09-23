package fr.pantheonsorbonne.miage.service;

import fr.pantheonsorbonne.miage.dao.QuotaDAO;
import fr.pantheonsorbonne.miage.dto.Quota;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class QuotaService {
    @Inject
    QuotaDAO quotaDAO;

    public void bookTickets(int vendorId, int concertId, int seating, int standing) throws InsufficientQuotaException, NoSuchQuotaException {
        Quota quota = quotaDAO.getQuota(vendorId, concertId);
        if(Objects.isNull(quota)) {
            throw new NoSuchQuotaException();
        }
        var remainingSeated = quota.seated() - seating;
        var remainingStanding = quota.standing() - standing;
        if (remainingSeated < 0 || remainingStanding < 0) {
            throw new InsufficientQuotaException();
        }
        quotaDAO.updateQuota(vendorId, concertId, remainingSeated, remainingStanding);
    }

    public Quota getQuota(int vendorId, int concertId) {
        return quotaDAO.getQuota(vendorId, concertId);
    }

    public List<Quota> getQuotas(int vendorId) {
        return quotaDAO.getQuotas(vendorId);
    }

}
