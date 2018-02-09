/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busticket1;

/**
 *
 * @author User
 */
public class Bus {
   final String bus_number;
   private final Integer seat_max;
   final String bus_type;
   private final Integer price;
   private final String source_station;
   private final String destination;
   private final String event_date;
   final String event_time;

    public Bus(String bus_number, Integer seat_max, String bus_type, Integer price, String source_station, String destination, String event_date, String event_time) {
        this.bus_number = bus_number;
        this.seat_max = seat_max;
        this.bus_type = bus_type;
        this.price = price;
        this.source_station = source_station;
        this.destination = destination;
        this.event_date = event_date;
        this.event_time = event_time;
    }

    public String getBus_number() {
        return bus_number;
    }

    public Integer getSeat_max() {
        return seat_max;
    }

    public String getBus_type() {
        return bus_type;
    }

    public Integer getPrice() {
        return price;
    }

    public String getSource_station() {
        return source_station;
    }

    public String getDestination() {
        return destination;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    @Override
    public String toString() {
        return bus_number +" " + bus_type + " "+ event_time ;
    }
   
   
    
}
