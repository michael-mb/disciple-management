package disciple.online.portal.scopes.report.repositories;

import disciple.online.portal.scopes.report.entities.DetailTicket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.util.Streamable;

import java.util.List;

public interface ReportDetailRepository extends CrudRepository<DetailTicket,Long> {
    @Override
    Streamable<DetailTicket> findAll();

    @Query("select e from #{#entityName} as e order by e.endDate desc ")
    List<DetailTicket> findAllByOrderByEndDateDesc();
}
