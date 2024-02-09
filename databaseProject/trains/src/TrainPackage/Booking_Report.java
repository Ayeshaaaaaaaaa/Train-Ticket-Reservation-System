package TrainPackage;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Booking_Report {
    static int Trainid,tno;
    static String destination,tclass;
    static float amount;
    public Booking_Report(int Trainid, String destination,String tclass, int tno, float amount){
        this.Trainid = Trainid;
        this.tno=tno;
        this.destination=destination;
        this.tclass=tclass;
        this.amount=amount;
    }
    public static void report(){
        System.out.println("\n***************************************************************************************************************************");
        System.out.println("----------------------------------------------------BOOKING RECEIPT--------------------------------------------------------");
        System.out.print("User Name: "+Reg_User.getN() +"\tTrain ID: "+Trainid +"\t\tDestination: "+destination);
        System.out.println("\nTrain Class: "+tclass +"\tNumber of Tickets Booked: "+tno +"\tTotal Amount: "+amount+"\tReceipt generated at:  "+ LocalDateTime.now());
        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------");
        try{
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains","root","chemistry&34");
            String q="insert into bookings(UserName,Train_Id,Destination,classBooked,NoOfTickets,Total_Amount,DateTime) Values(?,?,?,?,?,?,?)";
            PreparedStatement st= con.prepareStatement(q);
            st.setString(1,Reg_User.getN());
            st.setInt(2,Trainid);
            st.setString(3,destination);
            st.setString(4,tclass);
            st.setInt(5,tno);
            st.setFloat(6,amount);
            st.setString(7, String.valueOf(LocalDateTime.now()));
            st.executeUpdate();
            System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
class Bookings{
    static Scanner input=new Scanner(System.in);
    public static void viewbookings(){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trains","root","chemistry&34");
            String q="select * from bookings";
            Statement st= con.createStatement();
            ResultSet res=st.executeQuery(q);
            while(res.next()){
                String U_N= res.getString(1);
                int ID= res.getInt(2);
                String des= res.getString(3);
                String C= res.getString(4);
                int No  = res.getInt(5);
                float amount= res.getFloat(6);
                String date= String.valueOf(res.getTimestamp(7));
                if(U_N.equals(Reg_User.getN())){
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                    System.out.print("User Name: "+Reg_User.getN() +"\t\tTrain ID: "+ID +"\t\tDestination: "+des);
                    System.out.println("\nTrain Class: "+C +"\tNumber of Tickets Booked: "+No +"\tTotal Amount: "+amount+"\tReceipt generated at:  "+ date);
                    System.out.println("----------------------------------------------------------------------------------------------------------------------------");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void CancelTickets(){
        char c;
        int seats=0,id=0,no=0;
        System.out.println("Are You Sure You Want to Cancel The Ticket(Y/N)................");
        c=input.next().charAt(0);
        if(c=='Y'||c=='y'){
        try{
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/trains","root","chemistry&34");
            String q="select Train_Id,NoOfTickets from bookings where UserName='"+Reg_User.getN()+"'";
            Statement s=con.createStatement();
            ResultSet res=s.executeQuery(q);
            while(res.next()){
                 id=res.getInt(1);
                 no= res.getInt(2);
            }
            q="select Total_seating_capacity from add_train where id_train="+id;
            res=s.executeQuery(q);
            while(res.next()){
                seats=res.getInt(1);
            }
            q="update add_train set Total_seating_capacity=? where id_train="+id;
            PreparedStatement st= con.prepareStatement(q);
            st.setInt(1,(seats+no));
            st.executeUpdate();
            q="delete from bookings where UserName=?";
            st=con.prepareStatement(q);
            st.setString(1,Reg_User.getN());
            st.executeUpdate();
            System.out.println("Booking Cancelled!!!!!!!!!!");

        }catch (Exception e){
            e.printStackTrace();
        }
        }else{
            System.out.println("\tBOOKING CANCELLATION STOPPED!!!!!");
        }
    }
}
class ViewBookings{
    static Scanner input=new Scanner(System.in);
    public static void searchBookings(){
        int a,i;
        System.out.println("Search Bookings by Train ID OR by UserName\nEnter 1 for Train Id And 2 for User Name");
        a=input.nextInt();
        switch (a){
            case 1:
                System.out.println("Enter Train_Id:\t");
                i=input.nextInt();
                try {
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains","root","chemistry&34");
                    String q="select * from bookings where Train_Id="+i;
                    Statement s=con.createStatement();
                    ResultSet res=s.executeQuery(q);
                    while(res.next()){
                        String U_N= res.getString(1);
                        int ID= res.getInt(2);
                        String des= res.getString(3);
                        String C= res.getString(4);
                        int No  = res.getInt(5);
                        float amount= res.getFloat(6);
                        String date= String.valueOf(res.getTimestamp(7));
                        System.out.print("User Name: "+U_N +"\t\tTrain ID: "+ID +"\t\tDestination: "+des);
                        System.out.println("\nTrain Class: "+C +"\tNumber of Tickets Booked: "+No +"\tTotal Amount: "+amount+"\tReceipt generated at:  "+ date);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case 2:{
                String s;
                System.out.println("Enter User_Name:\t");
                s=input.next();
                try {
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains","root","chemistry&34");
                    String q="select * from bookings where UserName='"+s+"'";
                    Statement st=con.createStatement();
                    ResultSet res=st.executeQuery(q);
                    while(res.next()){
                        String U_N= res.getString(1);
                        int ID= res.getInt(2);
                        String des= res.getString(3);
                        String C= res.getString(4);
                        int No  = res.getInt(5);
                        float amount= res.getFloat(6);
                        String date= String.valueOf(res.getTimestamp(7));
                        System.out.print("User Name: "+U_N +"\t\tTrain ID: "+ID +"\t\tDestination: "+des);
                        System.out.println("\nTrain Class: "+C +"\tNumber of Tickets Booked: "+No +"\tTotal Amount: "+amount+"\tReceipt generated at:  "+ date);
                        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;}
            default:
                break;
        }
    }
}