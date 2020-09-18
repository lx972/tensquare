
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * PACKAGE_NAME
 *
 * @Author Administrator
 * @date 17:05
 */
public class MongoTest {

    @Test
    public void insert(){
        MongoClient mongoClient = new MongoClient("192.168.43.33");
        MongoDatabase database = mongoClient.getDatabase("test");
        database.createCollection("article");
        MongoCollection<Document> collection = database.getCollection("article");
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("12345",12345);
        map.put("asddf","nifdgdf");
        map.put("ahdgeeee","不规范积分");
        Document document = new Document(map);
        collection.insertOne(document);
    }


    @Test
    public void update(){
        MongoClient mongoClient = new MongoClient("192.168.43.33");
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("article");
        //修改的条件
        BasicDBObject filter = new BasicDBObject();
        filter.append("asddf","nifdgdf");
        //修改后的值
        BasicDBObject update = new BasicDBObject();
        update.append("$set",new Document("ahdgeeee","12345678"));
        collection.updateOne(filter,update);
    }


    @Test
    public void delete(){
        MongoClient mongoClient = new MongoClient("192.168.43.33");
        MongoDatabase database = mongoClient.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("article");
        //修改的条件
        BasicDBObject filter = new BasicDBObject();
        filter.append("asddf","nifdgdf");
        collection.deleteOne(filter);
    }
}
