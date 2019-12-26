package com.wizeline.skeleton.configuration;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;


@Component
public class AppFlywayMigrationStrategy implements FlywayMigrationStrategy {

  private static Logger LOGGER = LoggerFactory.getLogger(AppFlywayMigrationStrategy.class);

  @Override
  public void migrate(Flyway flyway) {
    LOGGER.info("##### Start migration #####");
    flyway.migrate();
    LOGGER.info("##### End migration #####");
  }
}
