package com.ias.training.Maintenance.infrastruture.gateways;

import com.ias.training.Maintenance.core.domain.MaintenanceService;
import com.ias.training.Maintenance.core.gateways.MaintenanceRepository;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqlMaintenancesRepositoryTest {
   DataSource dataSource;
   MaintenanceRepository maintenanceRepository = new SqlMaintenancesRepository(dataSource);

}