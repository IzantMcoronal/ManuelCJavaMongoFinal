
import java.io.IOException;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class Main {

	public static void main(String[] args) throws IOException {
		
		MongoDatabase db = Funcionalidades.connect("localhost", 27017, "ProyectoJava");
		
		MongoCollection<Document> collection = db.getCollection("videojuegos");
		
		
		Funcionalidades.Start(db, collection);
		
		Funcionalidades.disconnect();
		
		
	}


}
