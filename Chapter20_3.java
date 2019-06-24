/*********************************************************************************************************************
 *********************************************************************************************************************
 *****                                         Chapter 20: Problem 3                                             *****
 *****___________________________________________________________________________________________________________*****
 *****3. Return to your MyDate class from programming assignment #1 and implement the Comparator interface on it.*****
 *****                         Use the existing compareTo method to implement compare.                           *****
 *****-----------------------------------------------------------------------------------------------------------*****
 *****          NOTE: while you do not need to implement equals to implement Comparator, do so anyway.           *****
 *****___________________________________________________________________________________________________________*****
 *****                                               Solution:                                                   *****
 *****        1. Add import java.util.*; to your class because this is where Comparator is                       *****
 *****        2. Here is my compare method, notice the requirement to downcast                                   *****
 *****                                                                                                           *****
 *****        @Override                                                                                          *****
 *****        public int compare(Object d1, Object d2) {                                                         *****
 *****            return ((MyDate)d1).compareTo((MyDate)d2);                                                     *****
 *****        }                                                                                                  *****
 *****-----------------------------------------------------------------------------------------------------------*****
 *****        Here is my equals method:                                                                          *****
 *****        @Override                                                                                          *****
 *****        public boolean equals(Object d1) {                                                                 *****
 *****            return compare(this,(MyDate)d1)==0;                                                            *****
 *****        }                                                                                                  *****
 *********************************************************************************************************************
 *********************************************************************************************************************/

// IMPORTS of needed tools and plug-ins
import java.util.Comparator;

// Object class created as my own version of the Java "Date" class.
public class Chapter20_3 implements Comparable, Comparator, Incrementable {

    // States or fields or instance data of the MyDate class.
    private int month;
    private int day;
    private int year;



    // No-Argument ("no-arg") constructor used to set the date to the date I created this class ("05/18/2019").
    public Chapter20_3() {
        this.month = 5;
        this.day = 18;
        this.year = 2019;
    }


    // 1-Argument ("1-arg") constructor that takes in a date in the from "xx/xx/xxxx".
    // This 1-arg constructor checks for possible errors in the format of the passed in parameter date
    // (String fullDate). This 1-arg constructor will throw an "IllegalDateException" if something is
    // wrong with the format of the passed in String fullDate date parameter.
    // If the String fullDate date parameter was input correctly. This 1-arg constructor will set
    // the individual instance data to their respective int values.
    public Chapter20_3(String fullDate) throws IllegalDateException {

        // This if block checks that the String fullDate date parameter is in the correct form of "xx/xx/xxxx" and
        // will throw an IllegalDateException if not.
        if((!fullDate.substring(2, 3).equals("/")) || (!fullDate.substring(5, 6).equals("/"))) {
            throw new IllegalDateException("Bad date specified upon creation: You must input the date in the " +
                    "format: \"xx/xx/xxxx\", with the \"x\"'s being integers.");
        }

        // This else if block checks that the length of the String fullDate date parameter is less than 11 characters
        // long and will throw an IllegalDateException if not.
        else if(fullDate.length() < 10){
            throw new IllegalDateException("Bad date specified upon creation: You must input the date in the " +
                    "format: \"xx/xx/xxxx\", with the \"x\"'s being integers." +
                    "\nAny date with a year prior to 1000, needs to have \"0\"'s prepended, so that the " +
                    "the year is given in four-digit format.");
        }

        // This else it block checks if the "x"'s in the "xx/xx/xxxx" date format are all integers and
        // will throw an IllegalDateException if not.
        else if((!Character.isDigit(fullDate.charAt(0))) || (!Character.isDigit(fullDate.charAt(1))) ||
                (!Character.isDigit(fullDate.charAt(3))) || (!Character.isDigit(fullDate.charAt(4))) ||
                (!Character.isDigit(fullDate.charAt(6))) || (!Character.isDigit(fullDate.charAt(7))) ||
                (!Character.isDigit(fullDate.charAt(8))) || (!Character.isDigit(fullDate.charAt(9)))) {
            throw new IllegalDateException("Bad date specified upon creation: You input a character other than " +
                    "a number as part of the date.");
        }

        // This else if block checks that the length of the String fullDate date parameter is not more than
        // 10 characters long and will throw an IllegalDateException if it is. (Program not designed for dates
        // after "12/31/9999"
        else if(fullDate.length() > 10) {
            throw new IllegalDateException("Bad date specified upon creation: This program was not designed " +
                    "to handle dates after 12/31/9999");
        }

        // If the String fullDate date parameter passes all the previous checks, then this else block assigns
        // the int values to their respective instance data.
        else {
            String stringMonth = fullDate.substring(0, 2);
            String stringDay = fullDate.substring(3, 5);
            String stringYear = fullDate.substring(6);

            int testMonth = Integer.parseInt(stringMonth);
            int testDay = Integer.parseInt(stringDay);

            // if block to make sure the month variable is legal (between 1 and 12) and throw an
            // IllegalDateException if not.
            if((testMonth < 1) || (testMonth > 12)){
                throw new IllegalDateException("The month " + testMonth + " is out of range");
            }

            // else if block to make sure the "day" given is a legal day for the months that have 31 days,
            // create a new MyDate object if so and throw an IllegalDateException if not.
            else if((testMonth == 1) || (testMonth == 3) || (testMonth == 5) || (testMonth == 7) || (testMonth == 8) ||
                    (testMonth == 10) || (testMonth == 12)){
                if((testDay < 1) || (testDay > 31)){
                    throw new IllegalDateException("The day " + testDay + " is not legal for the month of " + testMonth);
                } else{
                    setMonth(testMonth);
                    setDay(testDay);
                    this.year = Integer.parseInt(stringYear);
                }
            }

            // else if block to make the "day" given is a legal day for February, which only has 28 days.
            // Create a new MyDate object if so and throw an IllegalDateException if not.
            else if(testMonth == 2){
                if((testDay < 1) || (testDay > 28)){
                    throw new IllegalDateException("The day " + testDay + " is not legal for the month of " + testMonth);
                } else{
                    setMonth(testMonth);
                    setDay(testDay);
                    this.year = Integer.parseInt(stringYear);
                }
            }

            // else block to make the "day" given is a legal day for months that have 30 days, create a
            // new MyDate object if so and throw an IllegalDateException if not.
            else{
                if((testDay < 1) || (testDay > 30)){
                    throw new IllegalDateException("The day " + testDay + " is not legal for the month of " + testMonth);
                } else{
                    setMonth(testMonth);
                    setDay(testDay);
                    this.year = Integer.parseInt(stringYear);
                }
            }
        }
    }


    // This accessor method converts the int value for "int month" into its respective String value and returns
    // the String value.
    // For example; 1 becomes "January", 2 becomes "February", 3 becomes "March" and so on.
    public String getMonth() {

        // String variable used to store the String representation of the "int month" instance data.
        String stringMonth = null;

        // Switch block used to assign the correct String to the "stringMonth" variable.
        switch (this.month) {
            case 1:
                stringMonth = "January";
                break;
            case 2:
                stringMonth = "February";
                break;
            case 3:
                stringMonth = "March";
                break;
            case 4:
                stringMonth = "April";
                break;
            case 5:
                stringMonth = "May";
                break;
            case 6:
                stringMonth = "June";
                break;
            case 7:
                stringMonth = "July";
                break;
            case 8:
                stringMonth = "August";
                break;
            case 9:
                stringMonth = "September";
                break;
            case 10:
                stringMonth = "October";
                break;
            case 11:
                stringMonth = "November";
                break;
            case 12:
                stringMonth = "December";
                break;
        }

        return "Month is " + stringMonth;
    }


    // This accessor method returns the int value of month. It has the "private" modifier, because this method is only used
    // in the compareTo() method implemented because of implementing the "Comparable" interface.
    private int getMonthAsInt(){

        return this.month;
    }


    // This mutator method returns "true" if the "month" instance data was successfully set and
    // throws an IllegalDateException if not.
    public boolean setMonth(int month) throws IllegalDateException {

        // if block used to make sure the input month parameter is between 1 and 12 and throw
        // an IllegalDateException if not.
        if((month < 1) || (month > 12)){
            throw new IllegalDateException("The month " + month + " is out of range");
        }

        // else block that assigns the month parameter to the "int month" instance data and then return "true".
        else{
            this.month = month;
            return true;
        }
    }


    // This accessor method returns the int value of the "int day" instance data.
    public int getDay() {

        return this.day;
    }


    // This mutator method sets the "int day" instance data after checking that the int day parameter is valid and
    // then returns "true" if successful and throws an IllegalDateException if not.
    public boolean setDay(int day) throws IllegalDateException {

        // This if block checks that when the "int month" instance data is set to any month that contains 31 days,
        // the int day parameter is between 1 and 31. If int day is between 1 and 31 for these months,
        // the "int day" instance data is set to the value of the int day parameter and
        // return "true" to indicate the setting of the "int day" instance data was successful.
        // If the int day parameter for these months is not between 1 and 31,
        // this if block will throw an IllegalDateException
        if((this.month == 1) || (this.month == 3) || (this.month == 5) || (this.month == 7) || (this.month == 8) ||
                (this.month == 10) || (this.month == 12)){
            if((day < 1) || (day > 31)){
                throw new IllegalDateException("IllegalDateException: The day " + day + " is not legal for the " +
                        "month of " + this.month);
            } else{
                this.day = day;
                return true;
            }

            // This else if block checks that for the int day parameter is between 1 and 28 when the "int month"
            // instance data is equal to 2 (February) and return "true" to indicate the setting of the
            // "int day" instance data was successful. If the int day parameter was not between 1 and 28, when
            // the "int month" instance data was equal to 2 (February), this else if block will throw
            // an IllegalDateException.
        } else if(this.month == 2){
            if((day < 1) || (day > 28)){
                throw new IllegalDateException("Bad Day: there is no " + getMonth() + " " + day);
            } else{
                this.day = day;
                return true;
            }
        }

        // This else block is used for all the months ("int month" instance data) that have 30 days,
        // the int day parameter is between 1 and 30. If so, set the "int day" instance data to the
        // value of the int day parameter and return "true" to indicate the setting of the
        // "int day" instance data was successful. If not, throw an IllegalDateException.
        else{
            if((day < 1) || (day > 30)){
                throw new IllegalDateException("Bad Day: there is no " + getMonth() + " " + day);
            } else{
                this.day = day;
                return true;
            }
        }
    }


    // This accessor method returns the int value of the "int year" instance data.
    public int getYear() {

        return this.year;
    }


    // This mutator method sets the int value of the "int year" instance data.
    public void setYear(int year) {

        this.year = year;
    }


    /*
    FOR THIS EXERCISE
     */
    // POWERPOINT: CHAPTER 20, SLIDE #14-15
    @Override
    public boolean equals(Object e){

        return compare(e, this) == 0;
    }


    /*
    FOR THIS EXERCISE
     */
    // POWERPOINT: CHAPTER 20, SLIDE #14-15
    @Override
    public int compare(Object o1, Object o2) {

        return ((Chapter20_3)o1).compareTo((Chapter20_3)o2);
    }


    // This overridden method is implemented because of implementing the Comparable interface.
    // This method takes in an Object parameter (this is down-casted to a MyDate object) and
    // compares it to another (the MyDate object that is calling this method).
    // Then returns "1" if the MyDate object calling this method is newer,
    // returns "-1" if the MyDate object calling this method is older
    // and "0" if the MyDate object calling this method is the same date as the passed in MyDate object.
    // This method also prints to the console, the result of the comparison.
    @Override
    public int compareTo(Object d) {

        // Local variables used to store the int values for the month, day and year of the passed in MyDate parameter.
        int otherMonth = ((Chapter20_3)d).getMonthAsInt();
        int otherDay = ((Chapter20_3)d).getDay();
        int otherYear = ((Chapter20_3)d).getYear();

        // This if block is used to test if "int otherYear" is older (less than) than the "int year" value of the
        // MyDate object that called the compareTo() method. If otherYear is older (less than),
        // and return "1".
        if(this.year > otherYear){
            return 1;
        }

        // This else if block is used to test if "int otherYear" is newer (greater than) than the "int year" value
        // of the MyDate object that called the compareTo() method. If otherYear is newer (greater than),
        // and return "-1".
        else if(this.year < otherYear){
            return -1;
        }

        // This else block is used when otherYear is equal to the "int year" value of the MyDate object
        // that called the compareTo() method.
        else{

            // This if block is used to test if "int otherMonth" is older (less than) than the "int month" value
            // of the MyDate object that called the compareTo() method. If otherMonth is older (less than),
            // and return "1".
            if(this.month > otherMonth){
                return 1;
            }

            // This else if block is used to test if "int otherMonth" is newer (greater than) than the "int month" value
            // of the MyDate object that called the compareTo() method. If otherMonth is newer (greater than),
            // and return "-1".
            else if(this.month < otherMonth){
                return -1;
            }

            // This else block is used when otherMonth is equal to the "int month" value of the MyDate object
            // that called the compareTo() method.
            else{

                // This if block is used to test if "int otherDay" is older (less than) than the "int day" value
                // of the MyDate object that called the compareTo() method. If otherDay is older (less than),
                // and return "1".
                if(this.day > otherDay){
                    return 1;
                }

                // This else if block is used to test if "int otherDay" is newer (greater than) than the "int day"
                // value of the MyDate object that called the compareTo() method. If otherDay is newer
                // (greater than), and return "-1".
                else if(this.day < otherDay){
                    return -1;
                }

                // This else block is used when the MyDate object that called the compareTo() method and
                // the passed in MyDate object are completely equal.
                // If this.year == otherYear, this.month == otherMonth and this.day == otherDay,
                // then the two dates are the same. and return "0".
                else{
                    return 0;
                }
            }
        }
    }


    // This overridden method is implemented because of implementing the Incrementable interface (which I wrote).
    // This method correctly increments the date by one day.
    @Override
    public void increment() {

        // Increases the value of the "int day" instance data by one.
        this.day++;

        // This if block checks if the value of the "int month" instance data is equal to a month
        // that has 31 days (excluding December, because incrementing the day in December, could result
        // in incrementing the year).
        if((this.month == 1) || (this.month == 3) || (this.month == 5) || (this.month == 7) || (this.month == 8) ||
                (this.month == 10)){

            // This if block checks if incrementing the value of the instance data  "int day" raises the value of
            // "int day" above 31. If so, set the value of the instance data "int day" to "1" and
            // increase the value of the instance data "int month" by one.
            if(this.day > 31){
                this.day = 1;
                this.month++;
            }
        }

        // This else if block checks if the value of the "int month" instance data is equal to "2" (February),
        // because February only has 28 days in it.
        else if(this.month == 2){

            // This if block checks if incrementing the value of the instance data "int day" raises the value of
            // the instance data "int day" above 28. If so, set the value of the instance data "int day"
            // to "1" and increase the value of the instance data "int month" by one.
            if(this.day > 28){
                this.day = 1;
                this.month++;
            }
        }

        // This else if block checks if the value of the instance data "int month" is equal to "12" (December),
        // because if so, incrementing the value of the instance data "int day" could result in having to
        // increment the value of the instance data "int year" by one.
        else if(this.month == 12){

            // This if block checks if incrementing the value of the instance data "int day" raises the value of
            // the instance data "int day" above 31.
            // If so, set the value of the instance data "int day" to "1",
            // set the value of the instance data "int month" to "1" and
            // increase the value of the instance data "int year" by one.
            if(this.day > 31){
                this.day = 1;
                this.month = 1;
                this.year++;
            }
        }

        // This else block handles the months that have 30 days.
        else{

            // This if block checks if incrementing the value of the instance data "int day" raises the value of
            // the instance data "int day" above 30. If so, set the value of the instance data "int day"
            // to "1" and increase the value of the instance data "int month" by one.
            if(day > 30){
                this.day = 1;
                this.month++;
            }
        }
    }


    // This overridden method is used for when we print out a MyDate object, so it is in a form that is helpful.
    @Override
    public String toString() {

        // Local String variables
        String monthS, dayS, yearS;

        // This if block is used to prepend a "0" to any month less than 10 (October).
        if(this.month < 10){
            monthS = "0" + this.month;
        } else{
            monthS = String.valueOf(this.month);
        }

        // This if block is used to prepend a "0" to any day less than 10.
        if(this.day < 10){
            dayS = "0" + this.day;
        } else{
            dayS = String.valueOf(this.day);
        }

        // This if block is used to find out if the instance data "int year" is greater than or equal to 2000.
        // If so, only print out the last two digits of the year.
        if((this.year >= 2000) && (this.year < 2100)){

            // This if block is used when the instance data "int year" is greater than or equal to 2000,
            // but less than 2010. If the instance data "int year" is between 2000 and 2009, prepend a "0"
            // to the last digit of the year.
            if(this.year % 2000 < 10){
                yearS = "0" + String.valueOf(this.year % 2000);
            } else{
                yearS = String.valueOf(this.year % 2000);
            }
        }

        // This else block makes it to where any year before the year 2000 or after the 2099,
        // is written out as four digits, instead of two.
        else{
            yearS = String.valueOf(this.year);
        }

        return monthS + "/" + dayS + "/" + yearS;
    }
}
