package org.study.periodicals.configuration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.study.periodicals.model.MongoDataUser;

import static com.mongodb.client.model.Filters.eq;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.study.periodicals")
public class MongoSessionManager extends AbstractMongoClientConfiguration {

    MongoDataUser dataUser;

    public MongoSessionManager(MongoDataUser dataUser) {
        this.dataUser = dataUser;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(mongoClientSettings);
        return mongoClient;
    }

    @Override
    protected String getDatabaseName() {
        return "users";
    }

    MongoCollection<Document> collection = mongoClient().getDatabase("users").getCollection("sessionbox");

    public boolean findKey(MongoDataUser dataUser){
        FindIterable<Document> findIterable =  collection.find(eq("sessionId", dataUser.getSessionId()));
        if (findIterable == null){
            return false;
        }
        return true;
    }
    public void saveSession(MongoDataUser dataUser){
        Document document = new Document("sessionId", dataUser.getSessionId())
                .append("login", dataUser.getLogin());
        collection.insertOne(document);
    }

    public void deleteSessionUser(MongoDataUser dataUser){
        Bson query = eq(("sessionId"), dataUser.getSessionId());
        DeleteResult result = collection.deleteOne(query);
    }
}