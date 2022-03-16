package org.study.periodicals.service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.study.periodicals.model.Role;
import org.study.periodicals.model.User;

import static com.mongodb.client.model.Filters.eq;


public class MongoSessionManager extends AbstractMongoClientConfiguration {

    private final String connectString;

    private final MongoCollection<Document> collection;

    public MongoSessionManager(String connectString) {
        this.connectString = connectString;
        this.collection = mongoClient().getDatabase("users").getCollection("sessionbox");
    }

    @Override
    public MongoClient mongoClient() {
        if (connectString == null) throw new AssertionError();
        ConnectionString connectionString = new ConnectionString(connectString);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return "users";
    }

    public boolean findKey(String sessionId) {
        FindIterable<Document> findIterable = collection.find(eq("sessionId", sessionId));
        return true;
    }

    public void saveSession(String sessionId, User user, Role role) {
        Document document = new Document("sessionId", sessionId)
                .append("user", user.getLogin())
                .append("role", Role.getById(user.getRole().getRoleId()).getRoleId());

        collection.insertOne(document);
    }

    public void deleteSessionUser(String sessionId) {
        Bson query = eq(("sessionId"), sessionId);
        DeleteResult result = collection.deleteOne(query);
    }

    public Integer findRole(String sessionId) {
        Integer role = null;
        Document found = collection.find(Filters.eq("sessionId", sessionId)).first();
        if (found != null) {
            role = found.getInteger("role");
        }
        return role;
    }
}