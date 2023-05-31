package org.example.redistest;


public class RedisTest {
    public static void main(String[] args) throws InterruptedException {

        UsersQueue usersQueue = new UsersQueue(RedisStorage.init());
        for (int i = 0; i < 20; i++) {
            usersQueue.add(String.valueOf(i));
        }
        int counter = 1;
        while (true) {
            printUser(usersQueue.getNext());
            counter++;
            if (counter > 10) {
                int randomUser = (int) (Math.random() * usersQueue.size());
                paidUser(usersQueue.get(randomUser));
                counter = 1;
            }
        }

        //     RedisStorage.shutdown();
    }

    public static void printUser(String user) {
        System.out.println("На главной странице показываем пользователя " + user);
    }

    public static void paidUser(String user) throws InterruptedException {
        System.out.println("Пользователь " + user + " оплатил платную услугу ");
        Thread.sleep(1000);
    }
}
