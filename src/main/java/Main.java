import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
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
        long iterations= 1000;
        long TIn = System.currentTimeMillis();
        for (i =0; i<iterations; i++) {

            MongoClient mongoClient = new MongoClient("conectores03", 27017);

            MongoDatabase database = mongoClient.getDatabase("casbahTest");
            MongoCollection<Document> collection = database.getCollection("test");
            //System.out.println("Número de elementos en la collección: " + collection.count());

            FindIterable<Document> documents = collection.find();
            MongoCursor<Document> cursor = documents.iterator();
//            try {
//                while (cursor.hasNext()) {
//                    System.out.println(cursor.next().toJson());
//                }
//            } finally {
//                cursor.close();
//            }


                while (cursor.hasNext()) {
                    System.out.println(cursor.next().toJson());
                }

                //cursor.close();
            mongoClient.close();
        }
        long TFin= System.currentTimeMillis();
        long Texecution = (TFin-TIn);
        long Titeration =Texecution /iterations;

        System.out.println(" Tiempo total con java driver en JAVA: "+Texecution+ " Tiempo por iteracion: "+Titeration);

    }
}
