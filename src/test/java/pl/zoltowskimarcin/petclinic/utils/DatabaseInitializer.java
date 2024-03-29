package pl.zoltowskimarcin.petclinic.utils;

import liquibase.command.CommandScope;
import liquibase.exception.CommandExecutionException;

public class DatabaseInitializer {

    public static void dropDatabase() throws CommandExecutionException {
        new CommandScope("dropAll")
                .addArgumentValue("url", "jdbc:h2:mem:pet-clinic")
                .addArgumentValue("username", "sa")
                .addArgumentValue("password", "")
                .addArgumentValue("changeLogFile", "db/changelog/master_test.xml")
                .execute();
    }

    public static void initializeDatabase() throws CommandExecutionException {
        new CommandScope("update")
                .addArgumentValue("url", "jdbc:h2:mem:pet-clinic")
                .addArgumentValue("username", "sa")
                .addArgumentValue("password", "")
                .addArgumentValue("changeLogFile", "db/changelog/master_test.xml")
                .execute();
    }
}
