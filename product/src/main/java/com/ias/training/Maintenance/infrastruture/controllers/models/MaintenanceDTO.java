package com.ias.training.Maintenance.infrastruture.controllers.models;

import com.ias.training.Maintenance.core.domain.MaintenanceService;

import java.time.LocalDateTime;

public class MaintenanceDTO {
    private String id;
    private LocalDateTime startService;
    private LocalDateTime endService;
    private String descripcion;

    public MaintenanceDTO(String id, LocalDateTime startService, LocalDateTime endService, String descripcion) {
        this.id = id;
        this.startService = startService;
        this.endService = endService;
        this.descripcion = descripcion;
    }

    public MaintenanceDTO(){

    }
    public static MaintenanceDTO fromDomain(MaintenanceService maintenanceService) {
        return new MaintenanceDTO(
                maintenanceService.getId().toString(),
                maintenanceService.getStartService(),
                maintenanceService.getEndService(),
                maintenanceService.getDescripcion().toString()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartService() {
        return startService;
    }

    public void setStartService(LocalDateTime startService) {
        this.startService = startService;
    }

    public LocalDateTime getEndService() {
        return endService;
    }

    public void setEndService(LocalDateTime endService) {
        this.endService = endService;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
