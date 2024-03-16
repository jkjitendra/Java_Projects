package org.program;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

public class MobileCountryCode {
    public static String getCountryISOCode(String countryName) {

        if (isValidCountryName(countryName)) {
            Optional<String> countryCode = Arrays.stream(Locale.getISOCountries())
                    .map(isoCode -> new Locale.Builder().setRegion(isoCode).build())
                    .filter(locale -> locale.getDisplayCountry(Locale.ENGLISH).equalsIgnoreCase(countryName))
                    .map(Locale::getCountry)
                    .findFirst();

            if (countryCode.isPresent()) {
                return countryCode.get();
            }
        }
        throw new IllegalArgumentException("Invalid Country Name or Country Name Not Found.");

    }

    public static boolean isValidCountryName(String countryName) {
        return Arrays.stream(Locale.getAvailableLocales())
                .map(locale -> locale.getDisplayCountry(Locale.ENGLISH))
                .anyMatch(displayCountry -> displayCountry.equalsIgnoreCase(countryName));
    }

    public static void main(String[] args) {
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
//            String countryName = "India";
//            String mobile = "9898981211";
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter CountryName:");
            String countryName = sc.next();
            System.out.println("Enter Mobile Number:");
            String mobile = sc.next();
            String countryCode = getCountryISOCode(countryName);
            String countryCallingCode = String.valueOf(phoneUtil.getCountryCodeForRegion(countryCode));
            Phonenumber.PhoneNumber number = phoneUtil.parse(mobile, countryCode);
            boolean isValid = phoneUtil.isValidNumber(number);
            System.out.println("isvalid number: " + isValid);
            System.out.println("Country code for " + countryName + ": " + countryCode + " " + ("+" + countryCallingCode + mobile));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (NumberParseException e) {
            throw new RuntimeException(e);
        }
    }
}