import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by pmadrigal on 8/05/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");


        /**
        * Generate a Java™ key store (JKS) file using the JDK keytool program
        * keytool -importcert -file /PATH/TO/mongodb-ca.crt -storepass PASSWORD -alias server.mongodb.com -keystore /PATH/TO/mongodb.keystore
        * */
        System.setProperty("javax.net.ssl.keyStore", "/etc/ssl/mongodb.keystore");
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
        System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/mongodb.keystore");
        System.setProperty("javax.net.ssl.trustStorePassword", "password");

        ServerAddress hostPort = new ServerAddress("localhost", 27017);

        MongoClientOptions options = new MongoClientOptions.Builder().socketFactory(SSLSocketFactory.getDefault()).build();
        MongoClient mongoClient = new MongoClient( hostPort, options );


        MongoDatabase database = mongoClient.getDatabase("mydb");
        MongoCollection<Document> collection = database.getCollection("mycollection");
        System.out.println("Número de collecciones "+collection.count());
    }
}
