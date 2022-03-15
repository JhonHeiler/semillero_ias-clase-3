package com.ias.training.Maintenance.infrastruture.controllers.models;

import java.time.LocalDateTime;

public class MaintenanceInput {
    private LocalDateTime startService;
    private LocalDateTime endService;
    private String descripcion;

    public MaintenanceInput(LocalDateTime startService, LocalDateTime endService, String descripcion) {
        this.startService = startService;
        this.endService = endService;
        this.descripcion = descripcion;
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
