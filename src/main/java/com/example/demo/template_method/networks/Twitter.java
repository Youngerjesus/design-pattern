package com.example.demo.template_method.networks;

public class Twitter extends Network {
    public Twitter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected boolean login(String username, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + this.username);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Twitter");
        return true;
    }

    private void simulateNetworkLatency() {
        try {
            createLatency();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void createLatency() throws InterruptedException {
        int i = 0;
        System.out.println();
        while (i < 10) {
            System.out.print(".");
            Thread.sleep(500);
            i++;
        }
    }

    @Override
    protected boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void logOut() {
        System.out.println("User: '" + username + "' was logged out from Twitter");
    }
}
