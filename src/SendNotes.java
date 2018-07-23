/*Brent Vaalburg
CSCI430
Week 3 Homework
Java Notes Player with Linux Piping support
Dependencies : Java 8, Jfugue (included in /jars folder), Linux (mkfifo command used to make named pipe)
*/
import java.util.Scanner;
import java.io.RandomAccessFile;

public class SendNotes {

    //create new scanner object to take user input
    static Scanner scan = new Scanner(System.in);


    public static void main(String[] args){

        try {


            //random access file for writing to the <pipe_name>
            RandomAccessFile pipe = new RandomAccessFile(
                    //path to <pipe_name> made with Linux mkfifo command
                    "/home/sqaresandcubes/notesPipe", "rw");

            while(true){

                //Ask user for notes to play
                System.out.print("Enter notes to play: \n");

                //scan for input and set to String notes variable
                String notes = scan.nextLine();

                //give user message of input
                System.out.println("Playing: " + notes + "\n");

                //write to pipe
                pipe.write(notes.getBytes());

            }


        }

        //catch all exceptions and print to console
        catch (Exception e){

            e.printStackTrace();

        }

    }

}
