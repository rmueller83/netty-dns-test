package de.rmueller83.netty;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LettuceNettyTest {

  static {
    System.setProperty("java.util.logging.SimpleFormatter.format",
            "(%2$s) %5$s %6$s%n");

    ConsoleHandler handler = new ConsoleHandler();
    handler.setLevel(Level.ALL);
    Logger.getGlobal().getParent().setLevel(Level.ALL);
    Logger.getGlobal().getParent().addHandler(handler);
  }

  public static void main(String[] args) {
    RedisClient redisClient = RedisClient.create("redis://mydomain:6379/0");
    StatefulRedisConnection<String, String> connection = redisClient.connect();
    RedisCommands<String, String> syncCommands = connection.sync();

    syncCommands.set("key", "Hello, Redis!");

    connection.close();
    redisClient.shutdown();
  }
}
