/*Brent Vaalburg
CSCI430
Week 3 Homework
Java Notes Player with Linux Piping support
Dependencies : Java 8, Jfugue (included in /jars folder), Linux (mkfifo command used to make named pipe)

********Instructions**************
* 1.  Please start PlayNotes application first and read instructions contained within.
* 2.  Start this application.
* 3.  Enter Notes to play , space delimited (ie. A B C D).  Advanced options (see below).
*
* Notes/Tones are represented by their respected letter designations.
* For Example A B C D E , you will be delimiting them with spaces.
* To set the duration of the Tone/Note, you add a 'q' for each quarter note time. For example Aqqq for 3/4 time of note A.
* Full input example below (a typical scale with different timed notes):
* Cqq Dqqqq Eq Fqqq Gqqqq Aqq Bqqq Cqqqq
*/
import java.util.Scanner;
import java.io.RandomAccessFile;

public class SendNotes {


    public static void main(String[] args){

        RandomAccessFile pipe;

        //create new scanner object to take user input
        Scanner scan = new Scanner(System.in);

        String notes;

        System.out.println("Have you started the PlayNotes application (y/n)? : ");

        String answer = scan.nextLine();

        if (answer.equals("y")) {

            try {

                while (true) {
                    //random access open file for writing to the <pipe_name>
                    pipe = new RandomAccessFile(
                            //path to <pipe_name> made with Linux mkfifo command
                            "/usr2/c_bvaalb/notesPipe", "rw");

                    //Ask user for notes to play
                    System.out.println("Enter notes to play: ");

                    //scan for input and set to String notes variable
                    notes = scan.nextLine();

                    //give user message of input
                    System.out.println("\nPlaying: " + notes + "\n");

                    //write to pipe
                    pipe.write(notes.getBytes());

                    //**important** pipe must be closed for PlayNotes to read the pipe
                    pipe.close();

                    //ask user if they want to play notes otherwise exit program
                    System.out.println("Would you like to play more notes (y/n)? : ");

                    String continueyn = scan.nextLine();

                    if (continueyn.equals("y")){

                        continue;
                    }

                    else{

                        System.out.println("Goodbye!");
                        break;
                    }

                }

            }

            //catch all exceptions and print to console
            catch (Exception e) {

                e.printStackTrace();

            }

        }

        else {

            //tell user to start PlayNotes application and come back later
            System.out.print("Goodbye, please come back and start this application once you have started PlayNotes");

        }

    }

}
