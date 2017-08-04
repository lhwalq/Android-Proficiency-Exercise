package com.sql;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class main {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.sql");
        addMenuJson(schema);
        new DaoGenerator().generateAll(schema, "sql_lib/src/main/java/java-gen");
    }

    private static void addMenuJson(Schema schema) {
        Entity specific = schema.addEntity("SpecificBean");
        specific.addStringProperty("_id").primaryKey();
        specific.addStringProperty("createdAt");
        specific.addStringProperty("desc");
        specific.addStringProperty("images");
        specific.addStringProperty("publishedAt");
        specific.addStringProperty("source");
        specific.addStringProperty("type");
        specific.addStringProperty("url");
        specific.addBooleanProperty("used");
        specific.addStringProperty("who");
        specific.addStringProperty("typeName");
    }

}
