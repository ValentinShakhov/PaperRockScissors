package service;

import jakarta.inject.Singleton;
import model.Shape;

import java.util.Random;

@Singleton
public class OpponentService {

    public Shape getShapeInput() {
        return Shape.values()[new Random().nextInt(Shape.values().length)];
    }
}
