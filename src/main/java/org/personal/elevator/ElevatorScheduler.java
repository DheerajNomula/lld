package org.personal.elevator;

import java.util.List;

public class ElevatorScheduler {
    public Elevator assignElevator(List<Elevator> elevators, Request request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.canTakeRequest(request) && minDistance > calculateDistance(elevator, request)) {
                bestElevator = elevator;
                minDistance = calculateDistance(elevator, request);
            }
        }
        if (bestElevator == null)
            return elevatorWithLessRequests(elevators);
        return bestElevator;
    }

    public Elevator elevatorWithLessRequests(List<Elevator> elevators) {
        // todo implement properly
        return elevators.get(0);
    }

    private int calculateDistance(Elevator elevator, Request request) {
        if (elevator.status == Status.IDLE)
            return Math.abs(elevator.currentFloor - request.floorNumber);
        if (elevator.getDirection() == request.getDirection())
            return Math.abs(elevator.currentFloor - request.floorNumber);
        // adding some penalty if its moving in opp direction
        return Math.abs(elevator.currentFloor - request.floorNumber) + 1000;

    }
}
