package org.example.redistest;

import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.Date;

import static java.lang.System.out;

public class RedisStorage {

    private static RedissonClient redisson;

    private static RKeys rKeys;

    private final static String KEY = "ONLINE_USERS";


    public static RList<String> init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.1.10:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        rKeys = redisson.getKeys();
        rKeys.delete(KEY);
        return redisson.getList(KEY);
    }

    static void shutdown() {
        redisson.shutdown();
    }

}
