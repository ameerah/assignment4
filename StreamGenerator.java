// Helper class; explicitly handles the queue and creates multiple streams (duplicates) for different modifications

import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StreamGenerator
{
   Queue patientQueue = new Queue(); // initial queue: one heap, one doctor
   Queue modQueue = null; // first modification queue: one heap, three doctors
   
   // three queues for three heaps, three doctors modification
   Queue redQueue = new Queue();
   Queue yellowQueue = new Queue();
   Queue greenQueue = new Queue();
   
   // method to make the queue
   public void createQueue()
   {
      // random number generators and their uses
      Random urgencyGenerator = new Random();
      int urgency;
      
      Random nameGenerator = new Random();
      int nameNum;
      
      Random surnameGenerator = new Random();
      int surnameNum;
      
      Random IDgenerator = new Random();
      int IDnum;
      
      // other patient info
      String name;
      String surname;
      
      Patient person = null;
      
      // creates 10 patients
      for (int i = 1; i <= 10; i++)
      {
         urgency = urgencyGenerator.nextInt(60) + 1; // assigns urgency of range 1 to 60
         nameNum = nameGenerator.nextInt(100); // picks an index from 0 to 99
         surnameNum = surnameGenerator.nextInt(100); // index 0 to 99
         IDnum = 10000 + IDgenerator.nextInt(90000); // 5 digit ID number
         
         /**
         Note: wrt to the urgency number choice - rather than inefficiently categorising and then sub-categorising
         I thought it would be more efficient to assign a wider range of numbers for varying degrees of urgency.
         An example: a brain haemorrhage could be like a 1 and a broken leg could be a 10. There's no arguing that
         both are urgent, i.e. "Red", but choosing a broader range of numbers for urgency assignment indicates urgency
         and deals with patient prioritisation in one number
         **/
         
         // searches in respective files at index randomly picked above for patient name / surname
         name = getName(nameNum, "names.txt");
         surname = getName(surnameNum, "surnames.txt");
         
         // creates instance of person
         person = new Patient(name, surname, IDnum, urgency);
         
         // inserts person into binary heap (queue)
         patientQueue.insertPatient(person);
      }
      
      // now the initial queue is formed, we can create the ones for the mods
      generateAdditionalQueues();
   }
   
   public void generateAdditionalQueues()
   {
      // One heap, three doctors mod
      // copy the array from the already instantiated queue; construct new queue with same array
      Patient[] clone = patientQueue.clonePatients();
      modQueue = new Queue(clone, 10);
      
      // so for every item in the clone, find its colour and insert it into a queue for that colour
      // the code below ensures it is inserted such that it keeps its binary heap structure and order
      // the heaps don't change in the previous modifications so we don't need to look at structure and order again
      for (int i = 0; i <= 9; i++)
      {
         if (clone[i].getColour() == "RED")
         {
            redQueue.insertPatient(clone[i]);
         }
         else if (clone[i].getColour() == "YELLOW")
         {
            yellowQueue.insertPatient(clone[i]);
         }
         else
         {
            greenQueue.insertPatient(clone[i]);
         }
      }      
   }
   
   public void treatPatients()
   {
      // calls a "treatPatient()" method which deletes one singular patient
      
      // ONE HEAP, ONE DOCTOR
      // iterates deletes every person in the list; delay faced after every person
      System.out.println("Illustration of One Heap / One Doctor method");
      for (int i = 0; i <= 9; i++)
      {
         System.out.println("_______________________________________________________________________________________________________");
         patientQueue.treatPatient();
         System.out.println("_______________________________________________________________________________________________________");
         patientQueue.printWaitlist(1);
         System.out.println("_______________________________________________________________________________________________________");
         
         //create delay here
      }
      
      // ONE HEAP, THREE DOCTORS
      // still iterates through every item, just calls three at a time before treatment "delay" in calling next batch
      System.out.println("\n\n\nIllustration of One Heap / Three Doctors method");
      for (int i = 0; i <= 3; i++)
      {
         if (i <= 2)
         {
            System.out.println("_______________________________________________________________________________________________________");
            modQueue.treatPatient();
            modQueue.treatPatient();
            modQueue.treatPatient();
            System.out.println("_______________________________________________________________________________________________________");
            modQueue.printWaitlist(3);
            System.out.println("_______________________________________________________________________________________________________");
         }
         else
         {
            modQueue.treatPatient();
            System.out.println("_______________________________________________________________________________________________________");
         }
         
         // create delay here
      }
      
      // THREE HEAPS, THREE DOCTORS
      // treats the most immediate three patients (batch may branch over multiple heaps)
      // we would do this by iterating three nodes from the red heap then the yellow heap then the green heap
      // trivial to include this in the code when the results won't differ from one heap, three doctors
   }
   
   public String getName(int index, String searchFile)
   {
      // method for fetching a random name from the file, given an index
      
      Scanner fileInput = null;
      
      try
      {
         fileInput = new Scanner(new FileInputStream(searchFile));
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Oops! Sorry; something went wrong.");
      }
      
      String line = null;
      
      // iterates through every line in the list until it gets to the specified index
      for (int i = 0; i <= index; i++)
      {
         line = fileInput.nextLine().trim();
      }
      
      fileInput.close();
      
      return line;
   }
}