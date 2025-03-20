import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactBook {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        HashMap<String, String> contactBook = new HashMap<>();
        boolean running = true;
        String name;
        String phoneNum;
        char yesOrNo;

        while(running) {
            System.out.print("""
                    *****************************
                    CONTACT BOOK
                    1. Add contact
                    2. Delete contact
                    3. Edit existing contact name
                    4. View all existing contacts
                    5. Exit
                    *****************************
                    Please enter your choice:
                    """);
            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); //Clearing input
            }
            catch(InputMismatchException e){
                System.out.println("Invalid choice");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    // Adding contact
                    System.out.print("Enter the name of the contact you'd like to add: ");
                    name = scanner.nextLine();
                    System.out.print("Please enter (" + name + ")'s phone number: ");
                    phoneNum = scanner.nextLine();

                    if (contactBook.containsKey(phoneNum)) { //Checking if num is taken
                        System.out.printf("Number %s is already in use by (%s)\n", phoneNum, contactBook.get(phoneNum));
                    } else if (!isValidNum(phoneNum)) {
                        System.out.println("Invalid number");
                    } else {
                        contactBook.put(phoneNum, name);
                        System.out.println("(" + name + ")'s contact has been successfully added.");
                    }

                }

                // Deleting contact
                case 2 -> {
                    System.out.print("Enter the number of the contact you'd like to delete: ");
                    phoneNum = scanner.nextLine();
                    if (contactBook.containsKey(phoneNum)) {
                        name = contactBook.get(phoneNum);
                        System.out.print("Are you sure you would like to delete (" + name + ")'s contact? (y/n): "); // Confirmation
                        yesOrNo = scanner.next().toLowerCase().charAt(0);
                        if (yesOrNo == 'y') {
                            System.out.println("(" + name + ")'s contact has been successfully deleted.");
                        } else if (yesOrNo != 'n') { // Accounts for inputs other than y or n
                            System.out.println("Invalid choice.");
                        }
                    } else if (!isValidNum(phoneNum)) {
                        System.out.println("Invalid number");
                    } else {
                        System.out.println("Number " + phoneNum + " is not in contacts.");
                    }
                }

                // Editing contact
                case 3 -> {
                    System.out.print("Enter the number of the contact you'd like to edit: ");
                    phoneNum = scanner.nextLine();
                    if (contactBook.containsKey(phoneNum)) {
                        name = contactBook.get(phoneNum);
                        System.out.print("What would you like to change (" + name + ")'s contact name to?: ");
                        name = scanner.nextLine();
                        contactBook.put(phoneNum, name);
                        System.out.println("(" + name + ")'s contact has been successfully updated.");
                    } else if (!isValidNum(phoneNum)) {
                        System.out.println("Invalid number");
                    } else {
                        System.out.println("Number " + phoneNum + " is not in contacts.");
                    }
                }

                // View all existing contacts
                case 4 -> {
                    if (contactBook.isEmpty()) {
                        System.out.println("You have no contacts.");
                    } else {
                        for (String contactNum : contactBook.keySet()) {
                            name = contactBook.get(contactNum);
                            System.out.printf("\n- (%s)\n  %s\n", name, contactNum);
                        }
                    }
                }

                // Exiting program
                case 5 -> running = false;

                default -> System.out.println("Invalid choice");
            }

        }
    scanner.close();
    }


    static boolean isValidNum(String num){
        if(num.length() < 10){ // Checks if the num length is valid
            return false;
        }
        for (char c : num.toCharArray()){ // Iterates through all chars
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
