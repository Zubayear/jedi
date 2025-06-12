package com.lucienvirecourt.jedi.other;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

//record Journey(
//  String origin,
//  List<String> via,
//  String destination,
//  ZonedDateTime departureTime,
//  ZonedDateTime arrivalTime
//) {
//  Journey {
//    origin = Objects.requireNonNull(origin, "Origin can't be null");
//    via = List.copyOf(via); // making the via list immutable
//  }
//}

interface Journey {
  String origin();

  String destination();

  ZonedDateTime departureTime();

  ZonedDateTime arrivalTime();
}

record BusJourney(
  String origin,
  String destination,
  ZonedDateTime departureTime,
  ZonedDateTime arrivalTime,
  String busNumber
) implements Journey {
}

record TrainJourney(
  String origin,
  String destination,
  ZonedDateTime departureTime,
  ZonedDateTime arrivalTime,
  String departurePlatform,
  String arrivalPlatform
) implements Journey {
}


public class RecordsAndPatternMatching {

  public static void journeyInfo(Journey journey) {
    switch (journey) {
      case BusJourney(
        _, _, _, _, String busNumber
      ) when busNumber.length() > 10 -> System.out.printf("Bus number %s\n", busNumber);
      case TrainJourney(
        _, _, _, _, String platform, _
      ) -> System.out.printf("Platform number %s\n", platform);
      default -> throw new IllegalStateException("Unexpected value: " + journey);
    }
  }

  public static void main(String[] args) {
    var departureTime = ZonedDateTime.parse("2025-06-10T08:00+02:00");
    var arrivalTime = departureTime
      .plusHours(6)
      .plusMinutes(30);
    Journey journey = new BusJourney("Amsterdam", "Paris", departureTime, arrivalTime, UUID.randomUUID().toString());
    journeyInfo(journey);
  }
}
