package com.bogdan.agenda.engine;

import com.bogdan.agenda.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Agenda {

    private final Scanner menu = new Scanner(System.in);
    private final Scanner data = new Scanner(System.in);
    private final Scanner newValue = new Scanner(System.in);

    private final List<Person> peopleList = new ArrayList<>();

    public void run() {
        printMenu();

        while (menu.hasNext()) {
            String input = menu.next();

            try {
                int option = Integer.valueOf(input);

                if (option == 0) {
                    System.exit(0);

                } else if (option == 1) {
                    createEntry();
                } else if (option == 2) {
                    updateEntry();
                } else if (option == 3) {
                    deleteEntry();
                } else if (option == 4) {
                    showEntries(peopleList);
                } else if (option == 5) {
                    searchEntry();
                } else {
                    System.out.println("The option does not exist");
                }

            } catch (Exception ex) {
                System.out.println("The option is not a number");
            }
            printMenu();
        }

    }

    private void printMenu() {
        System.out.println("\nPlease select an option");
        System.out.println("0. Exit");
        System.out.println("1. Create entry");
        System.out.println("2. Update entry");
        System.out.println("3. Delete entry");
        System.out.println("4. Show entries");
        System.out.println("5. Find entry");
        System.out.print("==> ");
    }

    private void createEntry() {
        System.out.println("-- Add new Entry");

        System.out.print("+  Name: ");
        String name = readString();

        System.out.print("+  Surname: ");
        String surname = readString();

        System.out.print("+  Phone: ");
        String phone = readString();

        peopleList.add(new Person(name, surname, phone));
        System.out.println("Entry added");
    }

    private void updateEntry() {
        if (listEmpty()) {
        } else {
            System.out.println("-- Update an Entry\nWhat entry number do you want to update?");
            try {
                int entry = Integer.valueOf(menu.next());

                if ((peopleList.size() - 1) >= entry) {
                    System.out.println("What do you want to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Surname");
                    System.out.println("3. Phone");
                    String option = menu.next();
                    if((Integer.valueOf(option)<1)||(Integer.valueOf(option)>3)){
                        System.out.println("The option does not exist");
                    }
                    else{
                    System.out.println("Enter the new value");
                    String value = newValue.next();

                        if (option.equals("1")) {
                            Person updatedPerson = peopleList.get(entry);
                            peopleList.remove(entry);
                            updatedPerson.setName(value);
                            peopleList.add(entry, updatedPerson);
                            System.out.println("Name updated");
                        } else if (option.equals("2")) {
                            Person updatedPerson = peopleList.get(entry);
                            peopleList.remove(entry);
                            updatedPerson.setSurname(value);
                            peopleList.add(entry, updatedPerson);
                            System.out.println("Surname updated");
                        } else if (option.equals("3")) {
                            Person updatedPerson = peopleList.get(entry);
                            peopleList.remove(entry);
                            updatedPerson.setPhone(value);
                            peopleList.add(entry, updatedPerson);
                            System.out.println("Phone updated");
                        }
                    }
                } else System.out.println("The entry does not exist");
            }
            catch (Exception ex) {
                System.out.println("The option is not a number");
            }
        }
    }

    private void deleteEntry() {
        if(listEmpty()){}

        else {
            System.out.println("-- Delete an Entry\nWhat entry number do you want to delete?");
            try
            {
                int entry = Integer.valueOf(menu.next());
                if((peopleList.size()-1)>=entry)
                {
                    System.out.println("Are you sure?\nPress Y to confirm, any other key to cancel");
                    if (newValue.next().equals("Y"))
                    {
                        peopleList.remove(entry);
                        System.out.println("Entry deleted");
                    }
                    else {}
                }
                else System.out.println("The entry does not exist");
            }
            catch (Exception ex){
                System.out.println("The option is not a number");

            }
        }
    }

    private void showEntries(List<Person> list) {
        System.out.println("-- Show Entries");
        if(listEmpty()){}

        else {

            list.forEach(person -> {
                System.out.println("- Entry " + peopleList.indexOf(person));
                System.out.println("- Name: " + person.getName());
                System.out.println("- Surname: " + person.getSurname());
                System.out.println("- Phone: " + person.getPhone());
                System.out.println();
            });
        }
        /*
        for (Person person : people) {
            System.out.println("- Entry " + people.indexOf(person));
            System.out.println("- Name:" + person.getName());
            System.out.println("- Surname:" + person.getSurname());
            System.out.println("- Phone:" + person.getPhone());
            System.out.println();
        }
        */
    }

    private void searchEntry() {
        if(listEmpty()){}

        else {
        System.out.println("-- Search Entry");
        System.out.println("1. By name");
        System.out.println("2. By surname");
        System.out.println("3. By Phone");
        String input = readString();
        try {
            int option = Integer.valueOf(input);

            if (option == 1) {
                searchByName();

            } else if (option == 2) {
                searchBySurname();

            } else if (option == 3) {
                searchByPhone();
            } else {
                System.out.println("The option does not exist");
            }

            } catch (Exception ex) {
                System.out.println("The option is not a number");
            }
        }
    }

    private void searchByName() {
        System.out.print("==> ");
        String name = readString();
        List<Person> searchList = peopleList.stream()
                .filter(person -> person.getName().startsWith(name))
                .collect(Collectors.toList());
        showEntries(searchList);
    }

    private void searchBySurname() {
        System.out.print("==> ");
        String surname = readString();
        List<Person> searchList = peopleList.stream()
                .filter(person -> person.getSurname().startsWith(surname))
                .collect(Collectors.toList());
        showEntries(searchList);
    }

    private void searchByPhone() {
        System.out.print("==> ");
        String phone = readString();
        List<Person> searchList = peopleList.stream()
                .filter(person -> person.getPhone().startsWith(phone))
                .collect(Collectors.toList());
        showEntries(searchList);
    }

    private String readString() {
        return data.hasNext() ? data.next() : null;
    }

    private  boolean listEmpty(){
        return peopleList.isEmpty() ? true : false;

        }
/*
    private  boolean listEmpty(){
        if(peopleList.isEmpty()) {
            System.out.println("The list is empty");
            return true;
        }
        else return false;
    }*/
}
