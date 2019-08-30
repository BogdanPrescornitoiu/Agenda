package com.bogdan.agenda.engine;

import com.bogdan.agenda.domain.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Agenda {

    private final Scanner menu = new Scanner(System.in);
    private final Scanner search = new Scanner(System.in);
    private final Scanner data = new Scanner(System.in);

    private final List<Person> people = new ArrayList<>();

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
                    showEntries(people);
                } else if (option == 5) {
                    searchEntry();
                } else {
                    System.out.println("The option is not valid");
                }

            } catch (Exception ex) {
                System.out.println("The option is not a number");
            }
            printMenu();
        }

    }

    private void printMenu() {
        System.out.println("Please select an option");
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

        people.add(new Person(name, surname, phone));
    }

    private void updateEntry() {
        System.out.println("-- Update an Entry");
    }

    private void deleteEntry() {
        System.out.println("-- Delete an Entry");
    }

    private void showEntries(List<Person> list) {
        System.out.println("-- Show Entries");

        list.forEach(person -> {
            System.out.println("- Entry " + people.indexOf(person));
            System.out.println("- Name:" + person.getName());
            System.out.println("- Surname:" + person.getSurname());
            System.out.println("- Phone:" + person.getPhone());
            System.out.println();
        });

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
                // searchBySurname();

            } else if (option == 3) {
                // searchByPhone();
            } else {
                System.out.println("The option is not valid");
            }

        } catch (Exception ex) {
            System.out.println("The option is not a number");
        }
    }

    private void searchByName() {
        System.out.print("==> ");
        String name = readString();
        List<Person> search = people.stream()
                .filter(person -> person.getName().startsWith(name))
                .collect(Collectors.toList());
        showEntries(search);
    }

    private String readString() {
        return data.hasNext() ? data.next() : null;
    }

}
