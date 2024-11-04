package org.personal.elevator;

import java.util.List;

public class ElevatorSystem {
    private List<Elevator> elevators;
    private ElevatorScheduler scheduler;

    public ElevatorSystem(List<Elevator> elevators) {
        this.elevators = elevators;
        this.scheduler = new ElevatorScheduler();
    }

    public void handleRequest(Request request) {
        Elevator assignedElevator = scheduler.assignElevator(elevators, request);
        assignedElevator.addRequest(request);
    }
}
