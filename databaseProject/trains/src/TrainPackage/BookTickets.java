package TrainPackage;
import java.sql.*;
import java.util.Scanner;
import java.time.LocalDate;
public class BookTickets {
    Scanner input=new Scanner(System.in);
    public boolean book(){
        int i,c,t,Seats=0,flag=0;
        float amount=0,b=0,e=0,s=0;
        String d="",C="";
        System.out.println("Please Provide train ID which you want to book");
        System.out.println("Enter Id:\t");
        i=input.nextInt();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q = "select * from add_train";
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(q);
            while (res.next()) {
                int id = res.getInt(1);
                String n = res.getString(2);
                int no = res.getInt(3);
                int seats = res.getInt(4);
                String des = res.getString(5);
                String tim = res.getString(6);
                String A_time=res.getString(7);
                float B_ticket=res.getFloat(8);
                float E_ticket=res.getFloat(9);
                float S_ticket=res.getFloat(10);
                if (id == i) {
                    System.out.println("Train id\t" + id);
                    System.out.println("Train name\t" + n);
                    System.out.println("Train no\t" + no);
                    System.out.println("Total Seats\t" + seats);
                    System.out.println("Destination\t" + des);
                    System.out.println("Timings of departure\t" + tim);
                    System.out.println("Time of Arrival\t" + A_time);
                    System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                    System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                    System.out.println("Second Class Ticket Price:\t"+S_ticket);
                    b=B_ticket;
                    e=E_ticket;
                    s=S_ticket;
                    Seats=seats;
                    d=des;
                    flag=1;
                    if(seats==0){
                        System.out.println("SORRY!!!!!SEATS ARE FULL YOUR TICKET CAN'T BE BOOKED!!!!..............");
                        return false;
                    }
                }
            }
            if(flag==1) {
                char b_T;
                System.out.println("Departure Date :\t" + LocalDate.now());
                System.out.println("Book No of Tickets");
                t = input.nextInt();
                System.out.println("Select Class 1.Buisness\t2.Economy\t3.Second\nEnter From (1,2,3) ");
                c=input.nextInt();
                switch (c){
                    case 1:
                        amount= b *t;
                        System.out.println("Total Amount to be payed:\t"+amount);
                        C="Buisness";
                        break;
                    case 2:
                        amount=e*t;
                        System.out.println("Total Amount to be payed :\t"+amount);
                        C="Economy";
                        break;
                    case 3:
                        amount=s*t;
                        System.out.println(" Total Amount to be payed:\t"+amount);
                        C="Second";
                        break;
                    default:
                        System.out.println("\tEnter From (1,2,3)!!!!!!!!");
                        break;
                }
                System.out.println("******---------BOOK TICKET--??????(Y/N)-----**********");
                b_T =input.next().charAt(0);
                if(b_T =='y'|| b_T =='Y'){
                    System.out.println("----------Ticket Booked Successfully...........");
                    String que="update add_train set Total_seating_capacity=? where id_train="+i;
                    PreparedStatement stmt= con.prepareStatement(que);
                    stmt.setInt(1,(Seats-t));
                    stmt.executeUpdate();
                    Booking_Report BOOK=new Booking_Report(i,d,C,t,amount);
                    Booking_Report.report();
                    return true;
                }
                else {
                    System.out.println("-------Ticket NOT Booked!!!!!!!!!!!!!");
                    return false;
                }
            }
        }
        catch (Exception E){
           E.printStackTrace();
           return false;
        }
        if(flag==0){
            char a;
            System.out.println("-----------YOUR PROVIDED TRAIN ID IS NOT CORRECT!!!!!!!!!!!");
            System.out.println("\tWould You Like To search Trains(Y/N)?????");
            a=input.next().charAt(0);
            if(a=='Y'||a=='y'){
                Train.searchTrain();
                return false;
            }
        }
        return true;
    }
}
