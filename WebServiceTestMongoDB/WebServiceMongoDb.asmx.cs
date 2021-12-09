﻿using System;
using System.Collections;
using System.Collections.Generic;
using System.Data;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Json;
using System.Text;
using System.Web;
using System.Web.Script.Serialization;
using System.Web.Services;
using System.Xml;
using System.Xml.Serialization;
using MongoDB.Bson;
using MongoDB.Bson.IO;
using MongoDB.Bson.Serialization;
using MongoDB.Driver;

/// <summary>
/// Descripción breve de WebServiceMongoDB
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
// [System.Web.Script.Services.ScriptService]

public class WebServiceMongoDB : System.Web.Services.WebService {
    public string DB_NAME = "ProyectoEcommerce";
    public string DB_MONGO = "mongodb://localhost:27017";

    public WebServiceMongoDB() {

        //Elimine la marca de comentario de la línea siguiente si utiliza los componentes diseñados 
        //InitializeComponent(); 
    }


    [WebMethod]
    public void ClientsAdd(String rut, String nombre, int edad, String prev, String phone, String email) {
        //IMongoClient client = new MongoClient(DB_MONGO);
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Clientes");

        var document = new BsonDocument();
        document.Add("rut", rut);
        document.Add("nombre", nombre);
        document.Add("edad", edad);
        document.Add("prev", prev);
        document.Add("phone", phone);
        document.Add("email", email);
        collection.InsertOneAsync(document);
    }

    [WebMethod]
    public void ClientsPatch(String rut, String nombre, int edad, String prev, String phone, String email) {
        //IMongoClient client = new MongoClient(DB_MONGO);
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Clientes");
        /* 
        var collectionClient = collection.FindAsync<BsonDocument>({"rut": rut});
       
        Console.WriteLine(collectionClient);

        var document = new BsonDocument();
        document.Add("rut", rut);
        document.Add("nombre", nombre);
        document.Add("edad", edad);
        document.Add("prev", prev);
        document.Add("phone", phone);
        document.Add("email", email);
        collection.UpdateOneAsync(document);
        */
    }


    [WebMethod]
    public String[] ClientsGetAllAsString() {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Clientes");


        var lista = collection.Find(new BsonDocument()).ToList();

        String[] argString = new string[lista.Count];
        int pos = 0;

        foreach (var lt in lista) {
            argString[pos] = (string) lt["rut"];
            pos++;
        }


        return argString;
    }


    [WebMethod]
    public String ClientsGetAllAsList() {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Clientes");
        var lista = collection.Find(new BsonDocument()).ToList();

        ArrayList auxArray = new ArrayList();


        foreach (var lt in lista) {
            auxArray.Add(lt["rut"]);

        }

        return SerializeArrayList(auxArray);
    }


    [WebMethod]
    public string ClientsGetAsJsonSerialized() {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Clientes");


        var lista = collection.Find(new BsonDocument()).ToList();



        return JSONSerialize(lista.ToJson());
    }

    [WebMethod]
    public string ClientsGetAll() {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Clientes");
        var lista = collection.Find(new BsonDocument()).ToList();

        return lista.ToJson();
    }

    [WebMethod]
    public void ProductsAdd(String codigo, String nombre, int precio, int cantidad, int cantidad_vendido) {
        //IMongoClient client = new MongoClient(DB_MONGO);
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Productos");

        var document = new BsonDocument();
        document.Add("codigo", codigo);
        document.Add("nombre", nombre);
        document.Add("precio", precio);
        document.Add("cantidad", cantidad);
        document.Add("cantidad_vendido", cantidad_vendido);
        collection.InsertOneAsync(document);
    }

    [WebMethod]
    public void ProductsPatch(String codigo, String nombre, int precio, int cantidad, int cantidad_vendido)
    {
        //IMongoClient client = new MongoClient(DB_MONGO);
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Productos");
        /* 
        var collectionClient = collection.FindAsync<BsonDocument>("{ rut: " + rut + "}");
       
        Console.WriteLine(collectionClient);

        var document = new BsonDocument();
        document.Add("rut", rut);
        document.Add("nombre", nombre);
        document.Add("edad", edad);
        document.Add("prev", prev);
        document.Add("phone", phone);
        document.Add("email", email);
        collection.UpdateOneAsync(document);
        */
    }


    [WebMethod]
    public String[] ProductsGetAllAsString()
    {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Productos");


        var lista = collection.Find(new BsonDocument()).ToList();

        String[] argString = new string[lista.Count];
        int pos = 0;

        foreach (var lt in lista)
        {
            argString[pos] = (string)lt["codigo"];
            pos++;
        }


        return argString;
    }


    [WebMethod]
    public String ProductsGetAllAsList()
    {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Productos");
        var lista = collection.Find(new BsonDocument()).ToList();

        ArrayList auxArray = new ArrayList();


        foreach (var lt in lista)
        {
            auxArray.Add(lt["codigo"]);

        }

        return SerializeArrayList(auxArray);
    }


    [WebMethod]
    public string ProductsGetAsJsonSerialized()
    {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Productos");
        var lista = collection.Find(new BsonDocument()).ToList();

        return JSONSerialize(lista.ToJson());
    }

    [WebMethod]
    public string ProductsGetAll()
    {
        MongoDB.Driver.IMongoClient client = new MongoClient(DB_MONGO);
        IMongoDatabase db = client.GetDatabase(DB_NAME);

        var collection = db.GetCollection<BsonDocument>("Productos");
        var lista = collection.Find(new BsonDocument()).ToList();

        return lista.ToJson();
    }

    //Metodo para serializar un arrayList
    public string SerializeArrayList(ArrayList obj) {
        XmlDocument doc = new XmlDocument();
        XmlSerializer serializer = new XmlSerializer(typeof(ArrayList), new Type[] { typeof(Rule) });
        using (MemoryStream stream = new System.IO.MemoryStream()) {
            try {
                serializer.Serialize(stream, obj);
                stream.Position = 0;
                doc.Load(stream);
                return doc.InnerXml;
            } catch (Exception ex) {
                // Do nothing
            }
        }
        return string.Empty;
    }


    public static string JSONSerialize<T>(T obj) {
        string retVal = String.Empty;
        using (MemoryStream ms = new MemoryStream()) {
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(obj.GetType());
            serializer.WriteObject(ms, obj);
            var byteArray = ms.ToArray();
            retVal = Encoding.UTF8.GetString(byteArray, 0, byteArray.Length);
        }
        return retVal;
    }

} //Fin clase