/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busticket1;

import com.sun.net.httpserver.Authenticator;
import java.awt.List;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author user
 */
public class BusDAO {

    ArrayList<Bus> BusList;

    private static final String GET_ALL = "select bus_number, seat_max, bus_type, price , source_station, destination, date_event, time_event from \"public\".bus;";
    private static final String GET_TICKET = "select b.bus_number, b.seat_max, b.bus_type, b.price , b.source_station, b.destination, b.date_event, b.time_event, "
            + " t.seat, t.ticket_id from ticket t, bus b where t.bus_id = b.bus_id";
    private static final String DELETE_TICKET = "delete from ticket where ticket_id= ?";
    private static final String ADD_BUS = "insert into bus (bus_number,seat_max,bus_type,price,source_station,destination,date_event,time_event,bus_id) values (?,?,?,?,?,?,?,?,(select (max(bus_id)+1) from bus))";
    private DataSource datasource;
    private Connection con = null;

    private final String username = "webuser";
    private final String password = "webuser";
    private final String dbUrl = "jdbc:postgresql://localhost:5432/ticket";
    private final String jdbcClassName = "org.postgresql.Driver";

    public BusDAO() {
        try {
            Class.forName(jdbcClassName);
            Connection con = DriverManager.getConnection(dbUrl, "webuser", "webuser");

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public ArrayList getBusList() {

        BusList = new ArrayList<Bus>();
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(dbUrl, "webuser", "webuser");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL);
            while (rs.next()) {
                Bus blist = new Bus(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                BusList.add(blist);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }

        }

        return BusList;
    }

    public ArrayList getSourceList() {

        ArrayList sourceList = new ArrayList<Bus>();
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(dbUrl, "webuser", "webuser");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL);
            while (rs.next()) {
                String soutcel = rs.getString(5);
                sourceList.add(soutcel);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }

        }
        
        Set <String> set = new HashSet<String>(sourceList);
        ArrayList result = new ArrayList(set);

       return result;
       
    }

    public ArrayList getDestinationList() {
        ArrayList destinationList = new ArrayList<Bus>();
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "webuser", "webuser");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL);
            while (rs.next()) {
                String destinationl = rs.getString(6);
                destinationList.add(destinationl);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }

        }
         Set <String> set = new HashSet<String>(destinationList);
        ArrayList result = new ArrayList(set);


        return result;
    }

    public void removeTicketById(int id) {
        Connection conn;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "webuser", "webuser");
            stmt = conn.prepareStatement(DELETE_TICKET);
            stmt.setInt(1, id);
            stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public void addBus(String busNumber, String seat, String price,
            String source, String destination, String type, Date date) {
        Connection conn;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "webuser", "webuser");
            stmt = conn.prepareStatement(ADD_BUS);
            stmt.setString(1, busNumber);
            stmt.setInt(2, Integer.parseInt(seat));
            stmt.setString(3, type);
            stmt.setInt(4, Integer.parseInt(price));
            stmt.setString(5, source);
            stmt.setString(6, destination);
            stmt.setDate(7, new java.sql.Date(date.getYear(), date.getMonth(), date.getDay() + 12));
            stmt.setTime(8, new java.sql.Time(date.getHours(), date.getMinutes(), 00));

            stmt.execute();
        } catch (SQLException ex) {
            System.err.println("Got an exception! ");
            System.err.println(ex.getMessage());
        }
    }

    public ArrayList getTicketList() {
        ArrayList<Ticket> TicketList = new ArrayList<>();
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(dbUrl, "webuser", "webuser");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_TICKET);
            while (rs.next()) {
                Ticket tlist = new Ticket(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4),
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10));
                TicketList.add(tlist);

            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            }

        }

        return TicketList;
    }

    
    
}
