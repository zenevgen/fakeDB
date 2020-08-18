package com.noblehouse;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;


public class App {
    static String lang;
    static int count;
    static int qtyMist = 0;
    static String finalPerson;
    static Random random = new Random();

    public static void main(String[] args) {
        try {
            lang = args[0];
            count = Integer.parseInt(args[1]);
            if (args.length > 2) {
                qtyMist = Integer.parseInt(args[2]);
            }
            for (int i = 0; i < count; i++) {
                finalPerson = fakePerson();
                int variety = random.nextInt(3);
                if (variety == 0) {
                    finalPerson = withRemoves(finalPerson);
                } else if (variety == 1) {
                    finalPerson = withReplc(finalPerson);
                } else {
                    finalPerson = withInsert(finalPerson);
                }
                System.out.println(finalPerson);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong");
            System.out.println(e.getMessage());
            System.out.println("Please make sure you enter correct input: e.g. ru 10 25");
        }
    }

    public static String fakePerson() {
        Faker faker = new Faker(new Locale(lang));
        String name = faker.name().fullName();
        String address = faker.address().fullAddress();
        String cellPhone = faker.phoneNumber().phoneNumber();
        String output = String.format("%s; %s; %s", name, address, cellPhone);
        return output.replaceAll("і", "i").replaceAll("І", "I").replaceAll("\\(", " ").replaceAll("\\)", " ").replaceAll("\\+", " ");
    }

    public static String withRemoves(String person) {
        String modString1 = person;
        for (int i = 0; i < qtyMist; i++) {
            int firstChar = random.nextInt(modString1.length());
            int secondChar = firstChar + 1;
            modString1 = modString1.replaceFirst(modString1.substring(firstChar, secondChar), "");
            if (modString1.length() < 1) {
                break;
            }
        }
        return modString1;
    }

    public static String withReplc(String person) {
        String modString2 = person;
        for (int i = 0; i < qtyMist; i++) {
            int firstChar = random.nextInt(modString2.length() - 1);
            int secondChar = firstChar + 1;
            modString2 = modString2.replaceFirst(modString2.substring(firstChar, secondChar), modString2.substring(secondChar, secondChar + 1));
        }
        return modString2;
    }

    public static String withInsert(String person) {
        StringBuffer intermedString = new StringBuffer(person);
        for (int i = 0; i < qtyMist; i++) {
            int position = random.nextInt(intermedString.length());
            char charForIns = person.charAt(random.nextInt(person.length()));
            intermedString = intermedString.insert(position, charForIns);
        }
        String modString3 = intermedString.toString();
        return modString3;
    }
}