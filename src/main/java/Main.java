import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.net.ssl.SSLSocketFactory;

/**
 * Created by pmadrigal on 03/06/15.
 */
public class Main {
    public static void main(String[] args) throws Exception {

        /**
         * Generate a Java™ key store (JKS) file using the JDK keytool program
         * keytool -importcert -file /PATH/TO/mongodb-ca.crt -storepass PASSWORD -alias server.mongodb.com -keystore /PATH/TO/mongodb.keystore
         * */

        /**
         System.setProperty("javax.net.ssl.keyStore", "/etc/ssl/mongodb.keystore");
         System.setProperty("javax.net.ssl.keyStorePassword", "password");
         System.setProperty("javax.net.ssl.trustStore", "/etc/ssl/mongodb.keystore");
         System.setProperty("javax.net.ssl.trustStorePassword", "password");
         */

        Integer i = 0;
        for (i =0; i<1000; i++) {
            ServerAddress hostPort = new ServerAddress("conectores02", 27017);

            // MongoClientOptions options = new MongoClientOptions.Builder().socketFactory(SSLSocketFactory.getDefault()).build();
            MongoClient mongoClient = new MongoClient(hostPort);

            MongoDatabase database = mongoClient.getDatabase("local");
            MongoCollection<Document> collection = database.getCollection("slaves");
            System.out.println("Número de elementos en la collección: " + collection.count());

            MongoCursor<Document> cursor = collection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }
            } finally {
                cursor.close();
            }

            mongoClient.close();

        }
    }
}
