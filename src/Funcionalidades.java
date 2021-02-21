
import java.io.IOException;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import excepciones.Excepciones;

/**
 * 
 * @author Manuel C. 
 * @version 1.0 m
 * 
 */

public class Funcionalidades {


	/**
	 * Metodo para el menu principal de la ejecucion del programa
	 * 
	 * @throws IOException excepcion
	 */
	public static void Start(MongoDatabase db, MongoCollection collection) throws IOException {

	
		int opcion = 0;

		do {
			Menu.MenuUnico();

			opcion = Excepciones.ControlMenu();
			switch (opcion) {
			case 1:
				AgregarVideojuego(db, collection);
				break;
			case 2:
				ListarVideojuegos(db, collection);
				break;
			case 3:
				ModificarVideojuego(db, collection);
				break;
			case 4:
				EliminarVideojuego(db, collection);
				break;
			case 0:
				 mongoClient.close();
				break;

			default:
				System.out.println("Por favor seleccione una opcion correcta");
				break;
			}

		} while (opcion != 0);
	}

	
	static MongoClient mongoClient = new MongoClient();
	
	
	
	/**
	 * Metodo para conectarte a la base de datos
	 * 
	 * @param host hodting
	 * @param port puerto de conexion
	 * @param database nombre de la base de datos
	 * @return la base de datos
	 */
	public static MongoDatabase connect(String host, int port, String database) {
		    // Create serverAddress, the location a MongoDB server
		    ServerAddress serverAddress = new ServerAddress(host, port);

		    // Connect to the MongoDB server with internal connection pooling
		    mongoClient = new MongoClient(serverAddress);
		    
		    // Getting a connection easily avoiding ServerAddress
		    // mongoClient = new MongoClient(host, port);
		    
		    // Select the database
		    MongoDatabase  db = mongoClient.getDatabase(database);

		    return db;

		  }
	  
	/**
	 * Metodo para desconectar de la base de datos
	 */
	public static void disconnect() {
		    mongoClient.close();
		    System.out.println("Saliendo del Programa..\nGuardando en la base de datos...\n\nGuardado & Log out.");
		  }
	  		

	/**
	 * Metodo para agregar un videojuego
	 *  
	 * @param db base de datos
	 * @param collection coleccion de videojuegos
	 * @throws IOException excepcion
	 */
	public static void AgregarVideojuego(MongoDatabase db, MongoCollection collection) throws IOException {
			
			System.out.println(
				"Bienvenido a la ficha de creacion de Videojuegos.\nPor favor proceda a introducir los datos del videojuego");
		
			System.out.println("");
			
			System.out.println("Nombre: ");
			String name = Excepciones.ControlString();
			
			System.out.println("Plataforma: ");
			String platform = Excepciones.ControlString();
			
			System.out.println("Año: ");
			int year = Excepciones.ControlEntero();
		
			System.out.println("Compañia: ");
			String company = Excepciones.ControlString();
			
			System.out.println("Precio: ");
			Double price = Excepciones.ControlDouble();
			
			
			// Create the document to insert
		    Document document = new Document("name", name).append("platform",platform).append("year",year).append("company",company).append("price",price);
		    
		    // Select the collection
		    collection.insertOne(document);
		    
		    System.out.println("");
		    System.out.println(
					"Los datos del videojuego han sido agregados correctamente a la base de datos");
		    


	}
	
	
	/**
	 * Metodo para modificar un videojuego
	 * @param db base de datos
	 * @param collection de videojuegos
	 */
    public static void ModificarVideojuego(MongoDatabase db, MongoCollection collection) {
        
    	System.out.println(
				"Bienvenido a la ficha de modificacion de Videojuegos.\nPor favor proceda a introducir los datos del videojuego a modificar");
		
		System.out.println("");
	    System.out.println("Nombre: ");
		String name = Excepciones.ControlString();
		
		System.out.println("Plataforma: ");
		String platform = Excepciones.ControlString();
		
	
		Document findDocument = new Document("name", name).append("platform", platform);
		
		
        MongoCursor<Document> resultDocument = collection.find(findDocument).iterator();
        
        if(!resultDocument.hasNext()){
        System.out.println("");	
        System.out.println("El videojuego que desea modificar no existe en la base de datos");	
        	
        }else {
        	System.out.println("");
    		System.out.println("Escriba los nuevos datos del videojuego");
    		System.out.println("");
        	System.out.println("Nombre: ");
    		String newname = Excepciones.ControlString();
    		
    		System.out.println("Plataforma: ");
    		String newplatform = Excepciones.ControlString();
    		
    		System.out.println("Año: ");
			int newyear = Excepciones.ControlEntero();
    	
    		System.out.println("Compañia: ");
    		String newcompany = Excepciones.ControlString();
    		
    		System.out.println("Precio: ");
    		Double newprice = Excepciones.ControlDouble();

            Document updateDocument = new Document("$set", new Document("name", newname).append("platform", newplatform).append("year", newyear).append("company", newcompany).append("price", newprice));

            collection.findOneAndUpdate(findDocument, updateDocument);
        	System.out.println("");
        	System.out.println("El videojuego ha sido modificado correctamente");	
        }
		
    	

      }
	
    
    /**
     * Metodo para eliminar un videojuego
     * 
     * @param db base de datos
     * @param collection de videojuegos
     */
	public static void EliminarVideojuego(MongoDatabase db, MongoCollection collection) {
		
		System.out.println(
				"Bienvenido a la ficha de eliminacion de Videojuegos.\nPor favor proceda a introducir los datos del videojuego que desea eliminar");
		
			System.out.println("");
		
		System.out.println("Nombre: ");
		String name = Excepciones.ControlString();
		
		System.out.println("Plataforma: ");
		String platform = Excepciones.ControlString();


        Document findDocument = new Document("name", name).append("platform", platform);
        
        MongoCursor<Document> resultDocument = collection.find(findDocument).iterator();
        
        if(!resultDocument.hasNext()){
        System.out.println("");	
        System.out.println("El videojuego que desea eliminar no existe en la base de datos");	
        	
        }else {
        	
        	collection.findOneAndDelete(findDocument);
        	
        	System.out.println("");
        	System.out.println("El videojuego ha sido eliminado correctamente de la base de datos");	
        }
        
		
	    
	  }
	
	
    

    public static void ListarVideojuegos(MongoDatabase db, MongoCollection collection) {
    	
    	System.out.println("Nombre: ");
		String name = Excepciones.ControlString();


        Document findDocument = new Document("name", name);


        Document sortingDocument = new Document("name", 1);
        Document projectionDocument = new Document("platform", 1).append("name", 1);

        MongoCursor<Document> resultDocument = collection.find(findDocument)
            .sort(sortingDocument).projection(projectionDocument).limit(5).iterator();

                
        if (!resultDocument.hasNext()) {
        	
        	System.out.println("el videojuego que esta buscando no existe en la base de datos");
			
		}else {
			
			System.out.println("*** Listando ¡¡¡¡¡");
			
			Document document = resultDocument.next();
			
			          System.out.println(document.getString("name") + " " +
			              (document.getString("platform")));
		}
        

      }

}
