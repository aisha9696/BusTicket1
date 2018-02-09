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
public class Ticket  extends Bus {
    private Integer seat; 
    private Integer id;
    public Ticket(String bus_number, Integer seat_max, String bus_type, Integer price, String source_station, String destination, String event_date, String event_time, Integer seat,Integer id) {
        super(bus_number, seat_max, bus_type, price, source_station, destination, event_date, event_time);
        this.seat = seat;
        this.id=id;
    }

    public Integer getId()
    {
        return id;
    }
    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }
     public void setId(Integer id) {
        this.id= id;
    }
    
    public String getBusInf(){
    
    return bus_number +" " + bus_type + " "+ event_time ;
    }
    
}
