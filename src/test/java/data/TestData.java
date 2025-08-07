package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

    public static Faker faker = new Faker(new Locale("en-GB"));

    private static final String[] genders = {"Male", "Female", "Other"};

    public static String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            gender = faker.options().option(genders),
            streetAddress = faker.address().streetAddress(),
            mobilePhone = faker.phoneNumber().subscriberNumber(10),
            date = String.format("%02d", faker.number().numberBetween(1, 28)),
            month = faker.options().option("January", "February", "March", "April",
                    "May", "June", "July", "August", "September", "October", "November", "December"),
            year = String.format("%s", faker.number().numberBetween(1900, 2025)),
            subjects = faker.options().option("Maths", "Physics", "Chemistry", "Biology",
                    "English", "History", "Geography", "Computer Science",
                    "Economics", "Arts", "Hindi", "Social Studies"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            picture = faker.options().option("picture.png", "picture1.png", "picture1.png"),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = selectCity(state);

    public static String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };


    }
}