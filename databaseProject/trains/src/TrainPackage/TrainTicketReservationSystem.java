package TrainPackage;
import java.util.Scanner;
public class TrainTicketReservationSystem {
    static Scanner Input=new Scanner(System.in);
    public static void main(String args[]) {
        int a,b;
        String s;
        System.out.println("\t--------------------------------------------");
        System.out.println("\tWELCOME TO TRAIN TICKET RESERVATION SYSTEM");
        System.out.println("\t--------------------------------------------");
        System.out.println("\t1.ADMIN\t\t2.USER\n\tENTER 1 FOR ADMIN 2 FOR USER");
        a=Input.nextInt();
        switch (a){
            case 1:
                System.out.print("Enter Password:\t");
                s=Input.next();
                if(s.equals("Admin&123")){
                    System.out.println("\n\tACCESS GRANTED..........");
                    do{
                    System.out.println("\t1.TRAIN SECTION\n\t2.BOOKING SECTION\n\t3.LOG OUT");
                    b=Input.nextInt();
                    switch (b){
                        case 1:
                            TrainSection t=new TrainSection();
                            t.Train_Menu();
                            break;
                        case 2:
                            ViewBookings.searchBookings();
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Enter 1, 2 OR 3!!.........");
                            break;
                    }
                    }while(b!=3);
                }
                break;
            case 2:
                UserMenu U=new UserMenu();
                U.Menu();
                break;
            default:
                System.out.println("Enter 1 OR 2!!.........");
                break;
        }
    }
}
