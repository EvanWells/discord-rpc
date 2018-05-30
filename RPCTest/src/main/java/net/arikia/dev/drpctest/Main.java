package net.arikia.dev.drpctest;

import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRPC;
import net.arikia.dev.drpc.DiscordRichPresence;

import javax.swing.*;
import java.util.Scanner;

public class Main {

<<<<<<< HEAD
    public static void main(String[] args) {
        JFrame frame = new JFrame("RPCTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel text = new JLabel("In Discord, set your active game to: " + frame.getTitle());
=======
    private static boolean ready = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Derp");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JLabel text = new JLabel("In Discord, set your active game to: \"Derp\"");
>>>>>>> 031fe6a9d23cfd42695b9ba9a869da5002217d25
        frame.getContentPane().add(text, SwingConstants.CENTER);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down Discord Hook.");
            DiscordRPC.discordShutdown();
        }));

        initDiscord();

        int score = 0;
        System.out.println("Starting callbacks.");

        while (true) {
            DiscordRPC.discordRunCallbacks();

            System.out.print("> ");
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();

            if (!input.equalsIgnoreCase("shutdown")) {
                if (input.equalsIgnoreCase("test")) {
                    score++;
                    System.out.println("New Score: " + score);
                    DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder("Score = " + score).setDetails("Running Test | Private").build());
                } else if (input.equalsIgnoreCase("dejay")) {
                    System.out.println("DeJay has a severe case of the gays, I'm afraid.");
                } else {
                    System.out.println("Unknown Command!");
                }
            } else {
                frame.dispose();
                System.exit(0);
            }
        }
    }

    private static void initDiscord() {
        DiscordEventHandlers handlers = new DiscordEventHandlers.Builder().setReadyEventHandler((user) -> {
            Main.ready = true;
            System.out.println("Welcome " + user.username + "#" + user.discriminator + ".");
            DiscordRPC.discordUpdatePresence(new DiscordRichPresence.Builder("Score = 0").setDetails("Running Test | Private").build());
        }).build();
        DiscordRPC.discordInitialize("415885161457123338", handlers, true);
    }

}
