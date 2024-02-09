package TrainPackage;
import java.sql.*;
import java.util.Scanner;

public class Train {
    static Scanner input=new Scanner(System.in);
    public static boolean InsertTrain(){
        String name,Destination,time,A_time;
        int train_No,Seats;
        float b,e,s;
        System.out.print("Train Number\t");
        train_No=input.nextInt();
        System.out.print("\nTrain Name:\t");
        name=input.next();
        System.out.print("\nTotal Seats/Seating Capacity:\t");
        Seats=input.nextInt();
        input.nextLine();
        System.out.print("\nEnter Destination:\t");
        Destination=input.nextLine();
        System.out.print("\nEnter Departure Timings Of Train (HH:MM:SS) :\t");
        time= input.next();
        System.out.print("\nEnter Arrival Timings Of Train (HH:MM:SS) :\t");
        A_time= input.next();
        System.out.print("Enter Price Of Buisness Class:\t");
        b=input.nextFloat();
        System.out.print("Enter Price Of Economy Class:\t");
        e=input.nextFloat();
        System.out.print("Enter Price Of Second Class:\t");
        s=input.nextFloat();
        try{
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q="insert into add_train(train_name,train_no,Total_seating_capacity,Destination,Timings,Arrival_time,B_Ticket,E_Ticket,S_Ticket) Values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt=con.prepareStatement(q);
            stmt.setString(1,name);
            stmt.setInt(2,train_No);
            stmt.setInt(3,Seats);
            stmt.setString(4,Destination);
            stmt.setString(5,time);
            stmt.setString(6,A_time);
            stmt.setFloat(6,b);
            stmt.setFloat(7,e);
            stmt.setFloat(8,s);
            stmt.executeUpdate();
            return true;
        }
        catch (Exception E)
        {
            E.printStackTrace();
            return false;
        }
    }
    public static boolean updateTrain(){
        int id,i=1;
        String s,update;
        System.out.println("To Update Train Provide Train Id:\t");
        id=input.nextInt();
        System.out.println("Mention Coloumn You want to Update");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            while (i != 0) {
                s = input.next();
                System.out.println("Enter Updated value:\t");
                if(s.equals("Destination")||s.equals("train_name")||s.equals("Timings")) {
                    update = input.nextLine();
                }
                else if(s.equals("id_train")||s.equals("train_no")||s.equals("Total_seating_capacity")){
                    update = String.valueOf(input.nextInt());
                }
                else{
                    System.out.println("Wrong coloumn name entered.....");
                    break;
                }
                String q = "update add_train set "+ s +"=? where id_train="+id;
                PreparedStatement st=con.prepareStatement(q);
                st.setString(1,update);
                st.executeUpdate();
                System.out.println("Update another coloumn?\nEnter Any no to continue OR Enter 0 to exit");
                i=input.nextInt();
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean deleteTrain(){
        int c, i;
        System.out.println("To Delete train enter train id or train number\n For train id Enter 1\n For train no Enter 2");
        c=input.nextInt();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            switch (c){
                case 1:{
                    System.out.println("Enter Train id:\t");
                    i = input.nextInt();
                    String q="delete from add_train where id_train=?";
                    PreparedStatement st=con.prepareStatement(q);
                    st.setInt(1, i);
                    st.executeUpdate();
                break; }
                case 2:{
                    System.out.println("Enter Train Number:\t");
                    i = input.nextInt();
                    String q="delete from add_train where id_train=?";
                    PreparedStatement st=con.prepareStatement(q);
                    st.setInt(1, i);
                    st.executeUpdate();
                break;}
                default:
                    System.out.println("Invalid Input");
                break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean trainInfo(){
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q="select * from add_train";
            Statement st=con.createStatement();
            ResultSet res= st.executeQuery(q);
            while (res.next()){
                int id=res.getInt(1);
                String n=res.getString(2);
                int no=res.getInt(3);
                int seats=res.getInt(4);
                String des=res.getString(5);
                String tim=res.getString(6);
                String A_time=res.getString(7);
                float B_ticket=res.getFloat(8);
                float E_ticket=res.getFloat(9);
                float S_ticket=res.getFloat(10);
                System.out.println("Train id\t"+id);
                System.out.println("Train name\t"+n);
                System.out.println("Train no\t"+no);
                System.out.println("Total Seats\t"+seats);
                System.out.println("Destination\t"+des);
                System.out.println("Departure Timings are\t"+tim);
                System.out.println("Time of Arrival\t" + A_time);
                System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                System.out.println("Second Class Ticket Price:\t"+S_ticket);
                System.out.println("********************************\n");
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean searchTrain(){
        int a,c;
        String s;
        System.out.println("Search trains by 1.ID\t2.Name\t3.Number\t4.Seating Capacity\t5.Destination");
        System.out.println("Enter From 1,2,3,4 OR 5");
        try {
            a = input.nextInt();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q = "select * from add_train";
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery(q);
            switch (a) {
                case 1: {
                    System.out.println("Enter Train ID :");
                    c = input.nextInt();
                    while (res.next()) {
                        int id = res.getInt(1);
                        String n = res.getString(2);
                        int no = res.getInt(3);
                        int seats = res.getInt(4);
                        String des = res.getString(5);
                        String tim=res.getString(6);
                        if (id == c) {
                            System.out.println("Train id\t" + id);
                            System.out.println("Train name\t" + n);
                            System.out.println("Train no\t" + no);
                            System.out.println("Total Seats\t" + seats);
                            System.out.println("Destination\t" + des);
                            System.out.println("Timings are\t"+tim);
                            String A_time=res.getString(7);
                            float B_ticket=res.getFloat(8);
                            float E_ticket=res.getFloat(9);
                            float S_ticket=res.getFloat(10);
                            System.out.println("Time of Arrival\t" + A_time);
                            System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                            System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                            System.out.println("Second Class Ticket Price:\t"+S_ticket);
                            System.out.println("********************************\n");
                            return true;
                        }
                    }
                    break;}
                case 2: {
                    System.out.println("Enter Train Name :");
                    s = input.next();
                    while (res.next()) {
                        int id = res.getInt(1);
                        String n = res.getString(2);
                        int no = res.getInt(3);
                        int seats = res.getInt(4);
                        String des = res.getString(5);
                        String tim=res.getString(6);
                        String A_time=res.getString(7);
                        float B_ticket=res.getFloat(8);
                        float E_ticket=res.getFloat(9);
                        float S_ticket=res.getFloat(10);
                        if (n.equals(s)) {
                            System.out.println("Train id\t" + id);
                            System.out.println("Train name\t" + n);
                            System.out.println("Train no\t" + no);
                            System.out.println("Total Seats\t" + seats);
                            System.out.println("Destination\t" + des);
                            System.out.println("Timings are\t"+tim);
                            System.out.println("Time of Arrival\t" + A_time);
                            System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                            System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                            System.out.println("Second Class Ticket Price:\t"+S_ticket);
                            System.out.println("********************************\n");
                            return true;
                        }
                    }
                    break;}
                    case 3: {
                        System.out.println("Enter Train Number :");
                        c = input.nextInt();
                        while (res.next()) {
                            int id = res.getInt(1);
                            String n = res.getString(2);
                            int no = res.getInt(3);
                            int seats = res.getInt(4);
                            String des = res.getString(5);
                            String tim=res.getString(6);
                            String A_time=res.getString(7);
                            float B_ticket=res.getFloat(8);
                            float E_ticket=res.getFloat(9);
                            float S_ticket=res.getFloat(10);
                            if (no == c) {
                                System.out.println("Train id\t" + id);
                                System.out.println("Train name\t" + n);
                                System.out.println("Train no\t" + no);
                                System.out.println("Total Seats\t" + seats);
                                System.out.println("Destination\t" + des);
                                System.out.println("Timings are\t"+tim);
                                System.out.println("Time of Arrival\t" + A_time);
                                System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                                System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                                System.out.println("Second Class Ticket Price:\t"+S_ticket);
                                System.out.println("********************************\n");
                                return true;
                            }
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("Enter Seats :");
                        c = input.nextInt();
                        while (res.next()) {
                            int id = res.getInt(1);
                            String n = res.getString(2);
                            int no = res.getInt(3);
                            int seats = res.getInt(4);
                            String des = res.getString(5);
                            String tim=res.getString(6);
                            String A_time=res.getString(7);
                            float B_ticket=res.getFloat(8);
                            float E_ticket=res.getFloat(9);
                            float S_ticket=res.getFloat(10);
                            if (seats == c) {
                                System.out.println("Train id\t" + id);
                                System.out.println("Train name\t" + n);
                                System.out.println("Train no\t" + no);
                                System.out.println("Total Seats\t" + seats);
                                System.out.println("Destination\t" + des);
                                System.out.println("Timings are\t"+tim);
                                System.out.println("Time of Arrival\t" + A_time);
                                System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                                System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                                System.out.println("Second Class Ticket Price:\t"+S_ticket);
                                System.out.println("********************************\n");
                                return true;
                            }
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Enter Destination :");
                        s = input.next();
                        while (res.next()) {
                            int id = res.getInt(1);
                            String n = res.getString(2);
                            int no = res.getInt(3);
                            int seats = res.getInt(4);
                            String des = res.getString(5);
                            String tim=res.getString(6);
                            String A_time=res.getString(7);
                            float B_ticket=res.getFloat(8);
                            float E_ticket=res.getFloat(9);
                            float S_ticket=res.getFloat(10);
                            if (des.equals(s)) {
                                System.out.println("Train id\t" + id);
                                System.out.println("Train name\t" + n);
                                System.out.println("Train no\t" + no);
                                System.out.println("Total Seats\t" + seats);
                                System.out.println("Destination\t" + des);
                                System.out.println("Timings are\t"+tim);
                                System.out.println("Time of Arrival\t" + A_time);
                                System.out.println("Buisness Class Ticket Price:\t"+B_ticket);
                                System.out.println("Economy  Class Ticket Price:\t"+E_ticket);
                                System.out.println("Second Class Ticket Price:\t"+S_ticket);
                                System.out.println("********************************\n");
                                return true;
                            }
                        }
                        break;
                    }
                    default:
                        System.out.println("Enter From 1,2,3,4 OR 5");
                }
            }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
