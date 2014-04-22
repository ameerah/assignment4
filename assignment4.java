// Assignment 4: simulate patient treatment at  hospital emegency roomm
// Driver class
// Ameerah Allie (ALLAME002)
// 15 April 2014

public class assignment4
{
   public static void main(String[] args)
   {
      // object that creates stream and handles stream
      StreamGenerator sg = new StreamGenerator();
      sg.createQueue();
      sg.treatPatients();
   }
}