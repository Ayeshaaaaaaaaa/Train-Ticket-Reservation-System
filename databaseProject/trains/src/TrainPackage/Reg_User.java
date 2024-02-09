package TrainPackage;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg_User {
    static String n;
    static Scanner input=new Scanner(System.in);
    public static void reg(){
        String name,pass,Email;
        long mobile,CNIC;
        System.out.println("Enter Name:\t");
        name=input.next();
        System.out.println("Mobile No:\t");
        mobile=input.nextLong();
        System.out.println("Enter CNIC:\t");
        CNIC=input.nextLong();
        System.out.println("Enter Email adress:\t");
        Email=input.next();
        while (!validation.emailVerification(Email)){
            System.out.println("Enter Email adress:\t");
            Email=input.next();
        }
        System.out.println("Enter a strong password consisting of letters,symbols:\t");
        pass=input.next();
        while(!validation.PassVerification(pass)) {
            System.out.println("Enter a strong password consisting of letters,symbols:\t");
            pass=input.next();
        }
        try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q="insert into user_info (User_Name,Mobile_NO,password,email,CNIC) values(?,?,?,?,?)";
            PreparedStatement st= con.prepareStatement(q);
            st.setString(1,name);
            st.setInt(2, (int) mobile);
            st.setString(3,pass);
            st.setString(4,Email);
            st.setLong(5,CNIC);
            st.executeUpdate();
            System.out.println("Your Account has been created.....");
        }
        catch (Exception e){
            System.out.println("Some error occoured while registeration");
            e.printStackTrace();
        }
    }
    public static boolean Login(){
        String p;
        System.out.println("------To Login Enter UserName And Password------");
        System.out.print("\tUSER NAME:\t");
        n=input.next();
        System.out.print("\n\tPASSWORD:\t");
        p=input.next();
        setN(n);
        try{
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q="select User_Name,password from user_info";
            Statement st=con.createStatement();
            ResultSet res=st.executeQuery(q);
            while(res.next()){
                String name=res.getString(1);
                String password=res.getString(2);
                if(name.equals(n)&& password.equals(p)){
                    System.out.println("*****---------WELCOME USER------*********");
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
            System.out.println("---------INCORRECT USERNAME OR PASSWORD!!!!!!!!!!!!!!!!!");
            e.printStackTrace();
            return false;
        }
    }
    public static void setN(String n) {
        Reg_User.n = n;
    }
    public static String getN() {
        return n;
    }
    public static void UpdatePassword(){
        String pass;
        System.out.println("Enter Your New Password:\t");
        System.out.println("Enter a strong password consisting of letters,symbols:\t");
        pass=input.next();
        while(!validation.PassVerification(pass)) {
            System.out.println("Enter a strong password consisting of letters,symbols:\t");
            pass=input.next();
        }
        try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/trains", "root", "chemistry&34");
            String q="update user_info set password=? where User_Name="+Reg_User.getN();
            PreparedStatement s=con.prepareStatement(q);
            s.setString(1,pass);
            s.executeUpdate();
            System.out.println("\tPASSWORD UPDATED...........");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class validation{
    public static boolean emailVerification(String email){
        Pattern pattern=Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat= pattern.matcher(email);
        if(mat.matches()){
            return true;
        }
        else{
            System.out.println("not a valid Email Adress");
            return  false;
        }
    }
    public static boolean PassVerification(String password){
         String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
         Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
         Matcher matcher = pattern.matcher(password);
         if (matcher.matches()){
             return  true;
         }
         else
         {
             System.out.println("not a valid Password");
             return  false;
         }
    }
}