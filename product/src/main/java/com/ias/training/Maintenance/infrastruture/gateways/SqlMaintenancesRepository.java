package com.ias.training.Maintenance.infrastruture.gateways;

import com.ias.training.Maintenance.core.domain.MaintenanceService;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceId;
import com.ias.training.Maintenance.core.gateways.MaintenanceRepository;
import com.ias.training.Maintenance.infrastruture.gateways.models.MaintenanceDBO;
import com.ias.training.Maintenance.shared.domain.PageQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SqlMaintenancesRepository implements MaintenanceRepository {
    private final DataSource dataSource;

    public SqlMaintenancesRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<MaintenanceService> query(PageQuery pageQuery) {
        String sql = "SELECT * FROM service LIMIT ? OFFSET ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, pageQuery.getLimit().getValue());
            preparedStatement.setInt(2, pageQuery.getSkip().getValue());


            ResultSet resultSet = preparedStatement.executeQuery();
            List<MaintenanceService> result = new ArrayList<>();

            while (resultSet.next()) {
                MaintenanceDBO dbo = new MaintenanceDBO();
                dbo.setId(resultSet.getString("service_id"));
                dbo.setStartService(resultSet.getTimestamp("start").toLocalDateTime());
                dbo.setEndService(resultSet.getTimestamp("end").toLocalDateTime());
                dbo.setDescripcion(resultSet.getString("description"));
                MaintenanceService domainProduct = dbo.toDomain();
                result.add(domainProduct);
            }

            resultSet.close();

            return result;
        } catch (SQLException exception) {
            throw new RuntimeException("Error querying database", exception);
        }
    }

    @Override
    public Optional<MaintenanceService> get(MaintenanceServiceId maintenanceServiceId) {
        return Optional.empty();
    }

    @Override
    public void store(MaintenanceService maintenanceService) {

    }
}
