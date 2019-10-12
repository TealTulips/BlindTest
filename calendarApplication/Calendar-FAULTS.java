/** File name: Calendar-FAULTS.java
 *  Date:      14-August-2019
 *  Author:    Original author unknown, from an introductory programming class at Princeton
 *  Update August 2019 
 *  Changed inputs from arguments to command line
 *  Update August 2019 
 *  Changed several public methods to private
 *  Cleaned up formatting
 */

import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac Calendar.java
 *  Execution:    java Calendar month year
 *
 *  This program reads the month and year from the command line
 *  and prints a calendar for that month.
 *
 *  % java Calendar
 *  Enter month: 7
 *  Enter year: 2015
 *   July 2005
 *   S  M  T  W Th  F  S
 *                  1  2
 *   3  4  5  6  7  8  9
 *  10 11 12 13 14 15 16
 *  17 18 19 20 21 22 23
 *  24 25 26 27 28 29 30
 *  31
 *
 ******************************************************************************/

/* FAULTY * This is a faulty version. */
/* FAULTY * the string FAULTY documents the bugs. */
/* FAULTY * grep -v Calendar-FAULTS.java to remove the comments and leave the faults. */
/* FAULTY * Calendar-ORIG.java is the original with only fault #1 */
/* FAULTY * Line numbers in known faults refers to Calendar-ORIG.java */
/* FAULTY * Known faults: */
/* FAULTY *   1. No input validation (negative numbers, strings, etc.)--This was how it came */
/* FAULTY *   2. Line 55: Change "&&" to "||" */
/* FAULTY *   3. Line 85: Change value of one month */
/* FAULTY *   4. Line 46: Broken computation in weekDay(), "/ 10" should be "/ 12" */
/* FAULTY *   5. Line 89: Change '29' to '30' */

public class Calendar
{
   /***************************************************************************
    *  Given the month, day, and year, return which day
    *  of the week it falls on according to the Gregorian calendar.
    *  For month, use 1 for January, 2 for February, and so forth.
    *  Returns 0 for Sunday, 1 for Monday, and so forth.
    ***************************************************************************/

   // compute weekday based on the day of the month
   // Sunday is 0, Monday is 1, ... Saturday is 6
   private static int weekDay(int month, int day, int year) {
/* FAULTY * fault 4, should divide by 12, not 10 */
      int y = year - (14 - month) / 10;
      int x = y + y/4 - y/100 + y/400;
      int m = month + 12 * ((14 - month) / 12) - 2;
      int wd = (day + x + (31*m)/12) % 7;
      return wd;
   }

   // return true if the given year is a leap year
   private static boolean isLeapYear(int year) {
/* FAULTY * fault 2, should be "&&", not "||" */
      if ((year % 4 == 0) || (year % 100 != 0))
         return true;
      if (year % 400 == 0)
         return true;
      return false;
   }

   // Read and return a string input from the command line
   private static String readInput(Scanner sc, String arg) {
      System.out.print("Enter "+arg+": "); /* CLI */
      return(sc.next()); /* CLI */
   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in); /* CLI */

      // Get month and year from user
      int month = Integer.parseInt(readInput(sc, "month")); /* CLI */ // month
      int year  = Integer.parseInt(readInput(sc, "year"));  /* CLI */ // year

      // months[i] = name of month i
      String[] months = {
            "",                               // leave empty so that months[1] = "January"
            "January", "February", "March",
            "April",   "May",      "June",
            "July",    "August",   "September",
            "October", "November", "December"
      };

      // days[i] = number of days in month i (skip month 0)
/* FAULTY * fault 3, August should be 31, not 30 */
      int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 30, 30, 31, 30, 31 };

      // check for leap year
      if (month == 2 && isLeapYear(year))
/* FAULTY * fault 5, should divide be 29, not 30 */
         days[month] = 30;

      // print calendar header
      System.out.println("   " + months[month] + " " + year);
      System.out.println(" S  M Tu  W Th  F  S");

      // starting day of the week
      int wd = weekDay(month, 1, year);
      // print the calendar
      // space over for the first day
      for (int i = 0; i < wd; i++)
         System.out.print("   ");
      for (int i = 1; i <= days[month]; i++) {
         System.out.printf("%2d ", i);
         if (((i + wd) % 7 == 0) || (i == days[month]))
            System.out.println();
      }
   }
}  // end class
