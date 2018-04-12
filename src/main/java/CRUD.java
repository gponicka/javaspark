import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import static java.util.Arrays.asList;

public class CRUD {

    MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://gracia:R1xEqxuHFKiWFogg@crudcluster-shard-00-00-8doit.mongodb.net:27017,crudcluster-shard-00-01-8doit.mongodb.net:27017,crudcluster-shard-00-02-8doit.mongodb.net:27017/test?ssl=true&replicaSet=crudcluster-shard-0&authSource=admin"));
    MongoDatabase mongoDatabase = mongoClient.getDatabase("userdb");
    MongoCollection<Document> userProfile = mongoDatabase.getCollection("userprofile");

    public Boolean CreateUser(User user){
        try{
            Document doc = new Document("firstname", user.getFirstName())
                    .append("lastname", user.getLastName())
                    .append("gender", user.getGender())
                    .append("age", user.getAge())
                    .append("phone", user.getPhone())
                    .append("email", user.getEmail())
                    .append("address", user.getAddress())
                    .append("fullname", user.getFullName());

            userProfile.withWriteConcern(WriteConcern.ACKNOWLEDGED).insertOne(doc);
            return true;
        }catch (MongoWriteConcernException e){
            e.printStackTrace();
            return false;
        }
    }

    public Document GetUserByID(ObjectId findById){
        Document profile = userProfile.find(eq("_id", findById)).first();
        return profile;
    }

    public Boolean UpdateUser(User user, ObjectId _id){
        try{
            Document doc = new Document("firstname", user.getFirstName())
                    .append("lastname", user.getLastName())
                    .append("gender", user.getGender())
                    .append("age", user.getAge())
                    .append("phone", user.getPhone())
                    .append("email", user.getEmail())
                    .append("address", user.getAddress())
                    .append("fullname", user.getFullName());

            userProfile.withWriteConcern(WriteConcern.ACKNOWLEDGED).updateOne(eq("_id", _id), new Document("$set", doc));
            return true;
        }catch(MongoWriteConcernException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Document> SearchUser(String findUser){
        List<Document> searchResult;
        if(findUser!=null && findUser!="" && findUser!=" ")
            searchResult = userProfile
                    .find(eq("fullname", new Document("$regex", findUser).append("$options", "i")))
                    .into(new ArrayList());
        else
            searchResult = userProfile.find().into(new ArrayList());

        return searchResult;
    }

    public List<Document> SearchByGender(String search){
        List<Document> searchResult;
        searchResult = userProfile
                .find(eq("gender", search))
                .projection(fields(include("fullname","_id","gender","age")))
                .into(new ArrayList<>());

        return searchResult;
    }

    public List<Document> SearchByLastname(){
        List<Document> searchResult;
        searchResult = userProfile
                .aggregate(asList(
                        Aggregates.group("$lastname", Accumulators.sum("count", 1)),
                        Aggregates.project(fields(include("_id","count")))
                )).into(new ArrayList<>());

        return searchResult;
    }

    public Boolean DeleteUser(ObjectId _id){
        return userProfile.deleteOne(eq("_id", _id)).wasAcknowledged();
    }

    public void DropDB(){
        userProfile.drop();
    }
}
