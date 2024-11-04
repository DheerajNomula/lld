package org.personal.elevator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request {
    int floorNumber;
    Direction direction;
}
