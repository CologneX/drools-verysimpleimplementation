package com.example;

import com.example.model.Person;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Scanner;

public class DroolsExample {
    public static void main(String[] args) {
        try {
            // Load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Prompt the user for input
                System.out.println("Enter person's name (or type 'exit' to quit):");
                String name = scanner.nextLine();

                if (name.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Enter person's age:");
                int age = Integer.parseInt(scanner.nextLine());

                // Create a person object
                Person person = new Person(name, age);

                // Insert the person object into the session
                kSession.insert(person);

                // Fire all rules
                kSession.fireAllRules();
            }

            // Dispose the session
            kSession.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
