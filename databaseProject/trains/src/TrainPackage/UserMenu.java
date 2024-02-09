package TrainPackage;
import java.util.Scanner;

public class UserMenu {
    Scanner input=new Scanner(System.in);
    public void Menu (){
        int c,a,q;
        System.out.println("1.Donot have a account?/Register\n2.Login");
        c=input.nextInt();
        switch (c) {
            case 1:
                Reg_User.reg();
            case 2:{
                if(Reg_User.Login()==true){
                    do{
                    System.out.println("\t1.BOOK TICKET\n\t2.SEARCH TRAINS\n\t3.VIEW TRAINS\n\t4.CANCEL TICKET\n\t5.VIEW BOOKINGS\n\t6.UPDATE/CHANGE PASSWORD");
                    a=input.nextInt();
                    switch (a){
                        case 1:
                            BookTickets B=new BookTickets();
                            B.book();
                            break;
                        case 2:
                            Train.searchTrain();
                            break;
                        case 3:
                            Train.trainInfo();
                            break;
                        case 4:
                            Bookings.CancelTickets();
                            break;
                        case 5:
                            Bookings.viewbookings();
                            break;
                        case 6:
                            Reg_User.UpdatePassword();
                            break;
                        default:
                            System.out.println("\tERROR:\tEnter Correct Option!!!!!!!!");
                            break;
                    }
                    System.out.println("-----------PRESS 0 TO EXIT/LOGOUT.......................");
                    q=input.nextInt();
                    }while(q!=0);
                }
            break;}
            default:
                System.out.println("-----INVALID INPUT-!!!!!!!!!!111");
            break;
        }
    }
}