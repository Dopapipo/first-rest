package fr.pantheonsorbonne.miage.dao;

import fr.pantheonsorbonne.miage.dto.Quota;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ApplicationScoped
public class QuotaDAO {
    Set<Quota> quotas = new HashSet<>();

    void onStartup(@Observes StartupEvent event) {
        quotas.add(new Quota(1, 1, 20, 20));
        quotas.add(new Quota(1, 2, 100, 100));
        quotas.add(new Quota(2, 1, 0, 10));
        quotas.add(new Quota(2, 2, 15, 15));
    }
    public Quota getQuota(int vendorId, int concertId) {
        return quotas
                .stream()
                .filter(q -> q.vendorId() == vendorId && q.concertId() == concertId)
                .findFirst()
                .orElse(null);
    }

    public List<Quota> getQuotas(int vendorId) {
        return quotas.stream().filter(q -> q.vendorId() == vendorId).toList();
    }

    public void updateQuota(int vendorId, int concertId, int seated, int standing) {
        quotas.removeIf(q -> q.vendorId() == vendorId && q.concertId() == concertId);
        quotas.add(new Quota(vendorId, concertId, seated, standing));
    }
}
