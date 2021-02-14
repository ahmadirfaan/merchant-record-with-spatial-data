



## Project Description

​	Proyek ini merupakan pembuatan REST API dengan meng-analogikan sebuah *customer* yang dapat melakukan transaksi di sebuah merchant yang sudah terdaftar dalam *database*, Dengan menyimpan database *record* transaksi *customer* di sebuah merchant nantinya data tersebut akan berguna untuk pengolahan data selanjutnya.  Ketika mendapatkan koordinat customer, API akan mengembalikan jarak lokasi antara customer dan lokasi merchant terdekat. Untuk dapat menjalankan proyek ini diperlukan : 

- Spring Boot dengan Java 11
- PostgreSQL dengan ekstensi PostGIS untuk mengeksekusi data spasial (lokasi merchant)
- Maven
- Pembuatan database daerah digunakan Kota Nairobi beserta *sub county*-nya dengan nama data `nairobi_sub_county` dan `merchant_location` di PostgreSQL, yaitu pada aplikasi QGIS lakukan export *shapefile* ke *database* dan pilih PostgreSQL sehingga data-data lokasi titik dan area (*subcounty*) akan otomatis berubah menjadi database di PostgreSQL dengan tipe data *Geometry*. Setelah merubah data *shapefile* tersebut dapat dilakukan fungsi-fungsi eksekusi query pada database.

## Untuk Dapat Menjalankan Proyek 

- Diharapkan nama *table* `merchant_location` dan `nairoby_sub_counties` sama dengan yang ada di proyek, jika ingin mengubah harap untuk merubah data yang ada di `schema.sql` dan `data.sql`
- Membuat schema database dengan nama `merchants` jika ingin membuat baru silahkan *edit* pada *file* `application.yaml`



## REST API Description

- ***Customer***

  - Mendapatkan semua *customer* : `GET http://localhost:8081/customer`

  - Mendapatkan *customer* berdasarkan id : `GET http://localhost:8081/customer/{id}`

  - Menambahkan *customer* baru : `POST http://localhost:8081/units` 

    dengan contoh :

    ```json
    {
        "firstName" : "Ahmad Irfaan",
        "lastName" : "Hibatullah",
        "address" : "Jl. Gang Sempit Banget",
        "city" : "Surabaya"
    }
    ```

    

  - Mengedit *customer* berdasarkan id : `PUT http://localhost:8081/customer/{id}`

  - Menghapus *customer* berdasarkan id : `DELETE http://localhost:8081/customer/{id}`

- ***Record Transactions***

  - Mendapatkan semua *record* : `GET http://localhost:8081/record`

  - Mendapatkan  *record* berdasarkan id  : `GET http://localhost:8081/record/{id}`

  - Menambahkan *record* baru   : `POST http://localhost:8081/record`

    dengan contoh :

    ```json
    {
        "name" : "Jam Tangan Swiss Army",
        "unitId" : 1,
      "price" : 500000
    }
    ```

    

  - Mengedit *record* berdasarkan Id: `PUT http://localhost:8081/record/{id}`

  - Menghapus *record* berdasarkan id: `DELETE http://localhost:8081/record/{id}`

- **Merchant Location**

  - Mendapatkan lokasi merchant berdasarkan *sub-county* atau distrik id, sehingga yang didapatkan hanya lokasi *merchant* di daerah tersebut :

    `GET http://localhost:8081/merchant/withinsubcounty?id=1`, 

    dengan menggunakan POSTMAN maka didapatkan hasil-nya sebagai berikut :

    ```
    {
        "code": 200,
        "message": null,
        "data": [
            {
                "id": 2,
                "name": "Merchant 2",
                "coordinateX": 36.7962817,
                "coordinateY": -1.2941
            },
            {
                "id": 25,
                "name": "Merchant 25",
                "coordinateX": 36.8107235,
                "coordinateY": -1.2782734
            },
            {
                "id": 37,
                "name": "Merchant 37",
                "coordinateX": 36.7916908,
                "coordinateY": -1.293483
            },
            {
                "id": 38,
                "name": "Merchant 38",
                "coordinateX": 36.7964866,
                "coordinateY": -1.2939955
            }
        ],
        "timestamp": "2021-02-15T03:08:12.210819"
    }
    ```
    
  - Mendapatkan 5 lokasi *merchant* berdasarkan jarak dari lokasi yang diinginkan :
  
    `GET http://localhost:8081/merchant/nearbymerchant?customerlocation=36.8219,-1.2921`, 
  
    dengan menggunakan POSTMAN maka didapatkan hasil-nya sebagai berikut :
  
    ```json
    {
        "code": 200,
        "message": null,
        "data": [
            {
                "id": 24,
                "name": "Merchant 24",
                "coordinateX": 36.8155523,
                "coordinateY": -1.2796122,
                "distance": 1554.9449733432957
            },
            {
                "id": 1,
                "name": "Merchant 1",
                "coordinateX": 36.8050851,
                "coordinateY": -1.2960949,
              "distance": 1918.4065743461151
            },
          {
                "id": 25,
              "name": "Merchant 25",
                "coordinateX": 36.8107235,
              "coordinateY": -1.2782734,
                "distance": 1973.4570715063412
            },
            {
                "id": 59,
                "name": "Merchant 59",
                "coordinateX": 36.8341362,
                "coordinateY": -1.3070192,
                "distance": 2141.7740343429873
            },
            {
                "id": 66,
                "name": "Merchant 66",
                "coordinateX": 36.8168874,
                "coordinateY": -1.3121609,
                "distance": 2295.2209162366194
            }
        ],
        "timestamp": "2021-02-15T03:09:01.155073"
    }
    ```
    
    ***Distance*** pada hasil tersebut  dengan satuan Meter.
    
    