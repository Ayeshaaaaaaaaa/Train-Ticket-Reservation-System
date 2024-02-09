package TrainPackage;
import java.util.Scanner;
public class TrainSection {
    Scanner Input=new Scanner(System.in);
    public void Train_Menu(){
        int c,a;
        System.out.println("----------------------------------------------------------------------");
        System.out.println("**********************************************************************");
        System.out.println("''''''''''''''''''''Welcome to train Section'''''''''''''''''''''''''''\n");
        do {
            System.out.println("1.Add Train\t2.Delete train\t3.Update train\t4.Search trains\t5.See all trains\t5.Exit");
            c= Input.nextInt();
            switch (c){
                case 1:{
                    if(Train.InsertTrain()==true){
                        System.out.println("'''TRAIN ADDED SUCCESSFULLY''''");
                    }
                    else{
                        System.out.println("Something went wrong..........");
                    }
                break;}
                case 2:{
                    if(Train.deleteTrain()==true){
                        System.out.println("'''TRAIN DELETED SUCCESSFULLY''''");
                    }
                    else{
                        System.out.println("Something went wrong..........");
                    }
                break;}
                case 3:{
                    if(Train.updateTrain()==true){
                        System.out.println("'''TRAIN UPDATED SUCCESSFULLY''''");
                    }
                    else{
                        System.out.println("Something went wrong..........");
                    }
                break;}
                case 4:{
                    if(Train.searchTrain()==true){
                        System.out.println("'''TRAIN SEARCHED SUCCESSFULLY''''");
                    }
                    else{
                        System.out.println("Something went wrong..........");
                    }
                break;}
                case 5:{
                    if(Train.trainInfo()==true){
                        System.out.println("'''TRAINS DISPLAYED SUCCESSFULLY''''");
                    }
                    else{
                        System.out.println("Something went wrong..........");
                    }
                break;}
                default:
                    System.out.println("****INVALID INPUT------------!!!!!!!!!!");
            }
            System.out.println("To Exit TRAIN MENU Press 0 \nTo Continue press any number");
            a= Input.nextInt();
        }while(a!=0);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("**********************************************************************");
    }
}
