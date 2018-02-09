/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busticket1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.JobAttributes;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Aisha
 */
public class BTicket implements ActionListener {

    private JFrame frame;
    private final JButton button1;
    private final JButton button2;
    private final JComboBox comboBox1;
    private final JComboBox comboBox2;
    private final JComboBox comboBox3;
    private final JComboBox comboBox4;
    private final JComboBox comboBox5;
    private final JComboBox comboBox6;
    private final JLabel label1;
    private final JLabel label2;
    private final JLabel label3;
    private final JLabel label4;
    private final JLabel label5;
    private final JLabel label6;
    private final JLabel label7;
    private final JLabel label8;
    private final JPanel panel1;
    private final JPanel panel2;
    private final JPanel panel3;
    private final JPanel panel4;
    private final JPanel panel5;
    private final JPanel panel6;
    private final JPanel panel7;
    private final JPanel panel8;
    private final JLabel jLabel10;
    private final JPanel jPanel20;
    private final JLabel jLabel20;
    private final JPanel jPanel30;
    private final JLabel jLabel30;
    private final JTextField jTextField10;
    private final JLabel jLabel40;
    private final JTextField jTextField20;
    private final JLabel jLabel50;
    private final JTextField jTextField30;
    private final JLabel jLabel60;
    private final JLabel success;
    private final JSpinner jSpinner10;
    private final JButton jButton20;
    private final JButton jButton21;
    private final JTabbedPane tabbedPane;
    BusDAO bd = new BusDAO();
    Calendar cal = Calendar.getInstance();
    private final String username = "webuser";
    private final String password = "webuser";
    private final String dbUrl = "jdbc:postgresql://localhost:5432/ticket";
    private final String jdbcClassName = "org.postgresql.Driver";
    Connection con = null;

    JButton[] buttons;
    PreparedStatement pr = null;
    private final JLabel jLabel29;
    private final JTextField jTextField11;
    private final JLabel jLabel28;
    private final JTextField jTextField12;
    private final JLabel jLabel26;
    private final JTextField jTextField14;

    /**
     * @param args the command line arguments
     */
    BTicket() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcClassName);
        con = DriverManager.getConnection(dbUrl, "webuser", "webuser");

        button1 = new JButton("Search");
        button2 = new JButton("More information");

        comboBox1 = new JComboBox(bd.getSourceList().toArray());
        comboBox2 = new JComboBox(bd.getDestinationList().toArray());
        comboBox3 = new JComboBox();

        for (int i = 0; i < 28; ++i) {
            comboBox3.addItem(new DateItem(cal.getTime()));
            cal.add(Calendar.DATE, 1);
        }

        comboBox4 = new JComboBox();
        comboBox5 = new JComboBox();
        comboBox6 = new JComboBox();
        label1 = new JLabel("Bus Managing System");
        label2 = new JLabel("Source Station");
        label3 = new JLabel("Destintion");
        label4 = new JLabel("Date");
        label5 = new JLabel("Month");
        label6 = new JLabel("Year");
        label7 = new JLabel("Choose bus and time");
        label8 = new JLabel("Today non available bus, enter another day and location");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        panel7 = new JPanel();
        panel8 = new JPanel();

        jLabel29 = new JLabel();
        jTextField11 = new JTextField();
        jLabel28 = new JLabel();
        jTextField12 = new JTextField();
        jLabel26 = new JLabel();
        jTextField14 = new JTextField();

        jLabel10 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jSpinner10 = new javax.swing.JSpinner();
        jButton20 = new JButton();
        jButton21 = new JButton();
        tabbedPane = new JTabbedPane();
        frame = new JFrame("Bus Managing System");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        panel2.setMaximumSize(new Dimension(450, 0));
        label1.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        label1.setFont(new Font("Verdana", Font.BOLD, 30));
        panel2.add(label1);
        JSeparator separator = new JSeparator();
        separator.setForeground(Color.GRAY);
        panel2.add(separator, BorderLayout.SOUTH);

        panel1.add(panel2);
        panel1.add(tabbedPane);

        tabbedPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tabbedPane.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        tabbedPane.setToolTipText("");
        tabbedPane.addTab("Search", panel3);

        panel3.setLayout(new GridLayout(3, 1));
        ///////////////
        panel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel4.add(label2);
        panel4.add(comboBox1);
        panel4.add(label3);
        panel4.add(comboBox2);
        panel4.add(label4);
        panel4.add(comboBox3);

        panel4.add(button1, BorderLayout.CENTER);
        panel3.add(panel4);
        /////////////////

        panel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel5.add(label7);
        panel5.add(comboBox6);
        panel5.add(button2);
        panel5.setVisible(false);

        panel3.add(panel5);

        ///////////////////11
        panel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
        panel6.add(panel8);

        panel6.setVisible(false);
        panel3.add(panel6);
        tabbedPane.addTab("CRUD", panel7);
        panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
        jLabel10.setText("Delete tickets");
        panel7.add(jLabel10);
        jButton21.setText("Retrieve tickets");
        jButton21.addActionListener(this);
        panel7.add(jButton21);
        panel7.add(jPanel20);
        jLabel20.setText("Add Bus");
        success = new JLabel();
        success.setFont(new Font("Verdana", Font.BOLD, 23));
        panel7.add(jLabel20);

        jLabel29.setText("Bus number");
        jPanel30.add(jLabel29);
        jTextField11.setMinimumSize(new java.awt.Dimension(60, 20));
        jTextField11.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel30.add(jTextField11);

        jLabel28.setText("Seats");
        jPanel30.add(jLabel28);
        jTextField12.setMinimumSize(new java.awt.Dimension(60, 20));
        jTextField12.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel30.add(jTextField12);

        jLabel26.setText("Price");
        jPanel30.add(jLabel26);
        jTextField14.setMinimumSize(new java.awt.Dimension(60, 20));
        jTextField14.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel30.add(jTextField14);

        jLabel30.setText("Source");
        jPanel30.add(jLabel30);

        jTextField10.setMinimumSize(new java.awt.Dimension(60, 20));
        jTextField10.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel30.add(jTextField10);

        jLabel40.setText("Destination");
        jPanel30.add(jLabel40);

        jTextField20.setMinimumSize(new java.awt.Dimension(60, 20));
        jTextField20.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel30.add(jTextField20);

        jLabel50.setText("Type");
        jPanel30.add(jLabel50);

        jTextField30.setMinimumSize(new java.awt.Dimension(60, 20));
        jTextField30.setPreferredSize(new java.awt.Dimension(60, 20));
        jPanel30.add(jTextField30);

        jLabel60.setText("Time");
        jPanel30.add(jLabel60);

        jSpinner10.setModel(new javax.swing.SpinnerDateModel());
        jPanel30.add(jSpinner10);
        jButton20.setText("OK");
        jButton20.addActionListener(this);
        jPanel30.add(jButton20);
        panel7.add(jPanel30);
//panel7.add(frame);
        button2.addActionListener(this);
        button1.addActionListener(this);
        frame.add(panel1);
        frame.setSize(1000, 610);
        frame.setVisible(true);

    }
    public static BTicket bticket = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // init();
        // TODO code application logic here
        bticket = new BTicket();

    }

    public boolean isReserved(ArrayList<Ticket> ticketes, int seat, String busInf) {
        for (int i = 0; i < ticketes.size(); i++) {
            if (ticketes.get(i).getSeat() == seat && busInf.equals(ticketes.get(i).getBusInf())) {
                return true;
            }
        }
        return false;
    }
    boolean actionSet = false;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton20) {
            addBus();
            success.setText("You have successfully added the bus!");
            jPanel30.add(success);

        } else if (e.getSource() == jButton21) {
            getTickets();
        } else {
            showTickets(e);
        }
    }

    public void enableComponents(Container container, boolean enable) {
        Component[] components = container.getComponents();
        for (Component component : components) {
            component.setEnabled(enable);
            if (component instanceof Container) {
                enableComponents((Container) component, enable);
            }
        }
    }

    void showTickets(ActionEvent e) {

        String source = comboBox1.getSelectedItem().toString();
        String destination = comboBox2.getSelectedItem().toString();
        String date = comboBox3.getSelectedItem().toString();
        final ArrayList<Bus> buses = bd.getBusList();
        if (e.getSource() == button1) {
          //  DelByDate();
            comboBox6.removeAllItems();

            for (int i = 0; i < buses.size(); i++) {

                if (!source.equals(buses.get(i).getSource_station()) || !destination.equals(buses.get(i).getDestination()) || !date.equals(buses.get(i).getEvent_date())) {
                    panel4.add(label8);
                    label8.setFont(new Font("Verdana", Font.BOLD, 23));
                    label8.setForeground(Color.red);
                } else {
                    panel5.setVisible(true);
                    comboBox6.addItem(buses.get(i).getBus_number() + " " + buses.get(i).getBus_type() + " " + buses.get(i).getEvent_time());
                    label8.setVisible(false);
                }
            }

        }

        final String busInf = comboBox6.getSelectedItem().toString();;
        /* try{
         busInf = comboBox6.getSelectedItem().toString();
         } catch(Exception ee){
            
         }*/

        //  if(busInf != null){
        if (!actionSet) {
            actionSet = true;
            button2.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    panel6.setVisible(true);

                    for (int i = 0; i < buses.size(); i++) {

                        if (!busInf.equals(buses.get(i).toString())) {
                            continue;
                        }

                        buttons = new JButton[buses.get(i).getSeat_max()];
                        panel8.removeAll();
                        final ArrayList<Ticket> ticketes = bd.getTicketList();
                        for (int j = 0; j < buttons.length; j++) {
                            buttons[j] = new JButton("" + (1 + j));
                            panel8.add(buttons[j]);

                            System.out.println(ticketes.size());
//                    System.out.println(buses.get(i).getSeat_max());
                            int next_tic = ticketes.size() + 1;
                            //  for (int n = 0; n < ticketes.size(); n++) {
//                        System.out.println(busInf);
//                        System.out.println(ticketes.get(n).getBusInf());
//                        System.out.println(ticketes.get(n).getSeat());

                            if (isReserved(ticketes, j + 1, busInf)) {
                                // System.out.println(ticketes.get(j).getSeat());
                                buttons[j].setBackground(Color.RED);
                                buttons[j].enable();

                            } else {

                                buttons[j].setBackground(Color.GREEN);
                                final int g = j + 1;
                                final int d = i + 1;
                                final String text = "Tickets from  " + buses.get(i).getSource_station() + "  to  " + buses.get(i).getDestination() + "\n"
                                        + "Bus number " + buses.get(i).getBus_number() + "\n" + "Bus type   " + buses.get(i).getBus_type() + "\n Date "
                                        + buses.get(i).getEvent_date() + "   in " + buses.get(i).getEvent_time() + "\n Seat number " + g + "\n" + "Price " + buses.get(i).getPrice() + "\n"
                                        + "HAVE A GOOD TRIP!";
                                buttons[j].addActionListener(new ActionListener() {

                                    @Override
                                    public void actionPerformed(ActionEvent ae) {
                                        System.out.println(g);
                                        int f = JOptionPane.showConfirmDialog(null, "Are you sure that you want to book seat?", "Confirm", JOptionPane.YES_NO_OPTION);

                                        System.out.println(g + " " + d);
                                        if (f == 0) {
                                            //  frame.setVisible(false);
                                            int next_tic = bd.getTicketList().size() + 1;
                                            File file = null;
                                            try {

                                                pr = con.prepareStatement("INSERT INTO ticket (ticket_id, seat, bus_id) VALUES (?, ?,? )");
                                                pr.setInt(1, next_tic);
                                                pr.setInt(2, g);
                                                pr.setInt(3, d);
                                                pr.executeUpdate();

                                            } catch (SQLException se) {
                                                JOptionPane.showMessageDialog(null, se.toString(), "Error in SQL", 0);
                                            }
                                            try {
                                                frame.setEnabled(false);
                                                frame.setTitle("WAIT PLEASE");
                                                new CreatePDFA().print("ticket_" + next_tic + ".pdf", text);
                                                frame.setEnabled(true);
                                                frame.setTitle("Bus Managing System");
                                            } catch (Exception e) {
                                                frame.setEnabled(true);
                                                frame.setTitle("Bus Managing System");
                                                return;
                                            }
                                            buttons[g - 1].setBackground(Color.RED);
                                            buttons[g - 1].disable();
                                            buttons[g - 1].removeActionListener(this);
                                        } else {
                                            frame.setVisible(true);

                                        }

                                    }
                                });

                            }
                            //  }

                        }
                    }
                }
            });
        }
        //}
        System.out.println("END");
    }

    private void addBus() {
        //11 12 14 10 20 30 

        bd.addBus(jTextField11.getText(), jTextField12.getText(), jTextField14.getText(),
                jTextField10.getText(), jTextField20.getText(), jTextField30.getText(), (Date) jSpinner10.getValue());
    }

    private void getTickets() {
        ArrayList<Ticket> tickets = bd.getTicketList();
        jPanel20.removeAll();
        for (int i = 0; i < tickets.size(); i++) {
            final JButton button = new JButton();
            button.setText(tickets.get(i).getBus_number() + " "
                    + tickets.get(i).getBus_type() + " "
                    + tickets.get(i).getEvent_time()
                    + " : " + tickets.get(i).getSeat());
            button.setName(tickets.get(i).getId().toString());
            button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    int f = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (f == 0) {
                        bd.removeTicketById(Integer.parseInt(button.getName()));
                        jPanel20.remove(button);
                    }

                    frame.repaint();
                }
            });
            jPanel20.add(button);
        }
        frame.repaint();
    }
/*
    private void DelByDate() {
        ArrayList<Bus> debus = bd.getBusList();
        int date1 = (Calendar.DATE -1);
        
        System.out.println(date1);
         
        
        for (int i = 0; i < debus.size(); i++) {
            if (debus.get(i).getEvent_date() == String.valueOf(Calendar.DATE-1)) {
                try {

                    pr = con.prepareStatement("DELETE FROM bus where event_date=? ");
                    pr.setString(1, debus.get(i).getEvent_date());

                    pr.executeUpdate();

                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, se.toString(), "Error in SQL", 0);
                }
            }
        }

    }*/
}
