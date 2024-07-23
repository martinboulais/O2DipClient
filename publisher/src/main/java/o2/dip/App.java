package o2.dip;

import cern.dip.*;

import java.util.Random;

public class App {
    public static void main(String[] args) throws InterruptedException {
        // Create the publications
        DipPublication[] pub = new DipPublication[2];
        var dip = Dip.create("LocalServer");
        String[] itemName = new String[]{"test.item1", "test.item2"};

        var random = new Random();

        var running = true;
        while (running) {
            for (int i = 0; i < 2; i++) {
                try {
                    pub[i] = dip.createDipPublication(
                        itemName[i],
                        (dipPublication, e) -> System.err.println("Publication error for " + dipPublication.getTopicName() + ": " + e.getMessage())
                    );
                } catch (DipException e) {
                    System.err.println(e);
                }
            }

            // set up vars whose values are to be sent
            DipData data = dip.createDipData();
            var intVal = random.nextInt();
            var floatVal = random.nextFloat();

            // send data via DIP
            try {
                data.insert("field1", intVal);
                data.insert("field2", floatVal);
                System.out.println("sending values " + intVal + " and " + floatVal);
                pub[0].send(intVal + 1, new DipTimestamp());
                pub[1].send(data, new DipTimestamp());
            } catch (DipException e) {
                System.out.println("Failed to send data");
            }

            // shutdown
            try {
                dip.destroyDipPublication(pub[0]);
                dip.destroyDipPublication(pub[1]);
            } catch (DipException exception) {
                System.out.println("Failed to destroy dip publication");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                running = false;
            }
        }
    }
}