package org.personal.elevator;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

import static org.personal.elevator.Status.*;

@Getter
@Setter
public class Elevator {
    String name;
    int id;
    Queue<Request> requestList;
    Direction direction;
    Status status;
    int currentFloor;

    public Elevator(String name, int id) {
        this.name = name;
        this.id = id;
        this.requestList = new LinkedList<>();
        this.status = IDLE;
        this.currentFloor = 0;
    }

    void addRequest(Request req) {
        requestList.add(req);
        if (status == IDLE)
            processNextRequest();
    }

    void processNextRequest() {
        if (requestList.isEmpty())
            status = IDLE;

        Request nextRequest = requestList.poll();
        moveToFloor(nextRequest.floorNumber);
    }

    public boolean canTakeRequest(Request request) {
        if (this.status == IDLE) {
            return true; // Idle elevators can take any request
        }
        // If the elevator is moving in the request's direction and is still passing the request's floor
        return (this.direction == request.getDirection()) &&
                ((this.direction == Direction.UP && this.currentFloor <= request.getFloorNumber()) ||
                        (this.direction == Direction.DOWN && this.currentFloor >= request.getFloorNumber()));
    }

    public void moveToFloor(int targetFloor) {
        if (currentFloor < targetFloor)
            status = UP;
        else
            status = DOWN;
        currentFloor = targetFloor;
        System.out.println("Reached " + targetFloor);
        processNextRequest();
    }
}
