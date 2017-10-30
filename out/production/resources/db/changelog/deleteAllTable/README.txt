Liquibase is designed to manage the objects within your application's schema.
This explains why there are no "create schema" database actions supported.
     This does not prevent someone writing custom SQL actions that would create/drop schemas,
     but this causes some problems, for example liquibase creates two additional book keeping tables
     within the schema that would also be wiped out....