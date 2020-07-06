package com.parkinglot;

import java.util.*;

public class ParkingLot {
    int MAX_SIZE = 0;
    private class Car {
        String carNumber;
        public Car(String carNumber) {
            this.carNumber = carNumber;
        }
    }
    ArrayList<Integer> availableSlotList;
    Map<String, Car> SlotCar;
    Map<String, String> NumberCar;


    public void createParkingLot(String lotCount) {
        try {
            this.MAX_SIZE = Integer.parseInt(lotCount);
        } catch (Exception e) {
            System.out.println("Invalid lot count");
            System.out.println();
        }
        this.availableSlotList = new ArrayList<Integer>() {};
        for (int i=1; i<= this.MAX_SIZE; i++) {
            availableSlotList.add(i);
        }
        this.SlotCar = new HashMap<String, Car>();
        this.NumberCar = new HashMap<String, String>();
        System.out.println("Created parking lot with " + lotCount + " slots");
        System.out.println();
    }
    public void park(String carNumber) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.SlotCar.size() == this.MAX_SIZE) {
            System.out.println("Sorry, parking lot is full");
            System.out.println();
        } else {
            Collections.sort(availableSlotList);
            String slot = availableSlotList.get(0).toString();
            Car car = new Car(carNumber);
            this.SlotCar.put(slot, car);
            this.NumberCar.put(carNumber, slot);
            System.out.println("Allocated slot number: " + slot);
            System.out.println();
            availableSlotList.remove(0);
        }
    }
    public void leave(String carNumber, String hours) {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.NumberCar.size() > 0) {
            String carToLeave = this.NumberCar.get(carNumber);
            Integer Charge;
            if (carToLeave != null) {
                this.SlotCar.remove(carToLeave);
                this.NumberCar.remove(carNumber);
                // Add the Lot No. back to available slot list.
                this.availableSlotList.add(Integer.parseInt(carToLeave));
                if(Integer.parseInt(hours) <= 2){
                    Charge = 10;
                }else{
                    Integer additionalHours = Integer.parseInt(hours) - 2;
                    Charge = 10 + additionalHours * 10;
                }

                System.out.println("Registration Number " + carNumber + " with Slot number " + carToLeave + " is free with Charge " + Charge);
                System.out.println();
            } else {
                System.out.println("Registration number " + carNumber + " not found");
                System.out.println();
            }
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
    public void status() {
        if (this.MAX_SIZE == 0) {
            System.out.println("Sorry, parking lot is not created");
            System.out.println();
        } else if (this.SlotCar.size() > 0) {
            System.out.println("Slot No.\tRegistration No.");
            Car car;
            for (int i = 1; i <= this.MAX_SIZE; i++) {
                String key = Integer.toString(i);
                if (this.SlotCar.containsKey(key)) {
                    car = this.SlotCar.get(key);
                    System.out.println(i + "\t" + car.carNumber);
                }
            }
            System.out.println();
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }
}
