package com.ias.training.Maintenance.infrastruture.controllers;


import com.ias.training.Maintenance.core.domain.MaintenanceService;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceDescription;
import com.ias.training.Maintenance.core.domain.MaintenanceServiceId;
import com.ias.training.Maintenance.core.gateways.MaintenanceRepository;
import com.ias.training.Maintenance.infrastruture.controllers.models.MaintenanceDTO;
import com.ias.training.Maintenance.infrastruture.controllers.models.MaintenanceInput;

import com.ias.training.Maintenance.shared.domain.Limit;
import com.ias.training.Maintenance.shared.domain.PageQuery;
import com.ias.training.Maintenance.shared.domain.Skip;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RestController
public class maintenanceController {
    private final MaintenanceRepository maintenanceRepository;

    public maintenanceController(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public MaintenanceDTO createMaintenance(@RequestBody MaintenanceInput maintenanceInput) {
        MaintenanceService maintenance = new MaintenanceService(
                new MaintenanceServiceId(UUID.randomUUID().toString()),
                maintenanceInput.getStartService(),
                maintenanceInput.getEndService(),
                new MaintenanceServiceDescription(maintenanceInput.getDescripcion())
        );
        return MaintenanceDTO.fromDomain(maintenance);
    }

    @RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
    public MaintenanceDTO getMaintenance(@PathVariable("id") String maintenanceId) {
        return new MaintenanceDTO(
                maintenanceId,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "SOMO MAS"
        );
    }

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    public List<MaintenanceDTO> listMaintenance(
            @RequestParam(name = "skip", defaultValue = "0") Integer skip,
            @RequestParam(name = "limit", defaultValue = "50") Integer limit
    ) {
        PageQuery pageQuery = new PageQuery(
            new Skip(skip),
            new Limit(limit)
    );
        List<MaintenanceService> maintenanceServices = maintenanceRepository.query(pageQuery);

        List<MaintenanceDTO> result = new ArrayList<>();
        for (MaintenanceService maintenanceService : maintenanceServices) {
            MaintenanceDTO dto = MaintenanceDTO.fromDomain(maintenanceService);
            result.add(dto);
        }
        return result;
    }


}
